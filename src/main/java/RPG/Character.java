public class Character {
    private String name;
    private CharacterClass characterClass;
    private CharacterRace characterRace;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private Helmet helmet;
    private Weapon weapon; 
    private Armor armor;

    public Character(String name, CharacterClass characterClass, CharacterRace characterRace, int health, int attack, int defense, int speed) {
        this.name = name;
        this.characterClass = characterClass;
        this.characterRace = characterRace;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        adjustStatsForRace();
    }

    private void adjustStatsForRace() {
        switch (this.characterRace) {
            case ORC:
                this.attack += 2;
                break;
            case ELF:
                this.speed += 2;
                break;
            case DWARF:
                this.defense += 2;
                break;
            case HUMAN:
                this.health -= 10;
                break;
        }
    }

    public void equipHelmet(Helmet helmet) {
        this.helmet = helmet;
        applyItemBonuses();
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        applyItemBonuses();
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
        applyItemBonuses();
    }

    public String getHelmetName() {
        return this.helmet != null ? this.helmet.getName() : "None";
    }

    public String getWeaponName() {
        return this.weapon != null ? this.weapon.getName() : "None";
    }

    public String getArmorName() {
        return this.armor != null ? this.armor.getName() : "None";
    }

    private void applyItemBonuses() {
        this.health = 100;
        this.attack = 10;
        this.defense = 5;
        this.speed = 8;

        if (helmet != null) {
            this.health += helmet.getBonusHealth();
            this.defense += helmet.getBonusDefense();
            this.speed += helmet.getBonusSpeed();
        }
        if (weapon != null) {
            this.attack += weapon.getBonusAttack();
        }
        if (armor != null) {
            this.health += armor.getBonusHealth();
            this.defense += armor.getBonusDefense();
        }
        adjustStatsForRace();
    }

    // Getters and Setters
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
}
