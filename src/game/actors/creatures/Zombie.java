package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.AttackBehaviour;
import game.behaviours.NPCController;
import game.behaviours.WanderBehaviour;
import game.effects.AttributeEffect;
import game.effects.EffectsList;
import game.timemanagement.Phases;
import game.timemanagement.ServiceLocator;
import game.timemanagement.TimeAware;

import java.util.Map;

public class Zombie extends Creature implements TimeAware {
    private static final int DEFAULT_HIT_POINTS = 100;

    private final Phases currentPhase;
    private final NPCController controller;
    /**
     * Constructor for a Creature.
     *
     * @param controller
     */
    public Zombie(NPCController controller) {
        super("Zombie", 'Z', DEFAULT_HIT_POINTS, controller);
        this.addBehaviour(0, new AttackBehaviour());
        this.addBehaviour(1, new WanderBehaviour());
        this.currentPhase = ServiceLocator.getTimeProvider().getCurrentPhase();
        this.controller = controller;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (currentPhase == Phases.DAY) {
            this.hurt(15);
        }
        return controller.playturn(this.getBehaviours(), this, map, display);
    }

    @Override
    public void onTimeChange(Location location) {
    }
}
