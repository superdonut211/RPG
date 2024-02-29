import java.util.Random;
import java.util.Scanner;

public class GameEvents {
    private static final Random random = new Random();

    public static void handlePostCombat(Character player) {
    	if (random.nextInt(100) < 30) {
            System.out.println("You found a chest!");
            int foundCurrency = random.nextInt(50) + 10;
            player.addCurrency(foundCurrency);
            System.out.println("You found " + foundCurrency + " coins!");

            // Generate a random item
            Item foundItem = generateRandomItem();
            System.out.println("You found a " + foundItem.getName() + "!");

            System.out.println("Do you want to equip it? (yes/no)");
            String input = InputScanner.SCANNER.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                // Logic to equip the item based on its type
                if (foundItem instanceof Helmet) {
                    player.equipHelmet((Helmet) foundItem);
                } else if (foundItem instanceof Weapon) {
                    player.equipWeapon((Weapon) foundItem);
                } else if (foundItem instanceof Armor) {
                    player.equipArmor((Armor) foundItem);
                }
                System.out.println("You equipped the " + foundItem.getName() + ".");
            } else {
                // If they choose not to equip, give them some gold instead
                int gold = random.nextInt(20) + 10; // Random gold between 10 and 30
                player.addCurrency(gold);
                System.out.println("You skipped the item and found " + gold + " gold instead.");
            }

        } else { // Chance to restore health/mana
            int healthRestored = random.nextInt(20) + 10; // Restore between 10 and 30 health
            player.setHealth(Math.min(player.getHealth() + healthRestored, player.getMaxHealth()));
            System.out.println("You restored " + healthRestored + " health!");

            int manaRestored = random.nextInt(15) + 5; // Restore between 5 and 20 mana
            player.restoreMana(Math.min(player.getMana() + manaRestored, player.getMaxMana()));
            System.out.println("You restored " + manaRestored + " mana!");
        }
    }
    
    private static Item generateRandomItem() {
        // Expanding the range for the new items
        int itemType = random.nextInt(17); // Adjusted for 8 new items

        switch (itemType) {
            // Fighter-focused items
            case 0:
                return new Weapon("Great Sword", 5);
            case 1:
                return new Armor("Plate Armor", 8, 3);
            case 2:
                return new Helmet("Warrior's Helm", 0, 2, 1);
            case 3:
                return new Weapon("Battle Axe", 6);
            case 4:
                return new Armor("Knight's Armor", 10, 4);

            // Thief-focused items
            case 5:
                return new Weapon("Dagger", 2);
            case 6:
                return new Armor("Leather Armor", 3, 1);
            case 7:
                return new Helmet("Thief's Hood", 0, 1, 3);
            case 8:
                return new Weapon("Short Sword", 3);
            case 9:
                return new Armor("Shadow Cloak", 2, 2);

            // Mage-focused items
            case 10:
                return new Weapon("Magic Staff", 4);
            case 11:
                return new Armor("Robes of the Magi", 5, 0);
            case 12:
                return new Helmet("Wizard's Hat", 0, 0, 2);
            case 13:
                return new Weapon("Wand of Power", 5);
            case 14:
                return new Armor("Mystic Robe", 4, 1);

            // Universal items
            case 15:
                return new Helmet("Adventurer's Cap", 1, 1, 1);
            case 16:
                return new Weapon("Longbow", 4);

            default:
                return null; // Fallback in case of an unexpected value
        }
    }

}
