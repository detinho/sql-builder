package br.com.detinho.sqlbuilder.criteria;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Table;

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
