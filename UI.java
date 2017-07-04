
import java.util.*;

public class UI{

  public UI(){}



  public void startTurn(Player p){
    System.out.println("\n"+ p.getName() +"'s turn...");
  }

  public void startRound(String r){
    System.out.println("\n\n" + r);
  }

  public void peg(Player p, int n){
    System.out.println(p.getName() + " pegged " + n);
  }

  public void displayHand(Player p){
    System.out.println(p.getName()"'s hand is: "+p.getHandToString());
  }

  public void draw(Player p){
    System.out.println(p.getName()" just drew a card.");
  }

  public void playCard(Player p, Card c){
    // refresh hand for p -- one less card
    // place card in front of that player
    System.out.println(p.getName()+" plays "+c.toString());
  }

  public void returnCardsToHands(ArrayList<Players> players){
    // refresh all of their hands in the UI - each should have 4 cards either face down or up
    // no cards at the center any more
    System.out.println("cards returned to players hands");
  }

  public void endPlayRound(){
    // just flip each players' cardsupside down in front of them, next ones will appear on top
    System.out.println("moving to next round....")
  }

  public void cut(Player p, Card c){
    // mock a cut from the UI, and flip over the resulting card
    System.out.println(p.getName()+" cut a "+c.toString());
  }

  public void deal(ArrayList<Players> players){
    int numCards = 6;
    if(players.size() == 3) numCards = 5;
    System.out.println("Displaying "+players.size+" cards face down for all players");
  }

  public void createCrib(ArrayList<Players> players){
    // 1 card less displayed for each player (should be 4 now)
    // new hand next to the dealer (the crib) with 4 cards, face down
    if(players.size() == 3){
      System.out.println("Creating crib with 1 card from each player and 1 card from the deck.");
    }

    if(players.size() == 2){
      System.out.println("Creating crib with 2 cards from each player");
    }

  }

  public void reset(){
    // prepare for next round, shuffle deck, clear hands visually
    System.out.println("resetting table");
  }

  public void pass(Player p){
    System.out.println(p.getName()+" says 'GO'");
  }

  public void win(Player p){
    System.out.println(p.getName() " WINS!!!!!");
  }






}
