package br.com.detinho;

import java.math.BigDecimal;
import java.util.Set;

public final class Column implements Selectable {

    private final Table table;
    private final String name;
    private final String alias;
    
    public Column(String name) {
        this(null, name);
    }
    
    public Column(String table, String name) {
        this(table, name, name);
    }

    public Column(String table, String name, String alias) {
        this.table = table == null ? null : new Table(table);
        this.name = name;
        this.alias = alias;
    }
    public Column(int value) {
        this(""+value);
    }

    public Column(BigDecimal value) {
        this(value.toString());
    }

    /* 
     * @see br.com.detinho.Writable#write()
     */
    @Override
    public String write() {
        if (table == null)
            return name;
        String ret = table.write() + "." + name;
        if (!name.equals(alias))
            ret += " AS \"" + alias + "\"";
        return ret;
    }
    
    /*
     * @see br.com.detinho.Selectable#addTable(java.util.Set)
     */
    @Override
    public void addTable(Set<Table> tables) {
        tables.add(table);
    }
}
