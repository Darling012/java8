package apache;


import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: 996
 * @version:
 * @description:
 * @author: ling
 * @create: 2020-08-24 22:12
 **/
public class StringUtilsTest {
    // 判断字符串是否为空
    @Test
    public void isEmpty() {
        String str = "";
        // 1.
        if (null == str || str.isEmpty()) {
            System.out.println(1111);
        }
        // StringUtils.isEmpty(String str) 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0
        // StringUtils.isBlank(String str) 判断某字符串是否为空或长度为 0 或由空白符 (whitespace) 构成
        // StringUtils.isNotEmpty(String str) 等价于 !isEmpty(String str)
        // StringUtils.isNotBlan(String str) 等价于 !isBlank(String str)

        System.out.println(StringUtils.isEmpty("")); // true
        System.out.println(StringUtils.isEmpty(" ")); // false
        System.out.println(StringUtils.isEmpty(null)); // true
        System.out.println(StringUtils.isBlank("")); // true
        System.out.println(StringUtils.isBlank(" ")); // true
        System.out.println(StringUtils.isBlank(null)); // true
    }


    // 字符串固定长度
    private void fillSpace() {
        // 字符串固定长度 8 位，若不足，往左补0
        StringUtils.leftPad("test", 8, "0");
        System.out.println(StringUtils.rightPad("test", 8, "0"));
    }


    private static void replaceChar() {
        // 默认替换所有关键字  = "zbz"
        StringUtils.replace("aba", "a", "z");
        // 替换关键字，仅替换一次 = "zba"
        StringUtils.replaceOnce("aba", "a", "z");
        // 使用正则表达式替换  = "ABC123"
        StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "");
    }

    private static void strAppend() {
        String[] array = new String[]{"test", "1234", "5678"};
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : array) {
            stringBuilder.append(s).append(";");
        }
        // 防止最终拼接字符串为空
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        System.out.println(stringBuilder.toString());
        List<String> list = new ArrayList<>();
        list.add("test");
        list.add("1234");
        list.add("5678");
        StringUtils.join(array, ",");

        // 逗号分隔符，跳过 null
        Joiner joiner = Joiner.on(",").skipNulls();
        joiner.join(array);
        joiner.join(list);
    }

    /**
     * public static boolean contains(String str,char searchChar)
     * str是原始字符串，searchChar是想要搜索的字符，此方法是检查字符串str中是否包含字符searchChar，如果str为null或””，则返回false。
     */
    @Test
    public void containsTest() {
        System.out.println(StringUtils.contains(null, ""));
        System.out.println(StringUtils.contains("", ""));
        System.out.println(StringUtils.contains("abc", 'a'));
        System.out.println(StringUtils.contains("abc", 'z'));
    }
}
