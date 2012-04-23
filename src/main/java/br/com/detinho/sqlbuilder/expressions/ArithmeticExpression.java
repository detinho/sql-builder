package br.com.detinho.sqlbuilder.expressions;

import java.util.Set;

import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.Table;

public class ArithmeticExpression implements Selectable {
    private final Selectable operand1;
    private final String operator;
    private final Selectable operand2;

    public ArithmeticExpression(Selectable operand1, String operator, Selectable operand2) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
    }
    
    @Override
    public String write() {
        String exp = operand1.write();
        if ("".equals(exp)) {
            return operand2.write();
        }
        return operand1.write() + operator + operand2.write();
    }

    @Override
    public void addTable(Set<Table> tables) {
        operand1.addTable(tables);
        operand2.addTable(tables);
    }
}
