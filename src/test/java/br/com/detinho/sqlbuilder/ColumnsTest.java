package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.col;
import static br.com.detinho.sqlbuilder.SqlBuilder.integer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ColumnsTest {

    @Test
    public void emptyColumnList() {
        new Columns();
    }
    
    @Test
    public void addTwoColumn() {
        Columns columns = new Columns();
    
        Scalar numberOne = integer(1);
        Column column = new Column("TABLE", "COLUMN");
        
        columns.add(numberOne);
        columns.add(column);
        
        assertEquals("1, TABLE.COLUMN", columns.write());
    }

    @Test
    public void writeAllColumns() {
        Columns columns = new Columns();
        columns.add(integer(1));
        columns.add(integer(2));
        
        assertEquals("1, 2", columns.write());
    }
    
    @Test
    public void addTables() {
        Set<Table> tables = new HashSet<Table>();
        Columns columns = new Columns();
        columns.add(col("TABLE1", "COL"));
        columns.add(col("TABLE2", "COL"));
        
        columns.addTable(tables);
        
        assertTrue(tables.contains(new Table("TABLE1")));
        assertTrue(tables.contains(new Table("TABLE2")));
    }
    
    @Test
    public void whenNoColumnsAreSpecifiedDefaultsToAll() {
        Columns columns = new Columns();
        assertEquals("*", columns.write());
    }
    
    @Test
    public void addAColumnWithAlias() {
        Columns columns = new Columns();
        columns.add(col("TABLE1", "COL"), "ALIAS");
        
        assertEquals("TABLE1.COL AS ALIAS", columns.write());
    }
}
