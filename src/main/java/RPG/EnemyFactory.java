

public class EnemyFactory {
    public static Enemy createEnemy(String type) {
        switch (type) {
            case "orc":
                return new Orc();
            case "troll":
                return new Troll();
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}

class Orc implements Enemy {
    private int health = 20;
    private final int attack = 5;
    private final String name = "Orc";

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
}

class Troll implements Enemy {
    private int health = 30;
    private final int attack = 7;
    private final String name = "Troll";

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
}
