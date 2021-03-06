package compare;

/**
 * @author daling
 */
public class Box {
     public static void main(String[] args) {
        Integer a = new Integer(1226);
        Integer b = new Integer(1226);
        Long c = new Long(1226);
        /* compareTo返回值：若a>b则返回1；若a==b则返回0；若a<b则返回-1 */
        int result = a.compareTo(b);

        System.out.println(a > b);
        System.out.println(a == b);
        System.out.println(a > b);
        System.out.println(result);
         System.out.println(a.intValue() == b.intValue());
         System.out.println(a.equals(b));
         //用equals方法时要注意类型相同
         System.out.println(a.equals(c));
         System.out.println(a.intValue() == c.intValue());
         /**
          * 通过对比字符串比较来理解，基本类型100通过包装类Integer包装后生产一个Integer对象的引用a，
          * 而“==”使用来判断两个操作数是否有相等关系。如果是基本类型就直接判断其值是否相等。
          * 若是对象就判断是否是同一个对象的引用，显然我们new了两个不同的对象。
          * 但注意：
          * 对于"<",">" 只是用来判断两个基本类型的数值的大小关系。在进行（a<b）运算时，
          *
          * 实际上是根据其intValue方法的返回对应的数值来进行比较的。因此返回肯定是false.
          *
          * 知道问题原因，解决起来就容易了。两种方法：
          * 第一种： a.intValue()==b.intValue();
          * 第二种： a.compareTo(b);//返回-1代码（a<b），返回0代表（a==b）,返回1代表（a>b）
          */
    }
}
