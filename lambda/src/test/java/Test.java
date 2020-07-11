public class Test {

    public static void main(String[] args) {
        // List<Integer> list = new ArrayList<Integer>();
        // Integer a = 1;
        // list.add(a);
        // a =2;
        // System.out.println(list.get(0));

        // List<StringBuffer> llist = new ArrayList();
        // StringBuffer b = new StringBuffer("a");
        // llist.add(b);
        // b.append("c");
        // b = new StringBuffer("b");
        // System.out.println(llist.get(0));

        // List<String> llist = new ArrayList();
        // String b = "a";
        // llist.add(b);
        // b = "b";
        // System.out.println(llist.get(0));

        int num = 10;
        String str = "hello";
        num = 20;
        str = "java";

        foo1(num); // num 没有被改变
        //        System.out.println(num);
        foo2(str); // str 也没有被改变
        //        System.out.println(str);
        StringBuilder sb = new StringBuilder("iphone");
        foo3(sb); // sb 被改变了，变成了"iphone4"。
        //        System.out.println(sb);
        StringBuilder sbb = new StringBuilder("iphone");
        foo4(sbb); // sb 没有被改变，还是 "iphone"。
        System.out.println(sbb);
    }

    // 第一个例子：基本类型
    static void foo1(int value) {
        value = 100;
    }


    // 第二个例子：没有提供改变自身方法的引用类型
    static void foo2(String text) {
        text = "windows";
    }


    // 第三个例子：提供了改变自身方法的引用类型
    static void foo3(StringBuilder builder) {
        builder.append("4");
    }


    // 第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
    static void foo4(StringBuilder builder) {
        builder = new StringBuilder("ipad");
    }

}
