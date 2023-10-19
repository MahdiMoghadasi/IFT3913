import org.junit.Assert;
import org.junit.Test;

public class SampleTestFile {

    @Test
    public void testMethod1() {
        Assert.assertEquals(1, 1);
        Assert.assertEquals(2, 2);
        Assert.assertTrue(true);
        Assert.assertFalse(false);
    }

    @Test
    public void testMethod2() {
        Assert.assertNotNull(new Object());
        Assert.assertNull(null);
    }

    @Test
    public void testMethod3() {
        Assert.assertSame("Same", "Same");
        Assert.assertNotSame(new Object(), new Object());
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Exception");
        });
    }

    @Test
    public void testMethod4() {
        Assert.fail("Failed");
    }
}
