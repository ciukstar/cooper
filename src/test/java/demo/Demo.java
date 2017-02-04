package demo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author sergiu
 */
public class Demo {
    
    @Test
    public void foo() {
        assertThat("abc".concat("d"), is("abcd"));
    }
}
