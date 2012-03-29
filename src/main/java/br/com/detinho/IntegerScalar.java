package br.com.detinho;

public final class IntegerScalar extends Scalar {

    private final int value;

    public IntegerScalar(int value) {
        this.value = value;
    }
    
    @Override
    public String write() {
        return String.valueOf(value);
    }

}
