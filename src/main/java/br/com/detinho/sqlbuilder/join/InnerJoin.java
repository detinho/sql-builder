package br.com.detinho.sqlbuilder.join;

import br.com.detinho.sqlbuilder.Table;

public class InnerJoin extends Join{

    public InnerJoin(String table) {
        this.table = new Table(table);
    }

    @Override
    public String joinSql() {
        return "INNER JOIN";
    }
    
}
