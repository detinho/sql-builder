package br.com.detinho.sqlbuilder;

import static org.junit.Assert.*;
import org.junit.Test;

import br.com.detinho.sqlbuilder.expressions.ExpressionBuilder;


import static br.com.detinho.sqlbuilder.SqlBuilder.*;

public class ExpressionsTest {

    private static final Expression num1 = integer(1);
    private static final Expression num2 = integer(2);
    private static final Expression num3 = integer(3);

    @Test
    public void simpleSum() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.add(num2);
        assertEquals("1+2", expr.write());
    }
    
    @Test
    public void simpleSubtraction() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.sub(num2);
        assertEquals("1-2", expr.write());
    }
    
    @Test
    public void simpleSumAndSubtraction() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.add(num2);
        expr.sub(num3);
        
        assertEquals("1+2-3", expr.write());
    }
    
    @Test
    public void multiplication() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.times(num2);
        assertEquals("1*2", expr.write());
    }
    
    @Test
    public void shoudHaveAnInitialOperand() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.add(num2);
        assertEquals("1+2", expr.write());        
    }
    
    @Test
    public void division() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        
        expr.div(num2);
        assertEquals("1/2", expr.write());
    }
    
    @Test
    public void canTransformAnExpressionIntoASelectable() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        expr.div(num2);
        
        Selectable selectable = expr.selectable("ALIAS");
        
        assertEquals("(1/2) AS ALIAS", selectable.write());
        assertEquals("ALIAS", selectable.alias());
    }
    
    @Test
    public void theAliasCanBeOptional() {
        ExpressionBuilder expr = new ExpressionBuilder(num1);
        expr.div(num2);
        
        Selectable selectable = expr.selectable();

        assertEquals("1/2", selectable.write());
    }
}
