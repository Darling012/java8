package stream.MyDemo;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @program: java8
 * @description: optional示例
 * @author: Darling
 * @create: 2019-08-10 22:47
 **/
public class OptionalDemo {
    public static void main(String[] args) {
        // 生成了一个Optional数据
        Optional<String> maxStrOpt = Stream.of("one", "two", "three").max(String::compareToIgnoreCase);

        // 如果值存在的情况下，把数据添加到List中
        ArrayList<String> result = new ArrayList<String>();
        maxStrOpt.ifPresent(result::add);

        // 把结果映射为大写，然后取出。
        Optional<String> upperResult = maxStrOpt.map(String::toUpperCase);
        System.out.println(upperResult.get());

        // 值为空的情况下的后续处理
        maxStrOpt.orElse(""); // 添加默认值""
        maxStrOpt.orElseGet(() -> System.getProperty("user.dir")); // 通过表达式返回结果
        maxStrOpt.orElseThrow(RuntimeException::new); // 抛出异常
    }
}
