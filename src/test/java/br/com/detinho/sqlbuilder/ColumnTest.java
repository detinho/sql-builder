package br.com.detinho.sqlbuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.detinho.sqlbuilder.Column;
import br.com.detinho.sqlbuilder.Table;

public class ColumnTest {

    @Test
    public void aColumnOfATable() {
        Column column = new Column("TABLE", "COLUMN");
        assertEquals("TABLE.COLUMN", column.write());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void aColumnWithoutTable() {
        new Column(null, "COLUMN");
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
        assertEquals(new Column("TABLE", "COLUMN"), new Column("TABLE", "COLUMN"));
        assertEquals(new Column("TABLE", "COLUMN", "ALIAS"), new Column("TABLE", "COLUMN", "ALIAS"));
        assertNotEquals(new Column("TABLE", "C"), new Column("TABLE", "COLUMN"));
        assertNotEquals(new Column("TABLE", "COLUMN", "A"), new Column("TABLE", "COLUMN", "ALIAS"));
    }
    
    private void assertNotEquals(Object obj1, Object obj2) {
        try {
            assertEquals(obj1, obj2);
            fail("Objects should not be equals");
        } catch (AssertionError e) {
            
        }
    }

}
