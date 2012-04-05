package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.col;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.detinho.sqlbuilder.OrderBy.OrderType;

public class OrderByTest {

    @Test
    public void aliasOrderBy() {
        OrderBy order = new OrderBy("SOME_ALIAS");
        assertEquals("SOME_ALIAS ASC", order.write());
    }
    
    @Test
    public void aliasOrderByDesc() {
        OrderBy order = new OrderBy("SOME_ALIAS", OrderBy.OrderType.DESC);
        assertEquals("SOME_ALIAS DESC", order.write());
    }
    
    @Test
    public void columnOrderBy() {
        OrderBy order = new OrderBy(col("TABLE", "COLUMN"));
        assertEquals("TABLE.COLUMN ASC", order.write());
    }
    
    @Test
    public void columnOrderByDesc() {
        OrderBy order = new OrderBy(col("TABLE", "COLUMN"), OrderType.DESC);
        assertEquals("TABLE.COLUMN DESC", order.write());
    }
    
    @Test
    public void orderClauseShouldAddReferencedTables() {
        Set<Table> tables = new HashSet<Table>();
        OrderBy order = new OrderBy(col("TABLE", "COLUMN"));
        
        order.addTable(tables);
        assertTrue(tables.contains(new Table("TABLE")));
    }

}
