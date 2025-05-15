package game.actors.monologues;

public enum Monologues {
    SELLEN1("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever."),
    SELLEN2("You sense it too, don’t you? The Glintstone hums, even now."),
    KALE_LESS("Ah, hard times, I see. Keep your head low and your blade sharp."),
    KALE_EMPTY("Not a scrap to your name? Even a farmer should carry a trinket or two."),
    KALE_CURSED("Rest by the flame when you can, friend. These lands will wear you thin."),
    KALE_DEFAULT("A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen."),
    GUTS1("RAAAAGH!"),
    GUTS2("I’LL CRUSH YOU ALL!"),
    GUTS_WEAK("WEAK! TOO WEAK TO FIGHT ME!");


    private final String message;
    Monologues(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
