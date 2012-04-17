package expressions;

import br.com.detinho.sqlbuilder.Expression;
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
    
}
