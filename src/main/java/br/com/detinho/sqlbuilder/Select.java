package br.com.detinho.sqlbuilder;

import java.util.LinkedHashSet;
import java.util.Set;

public class Select {

    private Columns columns = new Columns();
    private Set<Table> tables = new LinkedHashSet<Table>();
    
    private MatchCriteria criteria = null;

    public void column(Selectable value) {
        columns.add(value);
    }
    
    public void column(String tableName, String columnName) {
        Column column = new Column(tableName, columnName);
        columns.add(column);
    }

    public String toSql() {
        String sql = "SELECT ";
        for (int index=0; index < columns.size(); index++) {
            Selectable value = columns.get(index);
            value.addTable(tables);
            sql += value.write();

            if (index < columns.size()-1)
                sql += ", ";
        }
        
        String tabelas = "";
        for (Table t : tables) {
            tabelas += t.write();
        }
        
        if (! "".equals(tabelas)) {
            sql += " FROM ";
            sql += tabelas;
        }
        
        if (criteria != null) {
            sql += " WHERE " + criteria.write();
        }
        
        return sql;
    }

    public void where(String columnAlias, String operator, Scalar value) {
        Selectable selectable = columns.byAlias(columnAlias);

        criteria = new MatchCriteria(selectable, operator, value);
    }

    public void where(String leftColumn, String operator, String rightColumn) {
        Selectable left = columns.byAlias(leftColumn);
        Selectable right = columns.byAlias(rightColumn);
        
        criteria = new MatchCriteria(left, operator, right);
    }
}
