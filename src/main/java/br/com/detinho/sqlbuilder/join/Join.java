package br.com.detinho.sqlbuilder.join;

import static br.com.detinho.sqlbuilder.SqlBuilder.and;
import static br.com.detinho.sqlbuilder.SqlBuilder.match;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;
import br.com.detinho.sqlbuilder.Writable;

public abstract class Join implements Writable {

    protected Table table;
    private Criteria criteria = null;

    public abstract String joinSql();
    
    @Override
    public String write() {
        String format = String.format("%s %s", joinSql(), table.write());
        if (criteria != null) {
            format += String.format(" ON %s", criteria.write());
        }
        return format;
    }

    public void addTable(Set<Table> tables) {
        if (criteria != null)
            criteria.addTable(tables);
        removeTable(tables);
    }

    private void removeTable(Set<Table> tables) {
        tables.remove(table);
    }

    public void addOn(Selectable left, String operator, Selectable right) {
        Criteria match = match(left, operator, right);
        if (criteria == null)
            criteria = match;
        else
            criteria = and(criteria, match);
    }

}