package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonologuePool implements Monologue {
    private final List<Monologue> pool;
    private final Random random;

    public MonologuePool() {
        this.pool = new ArrayList<>();
        this.random = new Random();
    }

    public void addMonologue(Monologue monologue) {
        this.pool.add(monologue);
    }

    @Override
    public String listen(Actor actor, GameMap map) {
        ArrayList<Monologue> availableMonologues = new ArrayList<>();

        for (Monologue monologue : this.pool) {
            String message = monologue.listen(actor, map);
            if (message != null) {
                availableMonologues.add(monologue);
            }
        }
        return availableMonologues.get(random.nextInt(availableMonologues.size())).listen(actor, map);
    }
}
