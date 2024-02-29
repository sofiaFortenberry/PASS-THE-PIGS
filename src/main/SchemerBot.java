import java.util.ArrayList;

public class SchemerBot extends Player {
    public SchemerBot(String name){
        super(name, "super smart");
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        boolean isOpponentCloseToWin = false;
        for(int score : otherScores){
            if (score > 95){
                isOpponentCloseToWin = true;
                break;
            }
        }

        if(handScore < 10){
            return true;
        }
        if(myScore >= 10 && handScore < 20){ 
            return Math.random() < 0.7;
        }
        if(handScore >= 25){
            return false;
        }
        if(isOpponentCloseToWin){
            return handScore < 15;
        }
        if(myScore + handScore >= 100){
            return false;
        }
        return true;
    }
} 
