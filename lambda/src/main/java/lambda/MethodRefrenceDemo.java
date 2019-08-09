package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class Dog {
    private String name = "哮天犬";

    /**
     * 默认10斤狗粮
     */
    private int food = 10;

    public Dog() {

    }

    /**
     * 带参数的构造函数
     *
     * @param name
     */
    public Dog(String name) {
        this.name = name;
    }

    /**
     * 狗叫，静态方法
     *
     * @param dog
     */
    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    /**
     * 吃狗粮 JDK
     * <p>
     * 默认会把当前实例传入到非静态方法，参数名为this，位置是第一个；
     *
     * @param num
     * @return 还剩下多少斤
     */
    public int eat(Dog this, int num) {
        System.out.println("吃了" + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodRefrenceDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(3);

        // 方法引用
//        Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> consumer = System.out::println;
        consumer.accept("接受的数据");

        // 静态方法的方法引用 类名+方法名
        Consumer<Dog> consumer2 = Dog::bark;
        consumer2.accept(dog);

        Dog.bark(dog);
        // 非静态方法，使用对象实例的方法引用
        Function<Integer, Integer> function = dog::eat;
        // 输入 输出一样改为 一元函数
//         UnaryOperator<Integer> function = dog::eat;
//         IntUnaryOperator function = dog::eat;
        function.apply(1);
//         function.applyAsInt(1);

        // 使用类名来方法引用  非静态方法
        // 两个输入 一个输出
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
        System.out.println("还剩下" + eatFunction.apply(dog, 2) + "斤");

        // // dog置空，不影响下面的函数执行，因为java 参数是传值
        // dog = null;
        // System.out.println("还剩下" + function.applyAsInt(2) + "斤");
        //


        // 构造函数的方法引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象：" + supplier.get());

        // 带参数的构造函数的方法引用
        Function<String, Dog> function2 = Dog::new;
        System.out.println("创建了新对象：" + function2.apply("旺财"));

        // 测试java变量是传值还是穿引用
        List<String> list = new ArrayList<>();
        test(list);
        // 结果为 []
        System.err.println(list);
    }

    private static void test(List<String> a) {
        a = null;
    }
}
