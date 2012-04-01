package br.com.detinho.sqlbuilder.criteria;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

public final class MatchCriteria implements Criteria {

    private final Selectable leftSide;
    private final String operator;
    private final Selectable rightSide;

    public MatchCriteria(Selectable leftSide, String operator, Selectable rightSide) {
        this.leftSide = leftSide;
        this.operator = operator;
        this.rightSide = rightSide;
    }

    @Override
    public String write() {
        return String.format("%s %s %s", leftSide.write(), operator, rightSide.write());
    }

    @Override
    public void addTable(Set<Table> tables) {
        leftSide.addTable(tables);
        rightSide.addTable(tables);
    }
    
}
