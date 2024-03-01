import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyManagerTest {
    
    private EnemyManager enemyManager;

    @Before
    public void setUp() {
        enemyManager = EnemyManager.getInstance();
    }

    @Test
    public void testSpawnSmallEnemy() {
        Enemy enemy = enemyManager.spawnEnemy("SMALL", 1);
        assertNotNull("Failed to spawn small enemy", enemy);
        assertTrue("Spawned enemy is not an Orc", enemy instanceof Orc);
        assertEquals("Small enemy health does not scale correctly", 22, enemy.getHealth());
    }

    @Test
    public void testSpawnMediumEnemy() {
        Enemy enemy = enemyManager.spawnEnemy("MEDIUM", 2);
        assertNotNull("Failed to spawn medium enemy", enemy);
        assertTrue("Spawned enemy is not an enhanced Orc", enemy instanceof Orc);
        assertEquals("Medium enemy health does not scale correctly", 34, enemy.getHealth()); // Assuming specific scaling logic
    }

    @Test
    public void testSpawnBossEnemy() {
        Enemy enemy = enemyManager.spawnEnemy("BOSS", 5);
        assertNotNull("Failed to spawn boss enemy", enemy);
        assertTrue("Spawned enemy is not a Troll", enemy instanceof Troll);
        assertEquals("Boss enemy health does not scale correctly", 60, enemy.getHealth()); // Assuming specific scaling logic
    }

    @Test
    public void testSpawnUnknownEnemy() {
        Enemy enemy = enemyManager.spawnEnemy("UNKNOWN", 1);
        assertNull("Spawning unknown enemy type should return null", enemy);
    }
}
