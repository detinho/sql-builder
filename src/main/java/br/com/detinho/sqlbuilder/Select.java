package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.col;
import static br.com.detinho.sqlbuilder.StringUtils.writeSql;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.com.detinho.sqlbuilder.OrderBy.OrderType;
import br.com.detinho.sqlbuilder.criteria.And;
import br.com.detinho.sqlbuilder.criteria.MatchCriteria;
import br.com.detinho.sqlbuilder.join.InnerJoin;
import br.com.detinho.sqlbuilder.join.Join;
import br.com.detinho.sqlbuilder.join.LeftJoin;

public class Select {

    private Columns columns = new Columns();
    private Set<Table> tables = new LinkedHashSet<Table>();
    private List<Join> joins = new LinkedList<Join>();
    private List<OrderBy> orders = new LinkedList<OrderBy>();
    private List<GroupBy> groups = new LinkedList<GroupBy>();
    
    private Criteria criteria = null;

    public void column(Selectable value) {
        columns.add(value);
    }
    
    public void column(String tableName, String columnName) {
        columns.add(col(tableName, columnName));
    }

    public String toSql() {
        collectTables();
        
        String sql = "SELECT ";
        sql += columns.write();
        sql += writeSql("FROM", tables);
        sql = generateJoinsSql(sql);
        sql = generateWhereSql(sql);
        sql += writeSql("ORDER BY", orders);
        sql += writeSql("GROUP BY", groups);
        
        return sql;
    }

    private void collectTables() {
        columns.addTable(tables);
        
        for (Join join : joins)
            join.addTable(tables);

        if (criteria != null)
            criteria.addTable(tables);

        for (OrderBy order : orders)
            order.addTable(tables);
    }
    
    private String generateJoinsSql(String sql) {
        for (Join join : joins)
            sql += " " + join.write();
        
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

    public void join(String leftTable, String leftColumn, String operator,
            String rightTable, String rightColumn) {
        Join join = new InnerJoin(leftTable);
        join.addOn(col(leftTable, leftColumn), operator, col(rightTable, rightColumn));
        joins.add(join);
    }

    public Join leftJoin(String leftTable, String leftColumn, String operator,
            String rightTable, String rightColumn) {
        Join join = new LeftJoin(leftTable);
        join.addOn(col(leftTable, leftColumn), operator, col(rightTable, rightColumn));
        joins.add(join);
        
        return join;
    }

    public void orderBy(String alias) {
        orders.add(new OrderBy(alias));
    }

    public void orderBy(Selectable col) {
        orders.add(new OrderBy(col));
    }

    public void orderBy(Selectable col, OrderType type) {
        orders.add(new OrderBy(col, type));
    }

    public void orderBy(String alias, OrderType type) {
        orders.add(new OrderBy(alias, type));
    }

    public void groupBy(String alias) {
        groups.add(new GroupBy(alias));
    }

    public void table(String tableName) {
        tables.add(new Table(tableName));
    }

}
