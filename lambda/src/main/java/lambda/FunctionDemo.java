package lambda;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @link https://cloud.tencent.com/developer/article/1333532
 * @link https://segmentfault.com/a/1190000016596774
 */
public class FunctionDemo {


    public static void main(String[] args) {
        // 断言函数接口
        // 断言 也就是条件测试器 接收条件,进行测试
        // 接口定义了一个名叫test的抽象方法，它接受泛型T对象，并返回一个boolean。
        // test (条件测试) , and-or- negate(与或非) 方法
        IntPredicate predicate = i -> i > 0;
        System.out.println(predicate.test(-9));


        // 消费函数接口
        // 消费者 消费数据 接收参数,返回void  数据被消费了
        // 定义了一个名叫accept的抽象方法，它接受泛型T的对象，没有返回（void）
        // 你如果需要访问类型T的对象，并对其执行某些操作，就可以使用这个接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");

        // Function<T,R> 输入T输出R
        // 函数 有输入有输出 数据转换功能
        // 接口定义了一个叫作apply的方法，它接受一个泛型T的对象，并返回一个泛型R的对象。
        Function<Integer, String> function = i -> "i=" + i;
        System.out.println(function.apply(1));

        // 提供一个数据 Supplier<T>
        // 提供者 不需要输入,产出T 提供数据无参构造方法 提供T类型对象
        Supplier<String> supplier = () -> "supplier";
        System.out.println(supplier.get());

        // 一元函数（输出输入类型相同） UnaryOperator<T>
        UnaryOperator<String> unaryOperator = str -> str + "a";
        System.out.println(unaryOperator.apply("unaryOperator"));

        // BiFunction<T,U,R> 2个输入函数
        BiFunction<Integer, Integer, String> biFunction = (i, j) -> "" + i + j;
        System.out.println(biFunction.apply(1, 2));

        // BinaryOperator<T,T> 两元函数 （输出输入类型相同）
        BinaryOperator<String> binaryOperator = (str1, str2) -> str1 + str2;


    }

}
