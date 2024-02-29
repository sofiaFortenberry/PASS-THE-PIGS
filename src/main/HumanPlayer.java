import java.util.ArrayList;
import java.util.Scanner;

public 
class HumanPlayer extends Player{
    private static Scanner sc = new Scanner(System.in);

    public HumanPlayer(String name){
        super(name, "human strategy");
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore){
        System.out.println("Do you want to roll again?");
            String input = sc.nextLine();
            return input.equals("y");
    }
} 
