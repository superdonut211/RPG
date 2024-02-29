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
    private int xp = 0;
    private int level = 1;
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
    public int getMaxMana() {
        return maxMana;
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
    
    public void addXp(int amount) {
        xp += amount;
        System.out.println("Gained " + amount + " XP.");
        
        // Check if the character has enough XP to level up
        while (xp >= xpToNextLevel()) {
            xp -= xpToNextLevel(); // Subtract the XP needed for the next level
            level++; // Increase character's level
            levelUp(); // Apply level-up benefits
            System.out.println("Congratulations! You've reached level " + level + "!");
        }
    }
    
    // Calculate the XP needed to reach the next level
    private int xpToNextLevel() {
        // This is a simple formula; adjust according to your game's design
        return 100 * level;
    }

    // Apply benefits when leveling up
    private void levelUp() {
        // Increase stats upon leveling up. Adjust these values as needed.
        maxHealth += 10;
        health = maxHealth; // Fully heal the character upon leveling up
        maxMana += 5;
        mana = maxMana; // Fully restore mana upon leveling up
        attack += 2;
        defense += 2;
        speed += 1;
        
        // Optionally, you can also grant a currency bonus upon leveling up
        currency += 50;
        System.out.println("Stats increased! Health, Mana, Attack, Defense, and Speed have been improved.");
    }

    // Getters for XP and level
    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }
    
    public void printAllInfo() {
        System.out.println("Character Name: " + name);
        System.out.println("Class: " + characterClass);
        System.out.println("Race: " + characterRace);
        System.out.println("Level: " + level);
        System.out.println("XP: " + xp + "/" + xpToNextLevel());
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Speed: " + speed);
        System.out.println("Currency: " + currency);
        System.out.println("Helmet: " + getHelmetName());
        System.out.println("Weapon: " + getWeaponName());
        System.out.println("Armor: " + getArmorName());
    }
    public void printRelevantInfo() {
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Level: " + level);
        System.out.println("XP: " + xp + "/" + xpToNextLevel());
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Speed: " + speed);
        System.out.println("Currency: " + currency);
        System.out.println("Equipped Items:");
        System.out.println("  Helmet: " + getHelmetName());
        System.out.println("  Weapon: " + getWeaponName());
        System.out.println("  Armor: " + getArmorName());
    }
}
