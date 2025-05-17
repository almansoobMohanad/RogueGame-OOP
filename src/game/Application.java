package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.actors.creatures.SpiritGoat;
import game.actors.npcs.Guts;
import game.actors.npcs.MerchantKale;
import game.actors.npcs.Sellen;
import game.grounds.Blight;
import game.grounds.Floor;
import game.grounds.Soil;
import game.grounds.Wall;
import game.grounds.plants.Bloodrose;
import game.grounds.plants.Inheritree;
import game.items.Talisman;
import game.items.Seed;

/**
 * The main class to setup and run the game.
 * @author Adrian Kristanto
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
                new Wall(), new Floor(), new Soil());

        List<String> map = Arrays.asList(
                "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");

        GameMap gameMap = new GameMap("Valley of the Inheritree", groundFactory, map);
        world.addGameMap(gameMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Farmer", '@', 100, 200);
        player.hurt(20);
        player.addItemToInventory(new Seed("Bloodrose Seed", new Bloodrose(), 75));
        player.addItemToInventory(new Seed("Inheritree Seed", new Inheritree(), 25));
        world.addPlayer(player, gameMap.at(23, 10));

        // game setup
        gameMap.at(24, 11).addItem(new Talisman());
        gameMap.at(20, 14).addActor(new SpiritGoat());
        gameMap.at(35, 14).addActor(new OmenSheep());
        gameMap.at(20, 4).addActor(new Sellen());
        gameMap.at(20, 6).addActor(new MerchantKale());
        gameMap.at(21,5).addActor(new Guts());

        gameMap.at(8, 7).addActor(new OmenSheep());
        gameMap.at(20, 12).addActor(new GoldenBeetle());
        world.run();

        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }
}
