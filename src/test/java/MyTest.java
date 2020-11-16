import org.junit.Assert;
import org.junit.Test;

public class MyTest {

    @Test
    public void testIsLeap() {
        My m = new My();
        boolean result = m.isLeap(1001);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsLeap1() {
        My m = new My();
        boolean result = m.isLeap(1004);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsLeap2() {
        My m = new My();
        boolean result = m.isLeap(1100);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsLeap3() {
        My m = new My();
        boolean result = m.isLeap(1200);
        Assert.assertEquals(true, result);
    }
}
