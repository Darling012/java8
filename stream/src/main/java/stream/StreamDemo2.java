package stream;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 3.2 流的创建
 */
public class StreamDemo2 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 从集合创建
        list.stream();
        list.parallelStream();

        // 从数组创建 通过Arrays中得静态方法stream（）获取数组流
        IntStream intStream = Arrays.stream(new int[]{1, 2, 3});

        // 通过Stream类中得 of（）静态方法获取流
        Stream<String> streamStr = Stream.of("a", "b", "c");
        // 创建空流
        Stream<String> emptyStream = Stream.empty();

        // builder
        Stream.Builder<String> userStream = Stream.builder();
        userStream.accept("a");

        // 创建数字流
        IntStream.of(1, 2, 3).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        // 带边界
        IntStream.rangeClosed(1, 10).forEach(System.out::println);

        // generate()iterate()产生的都是无限流，使用limit()方截取为有限流
        //生成(无限产生对象)
        Stream<Double> stream2 = Stream.generate(() -> Math.random());
        // 创建一个元素为“hi”的无限流
        Stream<String> infiniteString = Stream.generate(() -> "hi");
        // 创建一个从0开始的递增无限流
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        //迭代（需要传入一个种子，也就是起始值，然后传入一个一元操作）
        Stream<Integer> stream1 = Stream.iterate(2, (x) -> x * 2);

        // 通过skip()方法跳过元素，concat()方法连接两个流
        Stream<Integer> skipedStream = Stream.of(1, 2, 3, 4).skip(2); // 3, 4
        Stream<String> concatedStream = Stream.concat(Stream.of("hello"), Stream.of(",world")); // hello,world

        // 使用random创建一个无限流
        new Random().ints().limit(10);
        new Random().longs().limit(10);
        new Random().doubles().limit(10);


        // 流转换为其它数据结构
        // 1. Array
        // String[] strArray1 = stream.toArray(String[]::new);
        // 2. Collection
        // List<String> list22 = stream.collect(Collectors.toList());
        // List<String> list23 = stream.collect(Collectors.toCollection(ArrayList::new));
        // Set set1 = stream.collect(Collectors.toSet());
        // Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // // 3. String
        // String str = stream.collect(Collectors.joining()).toString();
    }

}
