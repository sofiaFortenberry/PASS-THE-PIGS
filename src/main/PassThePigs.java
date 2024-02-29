import java.util.ArrayList;
import java.util.Scanner;


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


        Manager.runGame(players);
    }

}
    