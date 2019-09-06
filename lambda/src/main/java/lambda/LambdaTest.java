package lambda;

public class LambdaTest {


    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String args[]) {
        LambdaTest tester = new LambdaTest();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));

        // 不用类型声明 expression
        MathOperation subtraction = (a, b) -> a - b;
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));

        // 大括号中的返回语句 statement
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));


        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("Runoob");

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);
        greetService2.sayMessage("Google");
    }


}
