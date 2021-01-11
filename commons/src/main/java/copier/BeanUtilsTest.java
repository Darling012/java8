package copier;

import org.springframework.beans.BeanUtils;

/**
 * @program: 996
 * @description:
 * @author: ling
 * @create: 2020-02-26 21:25
 **/
public class BeanUtilsTest {
    public static void main(String[] args) {
        CopyTest1 test1 = new CopyTest1();
        test1.name = "hahaha";
        CopyTest1.InnerClass innerClass = new CopyTest1.InnerClass();
        innerClass.InnerName = "hohoho";
        test1.innerClass = innerClass;

        System.out.println(test1.toString());
        CopyTest2 test2 = new CopyTest2();
        BeanUtils.copyProperties(test1, test2);

        System.out.println(test2.toString());
    }
}
