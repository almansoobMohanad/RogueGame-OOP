package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.creatures.BedOfChaos;

import java.util.Random;

public class BedOfChaosWeapon extends IntrinsicWeapon {

    private final BedOfChaos boss;
    private final Random random = new Random();

    public BedOfChaosWeapon(BedOfChaos boss) {
        super(25, "smacks", 75);
        this.boss = boss;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        int damage = 25
                + (boss.getBranchCount() * 3)
                + boss.getLeafCount();

        if (!(random.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(damage);

        return String.format("%s %s %s for %d damage",
                attacker, verb, target, damage);
    }
}
