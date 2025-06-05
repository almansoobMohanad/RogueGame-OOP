package game.items.edibles;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.effects.AttributeEffect;
import game.effects.EffectsList;
import game.effects.MultiplierEffect;
import game.timemanagement.Phases;
import game.timemanagement.ServiceLocator;
import game.timemanagement.TimeAware;

public class NightingaleBerry extends Item implements TimeAware, Edible {

    private Phases currentPhase;
    private final EffectsList effectsList;
    private static final int HEALTH_INCREASE_AMOUNT = 20;
    private static final float MULTIPLIER_UPDATED_VALUE = 1.5f;
    /**
     * Constructor.
     */
    public NightingaleBerry() {
        super("Nightingale Berry", '^', true);
        this.effectsList = new EffectsList();
        this.saturateEffectsList();
        this.currentPhase = ServiceLocator.getTimeProvider().getCurrentPhase();
    }

    private void saturateEffectsList(){
        this.effectsList.addEffect(new AttributeEffect(BaseActorAttributes.HEALTH, HEALTH_INCREASE_AMOUNT));
        this.effectsList.addEffect(new MultiplierEffect(MULTIPLIER_UPDATED_VALUE));
    }

    @Override
    public void onTimeChange(Location location) {
        this.currentPhase = ServiceLocator.getTimeProvider().getCurrentPhase();
        if (this.currentPhase == Phases.DAY){
            location.removeItem(this);
        }
    }

    @Override
    public void tick(Location currentLocation) {
        this.onTimeChange(currentLocation);
        super.tick(currentLocation);
    }

    @Override
    public String eat(Actor actor, GameMap map) {
        effectsList.apply(actor, map);
        actor.removeItemFromInventory(this);
        return actor + " eats the Nightingale Berry.";
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map){
        ActionList actions = super.allowableActions(owner, map);

        actions.add(new EatAction(this));

        return actions;
    }
}
