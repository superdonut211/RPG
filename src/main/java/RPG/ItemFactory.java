public class ItemFactory {
    public static Item createItem(String type, String name, int bonusHealth, int bonusAttack, int bonusDefense, int bonusSpeed) {
        switch (type) {
            case "Helmet":
                return new Helmet(name, bonusHealth, bonusDefense, bonusSpeed);
            case "Weapon":
                return new Weapon(name, bonusAttack);
            case "Armor":
                return new Armor(name, bonusHealth, bonusDefense);
            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
}
