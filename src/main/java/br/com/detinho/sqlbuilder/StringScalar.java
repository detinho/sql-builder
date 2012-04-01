package br.com.detinho.sqlbuilder;

public final class StringScalar extends Scalar {

    private final String value;
    private final String alias;

    public StringScalar(String value) {
        this(value, "");
    }
    
    public StringScalar(String value, String alias) {
        this.value = value;
        this.alias = alias;        
    }
    
    @Override
    public String scalarValue() {
        return encodeString(value);
    }
    
    @Override
    public String alias() {
        return alias;
    }
    
    private String encodeString(String value) {
        //Need to improve this...
        return "\"" + value + "\"";
    }

}
