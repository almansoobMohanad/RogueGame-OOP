package game.actors.creatures;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import game.behaviours.AttackBehaviour;
import game.behaviours.GrowBehaviour;
import game.behaviours.NPCController;
import game.weapons.BedOfChaosWeapon;

import java.util.ArrayList;
import java.util.List;

public class BedOfChaos extends Creature {

    public static int MAX_HEALTH = 1000;

    private final List<Branch> branches = new ArrayList<>();
    private Branch lastBranch = null;
    private int leafCount = 0;

    public BedOfChaos(NPCController controller) {
        super("Bed of Chaos", 'T', MAX_HEALTH, controller);

        BedOfChaosWeapon weapon = new BedOfChaosWeapon(this);
        this.setIntrinsicWeapon(weapon);

        this.addBehaviour(0, new AttackBehaviour(0));

        this.addBehaviour(1, new GrowBehaviour());
    }

    public void growChain(Display display) {
        int currentHP = this.getAttribute(BaseActorAttributes.HEALTH);
        int maxHP = this.getAttributeMaximum(BaseActorAttributes.HEALTH);
        display.println(this + " (" + currentHP + "/" + maxHP + ") is growing...");

        Branch newBranch = new Branch(this);
        addBranch(newBranch);

        display.println("It grows a branch...");

        newBranch.chainGrow(display);
    }

    public void continueGrowChain(Display display) {
        if (lastBranch != null) {
            lastBranch.chainGrow(display);
        }
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
        lastBranch = branch;
    }

    public int getBranchCount() {
        return branches.size();
    }

    public int getLeafCount() {
        return leafCount;
    }

    public void incrementLeafCount() {
        leafCount++;
    }

}


