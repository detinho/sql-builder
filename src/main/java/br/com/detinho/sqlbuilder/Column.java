package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.StringUtils.nullToStr;

import java.util.Set;

public final class Column implements Selectable {

    private final Table table;
    private final String name;
    private final String alias;
    
    public Column(String table, String name) {
        this(table, name, name);
    }

    public Column(String table, String name, String alias) {
        checkPreConditions(table, name, alias);
        
        this.table = new Table(table);
        this.name = name;
        this.alias = alias;
    }
    
    public Column(Table table, String name) {
        this.table = table;
        this.name = name;
        this.alias = name;
    }

    private void checkPreConditions(String table, String name, String alias) {
        if (nullToStr(table).equals("") || nullToStr(name).equals("") || nullToStr(alias).equals(""))
            throw new IllegalArgumentException("All parameters (table, column name and alias) have to be informed.");
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
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (! (other instanceof Column)) return false;
        
        Column otherCol = (Column)other;
        return table.equals(otherCol.table) &&
                name.equals(otherCol.name) &&
                alias.equals(otherCol.alias);
    }
    
    @Override
    public int hashCode() {
        return 37 + table.hashCode() + 
                name.hashCode() + 77 + 
                alias.hashCode();
    }

    public boolean sameAlias(String alias) {
        return this.alias.equals(alias);
    }
}
