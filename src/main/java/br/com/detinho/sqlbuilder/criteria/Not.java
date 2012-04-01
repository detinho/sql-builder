package br.com.detinho.sqlbuilder.criteria;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

public final class Not implements Criteria {

    private final Criteria right;

    public Not(Selectable column) {
        this.right = new SelectableCriteria(column);
    }

    public Not(Criteria right) {
        this.right = right;
    }

    @Override
    public String write() {
        return String.format("(NOT %s)", right.write());
    }

    @Override
    public void addTable(Set<Table> tables) {
        right.addTable(tables);
    }

}
