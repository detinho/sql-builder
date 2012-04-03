package br.com.detinho.sqlbuilder;

import static org.junit.Assert.*;
import static br.com.detinho.sqlbuilder.SqlBuilder.*;

import org.junit.Test;

import br.com.detinho.sqlbuilder.Column;
import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Scalar;
import br.com.detinho.sqlbuilder.criteria.And;
import br.com.detinho.sqlbuilder.criteria.BetweenCriteria;
import br.com.detinho.sqlbuilder.criteria.InCriteria;
import br.com.detinho.sqlbuilder.criteria.MatchCriteria;
import br.com.detinho.sqlbuilder.criteria.Not;
import br.com.detinho.sqlbuilder.criteria.Or;

public class CriteriaTest {

    private Scalar one = integer(1);
    private Scalar ten = integer(10);
    private Column column1 = col("TABLE", "COLUMN");
    private Column column2 = col("OTHER_TABLE", "COLUMN");

    @Test
    public void simpleCriteria() {
        MatchCriteria criteria = new MatchCriteria(col("TABLE", "COLUMN"), "=",
                one);
        assertEquals("TABLE.COLUMN = 1", criteria.write());
    }

    @Test
    public void betweenCriteria() {
        BetweenCriteria criteria = new BetweenCriteria(column1, one, ten);
        assertEquals("TABLE.COLUMN BETWEEN 1 AND 10", criteria.write());
    }

    @Test
    public void andCriteria() {
        Criteria left = new MatchCriteria(column1, "=", one);
        Criteria right = new MatchCriteria(column2, ">", ten);
        And andCriteria = new And(left, right);
        
        assertEquals("(TABLE.COLUMN = 1 AND OTHER_TABLE.COLUMN > 10)",
            andCriteria.write());
    }
    
    @Test
    public void andCriteriaWithColumns() {
        And andCriteria = new And(column1, column2);
        
        assertEquals("(TABLE.COLUMN AND OTHER_TABLE.COLUMN)",
            andCriteria.write());
    }
    
    @Test
    public void orCriteria() {
        Criteria left = new MatchCriteria(column1, "=", one);
        Criteria right = new MatchCriteria(column2, ">", ten);
        Or orCriteria = new Or(left, right);
        
        assertEquals("(TABLE.COLUMN = 1 OR OTHER_TABLE.COLUMN > 10)",
            orCriteria.write());
    }
    
    @Test
    public void orCriteriaWithColumns() {
        Or orCriteria = new Or(column1, column2);
        
        assertEquals("(TABLE.COLUMN OR OTHER_TABLE.COLUMN)",
            orCriteria.write());
    }
    
    @Test
    public void complexBooleanExpression() throws Exception {
        Criteria first = new BetweenCriteria(column1, one, column2);
        Criteria left = new MatchCriteria(column1, "=", one);
        Criteria right = new MatchCriteria(column2, ">", ten);
        Criteria finalCriteria = new Or(first, new And(left, right));
        
        String sql = "(TABLE.COLUMN BETWEEN 1 AND OTHER_TABLE.COLUMN OR " +
        		"(TABLE.COLUMN = 1 AND OTHER_TABLE.COLUMN > 10))";
        
        assertEquals(sql, finalCriteria.write());
    }
    
    @Test
    public void notCriteriaWithColumn() {
        Not notCriteria = new Not(column1);
        assertEquals("(NOT TABLE.COLUMN)", notCriteria.write());
    }
    
    @Test
    public void notCriteriaWithScalar() {
        Not notCriteria = new Not(ten);
        assertEquals("(NOT 10)", notCriteria.write());
    }
    
    @Test
    public void notCriteriaWithCriteria() {
        Not notCriteria = new Not(new And(column1, column2));
        assertEquals("(NOT (TABLE.COLUMN AND OTHER_TABLE.COLUMN))", notCriteria.write());
    }
    
    @Test
    public void inCriteria() {
        InCriteria criteria = new InCriteria(column2, one, ten, column1);
        assertEquals("(OTHER_TABLE.COLUMN IN (1, 10, TABLE.COLUMN))", criteria.write());
    }
}
