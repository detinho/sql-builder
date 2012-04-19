package br.com.detinho.sqlbuilder.functions;

import static br.com.detinho.sqlbuilder.StringUtils.appendAlias;
import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

public class ConditionalFunction implements Selectable {

    private final Criteria criteria;
    private final Selectable whenTrue;
    private final Selectable whenFalse;
    private final String alias;

    public ConditionalFunction(Criteria criteria, Selectable whenTrue,
            Selectable whenFalse, String alias) {
        this.criteria = criteria;
        this.whenTrue = whenTrue;
        this.whenFalse = whenFalse;
        this.alias = alias;
        whenTrue = whenFalse;
    }

    @Override
    public String write() {
        String sql = String.format("IF(%s, %s, %s)", criteria.write(), whenTrue.write(),
            whenFalse.write());
        sql += appendAlias(alias);
        return sql;
    }

    @Override
    public void addTable(Set<Table> tables) {
        criteria.addTable(tables);
        whenFalse.addTable(tables);
        whenTrue.addTable(tables);
    }
}
