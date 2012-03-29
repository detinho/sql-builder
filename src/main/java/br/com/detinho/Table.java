package br.com.detinho;

public final class Table implements Writable {

    private final String name;

    public Table(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (! (other instanceof Table)) return false;
        
        return name.equals(((Table)other).name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String write() {
        return name;
    }
    
}
