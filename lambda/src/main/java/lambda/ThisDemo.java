package lambda;

/**
 * lambda表达式的this
 *
 * @author 晓风轻
 *
 */
public class ThisDemo {

  private final String name = "ThisDemo";

  public void test() {
    // 匿名类实现
    new Thread(new Runnable() {

      private final String name = "Runnable";

      @Override
      public void run() {
        System.out.println("这里的this指向匿名类:" + this.name);
      }
    }).start();

    // lambda实现
    new Thread(() -> {
      System.out.println("这里的this指向当前的ThisDemo类:" + this.name);
    }).start();
  }

  public static void main(String[] args) {
    ThisDemo demo = new ThisDemo();
    demo.test();
  }
}
