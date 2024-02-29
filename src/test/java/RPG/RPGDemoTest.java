package RPG;

import org.junit.Test;
import static org.junit.Assert.*;

public class RPGDemoTest {
    @Test 
    public void appHasAGreeting() {
        RPGDemo classUnderTest = new RPGDemo();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
