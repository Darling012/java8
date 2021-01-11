package guava;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: 996
 * @version:
 * @description: guava object工具类 jdk 已替代
 * @author: ling
 * @create: 2020-08-30 13:00
 *
 **/
@Slf4j
public class ObjectsTest {
    public static void main(String[] args) {
         System.out.println(Objects.equal("a", "a")); // returns true
         System.out.println(Objects.equal(null, "a")); // returns false
         System.out.println(Objects.equal("a", null)); // returns false
         System.out.println(Objects.equal(null, null)); // returns true
    //用对象的所有字段作散列[hash]运算应当更简单。Guava的Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。你可以使用Objects.hashCode(field1, field2, …, fieldn)来代替手动计算散列值。
    }
}
