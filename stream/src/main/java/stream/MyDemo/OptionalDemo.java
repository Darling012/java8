package stream.MyDemo;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @program: java8
 * @description: optional示例
 * @author: Darling
 * @create: 2019-08-10 22:47
 *
 * 1	static <T> Optional<T> empty()
 * 返回空的 Optional 实例。
 *
 * 2	boolean equals(Object obj)
 * 判断其他对象是否等于 Optional。
 *
 * 3	Optional<T> filter(Predicate<? super <T> predicate)
 * 如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
 *
 * 4	<U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper)
 * 如果值存在，返回基于Optional包含的映射方法的值，否则返回一个空的Optional
 *
 * 5	T get()
 * 如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
 *
 * 6	int hashCode()
 * 返回存在值的哈希码，如果值不存在 返回 0。
 *
 * 7	void ifPresent(Consumer<? super T> consumer)
 * 如果值存在则使用该值调用 consumer , 否则不做任何事情。
 *
 * 8	boolean isPresent()
 * 如果值存在则方法会返回true，否则返回 false。
 *
 * 9	<U>Optional<U> map(Function<? super T,? extends U> mapper)
 * 如果有值，则对其执行调用映射函数得到返回值。如果返回值不为 null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。
 *
 * 10	static <T> Optional<T> of(T value)
 * 返回一个指定非null值的Optional。
 *
 * 11	static <T> Optional<T> ofNullable(T value)
 * 如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional。
 *
 * 12	T orElse(T other)
 * 如果存在该值，返回值， 否则返回 other。
 *
 * 13	T orElseGet(Supplier<? extends T> other)
 * 如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果。
 *
 * 14	<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)
 * 如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常
 *
 * 15	String toString()
 * 返回一个Optional的非空字符串，用来调试
 **/
public class OptionalDemo {
    public static void main(String[] args) {

//        static <T> Optional<T> empty()
//         返回空的 Optional 实例。
        Optional optional = Optional.empty();

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
    }
}
