package br.com.detinho.sqlbuilder.scalar;

import br.com.detinho.sqlbuilder.Scalar;

public final class StringScalar extends Scalar {

    private final String value;

    public StringScalar(String value) {
        this.value = value;
    }
    
    @Override
    public String scalarValue() {
        return encodeString(value);
    }
    
    private String encodeString(String value) {
        //Need to improve this...
        return "\"" + value + "\"";
    }
}
