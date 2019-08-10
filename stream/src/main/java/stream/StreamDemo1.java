package stream;

import java.util.stream.IntStream;

/**
 * 1 Stream 自己不会存储元素
 * 2 Stream 不会改变源对象。相反，他们会返回一个持有结果的性stream 。
 * 3 Stream 操作是延迟执行的。这以为这他们会等到需要结果的时候才执行（延迟加载）
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


    }

    private static int doubleNum(int i) {
        int j = i * 2;
        System.out.println(i + "执行了乘以2=" + j);
        return j;
    }

}
