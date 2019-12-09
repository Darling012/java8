package lambda;

/**
 * 函数式接口里允许定义默认方法
 * 函数式接口里允许定义静态方法
 * 函数式接口里允许定义java.lang.Object里的public方法
 */
// 编译期间校验
// 加不加@FunctionalInterface对于接口是不是函数式接口没有影响，该注解知识提醒编译器去检查该接口是否仅包含一个抽象方法
@FunctionalInterface
interface Interface1 {
    int doubleNum(int i);

    default int add(int x, int y) {
        x = this.doubleNum(x);
        return x + y;
    }

    // int add(int x, int y);
    default String add() {
        return null;
    }

    String toString();

    static int sub(int x, int y) {

        return x - y;
    }

    static String pub() {
        return null;
    }
}

@FunctionalInterface
interface Interface2 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface3 extends Interface2, Interface1 {

    @Override
    default int add(int x, int y) {
        // 指明哪个父接口
        return Interface1.super.add(x, y);
    }

}

public strictfp class LambdaDemo1 {

    public static void main(String[] args) {
        Interface1 i1 = (i) -> i * 2;

        Interface1.sub(10, 3);
        System.out.println(i1.add(3, 7));
        System.out.println(i1.doubleNum(20));

        // 这种是最常见写法
        Interface1 i2 = i -> i * 2;

        Interface1 i3 = (int i) -> i * 2;

        Interface1 i4 = (int i) -> {
            System.out.println("-----");
            return i * 2;
        };

    }

}
