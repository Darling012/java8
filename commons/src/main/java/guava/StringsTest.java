package guava;

import com.google.common.base.Strings;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class StringsTest {

    /**
     * 将空的字符串转换成null,若不为空字符串则原样输出
     */
    @Test
    public void testEmptyToNull() {
        System.out.println(Strings.emptyToNull(""));
        System.out.println(Strings.emptyToNull("hello"));
        // assertThat(Strings.emptyToNull(""), nullValue());
        // assertThat(Strings.emptyToNull("hello"), equalTo("hello"));
    }

    /**
     * 将null转换成空字符串,若不为null，则原样输出
     */
    @Test
    public void testNullToEmpty() {
        System.out.println(Strings.nullToEmpty(null));
        System.out.println(Strings.nullToEmpty("hello"));
        // assertThat(Strings.nullToEmpty(null), equalTo(""));
        // assertThat(Strings.nullToEmpty("hello"), equalTo("hello"));
    }

     /**
     * 将空的字符串转换成null,若不为空字符串则原样输出
     */
    @Test
    public void isNullOrEmptyTest() {
        System.out.println(Strings.isNullOrEmpty(""));
        System.out.println(Strings.isNullOrEmpty(null));
        // assertThat(Strings.emptyToNull(""), nullValue());
        // assertThat(Strings.emptyToNull("hello"), equalTo("hello"));
    }
    /**
     * 返回两个单词公共的前缀，若没相同的前缀，则返回空字符串
     */
    @Test
    public void testCommonPrefix() {
        System.out.println(Strings.commonPrefix("Hello", "Hi"));
        System.out.println(Strings.commonPrefix("Hello", "World"));
        // assertThat(Strings.commonPrefix("Hello", "Hi"), equalTo("H"));
        // assertThat(Strings.commonPrefix("Hello", "World"), equalTo(""));
    }

    /**
     * 返回两个单词公共的后缀，若没相同的后缀，则返回空字符串
     */
    @Test
    public void testCommonSuffix() {
        System.out.println(Strings.commonSuffix("Hello", "Hi"));
        System.out.println(Strings.commonSuffix("Hello", "Wo"));
        // assertThat(Strings.commonSuffix("Hello", "Hi"), equalTo(""));
        // assertThat(Strings.commonSuffix("Hello", "Wo"), equalTo("o"));
    }

    /**
     * 返回某单词的重复N次后的结果
     */
    @Test
    public void testRepeat() {
        assertThat(Strings.repeat("abc", 2), equalTo("abcabc"));
    }

    /**
     * 判断是否为null或者空字符串
     */
    @Test
    public void testIsNullOrEmpty() {
        assertThat(Strings.isNullOrEmpty(""), equalTo(true));
        assertThat(Strings.isNullOrEmpty(null), equalTo(true));
    }

    /**
     * 在首位补充字符。
     * 比如：
     * Hello五个长度，我们指定4，则会判断Hello.length >= 4?，若是true，则原样返回。否则在首位拼接上第三个参数字符。
     */
    @Test
    public void testPadStart() {
        assertThat(Strings.padStart("Hello", -1, 'p'), equalTo("Hello"));
        assertThat(Strings.padStart("Hello", 4, 'p'), equalTo("Hello"));
        assertThat(Strings.padStart("Hello", 8, 'p'), equalTo("pppHello"));
    }

    /**
     * 在末尾补充字符。
     * 比如：
     * Hello五个长度，我们指定4，则会判断Hello.length >= 4?，若是true，则原样返回。否则在末尾拼接上第三个参数字符。
     */
    @Test
    public void testPadEnd() {
        assertThat(Strings.padEnd("Hello", -1, 'p'), equalTo("Hello"));
        assertThat(Strings.padEnd("Hello", 4, 'p'), equalTo("Hello"));
        assertThat(Strings.padEnd("Hello", 8, 'p'), equalTo("Helloppp"));
    }

}
