package stream.myCollector;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @param <T> 要收集的元素的泛型
 * @param <A> 累加器容器的类型，可变的中间结果容器类型
 * @param <R> 收集操作得到的对象类型,最终的结果容器类型
 * @author futao
 * @date 2020/9/24
 */
public class MyCustomCollector<T, A, R> implements Collector<T, A, R> {
    /**
     * 创建一个接收结果的可变容器
     * supplier()是一个创建并返回一个新的可变的结果容器的函数，也就是收集器工作时，首先要将收集的元素(也就是泛型T类型)放到supplier()创建的容器中。
     * @return a function which returns a new, mutable result container
     */
    @Override
    public Supplier<A> supplier() {
        return null;
    }

    /**
     * 将流中的元素放入可变容器中的逻辑, 方法
     * accumulator()是将一个个元素(泛型T类型)内容放到一个可变的结果容器(泛型A类型)中的函数，这个结果容器就是上面supplier()函数所创建的。
     * @return a function which folds a value into a mutable result container
     */
    @Override
    public BiConsumer<A, T> accumulator() {
        return null;
    }

    /**
     * 组合结果，当流被拆分成多个部分时，需要将多个结果合并。
     *
     * @return a function which combines two partial results into a combined
     * result
     */
    @Override
    public BinaryOperator<A> combiner() {
        return null;
    }

    /**
     * 最后调用：在遍历完流后将结果容器A转换为最终结果R
     *
     * @return a function which transforms the intermediate result to the final
     * result
     */
    @Override
    public Function<A, R> finisher() {
        return null;
    }

    /**
     * 返回一个描述收集器特征的不可变集合，用于告诉收集器时可以进行哪些优化，如并行化
     *
     * @return an immutable set of collector characteristics
     */
    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
