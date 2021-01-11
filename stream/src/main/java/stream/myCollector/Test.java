package stream.myCollector;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @program: java8
 * @version:
 * @description:
 * @author: ling
 * @create: 2021-01-11 11:32
 **/
public class Test {
     public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome", "hello");
        Set<String> collect = list.stream().collect(new MySetCollector<String>());
        System.out.println(collect);
         // System.out.println(collect.stream().collect(new MyCollectors().));
    }
}
