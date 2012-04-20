package br.com.detinho.sqlbuilder;

import java.math.BigDecimal;

import br.com.detinho.sqlbuilder.criteria.And;
import br.com.detinho.sqlbuilder.criteria.BetweenCriteria;
import br.com.detinho.sqlbuilder.criteria.InCriteria;
import br.com.detinho.sqlbuilder.criteria.MatchCriteria;
import br.com.detinho.sqlbuilder.criteria.Not;
import br.com.detinho.sqlbuilder.criteria.Or;
import br.com.detinho.sqlbuilder.functions.ConditionalFunction;
import br.com.detinho.sqlbuilder.scalar.BigDecimalScalar;
import br.com.detinho.sqlbuilder.scalar.BooleanScalar;
import br.com.detinho.sqlbuilder.scalar.IntegerScalar;
import br.com.detinho.sqlbuilder.scalar.StringScalar;

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
    
    public static Scalar bool(boolean value) {
        return new BooleanScalar(value);
    }
    
    public static Column col(String table, String column) {
        return new Column(table, column);
    }
    
    public static Column col(Table table, String column) {
        return new Column(table, column);
    }
    
    public static Criteria match(Selectable left, String operator, Selectable right) {
        return new MatchCriteria(left, operator, right);
    }
    
    public static Criteria between(Selectable left, Selectable start, Selectable end) {
        return new BetweenCriteria(left, start, end);
    }
    
    public static Criteria and(Criteria left, Criteria right) {
        return new And(left, right);
    }
    
    public static Criteria and(Column left, Column right) {
        return new And(left, right);
    }
    
    public static Criteria or(Criteria left, Criteria right) {
        return new Or(left, right);
    }
    
    public static Criteria or(Column left, Column right) {
        return new Or(left, right);
    }
    
    public static Criteria not(Criteria left) {
        return new Not(left);
    }
    
    public static Criteria not(Selectable left) {
        return new Not(left);
    }
    
    public static Criteria in(Selectable left, Selectable... values) {
        return new InCriteria(left, values);
    }
    
    public static ConditionalFunction cond(Criteria criteria, Selectable whenTrue, 
            Selectable whenFalse) {
        return new ConditionalFunction(criteria, whenTrue, whenFalse);
    }
}
