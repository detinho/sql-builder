package br.com.detinho.sqlbuilder;

import java.util.Set;

public interface Selectable extends Writable {

    public void addTable(Set<Table> tables);
    
}
