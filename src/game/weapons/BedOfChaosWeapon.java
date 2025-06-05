package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.creatures.boss.BedOfChaos;

import java.util.Random;

public class BedOfChaosWeapon extends IntrinsicWeapon {

    private static final int BASE_DAMAGE = 25;
    private static final int HIT_RATE = 75;
    private final BedOfChaos boss;
    private final Random random = new Random();

    public BedOfChaosWeapon(BedOfChaos boss) {
        super(BASE_DAMAGE, "smacks", HIT_RATE);
        this.boss = boss;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        int damage = BASE_DAMAGE + boss.getTotalAttackPower();

        if (random.nextInt(100) >= this.hitRate) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(damage);
        return String.format("%s %s %s for %d damage",
                attacker, verb, target, damage);
    }
}
