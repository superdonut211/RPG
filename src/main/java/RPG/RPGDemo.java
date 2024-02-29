import java.util.Random;
import java.util.Scanner;

public class RPGDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your character class:");
        System.out.println("1: Fighter");
        System.out.println("2: Mage");
        System.out.println("3: Thief");
        System.out.println("Enter the number for your choice or anything else for random:");

        CharacterClass chosenClass;
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                chosenClass = CharacterClass.FIGHTER;
                break;
            case "2":
                chosenClass = CharacterClass.MAGE;
                break;
            case "3":
                chosenClass = CharacterClass.THIEF;
                break;
            default:
                // Assign a random class
                CharacterClass[] classes = CharacterClass.values();
                chosenClass = classes[new Random().nextInt(classes.length)];
                System.out.println("Randomly assigned class: " + chosenClass);
                break;
        }

        Character player = new Character("Hero", chosenClass, "Human", 100, 10, 5, 8);

        // Simulate an enemy encounter (this part remains unchanged)
        Enemy enemy = EnemyFactory.createEnemy("orc");
        System.out.println("You encounter an " + enemy.getName() + "!");
        while(player.getHealth() > 0 && enemy.getHealth() > 0) {
            enemy.takeDamage(player.getAttack());
            System.out.println("You attack the " + enemy.getName() + " for " + player.getAttack() + " damage.");
            if(enemy.getHealth() <= 0) {
                System.out.println("You defeated the " + enemy.getName() + "!");
                break;
            }
            
            player.setHealth(player.getHealth() - enemy.getAttack());
            System.out.println("The " + enemy.getName() + " attacks you for " + enemy.getAttack() + " damage.");
            if(player.getHealth() <= 0) {
                System.out.println("You were defeated by the " + enemy.getName() + "...");
            }
        }

        scanner.close();
    }
}
