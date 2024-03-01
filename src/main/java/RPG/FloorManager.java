public class FloorManager {

    private int currentFloor = 1;

    public void nextFloor() {
        currentFloor++;
        System.out.println("You have moved to floor " + currentFloor);
    }

    public void goToMostRecentTenthFloor() {
        int targetFloor = (currentFloor / 10) * 10 + 1;
        currentFloor = targetFloor == 1 ? 1 : targetFloor; // Ensure we don't go to floor 0
        System.out.println("Returning to floor " + currentFloor + " to recuperate.");
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
    
    public FloorEffect getCurrentFloorEffect() {
        int cycleLength = 15; // Total length of the cycle for all effects
        int cyclePosition = (currentFloor % cycleLength); // Position within the current cycle

        // Determine the effect based on the position in the cycle
        if (cyclePosition >= 1 && cyclePosition <= 5) {
            // First 5 floors of the cycle
            return FloorEffect.DOUBLE_DAMAGE;
        } else if (cyclePosition >= 6 && cyclePosition <= 10) {
            // Next 5 floors of the cycle
            return FloorEffect.HALF_DAMAGE;
        } else if (cyclePosition >= 11 && cyclePosition <= 15 || cyclePosition == 0) {
            // Last 5 floors of the cycle, including the case where cyclePosition is 0 (which means it's exactly on the cycle boundary)
            return FloorEffect.SWAP_SPEED;
        } else {
            // Default case, should not be reached due to the modulo operation, but included for completeness
            return FloorEffect.NONE;
        }
    }
}
