package game.LLM;

import edu.monash.fit2099.engine.displays.Display;

import java.util.Scanner;

public class ExtendedDisplay extends Display {

    private final Scanner scanner = new Scanner(System.in);

    public String readLine(String prompt) {
        this.print(prompt);
        return scanner.nextLine();
    }



}
