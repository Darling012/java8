package stream.MyDemo;

import java.util.Arrays;
import java.util.List;

/**
 * @program: java8
 * @description: 模拟查出来的数据
 * @author: Darling
 * @create: 2019-08-10 16:35
 **/
class Data {
    static List<User> getData() {
        List<User> list = Arrays.asList(
                new User("钢铁侠", 40, 0, "华盛顿"),
                new User("钢铁侠", 40, 1, "华盛顿"),
                new User("蜘蛛侠", 20, 0, "华盛顿"),
                new User("赵丽颖", 30, 1, "湖北武汉市"),
                new User("詹姆斯", 35, 0, "洛杉矶"),
                new User("李世民", 60, 0, "山西省太原市"),
                new User("蔡徐坤", 20, 1, "陕西西安市"),
                new User("葫芦娃的爷爷", 70, 0, "山西省太原市")
        );
        return list;
    }
}
