import java.util.Random;

public class GameEvents {
    private static final Random random = new Random();

    public static void handlePostCombat(Character player) {
        if (random.nextInt(100) < 30) { // 30% chance to find a chest
            System.out.println("You found a chest!");
            int foundCurrency = random.nextInt(50) + 10; // Random currency amount between 10 and 60
            player.addCurrency(foundCurrency);
            System.out.println("You found " + foundCurrency + " coins!");

            // Example of item finding logic
            // if (random.nextBoolean()) {
            //     Item foundItem = ... // Generate or select an item
            //     System.out.println("You found a " + foundItem.getName() + "!");
            //     // Here you'd add logic to let the player choose to equip the item, etc.
            // }

        } else { // Chance to restore health/mana
            int healthRestored = random.nextInt(20) + 10; // Restore between 10 and 30 health
            player.setHealth(Math.min(player.getHealth() + healthRestored, player.getMaxHealth()));
            System.out.println("You restored " + healthRestored + " health!");

            int manaRestored = random.nextInt(15) + 5; // Restore between 5 and 20 mana
            player.restoreMana(manaRestored);
            System.out.println("You restored " + manaRestored + " mana!");
        }
    }
}
