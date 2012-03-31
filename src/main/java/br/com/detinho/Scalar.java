package br.com.detinho;

import java.util.Set;

public abstract class Scalar implements Selectable {
    
    @Override
    public void addTable(Set<Table> tables) {
    }
    
    @Override
    public String write() {
        String finalValue = scalarValue();
        if (! "".equals(alias()))
            finalValue += " AS " + alias();
        return finalValue;
    }

    public abstract String scalarValue();
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (! (other instanceof Scalar)) return false;
        
        Scalar otherScalar = (Scalar)other;
        return scalarValue().equals(otherScalar.scalarValue());
    }
    
    @Override
    public int hashCode() {
        return scalarValue().hashCode();
    }
    
}
