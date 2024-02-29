import java.util.Random;

public class CombatManager {
    private Random random = new Random();
    private int startingEHealth;
    private StatusEffect currentStatusEffect = StatusEffect.NONE;
    public void startCombat(Character player, Enemy enemy) {
    	System.out.println("A wild " + enemy.getName() + " appears!");
        boolean playerTurn = player.getSpeed() >= enemy.getSpeed();
        startingEHealth = enemy.getHealth();
        // Reset status effect at the start of combat
        currentStatusEffect = StatusEffect.NONE;

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            if (playerTurn) {
                if (random.nextInt(3) == 0) { // 1/3 chance
                    useSpecialAbility(player, enemy);
                } else {
                    performRegularAttack(player, enemy);
                } 
            } else {
                // Check for skip turn effect
                if (currentStatusEffect == StatusEffect.SKIP_TURN) {
                    System.out.println("The enemy skips their turn due to the Thief's trickery!");
                    currentStatusEffect = StatusEffect.NONE; // Reset status effect after it takes effect
                    playerTurn = !playerTurn; // Maintain player's turn
                    continue; // Skip to the next iteration, keeping it the player's turn
                }
                
                System.out.println("It's the enemy's turn.");
                performEnemyAttack(player, enemy);
            }

            playerTurn = !playerTurn; // Switch turns

            if (enemy.getHealth() <= 0) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                System.out.println("You are at: " + player.getHealth() + " HP.\n");
                player.addXp(startingEHealth);
                System.out.println("You gained: " + startingEHealth + " XP.\n");
                GameEvents.handlePostCombat(player); // Handle post-combat events
            } else if (player.getHealth() <= 0) {
                System.out.println("You were defeated by the " + enemy.getName() + "...");
            }
        }
    }
    
    private void performEnemyAttack(Character player, Enemy enemy) {
        int baseDamage = enemy.getAttack() - player.getDefense();
        baseDamage = Math.max(baseDamage, 1); // Ensure minimum damage
        
        // Apply HALF_DAMAGE status effect
        if (currentStatusEffect == StatusEffect.HALF_DAMAGE) {
            baseDamage /= 2; // Half the damage due to Fighter's intimidation
            System.out.println("The enemy's attack is weakened by your intimidation.");
            currentStatusEffect = StatusEffect.NONE; // Reset status effect after it takes effect
        }

        player.setHealth(player.getHealth() - baseDamage);
        System.out.println("The " + enemy.getName() + " dealt " + baseDamage + " damage to you.");
    }
    
    private void performRegularAttack(Character player, Enemy enemy) {
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
    }
    
    private void useSpecialAbility(Character player, Enemy enemy) {
        if (player.getMana() < 10) {
            System.out.println("Not enough mana for special ability. Performing regular attack instead.");
            performRegularAttack(player, enemy);
            return;
        }

        player.consumeMana(20); // Consume mana for special ability
        System.out.println("Using special ability!");

        int damage = 10 + player.getLevel() * 2; 

        switch (player.getCharacterClass()) {
            case THIEF:
                System.out.println("Thief's trickery! The enemy will skip their next turn.");
                enemy.takeDamage(damage); // Thief ability deals damage
                currentStatusEffect = StatusEffect.SKIP_TURN;
                break;
            case FIGHTER:
                System.out.println("Fighter's intimidation! The enemy deals half damage on their next turn.");
                enemy.takeDamage(damage); // Fighter ability deals damage
                currentStatusEffect = StatusEffect.HALF_DAMAGE;
                break;
            case MAGE:
                System.out.println("Mage's curse! The enemy takes double damage on their next turn.");
                enemy.takeDamage(damage * 2); // Mage ability deals damage
                break;
            default:
                System.out.println("No special ability for this class. Performing regular attack.");
                performRegularAttack(player, enemy);
                break;
        }
    }
    
}
