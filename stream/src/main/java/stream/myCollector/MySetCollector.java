package stream.myCollector;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * <see https://segmentfault.com/a/1190000013102111></see>
 *
 * MySetCollector类实现了Collector接口，并指定了三个泛型，集合中收集每个元素类型为T，中间结果容器类型为Set<T>，不需要对中间结果容器类型进行转换，所以最终结果类型也是Set<T>
 * supplier()中我们返回一个HashSet作为中间结果容器，accumulator()中调用Set的add方法将一个个元素加入到集合中，全都采用方法引用的方式实现。
 * 然后combiner()对中间结果容器两两合并，finisher()中直接调用Function.identity()将合并后的中间结果容器作为最终的结果返回
 * characteristics()方法定义了收集器的特性值，UNORDERED和IDENTITY_FINISH。表示容器中的元素是无序的并且不需要进行最终的类型转换
 * @param <T>
 */
public class MySetCollector<T> implements Collector<T, Set<T>,Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        return (Set<T> s1, Set<T> s2) -> {
            s1.addAll(s2);
            return s1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        EnumSet<Characteristics> characteristicsEnumSet = EnumSet.of(Characteristics.UNORDERED,
                                                                     Characteristics.IDENTITY_FINISH);//remove IDENTITY_FINISH finisher method will be invoked
        return Collections.unmodifiableSet(characteristicsEnumSet);
    }


}
