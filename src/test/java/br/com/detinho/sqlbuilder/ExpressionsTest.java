package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.col;
import static br.com.detinho.sqlbuilder.SqlBuilder.integer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.detinho.sqlbuilder.expressions.ExpressionBuilder;

public class ExpressionsTest {

    private static final Selectable num1 = integer(1);
    private static final Selectable num2 = integer(2);
    private static final Selectable num3 = integer(3);

    @Test
    public void simpleSum() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.add(num2);
        assertEquals("1+2", expr.make().write());
    }
    
    @Test
    public void simpleSubtraction() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.sub(num2);
        assertEquals("1-2", expr.make().write());
    }
    
    @Test
    public void simpleSumAndSubtraction() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.add(num2);
        expr.sub(num3);
        
        assertEquals("1+2-3", expr.make().write());
    }
    
    @Test
    public void multiplication() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.times(num2);
        assertEquals("1*2", expr.make().write());
    }
    
    @Test
    public void shoudHaveAnInitialOperand() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.add(num2);
        assertEquals("1+2", expr.make().write());        
    }
    
    @Test
    public void division() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.div(num2);
        assertEquals("1/2", expr.make().write());
    }
}
