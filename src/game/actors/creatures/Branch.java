package game.actors.creatures;

import edu.monash.fit2099.engine.displays.Display;

public class Branch {

    private final BedOfChaos boss;

    public Branch(BedOfChaos boss) {
        this.boss = boss;
    }

    public void chainGrow(Display display) {
        display.println("Branch is growing...");

        if (Math.random() < 0.5) {
            Branch newBranch = new Branch(boss);
            boss.addBranch(newBranch);

            display.println("It grows a branch...");
        }
        else {
            boss.incrementLeafCount();
            display.println("It grows a leaf...");
            boss.heal(5);

            display.println(boss + "is healed.");
        }
    }
}
