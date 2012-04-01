package br.com.detinho.sqlbuilder.criteria;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

public final class BetweenCriteria implements Criteria {

    private final Selectable column;
    private final Selectable start;
    private final Selectable end;
    
    public BetweenCriteria(Selectable column, Selectable start, Selectable end) {
        this.column = column;
        this.start = start;
        this.end = end;
    }

    @Override
    public String write() {
        return String.format("%s BETWEEN %s AND %s",
            column.write(), start.write(), end.write());
    }
    
    @Override
    public void addTable(Set<Table> tables) {
        column.addTable(tables);
        start.addTable(tables);
        end.addTable(tables);
    }
    
    

}
