import java.util.ArrayList;

public class BotPlayer extends Player {
    public BotPlayer(String name){
        super(name, "Simple bot strategy");
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        if(handScore >= winningScore - myScore){
            return false;
        }else if(myScore <= 50 || handScore < 20){
            return true;
        }
        return false;
    }
} 
