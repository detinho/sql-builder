package br.com.detinho.sqlbuilder.criteria;

import java.util.Set;

import br.com.detinho.sqlbuilder.Criteria;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

final class SelectableCriteria implements Criteria {

    private final Selectable selectable;

    public SelectableCriteria(Selectable selectable) {
        this.selectable = selectable;
        
    }
    
    @Override
    public String write() {
        return selectable.write();
    }

    @Override
    public void addTable(Set<Table> tables) {
        selectable.addTable(tables);
    }

}
