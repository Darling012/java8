package stream.MyDemo;

import java.util.List;

/**
 * @program: java8
 * @description:
 * @author: Darling
 * @create: 2019-09-02 10:47
 **/
public class Test {
    public static void main(String[] args) {
        //1.构建我们的list
        List<User> list = Data.getData();
        Integer ageSum = list.stream().mapToInt(User::getAge).sum();

    }

    ;
}
