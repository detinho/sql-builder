package br.com.detinho.sqlbuilder.functions;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

public class ConditionalFunction implements Selectable {

    private final Criteria criteria;
    private final Selectable whenTrue;
    private final Selectable whenFalse;

    public ConditionalFunction(Criteria criteria, Selectable whenTrue,
            Selectable whenFalse) {
        this.criteria = criteria;
        this.whenTrue = whenTrue;
        this.whenFalse = whenFalse;
    }

    @Override
    public String write() {
        return String.format("IF(%s, %s, %s)", criteria.write(), whenTrue.write(),
            whenFalse.write());
    }

    @Override
    public void addTable(Set<Table> tables) {
        criteria.addTable(tables);
        whenFalse.addTable(tables);
        whenTrue.addTable(tables);
    }
}
