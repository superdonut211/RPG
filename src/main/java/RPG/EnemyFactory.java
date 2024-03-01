

public class EnemyFactory {
	// This static method is the essence of the factory. It decides which type of Enemy to create based on the provided parameters.
    public static Enemy createEnemy(String type, int floor) {
        // Calculate the scaling factor based on the floor number to scale the enemy's attributes.
        int scalingFactor = floor * 2;

        switch (type) {
            case "small":
                // Creates a small Orc with scaled health and attack based on the floor number.
                return new Orc(20 + scalingFactor, 5 + scalingFactor / 2, "Small");
            case "medium":
                // Creates a medium Orc with more significant scaling for both health and attack.
                return new Orc(30 + scalingFactor, 7 + scalingFactor / 2, "Medium");
            case "boss":
                // Creates a boss Troll with the most significant scaling, including additional attributes like speed and defense.
                return new Troll(50 + scalingFactor, 10 + scalingFactor / 2, 10 + floor, 15 + floor / 2);
            default:
                // Throws an exception if the type is unknown, ensuring that only valid types are processed.
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}

class Orc implements Enemy {
    private int health;
    private int attack;
    private String name = "Orc";
    private int speed = 3; // Default speed
    private int defense = 2; // Default defense

    // Constructor for Orc with customizable stats, allowing flexibility in creating different Orc variants.
    public Orc(int health, int attack, String prefix) {
        this.name = prefix + " " + this.name; // Prefix adds specificity to the Orc type (e.g., "Small Orc").
        this.health = health;
        this.attack = attack;
    }

    // Implementation of Enemy interface methods below provides specific behavior for Orcs.
    @Override
    public String getName() { return name; }
    @Override
    public void setSpeed(int speed) { this.speed = speed; }
    @Override
    public int getAttack() { return attack; }
    @Override
    public int getHealth() { return health; }
    @Override
    public void takeDamage(int damage) { health -= damage; }
    @Override
    public int getSpeed() { return speed; }
    @Override
    public int getDefense() { return defense; }
}


class Troll implements Enemy {
    private int health;
    private int attack;
    private String name = "Troll";
    private int speed; // Customizable speed
    private int defense; // Customizable defense

    // Constructor for Troll allows setting of all attributes, making it highly flexible for different Troll variants.
    public Troll(int health, int attack, int speed, int defense) {
        this.health = health;
        this.attack = attack;
        this.speed = speed;
        this.defense = defense;
    }

    // Implementation of Enemy interface methods for Troll-specific behavior.
    @Override
    public String getName() { return name; }
    @Override
    public int getAttack() { return attack; }
    @Override
    public int getHealth() { return health; }
    @Override
    public void takeDamage(int damage) { health -= damage; }
    @Override
    public int getSpeed() { return speed; }
    @Override
    public void setSpeed(int speed) { this.speed = speed; }
    @Override
    public int getDefense() { return defense; }
}