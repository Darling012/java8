package jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * @program: 996
 * @version:
 * @description:
 * @author: ling
 * @create: 2020-08-26 22:55
 * <see><a href="https://blog.csdn.net/qq_26584263/article/details/102725025></see>
 * <see><a href="https://blog.csdn.net/xinghuo0007/article/details/78895577></see>
 * <see><a href="https://blog.csdn.net/weixin_38478780/article/details/107806504></see>
 **/
@Slf4j
public class ObjectsTest {


    @Test
    public void equalsTest() {
        //比较两个对象是否相等（首先比较内存地址，然后比较a.equals(b)，只要符合其中之一返回true）
        // public static boolean equals(Object a, Object b);

    }

    @Test
    public void deepEqualsTest() {
        //深度比较两个对象是否相等(首先比较内存地址,相同返回true;如果传入的是数组，则比较数组内的对应下标值是否相同)
        // public static boolean deepEquals(Object a, Object b);

        String str1 = "hello";
        String str2 = "hello";
        //传入对象
        boolean deepEquals = Objects.deepEquals(str1, str2);
        //Objects.deepEquals(str1, str2) ?  true
        System.out.println("Objects.deepEquals(str1, str2) ?  " + deepEquals);
        int[] arr1 = {1, 2};
        int[] arr2 = {1, 2};
        //传入数组
        deepEquals = Objects.deepEquals(arr1, arr2);
        //Objects.deepEquals(arr1, arr2) ?  true
        System.out.println("Objects.deepEquals(arr1, arr2) ?  " + deepEquals);
    }

    @Test
    public void hashCodeTest() {
        //返回对象的hashCode，若传入的为null，返回0
        // public static int hashCode(Object o);

        //传入对象
        String str1 = "hello";
        int hashCode = Objects.hashCode(str1);
        //Objects.hashCode(str1) ?  99162322
        System.out.println("Objects.hashCode(str1) ?  " + hashCode);

        //传入null
        hashCode = Objects.hashCode(null);
        //Objects.hashCode(null) ?  0
        System.out.println("Objects.hashCode(null) ?  " + hashCode);
    }

    @Test
    public void hashTest() {
        //返回传入可变参数的所有值的hashCode的总和（这里说总和有点牵强，具体参考Arrays.hashCode()方法）
        // public static int hash(Object... values);

        int  a  = 100;
        //传入对象
        int hashCode = Objects.hashCode(a);
        //Objects.hashCode(str1) ?  100
        System.out.println("Objects.hashCode(str1) ?  "+ hashCode);
        //输入数组
        int[] arr = {100,100};
        hashCode = Objects.hash(arr);
        //Objects.hashCode(arr) ?  1555093793
        System.out.println("Objects.hashCode(arr) ?  "+ hashCode);
    }

    @Test
    public void toStringTest() {
        //返回对象的String表示，若传入null，返回null字符串
        // public static String toString (Object o)
    }

    @Test
    public void toStringDefaultTest() {
        //返回对象的String表示，若传入null，返回默认值nullDefault
        // public static String toString(Object o, String nullDefault)
    }

    @Test
    public void compareTest() {
        //使用指定的比较器c 比较参数a和参数b的大小（相等返回0，a大于b返回整数，a小于b返回负数）
        // public static <T> int compare(T a, T b, Comparator<? super T> c)

         int a = 10;
        int b = 11;
        int compare = Objects.compare(a, b, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        // compare = -1
        System.out.println(" compare = "+ compare);
    }

    @Test
    public void requireNonNullTest() {
        //如果传入的obj为null抛出NullPointerException,否者返回obj
        // public static <T> T requireNonNull(T obj)
        String b = "a";
        log.info(requireNonNull(b));
        String a = null;
        log.info(requireNonNull(a));
    }

    @Test
    public void requireNonNullStringTest() {
        //如果传入的obj为null抛出NullPointerException并可以指定错误信息message,否者返回obj
        // public static <T> T requireNonNull(T obj, String message)
        String a = null;
        log.info(requireNonNull(a, "不能为空"));
    }

    // -----------------------------以下是jdk8新增方法---------------------------
    @Test
    public void isNullTest() {
        //判断传入的obj是否为null，是返回true,否者返回false
        // public static boolean isNull (Object obj)
        //  意义在于用在Predicate 中 filter(Objects::isNull)
        String a = null;
        Boolean b = Objects.isNull(a);
        log.info(b.toString());
    }

    @Test
    public void nonNullTest() {
        //判断传入的obj是否不为null，不为空返回true,为空返回false （和isNull()方法相反）
        // public static boolean nonNull (Object obj)
        // filter(Objects::nonNull)
        String a = null;
        Boolean b = Objects.nonNull(a);
        log.info(b.toString());
    }

    @Test
    public void requireNonNullSupplierTest() {
        //如果传入的obj为null抛出NullPointerException并且使用参数messageSupplier指定错误信息,否者返回obj
        // public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier)

        String a = null;
        log.info(requireNonNull(a, () ->
                                        "不能为空"
                               ));
    }
}
