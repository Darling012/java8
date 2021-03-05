> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzAwNjkxNzgxNg==&mid=2247488479&idx=2&sn=5e4d21e2ff85ba7b29a352d9d5930b89&chksm=9b074808ac70c11e45cfd9bd1c5a8924c510a9a05c93c6a09d5236ab28844c0eabb294b5b68b&mpshare=1&scene=1&srcid=0829ibPz3OinkfBuwhw8Ttwh&sharer_sharetime=1598678168226&sharer_shareid=07754c1336c3524bfffedc4dc59111b6&key=799ec3b15052ed3ccc05a1d75a1003499965c1cf40bd5b88553f63e2645384527b3c2375c1acd063e4d60f190ddf1c10654bd657e8dcf7d10c720f04a5fa179e89cbfa79baf09834a1ea26a8f710ed1e4c6598a22778454288ecc2183a6518076f64e788aadf7a692d24e2cb3495c344a0549d0c0005975d6fd20dd4a85b307d&ascene=1&uin=MTY5NjI3ODY2MQ%3D%3D&devicetype=Windows+10&version=62080079&lang=zh_CN&exportkey=AUgiQmlktMyEQkNtKdHZ6%2F4%3D&pass_ticket=Q8c%2BWbs%2FTNe0w0GO6UAnPxjDUxeSTmvwWvhbl3YMOlT4nKCADL%2F%2BTzHu8X6mRnwU)



1.  代码中存在依赖不稳定的场景，需要使用重试获取预期结果或者尝试重新执行逻辑不立即结束，比如远程接口访问，数据加载访问，数据上传校验等
    
2.  对于异常需要重试的场景，同时希望把正常逻辑和重试逻辑解耦
    
3.  对方接口不支持异步回调  
    

在平时开发中经常会遇到需要调用接口和外部服务的场景，但是有些接口服务方不能立即返回数据，而是需要处理一段时间才能返回真实的业务数据，如果没有处理完则直接返回一个中间状态的结果。

类似于 {"errorCode":"-1", "errorMsg":"处理中", "result":""} 这样的结果，然后调用方需要过段时间(一般间隔几秒种后，需要根据具体的业务确认) 再次调用才能获取真实的结果。

传统的写法如下 (伪代码)：

```java
int time = 0;
do {
    time++;
    result = testRedo(); // 调用接口
} while (null == result && time < 5);

```

对于这种场景大家或多或少都遇到过，但上面这样的写法有几个很明显的弊端：

1.   调用方需要不仅需要考虑多次调用的次数，还要考虑每次调用的间隔时间，尽量在最少调用情况下获取到最终结果（多一次请求意味着多一次网络开销，不方便实时调整）
    
2.  多次调用过程中偶尔有一次调用出现异常 (接口报错，网络异常)，如果没有异常处理就会影响剩下次数的调用，无法保证高可用
    
3.  多线程情况下上面的代码会出现并发问题，因为第一次调用的结果不一定是最早返回的，有可能后面调用的先返回，导致结果不是预期的
    
4.  性能问题，如果使用多线程要考虑线程创建销毁和切换问题  
    

当然这些问题自己实现完全可以解决，但已经有现成的轮子我们可以直接拿来用

上述场景可以考虑使用 google 的 guava-retry 工具，guava-retryer 的特点如下：  

1.  支持设置重试次数和间隔时间，支持多种复杂场景的重试策略，延迟策略
    
2.  而且支持多个异常或者自定义实体对象的重试源，让重试功能有更多的灵活性
    
3.  线程安全，我们只需要关注我们的业务逻辑实现即可
    
4.  内部使用线程池管理线程  
    
5.  基于命令模式使用链式调用，使用方便
    

pom 依赖:

```xml
<dependency>
    <groupId>com.github.rholder</groupId>
    <artifactId>guava-retrying</artifactId>
    <version>2.0.0</version>
</dependency>

```

git 地址：https://github.com/rholder/guava-retrying

下面的代码模拟接口调用，设置重试次数为 5 次，每次调用间隔为 2 秒，如果调用过程中出现异常或结果满足重试条件的则再次调用直到最大次数 (抛出异 常)：

```java
// 重试条件
Predicate<String> condition = response -> Objects.nonNull(response) 
    && "处理中".equals(response.getReturnCode());
Optional<String> response = RetryUtil.retry(
                condition, // 重试条件
                () -> invoke(request), // 重试任务（比如调用接口）
                500, // 500ms重试一次, 可以做成配置化
                3);  // 一共重试3次, 可以做成配置化
return response.orElse(null);

```

RetryUtil 是我对 guava-retrying 的封装实现，下面的代码大家可以直接拿去使用，只需要按照业务改下重试条件和重试任务以及重试间隔和次数即可：

```java
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
    try {
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
        log.error("retry fail:", e.getMessage());
    } finally {
        stopWatch.stop();
        log.info("retry execute time", stopWatch.getTime());
    }
    return result;
}

```

重试间隔时间和重试次数可以做成可配置的，方便后续根据日志记录观察调整

相关重试策略和 api 介绍：

*   AttemptTimeLimiter：单次任务执行时间限制 (如果单次任务执行超时，则终止执行当前任务)
    
*   BlockStrategies：任务阻塞策略，默认策略为：BlockStrategies.THREAD_SLEEP_STRATEGY，也就是调用 Thread.sleep ()
    
*   StopStrategy：停止重试策略，提供三种：
    
    StopAfterDelayStrategy：设定一个最长允许的执行时间，比如设定最长执行 10s，无论任务执行次数，只要重试的时候超出了最长时间，则任务终止，并返回重试异常 RetryException
    
    NeverStopStrategy：不停止，用于需要一直轮询直到返回期望结果的情况
    

        StopAfterAttemptStrategy：设定最大重试次数，如果超出最大重试次数则停止重试，并返回重试异常

*   WaitStrategy：等待时长策略 (控制时间间隔)，返回结果为下次执行时长：
    

        FixedWaitStrategy：固定等待时长策略

        RandomWaitStrategy：随机等待时长策略

        IncrementingWaitStrategy：递增等待时长策略

        ExponentialWaitStrategy：指数等待时长策略

        FibonacciWaitStrategy：斐波那契等待时长策略

        ExceptionWaitStrategy：异常时长等待策略

        CompositeWaitStrategy：复合时长等待策略

除了 google 的 retry 外，spring 框架也提供了一个重试工具：spring-retry，该工具把重试操作模板定制化。还有 RxJava 里有个 retry 的 api 也能实现类似的用法，感兴趣的同学可以研究下。

