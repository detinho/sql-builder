package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.StringUtils.writeSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Columns {

    private List<Selectable> columns = new ArrayList<Selectable>();
    
    public void add(Selectable column) {
        columns.add(column);
    }

    public String write() {
        if (columns.isEmpty())
            return "*";
        return writeSql("", columns);
    }
    
    public void addTable(Set<Table> tables) {
        for (Selectable sel : columns) {
            sel.addTable(tables);
        }
    }

    public void add(Select subSelect, String alias) {
        columns.add(new SelectableWrapper(subSelect, alias));
    }
    
    private static final class SelectableWrapper implements Selectable {
        private final Select subSelect;
        private final String alias;

        public SelectableWrapper(Select subSelect, String alias) {
            this.subSelect = subSelect;
            this.alias = alias;
        }

        @Override
        public String write() {
            return String.format("(%s AS %s)", subSelect.toSql(), alias); 
        }

        @Override
        public void addTable(Set<Table> tables) {
        }
    }

}
