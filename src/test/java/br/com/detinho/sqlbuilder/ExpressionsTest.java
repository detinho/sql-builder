package br.com.detinho.sqlbuilder;

import static org.junit.Assert.*;
import org.junit.Test;

import expressions.ExpressionBuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.*;

public class ExpressionsTest {

    @Test
    public void simpleSum() {
        ExpressionBuilder expr = new ExpressionBuilder();
        Expression num1 = integer(1);
        Expression num2 = integer(2);
        
        expr.add(num1);
        expr.add(num2);
        assertEquals("1+2", expr.write());
    }
    
    @Test
    public void simpleSubtraction() {
        ExpressionBuilder expr = new ExpressionBuilder();
        Expression num1 = integer(1);
        Expression num2 = integer(2);
        
        expr.add(num1);
        expr.sub(num2);
        assertEquals("1-2", expr.write());
    }
    
    @Test
    public void simpleSumAndSubtraction() {
        ExpressionBuilder expr = new ExpressionBuilder();
        Expression num1 = integer(1);
        Expression num2 = integer(2);
        Expression num3 = integer(3);
        
        expr.add(num1);
        expr.add(num2);
        expr.sub(num3);
        
        assertEquals("1+2-3", expr.write());
    }

}
