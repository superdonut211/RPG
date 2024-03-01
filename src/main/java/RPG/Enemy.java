public interface Enemy {
    String getName();
    int getAttack();
    int getHealth();
    void takeDamage(int damage);
    int getSpeed();
    int getDefense();
    void setSpeed(int speed);
}