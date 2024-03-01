import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FloorManagerTest {

    private FloorManager floorManager;

    @Before
    public void setUp() {
        floorManager = new FloorManager();
    }

    @Test
    public void testNextFloor() {
        int initialFloor = floorManager.getCurrentFloor();
        floorManager.nextFloor();
        assertEquals(initialFloor + 1, floorManager.getCurrentFloor());
    }

    @Test
    public void testGoToMostRecentTenthFloor() {
        // Move to a specific floor first
        for (int i = 0; i < 14; i++) {
            floorManager.nextFloor(); // Assume this moves to floor 15
        }
        floorManager.goToMostRecentTenthFloor();
        assertEquals(11, floorManager.getCurrentFloor());
    }

    @Test
    public void testGoToMostRecentTenthFloorFromFirstFloor() {
        floorManager.goToMostRecentTenthFloor(); // Should stay on the first floor
        assertEquals(1, floorManager.getCurrentFloor());
    }

    @Test
    public void testGetEnemyTypeForCurrentFloor() {
        // Test for SMALL enemy type
        assertEquals("SMALL", floorManager.getEnemyTypeForCurrentFloor());

        // Test for MEDIUM enemy type
        for (int i = 0; i < 4; i++) {
            floorManager.nextFloor(); // Move to 5th floor
        }
        assertEquals("MEDIUM", floorManager.getEnemyTypeForCurrentFloor());

        // Test for BOSS enemy type
        for (int i = 0; i < 5; i++) {
            floorManager.nextFloor(); // Move to 10th floor
        }
        assertEquals("BOSS", floorManager.getEnemyTypeForCurrentFloor());
    }

    @Test
    public void testGetCurrentFloorEffect_DoubleDamage() {
        // Move to a floor where DOUBLE_DAMAGE is expected
        for (int i = 0; i < 1; i++) { // Move to 2nd floor
            floorManager.nextFloor();
        }
        assertEquals(FloorEffect.DOUBLE_DAMAGE, floorManager.getCurrentFloorEffect());
    }

    @Test
    public void testGetCurrentFloorEffect_HalfDamage() {
        // Move to a floor where HALF_DAMAGE is expected
        for (int i = 0; i < 6; i++) { // Move to 7th floor
            floorManager.nextFloor();
        }
        assertEquals(FloorEffect.HALF_DAMAGE, floorManager.getCurrentFloorEffect());
    }

    @Test
    public void testGetCurrentFloorEffect_SwapSpeed() {
        // Move to a floor where SWAP_SPEED is expected
        for (int i = 0; i < 11; i++) { // Move to 12th floor
            floorManager.nextFloor();
        }
        assertEquals(FloorEffect.SWAP_SPEED, floorManager.getCurrentFloorEffect());
    }
}
