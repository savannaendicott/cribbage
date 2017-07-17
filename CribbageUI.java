
import java.util.*;

public class CribbageUI extends UserInterface{
  public  Scanner sc;

  public CribbageUI(){
    super();
  }

  public void peg(Player p, int n){
    System.out.println(p.getName() + " pegged " + n);
  }

  public void deal(ArrayList<Player> players, Player dealer){
    int numCards = 6;
    if(players.size() == 3) numCards = 5;
    System.out.println(dealer.getName()+" is dealing... displaying "+players.size()+" cards face down for all players");
  }

  public void createCrib(ArrayList<Player> players){
    // 1 card less displayed for each player (should be 4 now)
    // new hand next to the dealer (the crib) with 4 cards, face down
    if(players.size() == 3){
      System.out.println("Creating crib with 1 card from each player and 1 card from the deck.");
    }

    if(players.size() == 2){
      System.out.println("Creating crib with 2 cards from each player");
    }

  }

  public CribbagePlayer createUserPlayer(){
    System.out.println("Welcome to the game! Please enter your name: ");
    sc = new Scanner(System.in);
    String name = sc.next();
    CribbagePlayer user = new CribbagePlayer(name);
    return user;
  }





}
