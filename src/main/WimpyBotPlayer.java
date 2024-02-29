import java.util.ArrayList;

public class WimpyBotPlayer extends Player {
    public WimpyBotPlayer(String name){
        super(name, "always skip");
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        return false;
    }
} 