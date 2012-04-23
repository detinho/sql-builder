package br.com.detinho.sqlbuilder.expressions;

import java.util.Set;

import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

public class EmptyExpression implements Selectable {

    @Override
    public String write() {
        return "";
    }

    @Override
    public void addTable(Set<Table> tables) {
    }

}