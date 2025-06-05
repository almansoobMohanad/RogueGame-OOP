package game.actors.creatures.boss;

public class Leaf implements Growable {
    private static final int LEAF_ATTACK_POWER = 1;
    private static final int HEAL_AMOUNT = 5;

    private final BedOfChaos boss;

    public Leaf(BedOfChaos boss) {
        this.boss = boss;
    }

    @Override
    public int getAttackPower() {
        return LEAF_ATTACK_POWER;
    }

    @Override
    public void grow() {
        boss.heal(HEAL_AMOUNT);
    }
}

