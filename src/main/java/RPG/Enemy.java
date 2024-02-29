public interface Enemy {
    String getName();
    int getAttack();
    int getHealth();
    void takeDamage(int damage);
}