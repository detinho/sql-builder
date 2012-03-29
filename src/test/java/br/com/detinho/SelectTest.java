package br.com.detinho;

import static br.com.detinho.SqlBuilder.decimal;
import static br.com.detinho.SqlBuilder.integer;
import static br.com.detinho.SqlBuilder.string;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SelectTest {

    @Test
    public void simplestSelect() {
        Select select = new Select();
        select.column(integer(1));
        
        assertEquals("SELECT 1", select.toSql());
    }
    
    @Test
    public void twoColumns() {
        Select select = new Select();
        select.column(integer(1));
        select.column(integer(2));
        
        assertEquals("SELECT 1, 2", select.toSql());
    }
    
    @Test
    public void twoDifferentTypes() {
        Select select = new Select();
        select.column(integer(1));
        select.column(decimal("2.001"));
        
        assertEquals("SELECT 1, 2.001", select.toSql());
    }
    
    @Test
    public void aStringColumn() {
        Select select = new Select();
        select.column(string("value"));
        
        assertEquals("SELECT \"value\"", select.toSql());
    }
    
    @Test
    public void aColumnName() {
        Select select = new Select();
        select.column("TABLENAME", "COLUMN_NAME");
        
        assertEquals("SELECT TABLENAME.COLUMN_NAME FROM TABLENAME", select.toSql());
    }
    
//    @Test
//    public void simpleWhereClause() {
//        Select select = new Select();
//        select.column("TABLENAME", "COLUMN_NAME");
//        select.where(col, "=", string("Marcos"));
//        
//        assertEquals("SELECT TABLENAME.COLUMN_NAME FROM TABLENAME", select.toSql());
//    }

}
