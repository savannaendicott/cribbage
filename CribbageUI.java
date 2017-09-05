
import java.util.*;

public class CribbageUI extends UserInterface{
  public  Scanner sc;

  public CribbageUI(){
    super();
  }

  public int mainMenu(){
    System.out.println("\n\n\n\nWELCOME TO CRIBBAGE!\n\n  A game by Savanna Endicott, all rights reserved.\n\n\n");

    while(true){
      sc = new Scanner(System.in);
      System.out.println("Please enter the number of CPUs you would like to add (1 or 2):  ");
      try{
        int choice = sc.nextInt();
        if(choice != 1 && choice != 2)
          System.out.println("Sorry, you can only add 1 or 2 CPUs. Please try again.");
        else return choice;
      }catch(Exception e){
        System.out.println("Incorrect input! Please try again.");
      }
    }
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

  public void showHand(Player p, int pegs){
    System.out.println(p.getName()+" pegged "+pegs+" for the following hand: "+p.getHandToString());
  }

  public void revealCrib(Player p, int pegs, ArrayList<Card> crib){
    System.out.print("\n"+p.getName()+" pegged "+ pegs+" for the crib: ");
    for(Card c : crib){
      System.out.print(c.toString() + " ");
    }
  }

  public void updatePegBoard(CribbagePlayer p){
    System.out.println("updating "+p.getName()+"'s pegs on the board to "+p.getPegs());
  }

  public void peg(CribbagePlayer p, int n, String reason){
    System.out.println(p.getName()+" pegs "+n+" for "+ reason);
    System.out.println("updating "+p.getName()+"'s pegs on the board to "+p.getPegs());
  }





}
