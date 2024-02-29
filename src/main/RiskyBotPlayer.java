import java.util.ArrayList;

public class RiskyBotPlayer extends Player {
    public RiskyBotPlayer(String name){
        super(name, "always rolls");
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        return true;
    }
}

