package apache;


import org.springframework.util.StopWatch;

/**
 * @program: java8
 * @version:
 * @description:
 * @author: ling
 * @create: 2021-01-18 16:01
 **/
public class StopWatchTest {
     public static void main(String[] args) throws Exception{
        testUseCurrentMills();
        testUseCurrentStopWatch();
    }

    static  void testUseCurrentMills() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end1 = System.currentTimeMillis();
        System.out.println("代码块1执行时间"+(end1 - start));
        Thread.sleep(2000);
        long end2 = System.currentTimeMillis();
        System.out.println("代码块2执行时间"+(end2 - end1));
        Thread.sleep(3000);
        long end3 = System.currentTimeMillis();
        System.out.println("代码块3执行时间"+(end3 - end2));
        System.out.println("总共执行时间"+(end3 - start));
    }
    static void testUseCurrentStopWatch()throws InterruptedException {
        StopWatch stopWatch = new StopWatch("测试代码块组");
        stopWatch.start("代码块1");
        Thread.sleep(1000);
        stopWatch.stop();
        stopWatch.start("代码块2");
        Thread.sleep(2000);
        stopWatch.stop();
        stopWatch.start("代码块3");
        Thread.sleep(3000);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
