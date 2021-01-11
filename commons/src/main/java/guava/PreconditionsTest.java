package guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @program: 996
 * @version:
 * @description: 前置条件判断
 * @author: ling
 * @create: 2020-08-30 12:23
 * 每个方法都有三个变种：
 * <p>
 * 没有额外参数：抛出的异常中没有错误消息；
 * 有一个Object对象作为额外参数：抛出的异常使用Object.toString() 作为错误消息；
 * 有一个String对象作为额外参数，并且有一组任意数量的附加Object对象：这个变种处理异常消息的方式有点类似printf，但考虑GWT的兼容性和效率，只支持%s指示符。例如：
 * 1 checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
 * 2 checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
 **/
@Slf4j
public class PreconditionsTest {

    /**
     * checkArgument(boolean)
     * 检查boolean是否为true，用来检查传递给方法的参数
     * IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkArgumentTest() {
        Preconditions.checkArgument("A".equals("B"));
    }

    /**
     * checkNotNull(T)
     * 检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。
     * NullPointerException
     * <p>
     * ？？ 有以下优势吗？
     * 相比Apache Commons提供的类似方法，我们把Guava中的Preconditions作为首选。Piotr Jagielski在他的博客中简要地列举了一些理由：
     * 在静态导入后，Guava方法非常清楚明晰。checkNotNull清楚地描述做了什么，会抛出什么异常；
     * checkNotNull直接返回检查的参数，让你可以在构造函数中保持字段的单行赋值风格：this.field = checkNotNull(field)
     * 简单的、参数可变的printf风格异常信息。鉴于这个优点，在JDK7已经引入Objects.requireNonNull的情况下，我们仍然建议你使用checkNotNull。
     */
    @Test
    public void checkNotNullTest() {
        String b = "";
        log.info(checkNotNull(b));
        String a = null;
        log.info(checkNotNull(a, "不能为空"));
    }

    /**
     * 检查是否会抛出空指针异常。若会，则自定义message
     */
    @Test
    public void testCheckNotNullWithMessage() {
        try {
            Preconditions.checkNotNull(null, "空指针了");
        } catch (Exception e) {
            assertThat(e.getMessage(), equalTo("空指针了"));
        }
    }

    /**
     * 检查是否会抛出空指针异常。若会，则自定义可格式化的message（String.format()）
     */
    @Test
    public void testCheckNotNullWithFormatMessage() {
        try {
            Preconditions.checkNotNull(null, "test");
        } catch (Exception e) {
            assertThat(e.getMessage(), equalTo("null test."));
        }
    }

    // checkState(boolean)	用来检查对象的某些状态。	IllegalStateException
    @Test(expected = IllegalStateException.class)
    public void checkStateTest() {
        Preconditions.checkState("a".equals("b"));
    }

    // checkElementIndex(int index, int size)	检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *	IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class)
    public void checkElementIndexTest() {
        List<String> lists = ImmutableList.of();
        Preconditions.checkElementIndex(1, lists.size());
    }


    // checkPositionIndex(int index, int size)	检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *	IndexOutOfBoundsException

    @Test
    public void checkPositionIndexTest() {

    }

    // checkPositionIndexes(int start, int end, int size)
    // 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*
    // IndexOutOfBoundsException
    @Test
    public void checkPositionIndexesTest() {

    }
}
