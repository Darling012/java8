package stream.MyDemo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Optional<User> optionalUser1 = list.stream().collect(Collectors.maxBy(
                Comparator.comparing(User::getAge)));
        System.out.println(optionalUser1.orElse(new User()));

        // 3）求年龄总和是多少
        int totalAge = list.stream().collect(Collectors.summingInt(User::getAge));
        int totalAge1 = list.stream().mapToInt(User::getAge).sum();
        System.out.println(totalAge);
        Optional total = list.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(total.orElse(-1));
        // 经常会用BigDecimal来记录金钱，假设想得到BigDecimal的总和：
//        BigDecimal sum = list.stream() .map(User::getAge).reduce(BigDecimal.ZERO,BigDecimal::add);

        // 4）求年龄平均值
        double avgAge = list.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println(avgAge);

        /*
         * 短路操作
         */

        //查找类
        // 1）allMatch（T->boolean）
        // 检查是否匹配所有元素。
        // 检查是不是每个人都年满18周岁了
        Boolean bool = list.stream().allMatch(user -> user.getAge() > 18);
        System.out.println(bool);

        // 2）anyMatch（T->boolean）
        //  检查是否至少匹配一个元素。
        // 是否有一个女生
        boolean isGirl = list.stream().anyMatch(user -> user.getSex() == 1);
        System.out.println(isGirl);

        // 3）noneMatch(T -> boolean)
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
