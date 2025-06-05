package game.actors.creatures.boss;

import edu.monash.fit2099.engine.displays.Display;
import game.actors.creatures.Creature;
import game.behaviours.AttackBehaviour;
import game.behaviours.GrowBehaviour;
import game.behaviours.NPCController;
import game.weapons.BedOfChaosWeapon;

import java.util.ArrayList;
import java.util.List;

public class BedOfChaos extends Creature {

    public static final int MAX_HEALTH = 1000;

    private final List<Growable> growables = new ArrayList<>();

    public BedOfChaos(NPCController controller) {
        super("Bed of Chaos", 'T', MAX_HEALTH, controller);
        this.setIntrinsicWeapon(new BedOfChaosWeapon(this));
        this.addBehaviour(0, new AttackBehaviour(0));
        this.addBehaviour(1, new GrowBehaviour());
    }

    public void grow(Display display) {
        List<Growable> existingGrowables = new ArrayList<>(growables);

        display.println(this + " is growing...");

        if (Math.random() < 0.5) {
            Branch newBranch = new Branch(this);
            growables.add(newBranch);
            display.println("It grows a branch...");
        } else {
            Leaf newLeaf = new Leaf(this);
            growables.add(newLeaf);
            display.println("It grows a leaf...");
            this.heal(5);
            display.println(this + " is healed.");
        }

        for (Growable gc : existingGrowables) {
            gc.grow();
        }
    }

    public int getTotalAttackPower() {
        int total = 0;
        for (Growable growable : growables) {
            total += growable.getAttackPower();
        }
        return total;
    }
}


