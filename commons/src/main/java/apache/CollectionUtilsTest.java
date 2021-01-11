package apache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: 996
 * @version:
 * @description: CollectionUtils
 * @author: ling
 * @create: 2020-08-26 22:09
 **/
@Slf4j
public class CollectionUtilsTest {
    @Test
    public void test() {
        List<String> list1 = Stream.of("a", "b", "c", "d", "e").parallel().collect(Collectors.toList());
        List<String> list2 = Stream.of("a", "f", "C", "e", "g", "z").parallel().collect(Collectors.toList());

        // 并集
        Collection union = CollectionUtils.union(list1, list2);
        log.info("union:{}", union);

        // 交集
        Collection intersection = CollectionUtils.intersection(list1, list2);
        log.info("intersection:{}", intersection);

        // 交集的补集（析取）
        Collection disjunction = CollectionUtils.disjunction(list1, list2);
        log.info("disjunction:{}", disjunction);

        // 差集（扣除）list1扣除list2
        Collection subtract = CollectionUtils.subtract(list1, list2);
        log.info("subtract:{}", subtract);
    }

    @Test
    public void testEqual() {
        List<String> list1 = Stream.of("a", "b", "c", "d", "e").parallel().collect(Collectors.toList());
        List<String> list2 = Stream.of("a", "f", "C", "e", "g", "z").parallel().collect(Collectors.toList());

        // 比较值 false
        log.info("isEqualCollection:{}", CollectionUtils.isEqualCollection(list1, list2));


        List<Integer> list3 = Stream.of(1, 2, 3, 4).parallel().collect(Collectors.toList());
        List<Integer> list4 = Stream.of(1, 2, 3, 4).parallel().collect(Collectors.toList());
        // 比较值 true
        log.info("isEqualCollection:{}", CollectionUtils.isEqualCollection(list3, list4));

        class Person {
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode(callSuper = true)
        class Boy extends Person {
            String name;
        }

        List<Person> boy1 = new ArrayList<>();
        boy1.add(new Boy("阿牛"));
        List<Person> boy2 = new ArrayList<>();
        boy2.add(new Boy("阿牛"));

        // 比较集合中不同对象 false
        log.info("isEqualCollection:{}", CollectionUtils.isEqualCollection(boy1, boy2));


        Boy boy = new Boy();
        List<Boy> boy3 = new ArrayList<>();
        boy3.add(boy);
        List<Boy> boy4 = new ArrayList<>();
        boy4.add(boy);
        // 比较集合中相同对象 true
        log.info("isEqualCollection:{}", CollectionUtils.isEqualCollection(boy3, boy4));
    }

    @Test
    public void testUnmodifiable() {
        Collection<String> list = new ArrayList<>();
        // 抓换为不可变集合，添加数据会报错
        Collection<String> collection = CollectionUtils.unmodifiableCollection(list);
        collection.add("a");
        collection.add("b");
        collection.add("c");

        log.info("collection:{}", collection);
    }


    // Collections.unmodifiableCollection可以得到一个集合的镜像，它的返回结果是不可直接被改变，否则会提示错误
    // java.lang.UnsupportedOperationException
    // at org.apache.commons.collections.collection.UnmodifiableCollection.add(UnmodifiableCollection.java:75)
}
