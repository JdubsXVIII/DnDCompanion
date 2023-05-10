import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpendableTester {
    @Test
    public void ctorTest(){
        Expendable test = new Expendable("Test Expendable", 3, true, "Does a thing!");
        assertEquals("Test Expendable", test.getName());
        assertEquals(3, test.getMaxUses());
        assertEquals(true, test.getRefresh());
        assertEquals("Does a thing!", test.getDesc());
    }

    @Test
    public void useTest(){
        Expendable test = new Expendable("Test Expendable", 3, true, "Does a thing!");
        assertEquals(3, test.getUsesLeft());
        assertEquals("Does a thing! You have 2 uses remaining.", test.use());
        assertEquals(2, test.getUsesLeft());
        test.use();
        assertEquals(1, test.getUsesLeft());
        test.use();
        assertEquals(0, test.getUsesLeft());
        assertEquals("You have no uses of this left!", test.use());
    }

    @Test
    public void setUsesLeftTest(){
        Expendable test = new Expendable("Test Expendable", 3, true, "Does a thing!");
        test.setUsesLeft(1);
        assertEquals(1, test.getUsesLeft());
        test.setUsesLeft(3);
        assertEquals(3, test.getUsesLeft());
    }
}
