package opt;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @program: java8
 * @description: optional示例
 * @author: Darling
 * @create: 2019-08-10 22:47
 * <p>
 * 1	static <T> Optional<T> empty()
 * 返回空的 Optional 实例。
 * <p>
 * 2	boolean equals(Object obj)
 * 判断其他对象是否等于 Optional。
 * <p>
 * 3	Optional<T> filter(Predicate<? super <T> predicate)
 * 如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
 * <p>
 * <p>
 * 5	T get()
 * 如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
 * <p>
 * 6	int hashCode()
 * 返回存在值的哈希码，如果值不存在 返回 0。
 * <p>
 * 7	void ifPresent(Consumer<? super T> consumer)
 * 如果值存在则使用该值调用 consumer , 否则不做任何事情。
 * <p>
 * 8	boolean isPresent()
 * 如果值存在则方法会返回true，否则返回 false。
 * <p>
 * 9	<U>Optional<U> map(Function<? super T,? extends U> mapper)
 * 如果有值，则对其执行调用映射函数得到返回值。如果返回值不为 null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。
 * <p>
 * 4	<U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper)
 *  * 如果值存在，返回基于Optional包含的映射方法的值，否则返回一个空的Optional
 * 10	static <T> Optional<T> of(T value)
 * 返回一个指定非null值的Optional。
 * <p>
 * 11	static <T> Optional<T> ofNullable(T value)
 * 如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional。
 * <p>
 * 12	T orElse(T other)
 * 如果存在该值，返回值， 否则返回 other。
 * <p>
 * 13	T orElseGet(Supplier<? extends T> other)
 * 如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果。
 * <p>
 * 14	<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)
 * 如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常
 * <p>
 * 15	String toString()
 * 返回一个Optional的非空字符串，用来调试
 **/
public class OptionalDemo {
    public static void main(String[] args) {

        //1 创建 Optional 对象
        // 1.1可以使用静态方法 empty () 创建一个空的 Optional 对象
        Optional<String> empty = Optional.empty();
        System.out.println(empty); // 输出：Optional.empty

        // 1.2可以使用静态方法 of () 创建一个非空的 Optional 对象
        Optional<String> opt = Optional.of("沉默王二");
        System.out.println(opt); // 输出：Optional[沉默王二]
        // of () 方法的参数必须是非空的，也就是说不能为 null，否则仍然会抛出 NullPointerException。
        String name = null;
        // Optional<String> optnull = Optional.of(name);

        // 1.3可以使用静态方法 ofNullable () 创建一个即可空又可非空的 Optional 对象
        // ofNullable() 方法内部有一个三元表达式，如果为参数为 null，则返回私有常量 EMPTY；否则使用 new 关键字创建了一个新的 Optional 对象——不会再抛出 NPE 异常了。
        String name2 = null;
        Optional<String> optOrNull = Optional.ofNullable(name2);
        System.out.println(optOrNull); // 输出：Optional.empty


        Optional optional = Optional.empty();
        //  static <T> Optional<T> empty()

        //  Optional<T> filter(Predicate<? super <T> predicate)
        //如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
        Predicate predicate = i -> i.equals(1);
        optional.filter(predicate);


        // 生成了一个Optional数据
        Optional<String> maxStrOpt = Stream.of("one", "two", "three").max(String::compareToIgnoreCase);

        // 如果值存在的情况下，把数据添加到List中
        ArrayList<String> result = new ArrayList<String>();
        maxStrOpt.ifPresent(result::add);

        // 把结果映射为大写，然后取出。
        Optional<String> upperResult = maxStrOpt.map(String::toUpperCase);
        System.out.println(upperResult.get());

        // 值为空的情况下的后续处理
        maxStrOpt.orElse(""); // 添加默认值""
        maxStrOpt.orElseGet(() -> System.getProperty("user.dir")); // 通过表达式返回结果
        maxStrOpt.orElseThrow(RuntimeException::new); // 抛出异常


        Optional<String> optt=Optional.of("沉默王二");
        System.out.println(optt.isPresent());
        // 输出：false
         Optional<String> optOrNulll = Optional.ofNullable(null);
         System.out.println(optOrNulll.isPresent());// 输出：true


    }
}
