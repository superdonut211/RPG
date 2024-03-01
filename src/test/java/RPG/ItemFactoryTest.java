import org.junit.Test;
import static org.junit.Assert.*;

public class ItemFactoryTest {

    @Test
    public void testCreateHelmet() {
        Item helmet = ItemFactory.createItem("Helmet", "Warrior's Helmet", 10, 0, 5, 2);
        assertTrue(helmet instanceof Helmet);
        assertEquals("Warrior's Helmet", helmet.getName());
        assertEquals(10, helmet.getBonusHealth());
        assertEquals(5, helmet.getBonusDefense());
        assertEquals(2, helmet.getBonusSpeed());
    }

    @Test
    public void testCreateWeapon() {
        Item weapon = ItemFactory.createItem("Weapon", "Sword of Valor", 0, 15, 0, 0);
        assertTrue(weapon instanceof Weapon);
        assertEquals("Sword of Valor", weapon.getName());
        assertEquals(15, weapon.getBonusAttack());
    }

    @Test
    public void testCreateArmor() {
        Item armor = ItemFactory.createItem("Armor", "Knight's Armor", 20, 0, 10, 0);
        assertTrue(armor instanceof Armor);
        assertEquals("Knight's Armor", armor.getName());
        assertEquals(20, armor.getBonusHealth());
        assertEquals(10, armor.getBonusDefense());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUnknownItemType() {
        ItemFactory.createItem("Ring", "Ring of Power", 5, 5, 5, 5);
    }
}
