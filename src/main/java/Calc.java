import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Calc {
    BinaryOperator<Integer> plus = (x,y) -> x + y;
    BinaryOperator<Integer> divide = (x,y) -> x/y;

    UnaryOperator<Integer> pow = (x) -> x*x;
    UnaryOperator<Integer> abs = (x)-> x>0?x:x*-1;

    BinaryOperator<String> simplePlus = (x,y)-> x+y;
}
