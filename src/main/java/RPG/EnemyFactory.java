

public class EnemyFactory {
	public static Enemy createEnemy(String type, int floor) {
        // Calculate the scaling factor based on the floor number
        int scalingFactor = floor * 2;

        switch (type) {
            case "small":
                // Apply scaling to the health and attack of a small Orc
                return new Orc(20 + scalingFactor, 5 + scalingFactor / 2, "Small"); 
            case "medium":
                // Apply more significant scaling for a medium Orc
                return new Orc(30 + scalingFactor, 7 + scalingFactor / 2, "Medium"); 
            case "boss":
                // Apply the most significant scaling for the boss Troll
                return new Troll(50 + scalingFactor, 10 + scalingFactor / 2, 10 + floor, 15 + floor / 2); 
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
    public Orc(int health, int attack, String prefix) {
    	this.name = prefix + " Orc";
        this.health = health;
        this.attack = attack;
    }
    
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
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
}


class Troll implements Enemy {
	private StatusEffect statusEffect = null;
    private int health = 20;
    private int attack = 5;
    private String name = "Troll";
    private int speed = 10; 
    private int defense = 15; 


    public Troll(int health, int attack, int speed, int defense) {
        this.health = health;
        this.attack = attack;
        this.speed = speed;
        this.defense = defense;
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
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    @Override
    public int getDefense() {
        return defense;
    }
    
}