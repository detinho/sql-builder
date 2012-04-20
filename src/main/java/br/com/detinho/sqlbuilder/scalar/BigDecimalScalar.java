package br.com.detinho.sqlbuilder.scalar;

import java.math.BigDecimal;

import br.com.detinho.sqlbuilder.Scalar;

public final class BigDecimalScalar extends Scalar {

    private final BigDecimal value;

    public BigDecimalScalar(BigDecimal value) {
        this.value = value;
    }
    
    @Override
    public String scalarValue() {
        return value.toString();
    }
}
