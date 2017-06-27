import java.util.*;

public abstract class Player{
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_BLACK = "\u001B[30m";

  protected String          name;
  protected ArrayList<Card> hand;

	public Player(String n) {
    this.hand = new ArrayList<Card>();
    this.name = n;
	}

  public String getName(){
    return this.name;
  }

  public void draw(StandardDeck deck){
    hand.add(deck.draw());
  }

  public void add(Card c){
    hand.add(c);
  }


  public void discard(StandardDeck deck, Card card){
    deck.discard(card);
    this.hand.remove(card);
  }

  public void print(){
    System.out.print(this.name +"'s hand has "+ this.hand.size()+ " cards: ");
    for(Card card: hand) {
        if(card.isBlack())
          System.out.print(ANSI_WHITE + card.toString() + "; " + ANSI_WHITE );
        else if(card.isRed()){
          System.out.print(ANSI_RED + card.toString() + "; " + ANSI_WHITE );
        }
    }
    System.out.println();
  }

  /*public static void main(String[] args){
    StandardDeck deck = new StandardDeck();

  }*/

}
