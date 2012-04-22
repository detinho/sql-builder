package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.StringUtils.writeSql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Columns {

    private List<Selectable> columns = new ArrayList<Selectable>();
    private Map<String, AliasedSelectable> aliases = new HashMap<String, AliasedSelectable>();
    
    public void add(Selectable column) {
        columns.add(column);
    }
    
    public void add(Selectable col, String alias) {
        AliasedSelectable selectable = new AliasedSelectable(col, alias);
        add(selectable);
        aliases.put(alias, selectable);
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
        columns.add(new AroundWrapper(new AliasedSelectable(subSelect, alias)));
    }
    
    private static final class AroundWrapper implements Selectable {
        private final Selectable subSelect;

        public AroundWrapper(Selectable subSelect) {
            this.subSelect = subSelect;
        }

        @Override
        public String write() {
            return String.format("(%s)", subSelect.write()); 
        }

        @Override
        public void addTable(Set<Table> tables) {
            subSelect.addTable(tables);
        }
    }
}
