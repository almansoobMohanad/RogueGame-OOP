package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

public class EffectsList implements Effect {

    private final List<Effect> effects = new ArrayList<>();

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    @Override
    public void apply(Actor actor, GameMap gameMap) {
        for (Effect effect : effects) {
            effect.apply(actor, gameMap);
        }
    }
}
