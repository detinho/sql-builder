package br.com.detinho;

import static org.junit.Assert.*;
import static br.com.detinho.SqlBuilder.*;

import java.util.Iterator;

import org.junit.Test;

public class ColumnsTest {

    @Test
    public void emptyColumnList() {
        new Columns();
    }
    
    @Test
    public void addAColumn() {
        Columns columns = new Columns();
        columns.add(integer(1));
        
        assertEquals(integer(1), columns.iterator().next());
    }
    
    @Test
    public void addTwoColumn() {
        Columns columns = new Columns();
    
        Scalar numberOne = integer(1);
        Column column = new Column("TABLE", "COLUMN");
        
        columns.add(numberOne);
        columns.add(column);
        
        Iterator<Selectable> iterator = columns.iterator();
        assertEquals(numberOne, iterator.next());
        assertEquals(column, iterator.next());
    }
    
    @Test
    public void getColumnByAlias() {
        Columns columns = new Columns();
    
        Scalar numberOne = integer(1);
        Column column = new Column("TABLE", "COLUMN");
        
        columns.add(numberOne);
        columns.add(column);
        
        assertEquals(column, columns.byAlias("COLUMN"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenColumnDoesNotExists() {
        Columns columns = new Columns();
        columns.byAlias("COLUMN");
    }
    
    @Test
    public void getColumnIndex() {
        Columns columns = new Columns();
        Scalar numberOne = integer(1);
        columns.add(numberOne);
        
        assertEquals(numberOne, columns.get(0));
    }
    
    @Test
    public void numberOfColulmns() {
        Columns columns = new Columns();
        Scalar numberOne = integer(1);
        columns.add(numberOne);
        columns.add(numberOne);
        columns.add(numberOne);
        
        assertEquals(3, columns.size());
    }

}
