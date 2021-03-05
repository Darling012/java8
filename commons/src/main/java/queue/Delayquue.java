package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Delayquue {

    public static void main(String[] args) throws Exception {
        BlockingQueue<Task> delayqueue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        delayqueue.put(new Task(now+3000));
        delayqueue.put(new Task(now+4000));
        delayqueue.put(new Task(now+6000));
        delayqueue.put(new Task(now+1000));
        System.out.println(delayqueue);

        for(int i=0; i<4; i++) {
            System.out.println(delayqueue.take());
        }

    }
/**
 *  compareTo 方法必须提供与 getDelay 方法一致的排序
 */
    static class Task implements Delayed {
        long time = System.currentTimeMillis();
        public Task(long time) {
            this.time = time;
        }
        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }
        @Override
        public String toString() {
            return "" + time;
        }
    }
}
