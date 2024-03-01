public class EnemyManager {
    private EnemyFactory enemyFactory = new EnemyFactory();

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
