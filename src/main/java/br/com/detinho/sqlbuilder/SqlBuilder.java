package br.com.detinho.sqlbuilder;

import java.math.BigDecimal;

public final class SqlBuilder {

    private SqlBuilder() {}
    
    public static Scalar string(String value) {
        return new StringScalar(value);
    }
    
    public static Scalar integer(int value) {
        return new IntegerScalar(value);
    }
    
    public static Scalar decimal(String value) {
        return new BigDecimalScalar(new BigDecimal(value));
    }
    
    public static Column col(String table, String column) {
        return new Column(table, column);
    }
    
}
