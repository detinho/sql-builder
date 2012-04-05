package br.com.detinho.sqlbuilder;

import java.util.HashSet;
import java.util.Set;

public class OrderBy implements Writable {

    public enum OrderType {ASC, DESC};
    
    private final String alias;
    private final OrderType type;
    private Set<Table> tables = new HashSet<Table>();

    public OrderBy(String alias) {
        this(alias, OrderType.ASC);
    }
    
    public OrderBy(String alias, OrderType type) {
        this.alias = alias;
        this.type = type;
    }

    public OrderBy(Selectable col) {
        this(col, OrderType.ASC);
    }

    public OrderBy(Selectable col, OrderType type) {
        this(col.write(), type);
        col.addTable(tables);
    }

    @Override
    public String write() {
        String typeStr = type == OrderType.ASC ? "ASC" : "DESC";
        return String.format("%s %s", alias, typeStr);
    }

    public void addTable(Set<Table> tables) {
        tables.addAll(this.tables);
    }


}
