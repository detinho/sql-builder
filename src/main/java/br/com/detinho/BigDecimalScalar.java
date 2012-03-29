package br.com.detinho;

import java.math.BigDecimal;

public final class BigDecimalScalar extends Scalar {

    private final BigDecimal value;

    public BigDecimalScalar(BigDecimal value) {
        this.value = value;
    }
    
    @Override
    public String write() {
        return value.toString();
    }

}
