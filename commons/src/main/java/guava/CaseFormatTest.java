package guava;

import com.google.common.base.CaseFormat;

/**
 *
 * S.N.	枚举常量和说明
 * 1	LOWER_CAMEL
 * Java变量的命名规则，如“lowerCamel”。
 * 2	LOWER_HYPHEN
 * 连字符连接变量的命名规则，如“lower-hyphen”。
 * 3	LOWER_UNDERSCORE
 * C ++变量命名规则，如“lower_underscore”。
 * 4	UPPER_CAMEL
 * Java和C++类的命名规则，如“UpperCamel”。
 * 5	UPPER_UNDERSCORE
 * Java和C++常量的命名规则，如“UPPER_UNDERSCORE”。
 *
 *
 * S.N.	方法及说明
 * 1	Converter<String,String> converterTo(CaseFormat targetFormat
 * 返回一个转换，从这个格式转换targetFormat字符串。
 * 2	String to(CaseFormat format, String str)
 * 从这一格式指定格式的指定字符串 str 转换。
 * 3	static CaseFormat valueOf(String name)
 * 返回此类型具有指定名称的枚举常量。
 * 4	static CaseFormat[] values()
 * 返回一个包含该枚举类型的常量数组中的顺序被声明。
 *
 * @program: 996
 * @description:
 * @author: ling
 * @create: 2020-03-03 13:47
 **/
public class CaseFormatTest {
     public static void main(String[] args) {
        CaseFormatTest tester = new CaseFormatTest();
        // tester.testCaseFormat();
         System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testData"));
     }


    private void testCaseFormat() {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));

        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));
    }
}
