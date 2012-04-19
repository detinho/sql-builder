package br.com.detinho.sqlbuilder.expressions;

import java.util.Set;

import br.com.detinho.sqlbuilder.Expression;
import br.com.detinho.sqlbuilder.Selectable;
import br.com.detinho.sqlbuilder.StringUtils;
import br.com.detinho.sqlbuilder.Table;
import br.com.detinho.sqlbuilder.Writable;

public class ExpressionBuilder implements Writable {

    private Expression expr = new EmptyExpression();

    public ExpressionBuilder(Expression operand) {
        this.expr = operand;
    }

    public void add(Expression operand) {
        this.expr = new ArithmeticExpression(expr, "+", operand);
    }

    @Override
    public String write() {
        return expr.expression();
    }
    
    public void sub(Expression operand) {
        this.expr = new ArithmeticExpression(expr, "-", operand);
    }

    public void times(Expression operand) {
        this.expr = new ArithmeticExpression(expr, "*", operand);
    }

    public void div(Expression operand) {
        this.expr = new ArithmeticExpression(expr, "/", operand);
    }

    public Selectable selectable(final String alias) {
        return new SelectableExpression(expr, alias);
    }

    public Selectable selectable() {
        return selectable("");
    }
    
    private static final class SelectableExpression implements Selectable {
        private final String alias;
        private final Expression expr;
        
        private SelectableExpression(Expression expr, String alias) {
            this.expr = expr;
            this.alias = alias;
        }
        
        @Override
        public String write() {
            if (StringUtils.nullToStr(alias).equals(""))
                return expr.expression();
            
            return "(" + expr.expression() + ") AS " + alias;
        }
        
        @Override
        public String alias() {
            return alias;
        }
        
        @Override
        public void addTable(Set<Table> tables) {
            
        }
    }
    
}
