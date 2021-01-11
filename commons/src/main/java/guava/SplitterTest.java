package guava;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class SplitterTest {

    /**
     * 将字符串（没有空值的且分隔符之间无空白）按照自定义分隔符进行拆分成list数组
     * 存在问题就是：hello,world中间逗号前后不能加空格
     * hello
     * world
     */
    @Test
    public void testSplitOn() {
        List<String> list = Splitter.on(",").splitToList("hello,world");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(0), equalTo("hello"));
        assertThat(list.get(1), equalTo("world"));
    }

    /**
     * 将字符串（有空值且分隔符之间无空白）按照自定义分隔符进行拆分成list数组
     * 存在问题就是：hello,world中间逗号前后不能加空格
     * 出现异常，因为长度不是2而是5
     */
    @Test
    public void testSplitOnEmpty() {
        List<String> list = Splitter.on(",").splitToList("hello,world,,'',null");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(2));
    }

    /**
     * 将字符串（有空值且分隔符之间无空白）按照自定义分隔符进行拆分成list数组（刨除空值）
     * 存在的问题就是：空白还是无法解决，但是能把空值（,,）去掉
     * 所以长度是4，程序正常
     */
    @Test
    public void testSplitOnOmitEmpty() {
        List<String> list = Splitter.on(",").omitEmptyStrings().splitToList("hello,world,,'',null");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(4));
    }

    /**
     * 将字符串（有空值且分隔符之间无空白）按照自定义分隔符进行拆分成list数组（刨除空值和空白）
     */
    @Test
    public void testSplitOnOmitEmptyAndTrim() {
        List<String> list = Splitter.on(",").trimResults().omitEmptyStrings().splitToList("hello, world, ,'', null");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(4));
        assertThat(list.get(0), equalTo("hello"));
        assertThat(list.get(1), equalTo("world"));
        assertThat(list.get(2), equalTo("''"));
        assertThat(list.get(3), equalTo("null"));
    }

    /**
     * 将字符串按照字符长度进行拆分成list数组
     *
     * 比如：aaaabbbbccccdddd
     * 结果：
     * aaaa
     * bbbb
     * cccc
     * dddd
     */
    @Test
    public void testSplitFixLength() {
        List<String> list = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(4));
        assertThat(list.get(0), equalTo("aaaa"));
        assertThat(list.get(1), equalTo("bbbb"));
        assertThat(list.get(2), equalTo("cccc"));
        assertThat(list.get(3), equalTo("dddd"));
    }

    /**
     * 将字符串按照自定义字符进行拆分成list数组，并且限制截取list的个数
     *
     * 比如：on("#").limit(3)。按照#分割，分割出三个长度。
     * A#B#C#D#E#F
     * 结果：
     * A
     * B
     * C#D#E#F
     */
    @Test
    public void testSplitOnSplitLimit() {
        List<String> list = Splitter.on("#").limit(3).splitToList("A#B#C#D#E#F");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(3));
        assertThat(list.get(0), equalTo("A"));
        assertThat(list.get(1), equalTo("B"));
        assertThat(list.get(2), equalTo("C#D#E#F"));
    }

    /**
     * 按照正则表达式分割一
     */
    @Test
    public void testSplitOnPatternString() {
        List<String> list = Splitter.onPattern("\\/").trimResults().omitEmptyStrings().splitToList("A / B / C / / ///");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(3));
        assertThat(list.get(0), equalTo("A"));
        assertThat(list.get(1), equalTo("B"));
        assertThat(list.get(2), equalTo("C"));
    }

    /**
     * 按照正则表达式分割二
     */
    @Test
    public void testSplitOnPattern() {
        List<String> list = Splitter.on(Pattern.compile("\\/")).trimResults().omitEmptyStrings().splitToList("A / B / C / / ///");
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(3));
        assertThat(list.get(0), equalTo("A"));
        assertThat(list.get(1), equalTo("B"));
        assertThat(list.get(2), equalTo("C"));
    }

    /**
     * 按照某字符进行左右切割成map
     */
    @Test
    public void testSplitOnSplitToMap() {
        Map<String, String> map = Splitter.on(Pattern.compile("\\/")).trimResults().omitEmptyStrings()
                .withKeyValueSeparator("=").split("key=value / key1=value1 / key2=valu2 / / ///");
        assertThat(map, notNullValue());
        assertThat(map.size(), equalTo(3));
        assertThat(map.get("key"), equalTo("value"));
    }

}
