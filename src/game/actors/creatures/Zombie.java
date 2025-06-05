package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.AttackBehaviour;
import game.behaviours.NPCController;
import game.behaviours.WanderBehaviour;
import game.timemanagement.Phases;
import game.timemanagement.ServiceLocator;
import game.timemanagement.TimeAware;
import game.weapons.BareFist;

public class Zombie extends Creature implements TimeAware {
    private static final int DEFAULT_HIT_POINTS = 50;
    private static final int DAMAGE_TAKEN_IN_DAY_PHASE = 20;
    private Phases currentPhase;
    private final NPCController controller;
    /**
     * Constructor for a Creature.
     *
     * @param controller
     */
    public Zombie(NPCController controller) {
        super("Zombie", 'Z', DEFAULT_HIT_POINTS, controller);
        this.setIntrinsicWeapon(new BareFist());
        this.addBehaviour(0, new AttackBehaviour());
        this.addBehaviour(1, new WanderBehaviour());
        this.currentPhase = ServiceLocator.getTimeProvider().getCurrentPhase();
        this.controller = controller;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.onTimeChange(map.locationOf(this));

        if (!this.isConscious()){
            this.unconscious(map);
        }

        return controller.playturn(this.getBehaviours(), this, map, display);
    }

    @Override
    public void onTimeChange(Location location) {
        this.currentPhase = ServiceLocator.getTimeProvider().getCurrentPhase();
        if (this.currentPhase == Phases.DAY) {
            this.hurt(DAMAGE_TAKEN_IN_DAY_PHASE);
        }
    }

}
