import java.util.Random;

public class CombatManager {
    private Random random = new Random();

    public void startCombat(Character player, Enemy enemy) {
        System.out.println("A wild " + enemy.getName() + " appears!");

        boolean playerTurn = player.getSpeed() >= enemy.getSpeed();

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            if (playerTurn) {
                System.out.println("It's your turn.");
                int baseDamage = player.getAttack() - enemy.getDefense();
                baseDamage = Math.max(baseDamage, 1); // Ensure minimum damage

                // Check for critical hit
                if (random.nextInt(100) < 10) { // 10% chance for critical hit
                    baseDamage *= 2; // Double the damage for critical hit
                    System.out.println("Critical Hit!");
                }

                enemy.takeDamage(baseDamage);
                System.out.println("You dealt " + baseDamage + " damage to the " + enemy.getName() + ".");
                System.out.println("You are at: " + player.getHealth() + " HP.\n");
                System.out.println(enemy.getName() + " is at: " + enemy.getHealth() + " HP.");
            } else {
                System.out.println("It's the enemy's turn.");
                int baseDamage = enemy.getAttack() - player.getDefense();
                baseDamage = Math.max(baseDamage, 1); // Ensure minimum damage

                player.setHealth(player.getHealth() - baseDamage);
                System.out.println("The " + enemy.getName() + " dealt " + baseDamage + " damage to you.");
                System.out.println("You are at: " + player.getHealth() + " HP.\n");
                System.out.println(enemy.getName() + " is at: " + enemy.getHealth() + " HP.");
            }

            playerTurn = !playerTurn; // Switch turns

            if (enemy.getHealth() <= 0) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                System.out.println("You are at: " + player.getHealth() + " HP.\n");
                GameEvents.handlePostCombat(player); // Handle post-combat events
            } else if (player.getHealth() <= 0) {
                System.out.println("You were defeated by the " + enemy.getName() + "...");
            }
        }
    }
}
