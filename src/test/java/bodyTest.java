import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class bodyTest {
    Body body;

    @Before
    public void setUp() throws Exception {
        body = new Body();
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testDemofunc() {
//    	Assert.assertEquals(144, body.demofunc());
//    }   //模块A的驱动模块 ，调用被测的A模块

    //    @Test
//    public void testSumFunc() {
//    	Assert.assertEquals(144, body.sumFunc(4, 5));
//    }      //模块B的驱动模块 ，调用被测的B模块
//
    @Test
    public void testFactorial() {
        Assert.assertEquals(24, body.factorial(4));
        Assert.assertEquals(120, body.factorial(5));
    }
}
