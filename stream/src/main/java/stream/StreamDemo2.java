package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        // 创建数字流
        IntStream.of(1, 2, 3);
        // 带边界
        IntStream.rangeClosed(1, 10);


        //迭代（需要传入一个种子，也就是起始值，然后传入一个一元操作）
        Stream<Integer> stream1 = Stream.iterate(2, (x) -> x * 2);

        //生成(无限产生对象)
        Stream<Double> stream2 = Stream.generate(() -> Math.random());

        // 使用random创建一个无限流
        new Random().ints().limit(10);
        Random random = new Random();

        // 自己产生流
        Stream.generate(() -> random.nextInt()).limit(20);


        // 构造流的几种常见方法
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list1 = Arrays.asList(strArray);
        stream = list.stream();

        // 数值流的创建
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);


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
