import org.junit.Test;

/**
 * @program: java8
 * @description: 性能测试
 * @author: Darling
 * @create: 2019-08-13 11:38
 **/
public class PerformanceTest {

    @Test
    public void test11() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < 1000000000; i++) {
            try {
                a++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long useTime = System.currentTimeMillis() - start;
        System.out.println("useTime:" + useTime);

    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        int a = 0;
        try {
            for (int i = 0; i < 1000000000; i++) {
                a++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long useTime = System.currentTimeMillis() - start;
        System.out.println(useTime);
    }

    @Test
    public void test21() {

        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < 1000000000; i++) {
            a <<= 1;
            a = a >> 1;
        }
        long useTime = System.currentTimeMillis() - start;
        System.out.println("useTime:" + useTime);
    }
}
