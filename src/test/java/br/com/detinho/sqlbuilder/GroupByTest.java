package br.com.detinho.sqlbuilder;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupByTest {

    @Test
    public void groupBySomeAlias() {
        GroupBy groupBy = new GroupBy("ALIAS");
        assertEquals("ALIAS", groupBy.write());
    }

}
