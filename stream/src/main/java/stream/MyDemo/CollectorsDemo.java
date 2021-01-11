package stream.MyDemo;

import org.apache.commons.collections4.MapUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

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
        List<String> userList = list.stream().map(User::getName).collect(toList());
        System.out.println(userList);

        // 2 将流转换成HashSet
        // hashSet会去重复
        Set<String> userSet = list.stream().map(User::getName).collect(toSet());
        System.out.println(userSet);
        // 转换为map
        // Map<String,User> userMap = list.stream().collect(toMap(User::getId,user -> user));
        Map<String, User> userMapId = list.stream().collect(toMap(User::getId, identity()));
        MapUtils.verbosePrint(System.out, "收集到MapId", userMapId);
        Map<String, User> userMapName = list.stream().collect(toMap(User::getName, identity(), (t1, t2) -> t1));
        Map<String, User> userLinkedHashMapName = list.stream().collect(
                toMap(User::getName, identity(), (t1, t2) -> t1, LinkedHashMap::new));
        Map<String, User> userConcurrentMapMapName = list.stream().collect(
                toConcurrentMap(User::getName, identity(), (t1, t2) -> t1));

        MapUtils.verbosePrint(System.out, "收集到MapName", userMapName);
        // 3 将流转换成其他集合
        Set<String> userSet1 = list.stream().map(User::getName).collect(toCollection(LinkedHashSet::new));
        System.out.println(userSet1);

        // 4 Collectors.counting() 元素个数
        Long collectCount = list.stream().map(User::getAge).distinct().collect(counting());
        Long collectCount1 = list.stream().map(User::getAge).distinct().count();
        //        Long collectCount1 = list.stream().count();
        //        Long collectCount2 = (long) list.size();

        // 5 将流转换为其他形式 ， 接受一个collectors接口的实现，用于给Stream中元素做汇总的方法

        // 1 对元素进行汇总方法
        // 一次性得到元素的个数、总和、最大值、最小值
        DoubleSummaryStatistics collect = list.stream().collect(summarizingDouble(User::getAge));
        IntSummaryStatistics collectInt = list.stream().collect(summarizingInt(User::getAge));
        LongSummaryStatistics collectLong = list.stream().collect(summarizingLong(User::getAge));
        System.out.println("最大值为" + collect.getMax());
        System.out.println("平均值为" + collect.getAverage());
        System.out.println("size为" + collect.getCount());
        System.out.println("最小值为" + collect.getMin());
        System.out.println("sum值为" + collect.getSum());

        // 3）求年龄总和是多少
        int totalAge = list.stream().collect(summingInt(User::getAge));
        Long totalAgeLong = list.stream().collect(summingLong(User::getAge));
        Double totalAged = list.stream().collect(summingDouble(User::getAge));

        int totalAge1 = list.stream().mapToInt(User::getAge).sum();
        System.out.println(totalAge);
        Optional total = list.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(total.orElse(-1));
        // 经常会用BigDecimal来记录金钱，假设想得到BigDecimal的总和：
        //        BigDecimal sum = list.stream() .map(User::getAge).reduce(BigDecimal.ZERO,BigDecimal::add);


        // 元素转换为其他形式
        // 字符串拼接
        String names = list.stream().map(User::getName).collect(joining());
        String namess = list.stream().map(User::getName).collect(joining(","));
        System.out.println(names);

        // 平均值
        Double avgAgeInt = list.stream().collect(averagingInt(User::getAge));
        Double avgAgeLlong = list.stream().collect(averagingLong(User::getAge));
        Double avgAgeDouble = list.stream().collect(averagingDouble(User::getAge));

        //  Collectors.maxBy（） 求最大值
        Optional<User> optionalUser1 = list.stream().collect(maxBy(
                Comparator.comparing(User::getAge)));
        System.out.println(optionalUser1.orElse(new User()));
        //  Collectors.minBy（） 求最小值

        // 数量
        Long count = list.stream().collect(counting());
        System.out.println(count);

        // 分组
        // groupingBy和partitioningBy不同的一点是，它的输入是一个Function，这样返回结果的Map中的Key就不再是boolean型，而是符合条件的分组值
        // 1）根据用户所在城市进行分组
        // key为不重复的城市名，value为属于该城市的用户列表。已经实现了分组
        Map<String, List<User>> cityMap = list.stream().collect(groupingBy(User::getAddress));
        MapUtils.verbosePrint(System.out, "城市分组", cityMap);
        // 按照城市进行分组，再统计数量
        Map<String, Long> sizeMap = list.stream().collect(groupingBy(User::getAddress, counting()));
        MapUtils.verbosePrint(System.out, "城市分组后size", sizeMap);
        // 按照城市进行分组，再统计年龄之和
        Map<String, Integer> ageSumMap = list.stream().collect(groupingBy(User::getAddress, summingInt(User::getAge)));
        MapUtils.verbosePrint(System.out, "城市分组后每个组年龄之和", ageSumMap);
        // 按照城市进行分组,再找出年龄最大的人
        Map<String, Optional<User>> maxAgeMap = list.stream().collect(
                groupingBy(User::getAddress, maxBy(Comparator.comparing(User::getAge))));
        MapUtils.verbosePrint(System.out, "城市分组后每个组年龄最大的", maxAgeMap);
        // 按照城市进行分组,再找出名字最长的人
        Map<String, Optional<String>> maxAgeMap1 = list.stream().collect(
                groupingBy(User::getAddress, mapping(User::getName, maxBy(Comparator.comparing(String::length)))));
        MapUtils.verbosePrint(System.out, "城市分组后每个组最长的名字", maxAgeMap1);
        // 按照城市进行分组,再找出年龄统计信息
        Map<String, IntSummaryStatistics> statisticsMap = list.stream().collect(
                groupingBy(User::getAddress, summarizingInt(User::getAge)));
        MapUtils.verbosePrint(System.out, "城市分组后每个组年龄信息", statisticsMap);
        // 按照城市进行分组,再把每个人名字拼接起来
        Map<String, String> sumName1 = list.stream().collect(groupingBy(User::getAddress, reducing("", User::getName,
                                                                                                   (s, t) -> s
                                                                                                           .length() == 0 ? t : s + ", " + t)));
        MapUtils.verbosePrint(System.out, "城市分组后每个人名拼接1", sumName1);
        Map<String, String> sumName2 = list.stream()
                                           .collect(groupingBy(User::getAddress, mapping(User::getName, joining("-"))));
        MapUtils.verbosePrint(System.out, "城市分组后每个人名拼接2", sumName2);
        // 按城市年龄分组 统计
        Map<String, Long> nameAddre = list.stream().collect(
                groupingBy(user -> user.getAge() + "-" + user.getAddress(), counting()));

        // 2）二级分组，先根据城市分组再根据性别分组
        Map<String, Map<Integer, List<User>>> group = list.stream().collect(
                groupingBy(User::getAddress, // 一级分组，按所在地区
                           groupingBy(User::getSex))); // 二级分组，按性别
        MapUtils.verbosePrint(System.out, "二级分组", group);

        // 3）按城市分组并统计人数
        Map<String, Long> cityCountMap = list.stream()
                                             .collect(groupingBy(User::getAddress, counting()));
        MapUtils.verbosePrint(System.out, "城市人数", cityCountMap);

        // 4）先进行过滤再分组
        Map<String, Long> map = list.stream().filter(user -> user.getAge() >= 60)
                                    .collect(groupingBy(User::getAddress, counting()));
        MapUtils.verbosePrint(System.out, "30岁以上城市人数", map);


        // 5）partitioningBy 分区
        // 分区与分组的区别在于，分区是按照 true 和 false 来分的，因此partitioningBy 接受的参数的 lambda 也是 T -> boolean
        // 会根据条件对流进行分区操作，返回一个Map，Key是boolean型，Value是对应分区的List，也就是说结果只有符合条件和不符合条件两种
        //根据年龄是否小于等于30来分区
        Map<Boolean, List<User>> part = list.stream()
                                            .collect(partitioningBy(user -> user.getAge() <= 30));
        MapUtils.verbosePrint(System.out, "30岁分组", part);
        // 条件分组 List 按年龄分成三组:0<age<=20, 20<age<=40, 40<age.
        Map<String, List<User>> tripleUsers = list.stream()
                                                  .collect(Collectors.groupingBy(user -> {
                                                      String key;
                                                      if (user.getAge() <= 20) {
                                                          key = "less20";
                                                      } else if (user.getAge() <= 40) {
                                                          key = "less40";
                                                      } else {
                                                          key = "more40";
                                                      }
                                                      return key;
                                                  }, Collectors.toList()));

        System.out.println(tripleUsers);
        //{more40=[Kate, Lio], less40=[Tom, Leo], less20=[Jim, John]}

    }

    /**
     * BigDecimal求和与求平均数
     *
     * @param bigDecimals
     * @param roundingMode
     * @return
     */
    public BigDecimal average(List<BigDecimal> bigDecimals, RoundingMode roundingMode) {
        BigDecimal sum = bigDecimals.stream()
                                    .map(Objects::requireNonNull)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(bigDecimals.size()), roundingMode);
    }

    /**
     * 条件去重
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        // putIfAbsent方法主要是在向ConcurrentHashMap中添加键—值对的时候，它会先判断该键值对是否已经存在。
        // （1）如果是新的记录，那么会向map中添加该键值对，并返回null。
        // （2）如果已经存在，那么不会覆盖已有的值，直接返回已经存在的值。
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

}
