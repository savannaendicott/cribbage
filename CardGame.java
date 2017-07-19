import java.util.*;

public abstract class CardGame {

  protected StandardDeck deck;
  protected ArrayList<Player> players;
  protected Player dealer;
  protected Player user;
  protected UserInterface ui;
  //private ArrayList<Stage> stages;

  public CardGame () {//return one person 'a'
    this.deck = new StandardDeck();
    this.players = new ArrayList<Player>();
  }

  public CardGame (boolean jokers) {//return one person 'a'
    this.deck = new StandardDeck(jokers);
    this.players = new ArrayList<Player>();
  }

  public Player cutToFindWhoGoesFirst(){
    this.ui.printInfo("...Cutting to see who goes first!");
    ArrayList<Player> winners = new ArrayList<Player>(this.players);
    int max =0;
    HashMap<Player, Integer> competition = new HashMap<>();
    while(true){
      for(Player p : winners){
        Card c = this.deck.draw();
        competition.put(p,c.getIndexValue());
        this.ui.printInfo("..."+p.getName() +" drew a "+c.toString());
      }
      max = 0;
      for (Integer rank : competition.values()) {
        if(rank > max) {
          max = rank;
        }
      }
      for (Map.Entry<Player,Integer> entry : competition.entrySet())
        if(entry.getValue() != max) winners.remove(entry.getKey());

      if(winners.size()>1){
        competition.clear();
        System.out.println("...there was a tie! Tie breaker coming right up...");
      }
      else{
        return winners.get(0);
      }
    }
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
