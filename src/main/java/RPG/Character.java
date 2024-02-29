public class Character {
    private String name;
    private CharacterClass characterClass;
    private CharacterRace characterRace;
    private int health, maxHealth;
    private int mana, maxMana;
    private int currency; 
    private int attack;
    private int defense;
    private int speed;
    private Helmet helmet;
    private Weapon weapon; 
    private Armor armor;

    // Adjust the constructor to include maxHealth and maxMana
    public Character(String name, CharacterClass characterClass, CharacterRace characterRace, int maxHealth, int maxMana, int attack, int defense, int speed) {
        this.name = name;
        this.characterClass = characterClass;
        this.characterRace = characterRace;
        this.maxHealth = maxHealth;
        this.health = maxHealth; // Initialize current health to maxHealth
        this.maxMana = maxMana;
        this.mana = maxMana; // Initialize current mana to maxMana
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.currency = 0; // Initialize currency
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

    public void addCurrency(int amount) {
        this.currency += amount;
    }

    public int getCurrency() {
        return currency;
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
    
    // Methods for mana management
    public void consumeMana(int amount) {
        this.mana = Math.max(this.mana - amount, 0);
    }

    public void restoreMana(int amount) {
        this.mana = Math.min(this.mana + amount, this.maxMana);
    }

    public int getMana() {
        return mana;
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
    
    public void setMaxHealth(int maxHealth) {
    	this.maxHealth = maxHealth;
    }
    public int getMaxHealth() {
    	return this.maxHealth;
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
