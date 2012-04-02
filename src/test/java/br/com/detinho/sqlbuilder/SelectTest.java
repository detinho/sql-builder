package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.detinho.sqlbuilder.criteria.BetweenCriteria;
import br.com.detinho.sqlbuilder.criteria.MatchCriteria;
import br.com.detinho.sqlbuilder.criteria.Or;

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
    
    @Test
    public void twoConditionsOnWhereClause() {
        Select select = new Select();
        select.column("TABLE", "COLUMN1");
        select.column("TABLE", "COLUMN2");
        select.where("COLUMN1", ">", integer(2));
        select.where("COLUMN2", "=", integer(0));
        
        assertEquals("SELECT TABLE.COLUMN1, TABLE.COLUMN2 FROM TABLE " +
                "WHERE (TABLE.COLUMN1 > 2 AND TABLE.COLUMN2 = 0)", select.toSql());
    }
    
    @Test
    public void twoConditionsWithColumnsOnWhereClause() {
        Select select = new Select();
        select.column("TABLE", "COLUMN1");
        select.column("TABLE", "COLUMN2");
        select.column("TABLE", "COLUMN3");
        select.where("COLUMN1", ">", "COLUMN2");
        select.where("COLUMN2", "=", "COLUMN3");
        
        assertEquals("SELECT TABLE.COLUMN1, TABLE.COLUMN2, TABLE.COLUMN3 FROM TABLE " +
                "WHERE (TABLE.COLUMN1 > TABLE.COLUMN2 AND TABLE.COLUMN2 = TABLE.COLUMN3)", select.toSql());
    }
    
    @Test
    public void moreComplexWhereClause() throws Exception {
        Column column1 = col("TABLE", "COLUMN1");
        Column column2 = col("TABLE", "COLUMN2");
        Column column3 = col("TABLE", "COLUMN3");

        Criteria equals = new MatchCriteria(column1, "=", column2);
        Criteria between = new BetweenCriteria(column3, integer(1), integer(10));
        Criteria criteria = new Or(equals, between);        
        
        Select select = new Select();
        select.column(column1);
        select.where(criteria);
        
        assertEquals("SELECT TABLE.COLUMN1 FROM TABLE " +
        		"WHERE (TABLE.COLUMN1 = TABLE.COLUMN2 OR TABLE.COLUMN3 BETWEEN 1 AND 10)", select.toSql());
    }
    
    @Test
    public void columnOnWhereClauseNotReferencedOnColumnSelection() throws Exception {
        Column column1 = col("TABLE", "COLUMN1");

        Select select = new Select();
        select.column(column1);
        select.where("TABLE", "COLUMN2", "=", "TABLE", "COLUMN3");
        
        assertEquals("SELECT TABLE.COLUMN1 FROM TABLE " +
                "WHERE TABLE.COLUMN2 = TABLE.COLUMN3", select.toSql());
    }
    
    @Test
    public void selectWithTwoTables() {
        Select select = new Select();
        select.column("TABLE1", "COLUMN1");
        select.where("TABLE1", "COLUMN1", "=", "TABLE2", "COLUMN2");
        
        assertEquals("SELECT TABLE1.COLUMN1 " +
        		"FROM TABLE1, TABLE2 WHERE TABLE1.COLUMN1 = TABLE2.COLUMN2", select.toSql());
    }

}
