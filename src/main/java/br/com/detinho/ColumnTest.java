package br.com.detinho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ColumnTest {

    @Test
    public void aColumnOfATable() {
        Column column = new Column("TABLE", "COLUMN");
        assertEquals("TABLE.COLUMN", column.write());
    }
    
    @Test
    public void aColumnWithoutTable() {
        Column column = new Column("COLUMN");
        assertEquals("COLUMN", column.write());
    }
    
    @Test
    public void theColumnWillAddItsTable() {
        Set<Table> tables = new HashSet<Table>();
        Column column = new Column("TABLE", "COLUMN");
        column.addTable(tables);
        
        assertEquals(1, tables.size());
        assertEquals(new Table("TABLE"), tables.iterator().next());
    }
    
    @Test
    public void columnWithAlias() {
        Column column = new Column("TABLE", "COLUMN", "COL");
        assertEquals("TABLE.COLUMN AS \"COL\"", column.write());
    }
    
    @Test
    public void testEquality() {
        fail();
    }

}
