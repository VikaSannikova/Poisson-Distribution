package approximation.DoneClasses;
import javax.script.*;
import net.objecthunter.exp4j.*;

import java.util.ArrayList;

public class Formula {
        String str;
        Expression expression;

        public Formula(String string) {
                str = string;
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
//                ArrayList<Integer> a = new ArrayList<>();
//                a.add(0);
//                a.add(1);
//                ArrayList<Integer> b = new ArrayList<Integer>(a);
//                System.out.println(b);
//                b.set(0,2);
//                b.set(1,3);
//                b.add(4);
//                System.out.println(a);
//                System.out.println(b);
        }

        @Override
        public String toString() {
                return str;
        }
}
