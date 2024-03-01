import java.util.Random;

public class CombatManager {
    private RandomGenerator randomGenerator; // Strategy for generating random numbers, can be swapped for different implementations.
    private int startingEHealth;
    private StatusEffect currentStatusEffect = StatusEffect.NONE; // Current strategy for status effects, which can change during combat.
    private FloorManager floorManager;
    public FloorEffect floorEffect; // Strategy for floor effects, changes based on the current floor.
    private int originalPlayerSpeed;
    private int originalEnemySpeed;

    // Constructor allows for injecting a specific random number generation strategy.
    public CombatManager(FloorManager floorManager2, RandomGenerator randomGenerator) {
        this.floorManager = floorManager2;
        this.floorEffect = floorManager.getCurrentFloorEffect(); // Sets the current floor effect strategy.
        this.randomGenerator = randomGenerator; // Assigns the random number generation strategy.
    }

    // Overloaded constructor uses a default random generation strategy, demonstrating flexibility in strategy selection.
    public CombatManager(FloorManager floorManager) {
        this.randomGenerator = new DefaultRandomGenerator();
        this.floorManager = floorManager;
        this.floorEffect = floorManager.getCurrentFloorEffect();
    }

    public void startCombat(Character player, Enemy enemy) {
        // Initial setup for combat, including determining who goes first based on speed, demonstrates strategy in turn order.
        System.out.println("A wild " + enemy.getName() + " appears!");
        boolean playerTurn = player.getSpeed() >= enemy.getSpeed();
        startingEHealth = enemy.getHealth();
        currentStatusEffect = StatusEffect.NONE;
        originalPlayerSpeed = player.getSpeed();
        originalEnemySpeed = enemy.getSpeed();
        applyFloorEffect(player, enemy); // Applies a strategy based on the current floor effect.

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            if (playerTurn) {
                performPlayerTurn(player, enemy);
            } else {
                performEnemyTurn(player, enemy);
            }
            playerTurn = !playerTurn;
            pause(); // Adds a pause between turns, affecting the rhythm of combat as a subtle strategy.
        }
        checkCombatOutcome(player, enemy, startingEHealth);
        resetSpeed(player, enemy); // Resets speeds, reverting any temporary strategy changes.
    }

    private void applyFloorEffect(Character player, Enemy enemy) {
        // Applies a specific strategy based on the floor's effect, impacting the combat rules.
        switch (floorEffect) {
            case DOUBLE_DAMAGE:
            case HALF_DAMAGE:
            case SWAP_SPEED:
                // Each case applies a different combat strategy.
                break;
            default:
                System.out.println("No special floor effect.");
                break;
        }
    }

    private void performPlayerTurn(Character player, Enemy enemy) {
        // Decision to use a special ability or perform a regular attack is a strategic choice based on the context (random chance here).
        if (randomGenerator.nextInt(3) == 0) {
            useSpecialAbility(player, enemy); // Applies a strategy based on the player's class.
        } else {
            performRegularAttack(player, enemy); // Default attack strategy.
        }
    }

    private void performEnemyTurn(Character player, Enemy enemy) {
        // Strategy for enemy's turn can change based on the current status effect.
        if (currentStatusEffect == StatusEffect.SKIP_TURN) {
            System.out.println("Enemy skips their turn due to Thief's trickery!");
            currentStatusEffect = StatusEffect.NONE; // Resets the strategy for the next turn.
        } else {
            performEnemyAttack(player, enemy);
        }
    }
    private void performEnemyAttack(Character player, Enemy enemy) {
        int damage = enemy.getAttack() - player.getDefense();
        if (floorEffect == FloorEffect.HALF_DAMAGE) {
            damage /= 2;
        } else if (floorEffect == FloorEffect.DOUBLE_DAMAGE) {
            damage *= 2;
        }
        damage = Math.max(damage, 1); // Ensure minimum damage
        player.setHealth(player.getHealth() - damage);
        System.out.println("Enemy attacks for " + damage + " damage.");
    }

    private void performRegularAttack(Character player, Enemy enemy) {
        int damage = player.getAttack() - enemy.getDefense();
        if (floorEffect == FloorEffect.HALF_DAMAGE) {
            damage /= 2;
        } else if (floorEffect == FloorEffect.DOUBLE_DAMAGE) {
            damage *= 2;
        }
        if (randomGenerator.nextInt(10) < 1) { // 10% chance for critical hit
            damage *= 2;
            System.out.println("Critical Hit!");
        }
        damage = Math.max(damage, 1); // Ensure minimum damage
        enemy.takeDamage(damage);
        System.out.println("You dealt " + damage + " damage.");
    }

    private void checkCombatOutcome(Character player, Enemy enemy, int startingEHealth) {
        if (enemy.getHealth() <= 0) {
            System.out.println("You have defeated the " + enemy.getName() + "!");
            player.addXp(startingEHealth);
        } else if (player.getHealth() <= 0) {
            System.out.println("You were defeated by the " + enemy.getName() + "...");
        }
        // Reset any temporary effects after combat
        if (floorEffect == FloorEffect.SWAP_SPEED) {
            resetSpeed(player, enemy);
        }
    }

    private void resetSpeed(Character player, Enemy enemy) {
        player.setSpeed(originalPlayerSpeed); // Reset player speed to original
        enemy.setSpeed(originalEnemySpeed); // Reset enemy speed to original
        System.out.println("Speed attributes reset to normal.");
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
                System.out.println("The enemy takes " + (damage * 2) + " damage from your curse.");
                break;
            default:
                System.out.println("No special ability for this class. Performing regular attack.");
                performRegularAttack(player, enemy);
                break;
        }
    }
 // Helper method to pause execution
    private void pause() {
        try {
            Thread.sleep(1000); // Pause for 1 second
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.out.println("Combat was interrupted.");
        }
    }
}
