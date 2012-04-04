package br.com.detinho.sqlbuilder.scalar;

import br.com.detinho.sqlbuilder.Scalar;

public final class BooleanScalar extends Scalar {

    private static String TRUE = Boolean.toString(true).toUpperCase();
    private static String FALSE = Boolean.toString(false).toUpperCase();
    
    private final boolean value;
    private final String alias;
    
    public BooleanScalar(boolean value) {
        this(value, "");
    }
    
    public BooleanScalar(boolean value, String alias) {
        this.value = value;
        this.alias = alias;
    }
    
    @Override
    public String alias() {
        return alias;
    }

    @Override
    public String scalarValue() {
        return value ? TRUE : FALSE;
    }

}
