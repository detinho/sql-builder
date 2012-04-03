package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.*;
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

        Criteria equals = match(column1, "=", column2);
        Criteria between = between(column3, integer(1), integer(10));
        Criteria criteria = or(equals, between);        
        
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
    
    @Test
    public void selectWithSimpleJoin() {
        Select select = new Select();
        select.column("OTHER_TABLE", "SOME_COLUMN");
        select.join("OTHER_TABLE", "SOME_COLUMN", "=", "TABLE", "COLUMN");
        
        assertEquals("SELECT OTHER_TABLE.SOME_COLUMN FROM TABLE " +
        		"INNER JOIN OTHER_TABLE ON OTHER_TABLE.SOME_COLUMN = TABLE.COLUMN", select.toSql());
        
    }
    
    @Test
    public void selectWithSimpleLeftJoin() {
        Select select = new Select();
        select.column("OTHER_TABLE", "SOME_COLUMN");
        select.leftJoin("OTHER_TABLE", "SOME_COLUMN", "=", "TABLE", "COLUMN");
        
        assertEquals("SELECT OTHER_TABLE.SOME_COLUMN FROM TABLE " +
                "LEFT JOIN OTHER_TABLE ON OTHER_TABLE.SOME_COLUMN = TABLE.COLUMN", select.toSql());
    }
    
    @Test
    public void manyJoins() {
        Select select = new Select();
        select.column("OTHER_TABLE", "SOME_COLUMN");
        select.column("THIRD_TABLE", "THIRD_COLUMN");
        
        select.join("OTHER_TABLE", "SOME_COLUMN", "=", "TABLE", "COLUMN");
        select.leftJoin("THIRD_TABLE", "THIRD_COLUMN", "=", "TABLE", "COLUMN");
        
        assertEquals("SELECT OTHER_TABLE.SOME_COLUMN, THIRD_TABLE.THIRD_COLUMN FROM TABLE " +
                "INNER JOIN OTHER_TABLE ON OTHER_TABLE.SOME_COLUMN = TABLE.COLUMN " +
                "LEFT JOIN THIRD_TABLE ON THIRD_TABLE.THIRD_COLUMN = TABLE.COLUMN", select.toSql());
    }
    
    @Test
    public void aJoinWithMultipleConditions() {
        Select select = new Select();
        select.column("OTHER_TABLE", "SOME_COLUMN");
        select.leftJoin("OTHER_TABLE", "SOME_COLUMN", "=", "TABLE", "COLUMN").
            addOn("OTHER_TABLE", "OTHER_COLUMN", "=", "TABLE", "OTHER_COLUMN");
        
        assertEquals("SELECT OTHER_TABLE.SOME_COLUMN FROM TABLE " +
                "LEFT JOIN OTHER_TABLE ON " +
                "(OTHER_TABLE.SOME_COLUMN = TABLE.COLUMN AND " +
                "OTHER_TABLE.OTHER_COLUMN = TABLE.OTHER_COLUMN)", select.toSql());
    }

}
