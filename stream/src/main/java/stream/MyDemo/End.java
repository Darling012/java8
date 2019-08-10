package stream.MyDemo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

// 静态导入

/**
 * @program: java8
 * @description: 终止操作
 * @author: Darling
 * @create: 2019-08-10 16:43
 **/
public class End {
    public static void main(String[] args) {
        //1.构建我们的list
        List<User> list = Data.getData();

        /*
         * 非短路操作
         */
        // 计算类
        // 1）求用户的总人数
        long count = (long) list.size();
        long count1 = list.stream().count();
        long count2 = list.stream().collect(Collectors.counting());

        // 2）得到某一属性的最大最小值
        Optional<User> optionalUser = list.stream().max(Comparator.comparingInt(User::getAge));
        System.out.println(optionalUser.orElse(new User()));

        // 收集操作 (collect)
        // collect()方法主要用于把流转换成其他的数据类型。
        // 这里对类似Collectors.toList的方法实施了静态导入。
        // toList()默认转成ArrayList，toSet()默认转成HashSet，如果这两种数据类型都不满足要求的话，可以通过toCollectio()方法转成需要的集合类型。
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // 转换成List
        List<Integer> numberList = numbers.stream().collect(toList());
        // 转换成Set
        Set<Integer> numberSet = numbers.stream().collect(toSet());
        // 通过toCollection转成TreeSet
        TreeSet<Integer> numberTreeSet = numbers.stream().collect(Collectors.toCollection(TreeSet::new));


        // 归约
        // 可以将流中元素反复结合在一起，得到一个值
        // 1 reduce（T identity，BinaryOperator）首先，需要传一个起始值，然后，传入的是一个二元运算
        // identity 起始值 0  然后与集合中的值进行 相应的运算，再次赋值给 identity 然后 再进行运算。
        Integer sum = list.stream().map(User::getAge).reduce(0, Integer::sum);
        System.out.println(sum);

        // 2 reduce（BinaryOperator）没有起始值，则有可能结果为空，所以返回的值会被封装到Optional中。
        Optional<Integer> sum1 = list.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(sum1.orElse(-1));

        // 3 <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner);
        // 主要用于把元素转换成不同的数据类型。 accumulator是累加器，主要进行累加操作，combiner是把不同分段的数据组合起来(并行流场景)。
        Integer sum3 = Stream.of("on", "off").reduce(0, (total1, word) -> total1 + word.length(), (x, y) -> x + y);
        System.out.println(sum3);
        /*
         * 短路操作
         */

        //查找类
        // 1）allMatch（T->boolean）    and
        // 检查是否匹配所有元素。
        // 检查是不是每个人都年满18周岁了
        Boolean bool = list.stream().allMatch(user -> user.getAge() > 18);
        System.out.println(bool);

        // 2）anyMatch（T->boolean）    or
        //  检查是否至少匹配一个元素。
        // 是否有一个女生
        boolean isGirl = list.stream().anyMatch(user -> user.getSex() == 1);
        System.out.println(isGirl);

        // 3）noneMatch(T -> boolean)  not
        // 检查是否 没有 匹配所有元素。
        // 检测有没有来自巴黎的用户
        boolean isLSJ = list.stream().noneMatch(user -> user.getAddress().contains("巴黎"));
        // 打印true说明没有巴黎的用户
        System.out.println(isLSJ);

        // 4）findFirst( ):找到第一个元素
        Optional<User> optionalUser11 = list.stream().findFirst();
        System.out.println(optionalUser11.orElse(new User()));

        // 5）findAny():找到任意一个元素
        Optional<User> anyUser = list.stream().findAny();
        // 返回的也总是第一个元素
        System.out.println(anyUser.orElse(new User()));

        // 在并行流 parallelStream() 中找到的确实是任意一个元素
        Optional<User> anyParallelUser = list.parallelStream().findAny();
        System.out.println(anyParallelUser.orElse(new User()));
    }
}
