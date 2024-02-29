public class FloorManager {
    private int currentFloor = 1;

    public void nextFloor() {
        currentFloor++;
        System.out.println("You have moved to floor " + currentFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public String getEnemyTypeForCurrentFloor() {
        if (currentFloor % 10 == 0) {
            return "BOSS";
        } else if (currentFloor % 5 == 0) {
            return "MEDIUM";
        } else {
            return "SMALL";
        }
    }
}
