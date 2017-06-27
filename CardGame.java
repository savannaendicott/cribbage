import java.util.*;

public abstract class CardGame {

  protected StandardDeck deck;
  protected ArrayList<Player> players;
  protected Player dealer;
  //private ArrayList<Stage> stages;

  public CardGame (int numPlayers) {//return one person 'a'
    this.deck = new StandardDeck();
    this.players = new ArrayList<Player>();
    //this.stages = new ArrayList<Stage>();
  }

  public CardGame (int numPlayers, boolean jokers) {//return one person 'a'
    this.deck = new StandardDeck(jokers);
    this.players = new ArrayList<Player>();
  }

  /*
    function:     defineStages
    Description:  This function must be implemented by any CardGame child class.
                  It sets up each stage of the game so that they can be easily run through
  */
  //public abstract void defineStages();


  public abstract void run();

  //public abstract void setup();



}
