package apache;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @program: 996
 * @version: 1.0.0
 * @description: 一个对象多个返回值
 *  Pair不能当作Controller层的返回值，或者入参。会出问题，因为它不是标准的javaBean，序列化和反序列化会出问题。一般用于系统内部，比如Service方法直接、工具方法之间传递数据
 *  <see><a href="https://cloud.tencent.com/developer/article/1497510></see>
 * @author: ling
 * @create: 2020-08-26 22:02
 **/
public class MultipleReturnValue {

    public static void main(String[] args) {
        Pair<Integer, Integer> pair = Pair.of(1, 10); //同ImmutablePair.of(1, 10)
        Integer left = pair.getLeft();
        Integer right = pair.getRight();
        System.out.println(left); //1
        System.out.println(right); //10
        //pair.setValue(30); //报错：java.lang.UnsupportedOperationException


        pair = MutablePair.of(1, 10);
        left = pair.getLeft();
        right = pair.getRight();
        ((MutablePair<Integer, Integer>) pair).setLeft(100);
        ((MutablePair<Integer, Integer>) pair).setRight(200);
        System.out.println(left); //100
        System.out.println(right); //200
        pair.setValue(200); //备注setValue同setRight方法


        Triple<String, Integer, String> triple = Triple.of("姓名", 10, "邮箱");
        String name = triple.getLeft(); //姓名
        Integer age = triple.getMiddle(); //10
        String mail = triple.getRight(); //邮箱
        System.out.println(name);
        System.out.println(age);
        System.out.println(mail);

    }
}
