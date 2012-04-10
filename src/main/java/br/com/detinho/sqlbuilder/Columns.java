package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.StringUtils.writeSql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Columns implements Iterable<Selectable> {

    private List<Selectable> columns = new ArrayList<Selectable>();
    
    public void add(Selectable column) {
        columns.add(column);
    }

    @Override
    public Iterator<Selectable> iterator() {
        return columns.iterator();
    }

    public Selectable byAlias(String alias) {
        for (Selectable col : columns) {
            if (col.alias().equals(alias))
                return col;
        }
        
        throw new IllegalArgumentException("Alias " + alias + " does not exists.");
    }

    public String write() {
        return writeSql("", columns);
    }
    
    public void addTable(Set<Table> tables) {
        for (Selectable sel : columns) {
            sel.addTable(tables);
        }
    }

}
