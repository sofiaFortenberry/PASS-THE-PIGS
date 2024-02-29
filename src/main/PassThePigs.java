import java.util.ArrayList;
import java.util.Scanner;

class Player{
    private String name;
    private String strategy;

    public Player(String name){
        this.name = name;
    }

    //maybe change from default of always want to roll
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        return true;
    }

    public String getName() {
        return name;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy){
        this.strategy = strategy;
    }
}

class BotPlayer extends Player {
    public BotPlayer(String name){
        super(name);
        setStrategy("Simple logic");
    }

    @Override
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        if(handScore >= winningScore - myScore){
            return false;
        }else if(myScore <= 50 || handScore < 20){
            return true;
        }
        return false;
    }
}

class SchemerBot extends Player {
    public SchemerBot(String name){
        super(name);
        setStrategy("Super smart");
    }

    @Override
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
        return true;
    }
}

class RiskyBotPlayer extends Player {
    public RiskyBotPlayer(String name){
        super(name);
        setStrategy("always rolls");
    }

    @Override
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        return true;
    }
}

class WimpyBotPlayer extends Player {
    public WimpyBotPlayer(String name){
        super(name);
        setStrategy("Alwyas skip");
    }

    @Override
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        return false;
    }
}

class HumanPlayer extends Player{
    private static Scanner sc = new Scanner(System.in);

    public HumanPlayer(String name){
        super(name);
        setStrategy("Human");
    }

    @Override
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        System.out.println("Do you want to roll again?");
            String input = sc.nextLine();
            return input.equals("y");
    }
}

public class PassThePigs {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Let's play Pass the Pigs!");

        ArrayList<Player> players = new ArrayList<>();
        players.add(new BotPlayer("Generic BotSF"));
        players.add(new SchemerBot("SchemerBotSF"));
        players.add(new RiskyBotPlayer("RiskyBotSF"));
        players.add(new WimpyBotPlayer("WimpyBotSF"));
        players.add(new HumanPlayer("HumanSF"));

        runGame(players);
    }

    public static void runGame(ArrayList<Player> players){
        int winningScore = 100;
        int[] scores = new int[players.size()];

        while(true){
            //do each players turn
            for(int i = 0; i < players.size(); i++){
                Player currentPlayer = players.get(i);
                int myScore = scores[i];
                int handScore = 0;

                //roll the dice and put the percentages
                while(true){
                    int roll1 = rollPig();
                    int roll2 = rollPig();

                    //calc hand score
                    int rollScore = calculateHandScore(roll1, roll2);
                    String rollResult = getRollName(roll1) + " and " + getRollName(roll2) + " for a roll of " + rollScore;

                    //checkPigout
                    if(rollScore == 0){
                        System.out.println(currentPlayer.getName() + " rolls a " + rollResult + ". That's a PIG OUT!");
                        break; //end the turn ??!!!!!
                    } else {
                        handScore += rollScore;
                        System.out.println(currentPlayer.getName() + " rolls a " + rollResult + ". Handscore is now " + handScore + ".");
                    }

                    //check if want to roll agan
                    if(!currentPlayer.wantsToRoll(myScore, handScore, getOtherScores(scores, i), winningScore)){
                        System.out.println(currentPlayer.getName() + " passes.");
                        scores[i] += handScore;
                        break;
                    }
                }

                //show currentscore
                System.out.print("Scores: ");
                for(int x = 0; x < players.size(); x++){
                    System.out.print(players.get(x).getName() + ": " + scores[x] + " | ");   
                }
                System.out.println();

                //check if someone won
                if(scores[i] >= winningScore){
                    System.out.println("Game over! Winner is: " + currentPlayer.getName());
                    return; //ends the game
                }
            }
        }
    }

    public static int rollPig(){
        double roll = Math.random() * 100;
        if(roll < 34.9){
            return 1; //dot
        } else if (roll < 65.1) {
            return 2; //no dot
        } else if (roll < 87.5){
            return 3; //razorback
        } else if (roll < 96.3){
            return 4; //trotter
        } else if (roll < 99.3){
            return 5; //snouter
        } else{
            return 6; //leaning jowler
        }
    }

    public static String getRollName(int roll){
        String[] rollNames = {"Dot", "No Dot", "Razzorback", "Trotter", "Snouter", "Leaning Jowler"};
        return rollNames[roll - 1];
    }

    public static int calculateHandScore(int roll1, int roll2) {
        if(roll1 == 1 && roll2 == 1){
            return 1; //double dot
        } else if (roll1 == 2 && roll2 == 2){
            return 1; //double no dot
        }else if((roll1 == 1 && roll2 == 2) || (roll1 == 2 && roll2 == 1)){
            return 0; //piggy out :(
        } else if(roll1 == roll2){
            //score for double roles
            int[] doubleScores = {0,0,20,20,40,60};
            return doubleScores[roll1 -1];
        } else {
            int[] scores = {0,0,5,5,10,15};
            return scores[roll1 -1] + scores[roll2 - 1];
        }
    }

    public static ArrayList<Integer> getOtherScores(int[] scores, int currentPlayerIndex){
        ArrayList<Integer> otherScores = new ArrayList<Integer>();
        for(int i = 0; i < scores.length; i++){
            if(i != currentPlayerIndex){
                otherScores.add(scores[i]);
            }
        }
        return otherScores;
    }
}
    