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
    
    @Test
    public void simpleWhereClause() {
        Select select = new Select();
        select.column("TABLENAME", "COLUMN_NAME");
        select.where("COLUMN_NAME", "=", string("Marcos"));
        
        assertEquals("SELECT TABLENAME.COLUMN_NAME FROM TABLENAME WHERE TABLENAME.COLUMN_NAME = \"Marcos\"", 
                select.toSql());
    }
    
    @Test
    public void twoColumnsWhereClause() {
        Select select = new Select();
        select.column("TABLENAME", "COLUMN1");
        select.column("TABLENAME", "COLUMN2");
        select.where("COLUMN1", "=", "COLUMN2");
        
        assertEquals("SELECT TABLENAME.COLUMN1, TABLENAME.COLUMN2 FROM TABLENAME " +
        		"WHERE TABLENAME.COLUMN1 = TABLENAME.COLUMN2", 
                select.toSql());
    }
    
//    @Test
//    public void twoConditionsOnWhereClause() {
//        Select select = new Select();
//        select.column(integer(1));
//        select.where(integer(2), "=", integer(2));
//    }

}
