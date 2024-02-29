public interface Enemy {
	StatusEffect statusEffect = null;
    String getName();
    int getAttack();
    int getHealth();
    void takeDamage(int damage);
    int getSpeed();
    int getDefense();
    void applyStatusEffect(StatusEffect effect);
    StatusEffect getStatusEffect();
    void clearStatusEffect();
}