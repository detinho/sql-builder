package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.SqlBuilder.col;
import static br.com.detinho.sqlbuilder.SqlBuilder.integer;
import static br.com.detinho.sqlbuilder.SqlBuilder.match;
import static br.com.detinho.sqlbuilder.SqlBuilder.string;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.detinho.sqlbuilder.functions.ConditionalFunction;

public class ConditionalFunctionTest {

    @Test
    public void conditionalFuncion() {
        Criteria criteria = match(integer(1), "=", integer(1));
        ConditionalFunction function = 
                new ConditionalFunction(criteria, string("IS TRUE"), string("IS FALSE"));
        
        assertEquals("IF(1 = 1, \"IS TRUE\", \"IS FALSE\")", function.write());
    }
    
    @Test
    public void conditionalFuncionShouldIncludeTables() {
        Set<Table> tables = new HashSet<Table>();
        Criteria criteria = match(col("TABLE", "COL"), "=", integer(1));
        ConditionalFunction function = 
                new ConditionalFunction(criteria, col("TABLE2", "COL"), col("TABLE3", "COL"));
        
        function.addTable(tables);
        assertTrue("Should contain table.", tables.contains(new Table("TABLE")));
        assertTrue("Should contain table.", tables.contains(new Table("TABLE2")));
        assertTrue("Should contain table.", tables.contains(new Table("TABLE3")));
    }
    
    @Test
    public void conditionalFuncionWithoutAlias() {
        Criteria criteria = match(integer(1), "=", integer(1));
        ConditionalFunction function = 
                new ConditionalFunction(criteria, string("IS TRUE"), string("IS FALSE"));
        
        assertEquals("IF(1 = 1, \"IS TRUE\", \"IS FALSE\")", function.write());
    }

}
