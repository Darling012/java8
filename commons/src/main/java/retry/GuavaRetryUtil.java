package retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @program: girl
 * @version:
 * @description: Guava 重试
 * @author: ling
 * @create: 2021-03-02 13:39
 **/
@Slf4j
public class GuavaRetryUtil {
    /**
     * 根据输入的condition重复做task,在规定的次数内达到condition则返回,
     * 如果超过retryTimes则返回null, 重试次数,整个重试时间以及retry exception都会记录log
     *
     * @param condition  重试条件,比如接口返回errorCode为处理中,或不是最终需要的结果
     * @param task       重试做的任务
     * @param sleepTime  重试间隔时间,单位毫秒
     * @param retryTimes 重试次数
     * @return targetBean
     */

    public static <V> Optional<V> retry(Predicate<V> condition, Callable<V> task, int sleepTime, int retryTimes) {
        Optional<V> result = Optional.empty();
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            Retryer<V> retry = RetryerBuilder.<V>newBuilder()
                    // 默认任务执行过程中发生异常自动重试
                    .retryIfException()
                    // 重试条件（按照业务场景）
                    .retryIfResult(condition)
                    // 等待策略
                    .withWaitStrategy(WaitStrategies.fixedWait(sleepTime, TimeUnit.MILLISECONDS))
                    // 重试策略
                    .withStopStrategy(StopStrategies.stopAfterAttempt(retryTimes))
                    // 重试监听器
                    .withRetryListener(new RetryListener() {
                        @Override
                        public <V> void onRetry(Attempt<V> attempt) {
                            // 记录重试次数和异常信息
                            log.info(MessageFormat.format("{0}th retry", attempt.getAttemptNumber()));
                            if (attempt.hasException()) {
                                log.error(MessageFormat.format("retry exception:{0}", attempt.getExceptionCause()));
                            }
                        }
                    }).build();
            // 开始执行重试任务
            result = Optional.ofNullable(retry.call(task));
        } catch (Exception e) {
            log.error("retry fail:{}", e.getMessage());
        } finally {
            stopWatch.stop();
            log.info("retry execute time{}", stopWatch.getTime());
        }
        return result;
    }
}
