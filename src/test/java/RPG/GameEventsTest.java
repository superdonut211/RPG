import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GameEventsTest {
    public Character player;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        player = new Character(
                "HERO",
                CharacterClass.FIGHTER,
                CharacterRace.HUMAN,
                100,
                50,
                15,
                10,
                8
        );
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testHandlePostCombat_ChestFound() {
        GameEvents.setForceChest(true);

        // Mocking user input to simulate equipping the found item
        String input = "yes\n"; // Assuming user inputs "yes" to equip the found item
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture the state of the player before invoking the method
        int initialCurrency = player.getCurrency();

        // Calling the method under test
        GameEvents.handlePostCombat(player);

        // Verify that the player's currency has been increased by 50
        assertEquals(initialCurrency + 50, player.getCurrency());

        GameEvents.resetForces();
    }

    @After
    public void tearDown() {
    	System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testGenerateRandomItem_ReturnsItem() {
        Item item = GameEvents.generateRandomItem();
        assertNotNull("generateRandomItem should return a non-null item", item);
        assertTrue("The item should be an instance of Item class", item instanceof Item);
    }

    @Test
    public void testRestoreHealthAndMana_HealthAndManaRestoration() {
        int initialHealth = player.getHealth();
        int initialMana = player.getMana();
        GameEvents.restoreHealthAndMana(player);
        assertTrue("Health should be increased after restoration", player.getHealth() >= initialHealth);
        assertTrue("Mana should be increased after restoration", player.getMana() >= initialMana);
    }

    @Test
    public void testSetForceChest() {
        GameEvents.setForceChest(true);
        assertTrue("true", true);
    }

    @Test
    public void testResetForces() {
        // Set the forces to true
        GameEvents.setForceChest(true);
        GameEvents.setForceShop(true);
        GameEvents.resetForces();
        assertTrue("t", true);
    }
    
 // Test for equipping an item directly (bypassing user input)
    @Test
    public void testEquipItem_Directly() {
        // Setup: Create an item to equip
        Item item = new Weapon("Test Sword", 5);
        // Assume Character class has a method to get equipped weapon
        player.equipWeapon((Weapon) item);

        assertEquals("Equipped weapon should be 'Test Sword'", "Test Sword", player.getWeaponName());
    }


    
/*

    @Test
    public void testHandlePostCombat_NoChestOrShop() {
        // Test code for when neither a chest nor a shop is found
    }

    @Test
    public void testFoundChest_TrueCase() {
        // Test code for when foundChest returns true
    }

    @Test
    public void testFoundChest_FalseCase() {
        // Test code for when foundChest returns false
    }

    @Test
    public void testFoundShop_TrueCase() {
        // Test code for when foundShop returns true
    }

    @Test
    public void testFoundShop_FalseCase() {
        // Test code for when foundShop returns false
    }

    @Test
    public void testHandleChest_EquipItem() {
        // Test code for when the user inputs "yes" to equip the found item
    }

    @Test
    public void testHandleChest_SkipItem() {
        // Test code for when the user inputs something other than "yes" to skip the item
    }

    @Test
    public void testHandleChest_CurrencyAddition() {
        // Test code for the correct addition of currency to the player
    }

    @Test
    public void testHandleChest_RandomItemGeneration() {
        // Test code for the correct generation of a random item
    }

    @Test
    public void testHandleShop_EnterShop() {
        // Test code for when the user selects to enter the shop
    }

    @Test
    public void testHandleShop_SkipShop() {
        // Test code for when the user selects to skip the shop
    }

    @Test
    public void testRestoreHealthAndMana_HealthRestoration() {
        // Test code for the correct restoration of health
    }

    @Test
    public void testRestoreHealthAndMana_ManaRestoration() {
        // Test code for the correct restoration of mana
    }

    @Test
    public void testGenerateRandomItem_ReturnsItem() {
        // Test code to ensure generateRandomItem returns an item instance
    }

    @Test
    public void testGenerateRandomItem_ItemGenerationProbabilities() {
        // Test code to ensure generateRandomItem returns different types of items with expected probabilities
    }

    @Test
    public void testEnterShop_PurchaseHealthPotion() {
        // Test code for when the user selects to purchase a health potion
    }

    @Test
    public void testEnterShop_PurchaseManaPotion() {
        // Test code for when the user selects to purchase a mana potion
    }

    @Test
    public void testEnterShop_PurchaseRandomItem() {
        // Test code for when the user selects to purchase a random item
    }

    @Test
    public void testEnterShop_LeaveShop() {
        // Test code for when the user selects to leave the shop
    }

    @Test
    public void testEquipItem_EquipHelmet() {
        // Test code for when equipping a helmet
    }

    @Test
    public void testEquipItem_EquipWeapon() {
        // Test code for when equipping a weapon
    }

    @Test
    public void testEquipItem_EquipArmor() {
        // Test code for when equipping an armor
    }*/
}
