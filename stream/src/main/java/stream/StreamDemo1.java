package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 3.1
 * 1 创建stream：从数据源(如集合、数组等)获取一个流。
 * 2 中间操作：对数据一连串处理，每次处理后结果仍为一个流。
 * 3 终止操作：执行中间操作链，并产生最终结果。
 * <p>
 * 无存储。Stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
 * 为函数式编程而生。对Stream的任何修改都不会修改背后的数据源，比如对Stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新Stream。
 * 可消费性。Stream只能被“消费”一次，一旦遍历过就会失效，就像容器的Iterator迭代器那样，想要再次遍历必须重新生成。
 * 保护数据源对Stream中任何元素的修改都不会导致数据源被修改，比如过滤删除流中的一个元素，再次遍历该数据源依然可以获取该元素。
 * 流操作的过程中 不能修改数据源
 * filter, map 操作串联起来形成一系列中间运算，如果没有一个终端操作（如collect）这些中间运算永远也不会被执行。
 */
public class StreamDemo1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        // 外部迭代
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println("结果为：" + sum);

        // 使用stream的内部迭代
        // map就是中间操作（返回stream的操作）
        // 返回stream流就是中间操作 否则就是终止
        // sum就是终止操作
        int sum22 = IntStream.of(nums).sum();
        System.out.println("=========" + sum22);

        int sum2 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
        System.out.println("结果为：" + sum2);

        System.out.println("惰性求值就是终止操作没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);

        // 流程图
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "HelloWorld", "Hollis");
        strings = strings.stream().filter(string -> string.startsWith("Hollis")).collect(Collectors.toList());
        //Hollis, HollisChuang, Hollis666, Hollis
        System.out.println(strings);

    }

    private static int doubleNum(int i) {
        int j = i * 2;
        System.out.println(i + "执行了乘以2=" + j);
        return j;
    }

}
