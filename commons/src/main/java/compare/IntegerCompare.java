package compare;

public class IntegerCompare {
    public static void main(String[] args) {
         Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e= 321;
        Integer f= 321;
        Long g = 3L;
        //1.包装类比较，不会自动拆包，但是Integer中会有一个cache 存储-128到127的数，所以c与d的地址值相同。
        System.out.println(c == d);
        //2.地址值比较，没用到cache
        System.out.println(e == f); //2
        //当 '=='时，右侧发生自动拆包，所以其实是int值在比较
        System.out.println(c == (a+b)); //3
        //a+b 时拆包成int，传入Integer的equals方法进行自动装包。equals方法内是值比较。
        System.out.println(c.equals(a+b));//4
        //会拆包成基础数据类型比较
        System.out.println(g == (a+b)); //5
        //包装类的equals 会判断类型，Long.equals(Object object)中判断类型不符合，返回false。
        System.out.println(g.equals(a+b)); //6

        System.out.println("==========");
         Long h = 1L;
        Integer j = 1;
        System.out.println(h.equals(1)); //7 a.equals(1)时，int 1 装包成Integer，自然和Long不同类型。
        System.out.println(h.equals(1L));
        System.out.println(h.equals(j));

    }
}
