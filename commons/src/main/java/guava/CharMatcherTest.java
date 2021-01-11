package guava;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CharMatcherTest {

    @Test
    public void testJavaDigit() {
        assertThat(CharMatcher.javaDigit().matches('5'), equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('X'), equalTo(false));
    }

    @Test
    public void testCountIn() {
        assertThat(CharMatcher.is('A').countIn("ABCDEFGABCD"), equalTo(2));
    }

    @Test
    public void testBreakingWhitespace() {
        assertThat(CharMatcher.breakingWhitespace().collapseFrom("      Hello World  ", '*'), equalTo("*Hello*World*"));
    }

    @Test
    public void testRemoveFrom() {
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 1234 world"), equalTo("helloworld"));
    }

}
