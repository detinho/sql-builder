package br.com.detinho;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Select {

    private List<Selectable> columns = new ArrayList<Selectable>();
    private Set<Table> tables = new LinkedHashSet<Table>();

    public void column(Selectable value) {
        columns.add(value);
    }
    
    public void column(String tableName, String columnName) {
        columns.add(new Column(tableName, columnName));
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
        
        return sql;
    }
}
