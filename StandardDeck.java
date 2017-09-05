import java.util.*;
/*
  Standard card deck.
  Can include Jokers or not.
  If not: 52 cards, 4 suits, 13 ranks.
*/

public class StandardDeck{
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_BLACK = "\u001B[30m";

  private boolean includeJokers = true;
  private ArrayList<Card> deck;
  private ArrayList<Card> discardPile;
  private ArrayList<Suit> suits;

	public StandardDeck() {//return one person 'a'
    this(true);
	}
  public StandardDeck(boolean jokers){
    this.deck = new ArrayList<Card>();
    this.discardPile = new ArrayList<Card>();
    this.includeJokers = jokers;
    createSuits();
    reset();
  }

  public void shuffle(){
    Collections.shuffle(deck);
  }
  protected void createSuits(){
    this.suits = new ArrayList<Suit>();
    this.suits.add(new Suit("club",'♣',"black"));
    this.suits.add(new Suit("spade",'♠',"black"));
    this.suits.add(new Suit("diamond",'♦',"red"));
    this.suits.add(new Suit("heart",'♥',"red"));
  }

  protected void reset(){
    this.deck.clear();
    for(Suit s : this.suits){
      for(int r =2; r < 11; r++){
        addStandardCard(""+r, s);
      }
      addStandardCard("J", s);
      addStandardCard("Q", s);
      addStandardCard("K", s);
      addStandardCard("A", s);
    }

    if(includeJokers){
        // handle this somehow...
        add(new Joker("red"));
        add(new Joker("black"));
    }
    shuffle();
  }
  public void addStandardCard(String r, Suit s){
    add(new StandardCard(r,s));
  }
  public void add(Card c){
    if(!deck.contains(c))
      deck.add(c);
  }

  public int size(){
    return this.deck.size();
  }

  public Card draw(){
    if(this.deck.size() > 1)
      return deck.remove(this.deck.size() -1);
    for(Card card: discardPile){
      this.deck.add(card);
      this.discardPile.remove(card);
    }
    shuffle();
    return this.deck.remove(this.deck.size() -1);
  }
  public ArrayList<Card> draw(int n){
    ArrayList<Card> cards = new ArrayList<Card>();
    if(this.deck.size() < n){
      for(Card card: discardPile){
        this.deck.add(card);
        this.discardPile.remove(card);
      }
      shuffle();
    }
    for(int i =0; i < n; i ++ ){
      cards.add(this.deck.remove(this.deck.size()-1));
    }
    return cards;
  }

  public void print(){
    System.out.println("The deck currently has "+ this.deck.size()+" cards as follows: ");
    for(Card card: deck) {
        if(card.isBlack())
          System.out.print(ANSI_WHITE + card.toString() + "; " + ANSI_WHITE );
        else if(card.isRed()){
          System.out.print(ANSI_RED + card.toString() + "; " + ANSI_WHITE );
        }
    }
    System.out.println();
  }

  public void discard(Card card){
    this.discardPile.add(card);
  }

  public Card discardPileTop(){
    return this.discardPile.get(this.discardPile.size() -1);
  }


}
