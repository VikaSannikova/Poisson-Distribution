package example;
import javax.script.*;
import net.objecthunter.exp4j.*;
public class formula {
        private static Expression expression;

        public static void main(String[] args) {
                expression = new ExpressionBuilder("x^2/0").variables("x").build();
                double x = 2;
                System.out.println("f(x)="+f(x));
        }

        public static double f(double x) {
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

}
