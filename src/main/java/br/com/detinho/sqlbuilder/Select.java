package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.col;
import static br.com.detinho.sqlbuilder.StringUtils.removeTrailingComma;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.detinho.sqlbuilder.criteria.And;
import br.com.detinho.sqlbuilder.criteria.MatchCriteria;

public class Select {

    private Columns columns = new Columns();
    private Set<Table> tables = new LinkedHashSet<Table>();
    
    private Criteria criteria = null;

    public void column(Selectable value) {
        columns.add(value);
    }
    
    public void column(String tableName, String columnName) {
        columns.add(col(tableName, columnName));
    }

    public String toSql() {
        collectTablesFromColumns();
        collectTablesFromCriteria();
        
        String sql = "SELECT ";
        sql = generateColumnsSql(sql);
        sql = generateTablesSql(sql);
        sql = generateWhereSql(sql);
        
        return sql;
    }
    
    private void collectTablesFromColumns() {
        for (Selectable sel : columns)
            sel.addTable(tables);
    }
    
    private void collectTablesFromCriteria() {
        if (criteria != null)
            criteria.addTable(tables);
    }
    
    private String generateColumnsSql(String sql) {
        for (Selectable value : columns) {
            sql += value.write() + ", ";
        }
        sql = removeTrailingComma(sql);
        return sql;
    }

    private String generateTablesSql(String sql) {
        String tabelas = "";
        for (Table t : tables) {
            tabelas += t.write() + ", ";
        }
        
        if (! "".equals(tabelas)) {
            tabelas = removeTrailingComma(tabelas);
            sql += " FROM ";
            sql += tabelas;
        }
        return sql;
    }
    
    private String generateWhereSql(String sql) {
        if (criteria != null) {
            sql += " WHERE " + criteria.write();
        }
        return sql;
    }

    public void where(String columnAlias, String operator, Scalar value) {
        Selectable selectable = columns.byAlias(columnAlias);
        addNewMatchCriteria(selectable, operator, value);
    }

    public void where(String leftColumn, String operator, String rightColumn) {
        Selectable left = columns.byAlias(leftColumn);
        Selectable right = columns.byAlias(rightColumn);
        
        addNewMatchCriteria(left, operator, right);
    }
    
    private void addNewMatchCriteria(Selectable selectable, String operator,
            Selectable value) {
        MatchCriteria newCriteria = new MatchCriteria(selectable, operator, value);
        
        addNewCriteria(newCriteria);
    }

    private void addNewCriteria(Criteria newCriteria) {
        if (criteria == null)
            criteria = newCriteria;
        else
            criteria = new And(criteria, newCriteria);
    }

    public void where(Criteria criteria) {
        addNewCriteria(criteria);
    }

    public void where(String leftTable, String leftColumn, String operator,
            String rightTable, String rightColumn) {
        Criteria newCriteria = 
                new MatchCriteria(col(leftTable, leftColumn), operator, col(rightTable, rightColumn));
        addNewCriteria(newCriteria);
    }
}
