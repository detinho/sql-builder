package br.com.detinho.sqlbuilder;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ColumnTest {

    @Test
    public void aColumnOfATable() {
        Column column = new Column("TABLE", "COLUMN");
        assertEquals("TABLE.COLUMN", column.write());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void aColumnWithoutTable() {
        new Column("", "COLUMN");
    }
    
    @Test
    public void theColumnWillAddItsTable() {
        Set<Table> tables = new HashSet<Table>();
        Column column = new Column("TABLE", "COLUMN");
        column.addTable(tables);
        
        assertEquals(1, tables.size());
        assertEquals(new Table("TABLE"), tables.iterator().next());
    }
}
