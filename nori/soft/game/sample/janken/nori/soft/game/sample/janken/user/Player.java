package nori.soft.game.sample.janken.user;

public abstract class Player {
    public enum Hand {
        ROCK, SCISSORS, PAPER,NONE,
    }

    public Hand hand = null;
    public static final int rock = 0, scissors = 1, paper = 2;
    public int winCount=0,loseCount=0;
    public String handStatus="",name="";

    public abstract void execCommand(int command);

    public abstract void setName(String name);

    public abstract String getHandStatus();

    public abstract String getName();
}
