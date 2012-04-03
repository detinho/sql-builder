package br.com.detinho.sqlbuilder;

import br.com.detinho.sqlbuilder.join.Join;

public class LeftJoin extends Join {

    public LeftJoin(String table) {
        this.table = new Table(table);
    }

    @Override
    public String joinSql() {
        return "LEFT JOIN";
    }

}
