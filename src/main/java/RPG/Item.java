public abstract class Item {
    protected String name;
    protected int bonusHealth;
    protected int bonusAttack;
    protected int bonusDefense;
    protected int bonusSpeed;

    public Item(String name, int bonusHealth, int bonusAttack, int bonusDefense, int bonusSpeed) {
        this.name = name;
        this.bonusHealth = bonusHealth;
        this.bonusAttack = bonusAttack;
        this.bonusDefense = bonusDefense;
        this.bonusSpeed = bonusSpeed;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public int getBonusSpeed() {
        return bonusSpeed;
    }

}
