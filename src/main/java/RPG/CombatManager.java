import java.util.Random;

public class CombatManager {
	private RandomGenerator randomGenerator;
    private int startingEHealth;
    private StatusEffect currentStatusEffect = StatusEffect.NONE;
    private FloorManager floorManager;
    public FloorEffect floorEffect;
    private int originalPlayerSpeed;
    private int originalEnemySpeed;

    public CombatManager(FloorManager floorManager, RandomGenerator randomGenerator) {
        this.floorManager = floorManager;
        this.floorEffect = floorManager.getCurrentFloorEffect();
        this.randomGenerator = randomGenerator;
    }
    public CombatManager(FloorManager floorManager) {
    	this.randomGenerator = new DefaultRandomGenerator();
        this.floorManager = floorManager;
        this.floorEffect = floorManager.getCurrentFloorEffect();
    }

    public void startCombat(Character player, Enemy enemy) {
        System.out.println("A wild " + enemy.getName() + " appears!");
        boolean playerTurn = player.getSpeed() >= enemy.getSpeed();
        startingEHealth = enemy.getHealth();
        currentStatusEffect = StatusEffect.NONE;
        originalPlayerSpeed = player.getSpeed(); // Store original player speed
        originalEnemySpeed = enemy.getSpeed(); // Store original enemy speed
        applyFloorEffect(player, enemy);

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            if (playerTurn) {
                performPlayerTurn(player, enemy);
            } else {
                performEnemyTurn(player, enemy);
            }
            playerTurn = !playerTurn;
        }
        checkCombatOutcome(player, enemy, startingEHealth);
        resetSpeed(player, enemy); // Reset speeds after combat
    }

    private void applyFloorEffect(Character player, Enemy enemy) {
        switch (floorEffect) {
            case DOUBLE_DAMAGE:
                System.out.println("The floor's magic intensifies the battle! Double damage inflicted.");
                break;
            case HALF_DAMAGE:
                System.out.println("The floor's aura softens blows. Half damage inflicted.");
                break;
            case SWAP_SPEED:
                int tempSpeed = player.getSpeed();
                player.setSpeed(enemy.getSpeed());
                enemy.setSpeed(tempSpeed);
                System.out.println("The floor's trickery swaps speed attributes!");
                break;
            default:
                System.out.println("No special floor effect.");
                break;
        }
    }

    private void performPlayerTurn(Character player, Enemy enemy) {
        if (randomGenerator.nextInt(3) == 0) {
            useSpecialAbility(player, enemy);
        } else {
            performRegularAttack(player, enemy);
        }
    }

    private void performEnemyTurn(Character player, Enemy enemy) {
        if (currentStatusEffect == StatusEffect.SKIP_TURN) {
            System.out.println("Enemy skips their turn due to Thief's trickery!");
            currentStatusEffect = StatusEffect.NONE;
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
                System.out.println("The enemy takes " + (damage * 2)+ " damage from your curse.");
                break;
            default:
                System.out.println("No special ability for this class. Performing regular attack.");
                performRegularAttack(player, enemy);
                break;
        }
    }
}
