package br.com.detinho;

public final class StringScalar extends Scalar {

    private final String value;

    public StringScalar(String value) {
        this.value = value;
    }
    
    @Override
    public String write() {
        return encodeString(value);
    }
    
    private String encodeString(String value) {
        //Need to improve this...
        return "\"" + value + "\"";
    }

}
