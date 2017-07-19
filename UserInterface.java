
import java.util.*;

public abstract class UserInterface{
  public  Scanner sc;

  public UserInterface(){}

  public abstract int mainMenu();

  public void startTurn(Player p){
    System.out.println("\n"+ p.getName() +"'s turn...");
  }

  public void printInfo(String r){
    System.out.println("\n" + r);
  }

  public void peg(Player p, int n){
    System.out.println(p.getName() + " pegged " + n);
  }

  public void displayHand(Player p){
    System.out.println(p.getName()+"'s hand is: "+p.getHandToString());
  }

  public void draw(Player p){
    System.out.println(p.getName()+" just drew a card.");
  }

  public void playCard(Player p, Card c){
    // refresh hand for p -- one less card
    // place card in front of that player
    System.out.println(p.getName()+" plays "+c.toString());
  }

  public void returnCardsToHands(ArrayList<Player> players){
    // refresh all of their hands in the UI - each should have 4 cards either face down or up
    // no cards at the center any more
    System.out.println("cards returned to players hands");
  }

  public void endPlayRound(){
    // just flip each players' cardsupside down in front of them, next ones will appear on top
    System.out.println("moving to next round....");
  }

  public void cut(Player p, Card c){
    // mock a cut from the UI, and flip over the resulting card
    System.out.println(p.getName()+" cut a "+c.toString());
  }

  public void deal(int n, Player dealer){
    System.out.println(dealer.getName()+" is dealing... displaying "+n+" cards face down for all players");
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

  public void displayHandToUser(ArrayList<Card> hand){
    System.out.println("\nHere's whats in your hand: ");
    for(Card c : hand){
      System.out.print(c.toString()+" ");
    }
  }

  public String promptDiscard(){
    Scanner sc;
    sc = new Scanner(System.in);
    System.out.println("Please enter the card you'd like to discard (please copy paste): ");
    try{
      String choice = sc.next();
      return choice;
    }catch(Exception e){
      System.out.println("Incorrect input! Please try again.");
    }
    return "";
  }

  public void reset(){
    // prepare for next round, shuffle deck, clear hands visually
    System.out.println("resetting table");
  }

  public void pass(Player p){
    System.out.println(p.getName()+" says 'GO'");
  }

  public void win(Player p){
    System.out.println(p.getName() +" WINS!!!!!");
  }

  public String getUserName(){
    System.out.println("\n\n----------------------------------------------\n\nWelcome to the game! Please enter your name: ");
    sc = new Scanner(System.in);
    return sc.next();
  }

  public void startGame(ArrayList<Player> players) {
    System.out.println("\n\n----------------------------------------------\n\nStarting the game! Players are: ");
    for(Player p : players){
      System.out.println("  "+p.getName());
    }
  }

  public void cardFlip(Player p, Card c) {
    System.out.println("\n\n... "+ p.getName()+" cut a "+ c.toString());
  }





}
