import java.util.Random;
import java.util.Scanner;

public class RPGDemo {
    public static void main(String[] args) {
        FloorManager floorManager = new FloorManager();
        EnemyManager enemyManager = new EnemyManager();

        System.out.println("Choose your character class:");
        System.out.println("1: Fighter\n2: Mage\n3: Thief\nEnter number for choice or anything else for random:");
        CharacterClass chosenClass = chooseClass(InputScanner.SCANNER.nextLine());

        System.out.println("Choose your character race:");
        System.out.println("1: Orc\n2: Elf\n3: Dwarf\n4: Human\nEnter number for choice or anything else for random:");
        CharacterRace chosenRace = chooseRace(InputScanner.SCANNER.nextLine());

        Character player = new Character("Hero", chosenClass, chosenRace, 100, 10, 5, 8, 10);
        CombatManager combatManager = new CombatManager();
        Item helmet = ItemFactory.createItem("Helmet", "Steel Helm", 0, 0, 2, 0);
        Item weapon = ItemFactory.createItem("Weapon", "Sword", 0, 5, 0, 0);
        Item armor = ItemFactory.createItem("Armor", "Chainmail", 10, 0, 3, 0);

        player.equipHelmet((Helmet) helmet);
        player.equipWeapon((Weapon) weapon);
        player.equipArmor((Armor) armor);
        
        boolean gameRunning = true;
        while (gameRunning) {
            player.printRelevantInfo();
            floorManager.nextFloor();
            String enemyType = floorManager.getEnemyTypeForCurrentFloor();
            Enemy enemy = enemyManager.spawnEnemy(enemyType,floorManager.getCurrentFloor());
            
            if (enemy != null) {
                System.out.println("Encountering enemy: " + enemy.getName());
                combatManager.startCombat(player, enemy, floorManager);
                
                if (player.getHealth() <= 0) {
                    if (floorManager.getCurrentFloor() % 10 == 0) {
                        System.out.println("You were defeated on the boss floor. Game over.");
                        break; // Game over only on tenth (boss) floor
                    } else {
                        System.out.println("Defeated, but not on a boss floor. Losing 50 gold and returning to the most recent safe floor to recuperate.");
                        player.addCurrency(-50); // Losing 50 gold
                        floorManager.goToMostRecentTenthFloor();
                        player.setHealth(player.getMaxHealth()); // Restore health
                        player.restoreMana(player.getMaxMana()); // Restore mana
                        continue; // Skip to the next iteration, allowing the game to continue
                    }
                }
            } else {
                System.out.println("No enemies on this floor.");
            }

            // After combat or shop, check player's health
            if (((double)player.getHealth() / player.getMaxHealth()) < 0.15) {
                System.out.println("Critically injured, returning to the most recent safe floor to recuperate.");
                floorManager.goToMostRecentTenthFloor();
                player.setHealth(player.getMaxHealth()); // Restore health
                player.restoreMana(player.getMaxMana()); // Restore mana
            }

            System.out.println("Continue to the next floor? (yes/no)");
            String input = InputScanner.SCANNER.nextLine();
            if (!input.equalsIgnoreCase("yes")) {
                gameRunning = false;
            }
        }


        InputScanner.closeScanner();
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
