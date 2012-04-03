package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.detinho.sqlbuilder.join.InnerJoin;
import br.com.detinho.sqlbuilder.join.Join;
import br.com.detinho.sqlbuilder.join.LeftJoin;

public class JoinTest {

    @Test
    public void aNewJoin() {
        Join join = new InnerJoin("OTHER_TABLE");
        assertEquals("INNER JOIN OTHER_TABLE", join.write());
    }
    
    @Test
    public void joinWithASimpleOnClause() {
        Join join = new InnerJoin("OTHER_TABLE");
        join.addOn(col("OTHER_TABLE", "COLUMN1"), "=", col("TABLE", "COLUMN1"));
        
        assertEquals("INNER JOIN OTHER_TABLE ON OTHER_TABLE.COLUMN1 = TABLE.COLUMN1", join.write());
    }
    
    @Test
    public void twoConditionsOnClause() {
        Join join = new InnerJoin("OTHER_TABLE");
        join.addOn(col("OTHER_TABLE", "COLUMN1"), "=", col("TABLE", "COLUMN1"));
        join.addOn(col("OTHER_TABLE", "COLUMN2"), "=", col("TABLE", "COLUMN2"));
        
        assertEquals("INNER JOIN OTHER_TABLE ON (OTHER_TABLE.COLUMN1 = TABLE.COLUMN1 AND " +
        		"OTHER_TABLE.COLUMN2 = TABLE.COLUMN2)", join.write());
    }
    
    @Test
    public void shouldTheReferencedTables() {
        Set<Table> tables = new HashSet<Table>();
        Join join = new InnerJoin("OTHER_TABLE");
        join.addOn(col("OTHER_TABLE", "COLUMN1"), "=", col("TABLE", "COLUMN1"));
        join.addOn(col("OTHER_TABLE", "COLUMN2"), "=", col("TABLE", "COLUMN2"));
        
        join.addTable(tables);
        assertEquals(1, tables.size());
        assertTrue(tables.contains(new Table("TABLE")));
    }
    
    @Test
    public void leftJoin() {
        Join dummyJoin = new LeftJoin("OTHER_TABLE");
        dummyJoin.addOn(integer(1), "=", integer(1));
        
        assertEquals("LEFT JOIN OTHER_TABLE ON 1 = 1", dummyJoin.write());
    }

    @Test
    public void manyConditionsOnClauseMoreFluently() {
        Join join = new InnerJoin("OTHER_TABLE");
        join.
            addOn(col("OTHER_TABLE", "COLUMN1"), "=", col("TABLE", "COLUMN1")).
            addOn(col("OTHER_TABLE", "COLUMN2"), "=", col("TABLE", "COLUMN2")).
            addOn(col("OTHER_TABLE", "COLUMN2"), "=", col("TABLE", "COLUMN2"));

        assertEquals("INNER JOIN OTHER_TABLE ON " +
                "((OTHER_TABLE.COLUMN1 = TABLE.COLUMN1 AND " +
                "OTHER_TABLE.COLUMN2 = TABLE.COLUMN2) AND " +
                "OTHER_TABLE.COLUMN2 = TABLE.COLUMN2)", join.write());
    }

}
