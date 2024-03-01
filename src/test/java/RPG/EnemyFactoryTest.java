import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyFactoryTest {

    @Test
    public void testCreateSmallOrc() {
        Enemy enemy = EnemyFactory.createEnemy("small", 1);
        assertTrue(enemy instanceof Orc);
        assertEquals("Small Orc", enemy.getName());
        assertEquals(22, enemy.getHealth()); // Assuming the base health of 20 + (1 * 2) scaling factor
    }

    @Test
    public void testCreateMediumOrc() {
        Enemy enemy = EnemyFactory.createEnemy("medium", 5);
        assertTrue(enemy instanceof Orc);
        assertEquals("Medium Orc", enemy.getName());
        assertEquals(40, enemy.getHealth()); // Assuming the base health of 30 + (5 * 2) scaling factor
    }

    @Test
    public void testCreateBossTroll() {
        Enemy enemy = EnemyFactory.createEnemy("boss", 10);
        assertTrue(enemy instanceof Troll);
        assertEquals(70, enemy.getHealth()); // Assuming the base health of 50 + (10 * 2) scaling factor
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUnknownEnemyType() {
        EnemyFactory.createEnemy("unknown", 1);
    }
}
