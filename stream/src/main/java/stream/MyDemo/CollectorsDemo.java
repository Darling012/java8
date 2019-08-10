package stream.MyDemo;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description: Collectors类操作
 * @author: Darling
 * @create: 2019-08-10 16:59
 **/
public class CollectorsDemo {
    public static void main(String[] args) {
        //1.构建我们的list
        List<User> list = Data.getData();

        // 3 收集 collect（将流转换为其他形式。接收一个Collector接口得实现，用于给其他Stream中元素做汇总的方法）
        // Collector接口中方法得实现决定了如何对流执行收集操作（如收集到List，Set，Map）。
        // Collectors实用类提供了很多静态方法，可以方便地创建常见得收集器实例。

        // 1 Collectors.toList（） 将流转换成List
        List<String> userList = list.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(userList);

        // 2 将流转换成HashSet
        // hashSet会去重复
        Set<String> userSet = list.stream().map(User::getName).collect(Collectors.toSet());
        System.out.println(userSet);

        // 3 将流转换成其他集合
        Set<String> userSet1 = list.stream().map(User::getName).collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(userSet1);

        // 4 Collectors.counting() 元素个数
        Long collectCount = list.stream().map(User::getAge).distinct().collect(Collectors.counting());
        Long collectCount1 = list.stream().map(User::getAge).distinct().count();
//        Long collectCount1 = list.stream().count();
//        Long collectCount2 = (long) list.size();

        // 5 将流转换为其他形式 ， 接受一个collectors接口的实现，用于给Stream中元素做汇总的方法

        // 1 对元素进行汇总方法
        DoubleSummaryStatistics collect = list.stream().collect(Collectors.summarizingDouble(User::getAge));
//        IntSummaryStatistics collect2 = list.stream().collect(Collectors.summarizingInt(User::getAge));
        System.out.println("最大值为" + collect.getMax());
        System.out.println("平均值为" + collect.getAverage());
        System.out.println("size为" + collect.getCount());
        System.out.println("最小值为" + collect.getMin());
        System.out.println("sum值为" + collect.getSum());

        // 元素转换为其他形式
        String names = list.stream().map(User::getName).collect(Collectors.joining());
        String namess = list.stream().map(User::getName).collect(Collectors.joining(","));

        // 平均值
        Double avgAgeInt = list.stream().collect(Collectors.averagingInt(User::getAge));
        Double avgAgeLlong = list.stream().collect(Collectors.averagingLong(User::getAge));
        Double avgAgeDouble = list.stream().collect(Collectors.averagingDouble(User::getAge));

        //  Collectors.maxBy（） 求最大值
        //  Collectors.minBy（） 求最小值
    }
}
