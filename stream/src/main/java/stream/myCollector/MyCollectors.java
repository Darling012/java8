package stream.myCollector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * <see>https://cloud.tencent.com/developer/article/1710047</see>
 */
public class MyCollectors {

    private MyCollectors() {
    }

    /**
     * 描述：将流中的元素转换成List<T>输出
     *
     * Collector三个泛型:
     *                  <入参泛型类型，
     *                  中间结果容器类型(在这个例子中为List<T>)，
     *                  最终结果容器类型(这个例子中也是List<T>)>
     * @param <T>
     */
    public static class ToList<T> implements Collector<T, List<T>, List<T>> {

        /**
         * 创建一个接收结果的可变容器
         * 即：创建一个List<T>对象的方法
         *
         * @return
         */
        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        /**
         * 将流中的元素放入可变容器中的方法
         *  不断从流中遍历元素，然后不断的将T累加到A中
         * @return
         */
        @Override
        public BiConsumer<List<T>, T> accumulator() {
            // return (list, item) -> list.add(item);
            return List::add;
        }

        /**
         * 组合结果，当流被拆分成多个部分时，需要将多个结果合并。
         *
         * @return
         */
        @Override
        public BinaryOperator<List<T>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }


        /**
         * 最后调用：在遍历完流后将结果容器A转换为最终结果R
         * 在该例子中，combiner结果可作为最终结果，所以返回一个恒等式
         *
         * @return
         */
        @Override
        public Function<List<T>, List<T>> finisher() {
            // return x -> x;
            return Function.identity();
        }

        /**
         * 返回一个描述收集器特征的不可变集合
         * 该例子中可用的特性是：
         *                  finisher可跳过，直接将combiner结果返回。
         *                  需要保证有序
         *                  不可并发
         *
         * @return
         */
        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }
}
