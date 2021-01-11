package jdk;

import java.math.BigDecimal;

/**
 * @program: 996
 * @version:
 * @description:
 * @author: ling
 * @create: 2020-08-24 22:45
 * <p>
 * BigDecimal BigDecimal(double d); //不允许使用
 * BigDecimal BigDecimal(String s); //常用,推荐使用
 * static BigDecimal valueOf(double d); //常用,推荐使用
 * BigDecimal类型变量比较大小时用compareTo方法，判断变量值是否为0，与BigDecimal.ZERO比较大小。
 * BigDecimal作除法时，除了要考虑除数是否为0，更要考虑是否能除尽的问题，直接调用BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)方法做除法可以避免除不尽的问题。
 **/
public class BigDecimalTest {
    public static void main(String[] args) {
        double d1 = 0.10334;
        double d2 = 1234.0;

        //  0.10334000000000000130118138486068346537649631500244140625    1234
        // System.out.println("new BigDecimal("+d1+")=" + new BigDecimal(d1)); //此种方式绝对不允许!!!!!
        // System.out.println("new BigDecimal("+d2+")=" + new BigDecimal(d2)); //此种方式绝对不允许!!!!!
        // System.out.println("");

        // 0.10334  1234.0
        // System.out.println("new BigDecimal(String.valueOf("+d1+"))=" + new BigDecimal(String.valueOf(d1)));
        // System.out.println("new BigDecimal(String.valueOf("+d2+"))=" + new BigDecimal(String.valueOf(d2)));
        // System.out.println("");

        // 0.10334 1234.0
        // System.out.println("new BigDecimal(String.valueOf("+d1+"))=" + new BigDecimal(Double.toString(d1)));
        // System.out.println("new BigDecimal(String.valueOf("+d2+"))=" + new BigDecimal(Double.toString(d2)));
        // System.out.println("");


        // System.out.println("new BigDecimal(String.valueOf(" + 100 + "))=" + new BigDecimal(Double.toString(100)));
        // System.out.println("BigDecimal.valueOf(" + 100 + ")=" + BigDecimal.valueOf(100));
        // System.out.println("BigDecimal.valueOf(" + 100 + ")=" + BigDecimal.valueOf(100D));
		// System.out.println(Double.toString(100));
        // System.out.println("new BigDecimal(" + 100 + ")=" + new BigDecimal("100"));
        // System.out.println("");

        // 0.10334 1234.0
        // System.out.println("BigDecimal.valueOf("+d1+")=" + BigDecimal.valueOf(d1));
        // System.out.println("BigDecimal.valueOf("+d2+")=" + BigDecimal.valueOf(d2));
        // System.out.println("");

        // false true
        // BigDecimal b1 = BigDecimal.valueOf(1);
        // BigDecimal b2 = BigDecimal.valueOf(1.00000);
        // System.out.println(b1.equals(b2));
        // System.out.println(b1.compareTo(b2) == 0);


		// System.out.println(BigDecimal.valueOf(4.015));
		// System.out.println(new BigDecimal("4.015"));
		// System.out.println(new BigDecimal("4.015").multiply(BigDecimal.valueOf(100D)));
		// System.out.println(new BigDecimal("4.015").multiply(new BigDecimal(Double.toString(100))));

		System.out.println(BigDecimal.valueOf(1.0));
		System.out.println(new BigDecimal("1.0"));
		System.out.println(BigDecimal.valueOf(1.000).multiply(new BigDecimal("1.000")));
    }
}
