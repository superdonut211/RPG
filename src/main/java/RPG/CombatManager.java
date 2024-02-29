public class CombatManager {
    public void startCombat(Character player, Enemy enemy) {
        System.out.println("A wild " + enemy.getName() + " appears!");

        // Determine who attacks first based on speed
        boolean playerTurn = player.getSpeed() >= enemy.getSpeed();

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            if (playerTurn) {
                // Player's turn
                System.out.println("It's your turn.");
                int damageDealt = player.getAttack() - enemy.getDefense();
                damageDealt = Math.max(damageDealt, 1); // Ensure at least 1 damage is dealt
                enemy.takeDamage(damageDealt);
                System.out.println("You dealt " + damageDealt + " damage to the " + enemy.getName() + ".");
                System.out.println("You are at: " + player.getHealth() + " HP.\n");
                System.out.println(enemy.getName() + " is at: " + enemy.getHealth() + " HP.");
            } else {
                // Enemy's turn
                System.out.println("It's the enemy's turn.");
                int damageDealt = enemy.getAttack() - player.getDefense();
                damageDealt = Math.max(damageDealt, 1); // Ensure at least 1 damage is dealt
                player.setHealth(player.getHealth() - damageDealt);
                System.out.println("The " + enemy.getName() + " dealt " + damageDealt + " damage to you.");
                System.out.println("You are at: " + player.getHealth() + " HP.\n");
                System.out.println(enemy.getName() + " is at: " + enemy.getHealth() + " HP.");
            }

            playerTurn = !playerTurn; // Switch turns

            if (enemy.getHealth() <= 0) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                System.out.println("You are at: " + player.getHealth() + " HP.\n");
            } else if (player.getHealth() <= 0) {
                System.out.println("You were defeated by the " + enemy.getName() + "...");
            }
        }
    }
}
