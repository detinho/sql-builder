package expressions;

import br.com.detinho.sqlbuilder.Expression;

public class ArithmeticExpression implements Expression {
    private final Expression operand1;
    private final String operator;
    private final Expression operand2;

    public ArithmeticExpression(Expression operand1, String operator, Expression operand2) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
    }
    
    @Override
    public String expression() {
        if (operand1 instanceof EmptyExpression) {
            return operand2.expression();
        }
        return operand1.expression() + operator + operand2.expression();
    }
}
