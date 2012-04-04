package br.com.detinho.sqlbuilder.criteria;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.StringUtils;
import br.com.detinho.sqlbuilder.Table;

public class InCriteria implements Criteria {

    private final Selectable left;
    private final Selectable[] values;

    public InCriteria(Selectable left, Selectable... values) {
        this.left = left;
        this.values = values;
    }
    
    @Override
    public String write() {
        String sql = "";
        for (Selectable value : values) {
            sql += value.write() + ", ";
        }
        sql = StringUtils.removeTrailingComma(sql);
        return String.format("(%s IN (%s))", left.write(), sql);
    }

    @Override
    public void addTable(Set<Table> tables) {

    }

}
