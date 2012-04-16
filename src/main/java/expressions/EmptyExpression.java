package expressions;

import br.com.detinho.sqlbuilder.Expression;

public class EmptyExpression implements Expression {

    @Override
    public String expression() {
        return "";
    }
    
}