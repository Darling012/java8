package retry;

import com.google.common.base.Predicate;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @program: girl
 * @version:
 * @description:
 * @author: ling
 * @create: 2021-03-02 13:50
 **/
public class GuavaTest {
    public static void main(String[] args) {
        // 重试条件
        // Predicate<Integer> condition = response -> Objects.nonNull(response) && "处理中".equals(response.getReturnCode());
        Predicate<Integer> condition = i -> i > 3;
        Optional<Integer> response = GuavaRetryUtil.retry(
                // 重试条件
                condition,
               // 重试任务（比如调用接口）
                new Task()
               ,
                // 500ms重试一次, 可以做成配置化
                500,
                // 一共重试3次, 可以做成配置化
                3);
        System.out.println(response.get());

    }

}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
         Thread.sleep(3000);
        System.out.println("任务");
        return 2;
    }
}
