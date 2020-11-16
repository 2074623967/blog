import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MyTest1 {
    My m;
    int num;
    boolean exp;

    @Parameterized.Parameters
    public static Collection data() {
        Object[][] obj = {{1001, false}, {1004, true}, {1100, false}, {1200, true}};
        return Arrays.asList(obj);
    }

    public MyTest1(int n, boolean exp) {
        this.num = n;
        this.exp = exp;
    }

    @Before
    public void setUp() throws Exception {
        m = new My();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsLeap() {
        Assert.assertEquals(exp, m.isLeap(num));
    }
}
