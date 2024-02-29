
public class Character {
	private String name;
    private CharacterClass characterClass;
    private String characterType; // Consider implementing similar enum for character types
    private int health;
    private int attack;
    private int defense;
    private int speed;

    public Character(String name, CharacterClass characterClass, String characterType, int health, int attack, int defense, int speed) {
        this.name = name;
        this.characterClass = characterClass;
        this.characterType = characterType;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    // Getters and setters for character attributes
    public String getName() {
        return name;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public String getCharacterType() {
        return characterType;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    // Additional methods like attacking, defending, etc., can be added here.
}
