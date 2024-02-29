public class Character {
    private String name;
    private CharacterClass characterClass;
    private CharacterRace characterRace; // New attribute for race
    private int health;
    private int attack;
    private int defense;
    private int speed;

    // Constructor updated to include CharacterRace
    public Character(String name, CharacterClass characterClass, CharacterRace characterRace, int health, int attack, int defense, int speed) {
        this.name = name;
        this.characterClass = characterClass;
        this.characterRace = characterRace;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        adjustStatsForRace(); // Adjust stats based on race
    }

    private void adjustStatsForRace() {
        switch (this.characterRace) {
            case ORC:
                this.attack += 2; // Example: Orcs have higher attack
                break;
            case ELF:
                this.speed += 2; // Elves are faster
                break;
            case DWARF:
                this.defense += 2; // Dwarves are more defensive
                break;
            case HUMAN:
                this.health -= 10; // Humans have less HP but could have other advantages
                break;
        }
    }

    // Getters and setters for character attributes
    public String getName() {
        return name;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public CharacterRace getCharacterRace() {
        return characterRace;
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
