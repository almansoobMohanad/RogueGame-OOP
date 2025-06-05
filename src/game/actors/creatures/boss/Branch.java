package game.actors.creatures.boss;

import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.List;

public class Branch implements Growable {

    private final BedOfChaos boss;
    private final List<Growable> growables = new ArrayList<>();

    private static final int BRANCH_BASE_ATTACK = 3;
    private static final int HEAL_AMOUNT = 5;

    public Branch(BedOfChaos boss) {
        this.boss = boss;
    }

    @Override
    public int getAttackPower() {
        int total = BRANCH_BASE_ATTACK;
        for (Growable child : growables) {
            total += child.getAttackPower();
        }
        return total;
    }

    @Override
    public void grow() {
        List<Growable> existingGrowables = new ArrayList<>(this.growables);

        Display display = new Display();
        display.println("Branch is growing...");

        if (Math.random() < 0.5) {
            Branch newBranch = new Branch(boss);
            this.growables.add(newBranch);
            display.println("It grows a branch...");
        } else {
            Leaf newLeaf = new Leaf(boss);
            this.growables.add(newLeaf);
            display.println("It grows a leaf...");
            boss.heal(HEAL_AMOUNT);
            display.println(boss + " is healed.");
        }

        for (Growable growable : existingGrowables) {
            growable.grow();
        }
    }
}
