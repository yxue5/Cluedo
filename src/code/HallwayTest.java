package code;
import org.junit.Assert;
import org.junit.Test;
public class HallwayTest {
    @Test
    public void test() throws Exception {
       Hallway hl=new Hallway();
        Assert.assertFalse(hl.array[0][0].real);
        Assert.assertTrue(hl.array[7][7].real);
        Assert.assertEquals(hl.rooms.size(),7);
    }
}