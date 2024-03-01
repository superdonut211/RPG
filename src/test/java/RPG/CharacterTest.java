import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {

    private Character character;

    @Before
    public void setUp() {
        character = new Character("Hero", CharacterClass.FIGHTER, CharacterRace.HUMAN, 100, 50, 10, 5, 5);
    }

    @Test
    public void testCharacterInitialization() {
        assertEquals("Hero", character.getName());
        assertEquals(CharacterClass.FIGHTER, character.getCharacterClass());
        assertEquals(CharacterRace.HUMAN, character.getCharacterRace());
        assertEquals(100, character.getMaxHealth());
        assertEquals(50, character.getMaxMana());
        assertEquals(10, character.getAttack());
        assertEquals(5, character.getDefense());
        assertEquals(5, character.getSpeed());
        assertEquals(0, character.getCurrency()); // Initial currency is 0
        assertEquals(0, character.getXp()); // Initial XP is 0
        assertEquals(1, character.getLevel()); // Initial level is 1
    }

    @Test
    public void testEquipHelmet() {
        Helmet helmet = new Helmet("Basic Helm", 5, 1, 0);
        character.equipHelmet(helmet);
        assertEquals("Basic Helm", character.getHelmetName());
    }

    @Test
    public void testEquipWeapon() {
        Weapon weapon = new Weapon("Sword", 3);
        character.equipWeapon(weapon);
        assertEquals("Sword", character.getWeaponName());
    }

    @Test
    public void testEquipArmor() {
        Armor armor = new Armor("Chain Mail", 10, 2);
        character.equipArmor(armor);
        assertEquals("Chain Mail", character.getArmorName());
    }

    @Test
    public void testAddCurrency() {
        character.addCurrency(100);
        assertEquals(100, character.getCurrency());
    }

    @Test
    public void testConsumeMana() {
        character.consumeMana(20);
        assertEquals(30, character.getMana()); // Starting mana is 50
    }

    @Test
    public void testRestoreMana() {
        character.consumeMana(25); // Reduce mana to 25
        character.restoreMana(10);
        assertEquals(35, character.getMana());
    }

    @Test
    public void testAddXpAndLevelUp() {
        character.addXp(200); // Assuming 100 XP needed for the first level
        assertTrue(character.getLevel() > 1);
        assertTrue(character.getXp() < 200); // XP should be deducted by level-up threshold
    }

    @Test
    public void testPrintAllInfo() {
        // This test ensures the method runs without error, actual print testing is not typical
        character.printAllInfo();
        assertTrue(true); // Verify the method executes
    }


    // Additional tests should cover edge cases, such as setting health or mana beyond max limits,
    // equipping items when already equipped, and more complex scenarios involving interactions
    // between methods.
}
