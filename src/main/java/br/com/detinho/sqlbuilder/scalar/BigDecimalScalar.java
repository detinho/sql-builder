package br.com.detinho.sqlbuilder.scalar;

import java.math.BigDecimal;

import br.com.detinho.sqlbuilder.Scalar;

public final class BigDecimalScalar extends Scalar {

    private final BigDecimal value;
    private final String alias;

    public BigDecimalScalar(BigDecimal value) {
        this(value, "");
    }
    
    public BigDecimalScalar(BigDecimal value, String alias) {
        this.value = value;
        this.alias = alias;
    }
    
    @Override
    public String scalarValue() {
        return value.toString();
    }
    
    @Override
    public String alias() {
        return alias;
    }

}
