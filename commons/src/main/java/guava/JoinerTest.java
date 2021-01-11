package guava;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.google.common.collect.ImmutableMap.of;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public final class JoinerTest {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final List<String> lists = Arrays.asList("A", "B", "C", "D");

    private final List<String> listsWithNull = Arrays.asList("A", "B", "C", null);

    private final String targetFileName = "targetTest/guava.txt";



    /**
     * 将List中的元素按照自定义的分隔符分割成字符串
     * A,B,C,D
     */
    @Test
    public void testJoinOn() {
        String result = Joiner.on(",").join(lists);
        logger.info(result);
        assertThat(result, equalTo("A,B,C,D"));
    }

    /**
     * 将List中的带有null值的元素按照自定义的分隔符分割成字符串
     * 会出现空指针
     */
    @Test(expected = NullPointerException.class)
    public void testJoinOnWithNullValue() {
        String result = Joiner.on(",").join(listsWithNull);
        logger.info(result);
        assertThat(result, equalTo("A,B,C"));
    }

    /**
     * 将List中的带有null值的元素按照自定义的分隔符分割成字符串，但是排除null值
     * A,B,C
     */
    @Test
    public void testJoinOnWithNullValueButSkip() {
        String result = Joiner.on(",").skipNulls().join(listsWithNull);
        logger.info(result);
        assertThat(result, equalTo("A,B,C"));
    }

    /**
     * 将List中的带有null值的元素按照自定义的分隔符分割成字符串，但是将null值换成自定义的值
     * A,B,C,JAVA
     */
    @Test
    public void testJoinOnWithNullValueButUseDefaultValue() {
        String result = Joiner.on(",").useForNull("JAVA").join(listsWithNull);
        logger.info(result);
        assertThat(result, equalTo("A,B,C,JAVA"));
    }

    /**
     * 将List中的带有null值的元素按照自定义的分隔符分割成字符串，但是将null值换成自定义的值，并append到StringBuilder里
     * A,B,C,JAVA
     */
    @Test
    public void testJoinOnAppendToStringBuilder() {
        StringBuilder result = Joiner.on(",").useForNull("JAVA").appendTo(new StringBuilder(), listsWithNull);
        logger.info(result.toString());
        assertThat(result.toString(), equalTo("A,B,C,JAVA"));
    }

    /**
     * 将List中的带有null值的元素按照自定义的分隔符分割成字符串，但是将null值换成自定义的值，并输出到文件中
     */
    @Test
    public void testJoinOnAppendToWriter() {
        try(FileWriter writer = new FileWriter(targetFileName)) {
            Joiner.on(",").useForNull("JAVA").appendTo(writer, listsWithNull);
            assertThat(com.google.common.io.Files.isFile().test(new File(targetFileName)), equalTo(true));
        } catch (IOException e) {
            fail("error append to writer");
        }
    }

    /**
     * 将Map中的key-value按照不同的分隔符分割成字符串
     * key1=value1#key2=value2#key3=value3
     */
    @Test
    public void testJoinOnWithMap() {
        Map<String, String> stringMap = of("key1", "value1", "key2", "value2", "key3", "value3");
        String result = Joiner.on("#").withKeyValueSeparator("=").join(stringMap);
        logger.info(result);
        assertThat(result, equalTo("key1=value1#key2=value2#key3=value3"));
    }
}
