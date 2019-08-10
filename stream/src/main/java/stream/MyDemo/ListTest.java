package stream.MyDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: java8
 * @description: 实际开发中用到的
 * @author: Darling
 * @create: 2019-08-10 20:35
 **/

public class ListTest {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("赵丽颖", 30, 1, "湖北武汉市"));
        list.add(new User("蔡徐坤", null, 1, "陕西西安市"));
        list.add(new User("詹姆斯", 35, 0, "洛杉矶"));
        list.add(new User("李世民", 60, 0, "山西省太原市"));

        int sum = list.stream().map(User::getAge).filter(Objects::nonNull).mapToInt(o -> o).sum();
        System.out.println(sum);
    }
}
