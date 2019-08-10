package stream.MyDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description: 中间操作
 * @author: Darling
 * @create: 2019-08-10 16:38
 **/
public class Middle {
    public static void main(String[] args) {
        //1.构建我们的list
        List<User> list = Data.getData();


        /*  无状态操作 */

        // 映射
        // 1）map(T->R) map是将T类型的数据转为R类型的数据
        // 接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新元素
        list.stream().map(User::getAddress).distinct().collect(Collectors.toList()).forEach(System.out::println);

        // flatMap(T -> Stream<R>) 将流中的每一个元素 T 映射为一个流，再把每一个流连接成为一个流。
        // 这里原集合中的数据由逗号分割，使用split进行拆分后，得到的是Stream<String[]>，字符串数组组成的流，要使用flatMap的Arrays::stream
        //将Stream<String[]>转为Stream<String>,然后把流相连接，组成了完整的唱、跳、rap、篮球和music。
        List<String> flatList = new ArrayList<>();
        flatList.add("唱,跳");
        flatList.add("rape,篮球,music");
        flatList.stream().map(s -> s.split(",")).flatMap(Arrays::stream).collect(Collectors.toList()).forEach(System.out::println);

        // 过滤类操作
        // 2）filter 过滤(T-> boolean)
        // filter里面，->箭头后面跟着的是一个boolean值，可以写任何的过滤条件，就相当于sql中where后面的东西，换句话说，能用sql实现的功能这里都可以实现
        // 过滤年龄在40岁以上的用户
        list.stream().filter(u -> u.getAge() > 40).collect(Collectors.toList()).forEach(System.out::println);

        // 还有peek  unordered

        // 有状态操作

        // 3）distinct 去重 和sql中的distinct关键字很相似
        // 通过流所生成元素的hashCode（）和equals（）去除重复元素
        list.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);

        // 4）sorted排序
        // 一种是不传任何参数，叫自然排序，还有一种需要传Comparator 接口参数，叫做定制排序
//        list.stream().sorted()
        list.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList()).forEach(System.out::println);


        // 5）limit（） 返回前n个元素
        // 可以配合其他得中间操作，并截断流，取到相应的元素个数，这不会往下执行，可以提高效率
        list.stream().limit(4).collect(Collectors.toList()).forEach(System.out::println);

        // 6）skip() 跳过前n个元素。
        // skip（n），返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空，与limit（n）互补
        list.stream().skip(4).collect(Collectors.toList()).forEach(System.out::println);


    }
}
