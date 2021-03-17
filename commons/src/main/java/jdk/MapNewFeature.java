package jdk;

import com.google.common.collect.ArrayListMultimap;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @program: java8
 * @version:
 * @description: map 新特性  https://mp.weixin.qq.com/s/SzGnIXeVHTzfE6Q36sRD3g
 * @author: ling
 * @create: 2021-03-17 09:22
 **/
public class MapNewFeature {
    @Test
    public void testNullPoint() {
        // 预防空指针
        Map<String, String> map = new HashMap();
        map.put("公号", "小黑十一点半");
        map.put("主理人", "楼下小黑哥");
        // 可能存在 NPE 问题
        // System.out.println(map.get("支付").toUpperCase());

        // 等同于条件运算符的效果：Objects.isNull(value) ? "" : value;
        System.out.println(map.getOrDefault("支付", ""));
        // Apache MapUtils
        System.out.println(MapUtils.getString(map, "支付", ""));
        // 假设我们是从 POJO对象获取 Map 参数，这个时候为了防止空指针，我们就需要提前做一个空指针的判断。
        // 不过如果使用 MapUtils，那我们就不需要判断是否为 null，方法内部已经封装这个逻辑。
        // MapUtils.getString(pojo.getMap(), "支付", "");
    }

    @Test
    public void testComputeIfAbsent() {
        // 需要一个键需要映射到多个值，这个时候我们可以使用 Map<K, List<V>>这个结构。
        // 此时添加元素的时候，我们需要做一些判断，当内部元素不存在时候主动创建一个集合对象
        Map<String, List<String>> map = new HashMap();
        List<String> classify = map.get("java框架");
        if (Objects.isNull(classify)) {
            classify = new ArrayList<>();
            classify.add("Spring");
            map.put("java框架", classify);
        } else {
            classify.add("Spring");
        }
        // 如果 Map中  key 对应的 value 不存在，则会将 mappingFunction 计算产生的值作为保存为该 key 的 value，并且返回该值。否则不作任何计算，将会直接返回  key 对应的 value。
        map.computeIfAbsent("java框架", key -> new ArrayList<>())
           .add("Spring");

        // ERROR:会有 NPE 问题
        // 当 Map 中 key 对应 value 不存在的时候，putIfAbsent将会直接返回 null。
        // 而 computeIfAbsent将会返回 mappingFunction计算之后的值，像上面的场景直接返回就是 new ArrayList。
        map.putIfAbsent("java框架", new ArrayList<>())
           .add("Spring");
        // 针对上面这种一个键需要映射到多个值，其实还有一个更优秀的解决办法
        ArrayListMultimap<Object, Object> multiset = ArrayListMultimap.create();
        multiset.put("java框架", "Spring");
        multiset.put("java框架", "Mybatis");
    }

    @Test
    public void testCount() {
        // 利用map分类计数
        Map<String, Integer> countMap = new HashMap();
        Integer count = countMap.get("java");
        if (Objects.isNull(count)) {
            countMap.put("java", 1);
        } else {
            countMap.put("java", count++);
        }
        // getOrDefault
        Integer count1 = countMap.getOrDefault("java", 0);
        countMap.put("java", count1 + 1);

        //如果 java这个值在 countMap中不存在，那么将会其对应的 value 设置为 1。
        // 那如果 java 在 countMap 中存在，则会调用第三个参数 remappingFunction 函数方法进行计算。
        // remappingFunction 函数中，oldValue代表原先 countMap 中 java 的值，newValue代表我们设置第二个参数 「1」，这里我们将两者相加，刚好完成累加的需求。

        countMap.merge("java", 1, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer oldValue, Integer newValue) {
                return Integer.sum(oldValue, newValue);
            }
        });
        countMap.merge("java", 1, Integer::sum);

    }
}
