

public class EnemyFactory {
    public static Enemy createEnemy(String type) {
        switch (type) {
            case "small":
                return new Orc(); // Standard Orc for small enemy
            case "medium":
                return new Orc(30, 7); // Enhanced Orc for medium enemy
            case "boss":
                return new Troll(); // Troll for boss enemy
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}

class Orc implements Enemy {
	private StatusEffect statusEffect = null;
    private int health = 20;
    private int attack = 5;
    private String name = "Orc";
    private int speed = 3; // Example value
    private int defense = 2; // Example value

    // Default constructor for a small Orc
    public Orc() {
        this.health = 20;
        this.attack = 5;
    }

    // Constructor for variable stats, allowing for "medium" Orcs
    public Orc(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getDefense() {
        return defense;
    }
    @Override
    public void applyStatusEffect(StatusEffect effect) {
        this.statusEffect = effect;
    }

    @Override
    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    @Override
    public void clearStatusEffect() {
        this.statusEffect = null;
    }
}


class Troll implements Enemy {
	private StatusEffect statusEffect = null;
    private int health = 20;
    private int attack = 5;
    private String name = "Troll";
    private int speed = 10; 
    private int defense = 15; 


    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getDefense() {
        return defense;
    }
    @Override
    public void applyStatusEffect(StatusEffect effect) {
        this.statusEffect = effect;
    }

    @Override
    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    @Override
    public void clearStatusEffect() {
        this.statusEffect = null;
    }
    
}