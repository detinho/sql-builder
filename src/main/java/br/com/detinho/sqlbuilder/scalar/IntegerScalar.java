package br.com.detinho.sqlbuilder.scalar;

import br.com.detinho.sqlbuilder.Scalar;

public final class IntegerScalar extends Scalar {

    private final int value;
    private final String alias;

    public IntegerScalar(int value) {
        this(value, "");
    }
    
    public IntegerScalar(int value, String alias) {
        this.value = value;
        this.alias = alias;
    }
    
    @Override
    public String scalarValue() {
        return String.valueOf(value);
    }
    
    @Override
    public String scalarAlias() {
        return alias;
    }

}
