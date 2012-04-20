package br.com.detinho.sqlbuilder;

import static br.com.detinho.sqlbuilder.StringUtils.appendAlias;
import java.util.Set;

class AliasedSelectable implements Selectable {
    private final Selectable selectable;
    private final String alias;

    public AliasedSelectable(Selectable selectable, String alias) {
        this.selectable = selectable;
        this.alias = alias;
    }

    @Override
    public String write() {
        return selectable.write() + appendAlias(alias);
    }

    @Override
    public void addTable(Set<Table> tables) {
        selectable.addTable(tables);
    }
    
}