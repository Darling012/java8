package stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 终止操作分为两类
 * 非短路操作
 * 短路操作 不需要等待所有数据都计算完就可以结束流
 * <p>
 * 会消耗流，产生一个最终结果也就是说，在最终操作之后，不能再次使用流，也不能在使用任何中间操作，否则将抛出异常：
 */
public class StreamDemo4 {

    public static void main(String[] args) {
        String str = "my name is 007";

        // 使用并行流
        str.chars().parallel().forEach(i -> System.out.print((char) i));
        System.out.println();
        // 使用 forEachOrdered 保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));

        // 收集到list
        System.out.println();
        List<String> list = Stream.of(str.split(" "))
                .collect(Collectors.toList());
        System.out.println(list);

        // 使用 reduce 拼接字符串
        System.out.println();
        Optional<String> letters = Stream.of(str.split(" "))
                .reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(letters.orElse(""));

        // 带初始化值的reduce
        System.out.println();
        String reduce = Stream.of(str.split(" ")).reduce("",
                (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce);

        // 计算所有单词总长度
        System.out.println();
        Integer length = Stream.of(str.split(" ")).map(s -> s.length())
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(length);


        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

        // max 的使用
        Optional<String> max = Stream.of(str.split(" "))
                .max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());

        // 使用 findFirst 短路操作 总是返回 Stream 的第一个元素，或者空
        OptionalInt findFirst = new Random().ints().findFirst();
        System.out.println(findFirst.getAsInt());


        // limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）。
        System.out.println("-----------------------limit-----------------");
        Stream.of(str.split(" ")).limit(2).forEach(System.out::println);
        System.out.println("-----------------------skip	-----------------");
        Stream.of(str.split(" ")).skip(2).forEach(System.out::println);
    }

}
