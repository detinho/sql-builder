package br.com.detinho.sqlbuilder.scalar;

import br.com.detinho.sqlbuilder.Scalar;

public final class BooleanScalar extends Scalar {

    private static String TRUE = Boolean.toString(true).toUpperCase();
    private static String FALSE = Boolean.toString(false).toUpperCase();
    
    private final boolean value;
    
    public BooleanScalar(boolean value) {
        this.value = value;
    }
    
    @Override
    public String scalarValue() {
        return value ? TRUE : FALSE;
    }
}
