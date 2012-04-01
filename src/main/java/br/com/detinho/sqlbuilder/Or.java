package br.com.detinho.sqlbuilder;

import java.util.Set;

public class Or implements Criteria {

    private final Criteria left;
    private final Criteria right;

    public Or(Criteria left, Criteria right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String write() {
        return String.format("(%s OR %s)", left.write(), right.write());
    }

    @Override
    public void addTable(Set<Table> tables) {
        left.addTable(tables);
        right.addTable(tables);
    }


}
