import java.util.Random;
import java.util.Scanner;

public class GameEvents {
	public static Random random = new Random();
    public static boolean forceChest = false;
    public static boolean forceShop = false;

    // Setter methods for test control
    public static void setForceChest(boolean forceChest) {
        GameEvents.forceChest = forceChest;
    }

    public static void setForceShop(boolean forceShop) {
        GameEvents.forceShop = forceShop;
    }

    public static void resetForces() {
        forceChest = false;
        forceShop = false;
    }

    public static boolean handlePostCombat(Character player) {
        if (forceChest || foundChest() && !forceShop) {
            handleChest(player);
            return false;
        } else if (forceShop || foundShop()) {
            handleShop(player);
            return true;
        } else {
            restoreHealthAndMana(player);
            return false;
        }
    }

    public static boolean foundChest() {
        return random.nextInt(100) < 40;
    }

    public static boolean foundShop() {
        return random.nextInt(100) < 10;
    }

    public static void handleChest(Character player) {
        System.out.println("You found a chest!");
        int foundCurrency = 50;
        player.addCurrency(foundCurrency);
        System.out.println("You found " + foundCurrency + " coins!");

        Item foundItem = generateRandomItem();
        System.out.println("You found a " + foundItem.getName() + "!");

        System.out.println("Do you want to equip it? (yes/no)");
        String input = InputScanner.SCANNER.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            equipItem(player, foundItem);
        } else {
            int gold = 50; // Adjusted to be consistent with the currency obtained if the item is equipped
            player.addCurrency(gold);
            System.out.println("You skipped the item and found " + gold + " gold instead.");
        }
    }


    public static boolean handleShop(Character player) {
        System.out.println("You've stumbled upon a shop! Do you wish to enter? (yes/no)");
        String shopInput = InputScanner.SCANNER.nextLine();
        if (shopInput.equalsIgnoreCase("yes")) {
            enterShop(player);
            return true;
        } else {
            System.out.println("You decided to skip the shop.");
            return false;
        }
    }

    public static void restoreHealthAndMana(Character player) {
        int healthRestored = random.nextInt(20) + 10;
        player.setHealth(Math.min(player.getHealth() + healthRestored, player.getMaxHealth()));
        System.out.println("You found a health potion and restored " + healthRestored + " health!");

        int manaRestored = random.nextInt(15) + 5;
        player.restoreMana(Math.min(player.getMana() + manaRestored, player.getMaxMana()));
        System.out.println("You found a mana potion and restored " + manaRestored + " mana!");
    }

    public static Item generateRandomItem() {
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

    public static void enterShop(Character player) {
    	System.out.println("Welcome to the shop! Here's what's available for purchase:");
        System.out.println("1. Health Potion (50 gold)");
        System.out.println("2. Mana Potion (50 gold)");
        System.out.println("3. Random Item (100 gold)");
        System.out.println("4. Leave Shop");
        System.out.print("Please make a selection (1-4): ");
        
        String selection = InputScanner.SCANNER.nextLine();
        switch (selection) {
            case "1":
                if (player.getCurrency() >= 50) {
                    player.addCurrency(-50);
                    player.setHealth(Math.min(player.getHealth() + 50, player.getMaxHealth()));
                    System.out.println("You purchased a Health Potion and restored 50 health.");
                } else {
                    System.out.println("Not enough gold.");
                }
                break;
            case "2":
                if (player.getCurrency() >= 50) {
                    player.addCurrency(-50);
                    player.restoreMana(Math.min(player.getMana() + 50, player.getMaxMana()));
                    System.out.println("You purchased a Mana Potion and restored 50 mana.");
                } else {
                    System.out.println("Not enough gold.");
                }
                break;
            case "3":
                if (player.getCurrency() >= 100) {
                    player.addCurrency(-100);
                    Item randomItem = generateRandomItem();
                    System.out.println("You purchased a random item: " + randomItem.getName());
                    // Assuming there's logic to equip or add this item to inventory
                } else {
                    System.out.println("Not enough gold.");
                }
                break;
            case "4":
                System.out.println("Thank you for visiting!");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    public static void equipItem(Character player, Item item) {
        if (item instanceof Helmet) {
            player.equipHelmet((Helmet) item);
        } else if (item instanceof Weapon) {
            player.equipWeapon((Weapon) item);
        } else if (item instanceof Armor) {
            player.equipArmor((Armor) item);
        }
        System.out.println("You equipped the " + item.getName() + ".");
    }
}
