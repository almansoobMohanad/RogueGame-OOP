package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.weapons.Buyable;

public class BuyAction extends Action {

    private Buyable item;
    private Merchant merchant;

    public BuyAction(Buyable item, Merchant merchant){
        this.item = item;
        this.merchant  = merchant;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        int cost = item.getCost();

        if (actor.getBalance() < cost) {
            return actor + " does not have enough Runes to buy " + item;
        }


        //item.onPurchase(actor);
        actor.addItemToInventory((Item) item);
        item.onPurchase(actor);
        merchant.decideEffect(item, actor);

        return actor + " buys " + item + " from " + merchant;


    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + item + " from " + merchant + " for " + item.getCost() + " runes";
    }
}
