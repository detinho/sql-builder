package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.col;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.detinho.sqlbuilder.expressions.ArithmeticExpression;

public class ArithmeticExpressionTest {

    @Test
    public void shouldAddTheTablesFromOperands() {
        Set<Table> tables = new HashSet<Table>();
        ArithmeticExpression expression = 
                new ArithmeticExpression(col("T1","COL"), "+", col("T2", "COL"));
        
        expression.addTable(tables);
        
        assertTrue("should contain table", tables.contains(new Table("T1")));
        assertTrue("should contain table", tables.contains(new Table("T2")));
    }

}
