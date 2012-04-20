package br.com.detinho.sqlbuilder.scalar;

import br.com.detinho.sqlbuilder.Scalar;

public final class IntegerScalar extends Scalar {

    private final int value;

    public IntegerScalar(int value) {
        this.value = value;
    }

    @Override
    public String scalarValue() {
        return String.valueOf(value);
    }
}
