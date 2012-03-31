package br.com.detinho;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public Selectable get(int index) {
        return columns.get(index);
    }

    public int size() {
        return columns.size();
    }

}
