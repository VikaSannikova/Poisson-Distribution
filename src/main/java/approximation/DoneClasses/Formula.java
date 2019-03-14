package approximation.DoneClasses;
import javax.script.*;
import net.objecthunter.exp4j.*;
public class Formula {
        Expression expression;

        public Formula(String string) {
               expression = new ExpressionBuilder(string).variables("x").build();
        }

        public  double f(double x) {
                try{
                        return expression.setVariable("x",x).evaluate();
                }catch (Throwable cause){
                        if(cause instanceof ArithmeticException && "Division by zero!".equals(cause.getMessage()))
                                return Double.POSITIVE_INFINITY;
                        else{
                                return Double.NaN;
                        }
                }
        }
        public static void main(String[] args) {
                Formula formula = new Formula("x^2");
                //formula.expression = new ExpressionBuilder("x^2/0").variables("x").build();
                double x = 2;
                System.out.println("f(x)="+formula.f(x));
        }



}
