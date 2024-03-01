public class EnemyManager {
    // Static variable to hold the one instance of EnemyManager that will exist.
    private static EnemyManager instance;

    // Instance of EnemyFactory used to create enemies. This composition allows EnemyManager to delegate the creation details to EnemyFactory.
    private EnemyFactory enemyFactory = new EnemyFactory();

    // Private constructor so no other class can instantiate EnemyManager directly.
    private EnemyManager() {}

    // Public static method that provides the global point of access to the EnemyManager instance.
    public static EnemyManager getInstance() {
        // If the instance doesn't exist, create it. This is known as "lazy initialization".
        if (instance == null) {
            instance = new EnemyManager();
        }
        // Return the single instance of EnemyManager.
        return instance;
    }

    // Method to spawn an enemy based on the type and floor number. This method delegates the creation of enemies to the EnemyFactory class.
    public Enemy spawnEnemy(String enemyType, int floorNumber) {
        // The switch statement translates the high-level request into a specific call to the enemy factory,
        // demonstrating how the Singleton can delegate to other components while controlling access and logic flow.
        switch (enemyType) {
            case "SMALL":
                return enemyFactory.createEnemy("small", floorNumber);
            case "MEDIUM":
                return enemyFactory.createEnemy("medium", floorNumber);
            case "BOSS":
                return enemyFactory.createEnemy("boss", floorNumber);
            default:
                return null;
        }
    }
}