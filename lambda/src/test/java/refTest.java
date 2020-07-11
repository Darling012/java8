/**
 * @program: java8
 * @description:
 * @author: ling
 * @create: 2020-01-13 12:03
 **/
public class refTest {

    public static void test1(String name, int age) {
        System.out.println("传入的name=" + name);
        System.out.println("传入的age=" + age);
        name = "lisi";
        age = 2;
        System.out.println("赋值后的name=" + name);
        System.out.println("赋值后的age=" + age);
    }

    public static void test2(Person person) {
        System.out.println("传入的name=" + person.getName());
        System.out.println("传入的age=" + person.getAge());
        person = new Person();
        person.setName("lisi");
        person.setAge(2);
        //    person = new Person();
        System.out.println("赋值后的name=" + person.getName());
        System.out.println("赋值后的age=" + person.getAge());
    }

    public static void main(String[] args) {
        //    String name = "zhangsan";
        //    int age = 1;
        //    test1(name,age);
        //    System.out.println("方法执行后的name=" + name);
        //    System.out.println("方法执行后的age=" + age);

        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(1);
        test2(person);
        System.out.println("方法执行后的name=" + person.getName());
        System.out.println("方法执行后的age=" + person.getAge());
    }
}
