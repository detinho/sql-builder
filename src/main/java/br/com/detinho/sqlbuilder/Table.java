package br.com.detinho.sqlbuilder;

public final class Table implements Writable {

    private final String name;
    private final String alias;

    public Table(String name) {
        this.name = name;
        this.alias = name;
    }
    
    public Table(String name, String alias) {
        this.name = name;
        this.alias = alias;
        
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (! (other instanceof Table)) return false;
        
        Table otherTable = (Table)other;
        return name.equals(otherTable.name) && alias.equals(otherTable.alias);
    }
    
    @Override
    public int hashCode() {
        return 77 + name.hashCode() + alias.hashCode() ;
    }

    @Override
    public String write() {
        if ("".equals(alias) || name.equals(alias))
            return name;
        
        return String.format("%s AS %s", name, alias);
    }
    
    public String alias() {
        return alias;
    }
    
}
