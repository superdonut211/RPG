import java.util.Random;
import java.util.Scanner;

public class RPGDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FloorManager floorManager = new FloorManager();
        EnemyManager enemyManager = new EnemyManager();

        System.out.println("Choose your character class:");
        System.out.println("1: Fighter\n2: Mage\n3: Thief\nEnter number for choice or anything else for random:");
        CharacterClass chosenClass = chooseClass(scanner.nextLine());

        System.out.println("Choose your character race:");
        System.out.println("1: Orc\n2: Elf\n3: Dwarf\n4: Human\nEnter number for choice or anything else for random:");
        CharacterRace chosenRace = chooseRace(scanner.nextLine());

        Character player = new Character("Hero", chosenClass, chosenRace, 100, 10, 5, 8);
        CombatManager combatManager = new CombatManager();
        Item helmet = ItemFactory.createItem("Helmet", "Steel Helm", 0, 0, 2, 0);
        Item weapon = ItemFactory.createItem("Weapon", "Sword", 0, 5, 0, 0);
        Item armor = ItemFactory.createItem("Armor", "Chainmail", 10, 0, 3, 0);

        player.equipHelmet((Helmet) helmet);
        player.equipWeapon((Weapon) weapon);
        player.equipArmor((Armor) armor);
        
        System.out.println("Player Helm: " + player.getHelmetName());
        System.out.println("Player Weapon: " + player.getArmorName());
        System.out.println("Player Weapon: " + player.getWeaponName());
        System.out.println("Player Attack: " + player.getAttack());
        System.out.println("Player Defense: " + player.getDefense());
        System.out.println("Player Health: " + player.getHealth());
        System.out.println("Player Speed: " + player.getSpeed());
        boolean gameRunning = true;

        while (gameRunning) {
            floorManager.nextFloor();
            String enemyType = floorManager.getEnemyTypeForCurrentFloor();
            Enemy enemy = enemyManager.spawnEnemy(enemyType);
            
            if (enemy != null) {
                System.out.println("Encountering enemy: " + enemy.getName());
                combatManager.startCombat(player, enemy); // Implement combat logic
                
                if (player.getHealth() <= 0) {
                    System.out.println("You were defeated. Game over.");
                    break; // Exit the game loop if the player is defeated
                }
            } else {
                System.out.println("No enemies on this floor.");
            }

            System.out.println("Continue to the next floor? (yes/no)");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("yes")) {
                gameRunning = false;
            }
        }

        scanner.close();
    }
    
    private static CharacterClass chooseClass(String input) {
        switch (input) {
            case "1": return CharacterClass.FIGHTER;
            case "2": return CharacterClass.MAGE;
            case "3": return CharacterClass.THIEF;
            default:
                CharacterClass[] classes = CharacterClass.values();
                return classes[new Random().nextInt(classes.length)];
        }
    }

    private static CharacterRace chooseRace(String input) {
        switch (input) {
            case "1": return CharacterRace.ORC;
            case "2": return CharacterRace.ELF;
            case "3": return CharacterRace.DWARF;
            case "4": return CharacterRace.HUMAN;
            default:
                CharacterRace[] races = CharacterRace.values();
                return races[new Random().nextInt(races.length)];
        }
    }
}
