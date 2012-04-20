package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.StringUtils.nullToStr;

import java.util.Set;

public final class Column implements Selectable {

    private final Table table;
    private final String name;
    
    public Column(String table, String name) {
        this(new Table(table), name);
    }
    
    public Column(Table table, String name) {
        checkPreConditions(name);
        this.table = table;
        this.name = name;
    }

    private void checkPreConditions(String name) {
        if (nullToStr(name).equals(""))
            throw new IllegalArgumentException("All parameters (column name and alias) have to be informed.");
    }

    /* 
     * @see br.com.detinho.Writable#write()
     */
    @Override
    public String write() {
        if (table == null)
            return name;
        
        return table.alias() + "." + name;
    }
    
    /*
     * @see br.com.detinho.Selectable#addTable(java.util.Set)
     */
    @Override
    public void addTable(Set<Table> tables) {
        tables.add(table);
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (! (other instanceof Column)) return false;
        
        Column otherCol = (Column)other;
        return table.equals(otherCol.table) &&
                name.equals(otherCol.name);
    }
    
    @Override
    public int hashCode() {
        return 37 + table.hashCode() + 
                name.hashCode();
    }
}
