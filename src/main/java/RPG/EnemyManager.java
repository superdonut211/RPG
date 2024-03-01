public class EnemyManager {
	private static EnemyManager instance;
    private EnemyFactory enemyFactory = new EnemyFactory();


    private EnemyManager() {}


    public static EnemyManager getInstance() {
        if (instance == null) {
            instance = new EnemyManager();
        }
        return instance;
    }

    public Enemy spawnEnemy(String enemyType, int floorNumber) {
        switch (enemyType) {
            case "SMALL":
                return enemyFactory.createEnemy("small", floorNumber);
            case "MEDIUM":
                return enemyFactory.createEnemy("medium", floorNumber);
            case "BOSS":
                return enemyFactory.createEnemy("boss", floorNumber);
            default:
                return null; // Or throw an exception if the type is unrecognized
        }
    }
}
