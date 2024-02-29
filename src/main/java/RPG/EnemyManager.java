public class EnemyManager {
    private EnemyFactory enemyFactory = new EnemyFactory();

    public Enemy spawnEnemy(String enemyType) {
        switch (enemyType) {
            case "SMALL":
                return enemyFactory.createEnemy("small");
            case "MEDIUM":
                return enemyFactory.createEnemy("medium");
            case "BOSS":
                return enemyFactory.createEnemy("boss");
            default:
                return null; // Or throw an exception if the type is unrecognized
        }
    }
}
