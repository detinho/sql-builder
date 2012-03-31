package br.com.detinho;

import java.util.Set;

public final class And implements Criteria {

    private final Criteria left;
    private final Criteria right;

    public And(Criteria left, Criteria right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String write() {
        return String.format("%s AND %s", left.write(), right.write());
    }

    @Override
    public void addTable(Set<Table> tables) {
        left.addTable(tables);
        right.addTable(tables);
    }

}
