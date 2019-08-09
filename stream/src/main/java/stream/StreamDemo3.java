package stream;

import java.util.stream.Stream;

/**
 * 中间操作包含两类
 * 无状态操作 当前操作跟其他元素没有关系
 * 有状态操作 依赖其他元素 例如排序
 */
public class StreamDemo3 {

    public static void main(String[] args) {
        String str = "my name is 007";

        // map 生成的是个 1:1 映射，每个输入元素，都按照规则转换成为另外一个元素。
        // forEach 方法接收一个 Lambda 表达式，然后在 Stream 的每一个元素上执行该表达式。
        // Stream.of(str.split(" ")).map(ss ->  "A" + ss +"A").forEach(System.out::println);


        // filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
        Stream.of(str.split(" ")).filter(s -> s.length() > 2)
                .forEach(System.out::println);

        // // 把每个单词的长度调用出来
        Stream.of(str.split(" ")).filter(s -> s.length() > 2)
                .map(s -> s.length()).forEach(System.out::println);
        //
        // // flatMap A->B属性(是个集合), 最终得到所有的A元素里面的所有B属性集合
        // // intStream/longStream 并不是Stream的子类, 所以要进行装箱 boxed
        // Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed())
        // 		.forEach(i -> System.out.println((char) i.intValue()));
        //
        // // peek 用于debug. 是个中间操作, forEach 是终止操作forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。
        System.out.println("--------------peek------------");
        Stream.of(str.split(" ")).peek(System.out::println)
                .forEach(System.out::println);
        //
        // // limit 使用, 主要用于无限流
        // new Random().ints().filter(i -> i > 100 && i < 1000).limit(10)
        // 		.forEach(System.out::println);

    }

}
