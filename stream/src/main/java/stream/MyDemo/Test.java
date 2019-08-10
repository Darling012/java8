package stream.MyDemo;

import org.apache.commons.collections4.MapUtils;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description: List 集合steam流操作
 * @author: Darling
 * @create: 2019-08-10 14:00
 **/
public class Test {
    public static void main(String[] args) {
        //1.构建我们的list
        List<User> list = Data.getData();

        // 5）一次性得到元素的个数、总和、最大值、最小值
        IntSummaryStatistics statistics = list.stream().collect(Collectors.summarizingInt(User::getAge));
        System.out.println(statistics);

        // 6）字符串拼接
        // 将用户的姓名连成一个字符串并用逗号分割。
        String names = list.stream().map(User::getName).collect(Collectors.joining(","));
        System.out.println(names);

        // 分组

        // 1）根据用户所在城市进行分组
        // key为不重复的城市名，value为属于该城市的用户列表。已经实现了分组
        Map<String, List<User>> cityMap = list.stream().collect(Collectors.groupingBy(User::getAddress));
        MapUtils.verbosePrint(System.out, "分组", cityMap);

        // 2）二级分组，先根据城市分组再根据性别分组
        Map<String, Map<Integer, List<User>>> group = list.stream().collect(
                Collectors.groupingBy(User::getAddress, // 一级分组，按所在地区
                        Collectors.groupingBy(User::getSex))); // 二级分组，按性别
        MapUtils.verbosePrint(System.out, "二级分组", group);

        // 3）按城市分组并统计人数
        Map<String, Long> cityCountMap = list.stream()
                .collect(Collectors.groupingBy(User::getAddress, Collectors.counting()));
        MapUtils.verbosePrint(System.out, "城市人数", cityCountMap);

        // 4）先进行过滤再分组
        Map<String, Long> map = list.stream().filter(user -> user.getAge() >= 60)
                .collect(Collectors.groupingBy(User::getAddress, Collectors.counting()));
        MapUtils.verbosePrint(System.out, "30岁以上城市人数", map);


        // 5）partitioningBy 分区
        // 分区与分组的区别在于，分区是按照 true 和 false 来分的，因此partitioningBy 接受的参数的 lambda 也是 T -> boolean
        //根据年龄是否小于等于30来分区
//        Map<Boolean, List<User>> part = list.stream()
//                .collect(partitioningBy(user -> user.getAge() <= 30));
//        MapUtils.verbosePrint(System.out, "30岁分组", part);


        // 归约
        // 可以将流中元素反复结合在一起，得到一个值
        // 1 reduce（T identity，BinaryOperator）首先，需要传一个起始值，然后，传入的是一个二元运算
        // identity 起始值 0  然后与集合中的值进行 相应的运算，再次赋值给 identity 然后 再进行运算。
        Integer sum = list.stream().map(User::getAge).reduce(0, Integer::sum);
        System.out.println(sum);

        // 2 reduce（BinaryOperator）没有起始值，则有可能结果为空，所以返回的值会被封装到Optional中。
        Optional<Integer> sum1 = list.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(sum1.orElse(-1));

    }

}
