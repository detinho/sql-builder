package br.com.detinho.sqlbuilder.expressions;

import br.com.detinho.sqlbuilder.Selectable;

public class ExpressionBuilder {

    private Selectable expr = new EmptyExpression();

    public ExpressionBuilder(Selectable start) {
        this.expr = start;
    }
    
    public Selectable make() {
        return expr;
    }

    public void add(Selectable operand) {
        this.expr = new ArithmeticExpression(expr, "+", operand);
    }
    
    public void sub(Selectable operand) {
        this.expr = new ArithmeticExpression(expr, "-", operand);
    }

    public void times(Selectable operand) {
        this.expr = new ArithmeticExpression(expr, "*", operand);
    }

    public void div(Selectable operand) {
        this.expr = new ArithmeticExpression(expr, "/", operand);
    }
}
