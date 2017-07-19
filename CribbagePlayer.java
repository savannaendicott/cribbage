import java.util.*;


public class CribbagePlayer extends Player{

  private static int NO_CARDS = 4;
  protected int pegs;

  public CribbagePlayer(String n){
    super(n);
  }

  public void peg(int p){
    this.pegs += p;
  }

  public int getPegs(){
    return this.pegs;
  }

  // temporary function for testing
  // the accumulation of points will be done from the Cribbage class
  // so that it can display the peg details to the UI

  public ArrayList<String> getHandForMultiples(Card cut){
    ArrayList<String> values = new ArrayList<String>();
    for(Card c: this.hand){
      values.add(c.getRank());
    }
    values.add(cut.getRank());
    return values;
    //int occurrences = Collections.frequency(animals, "bat");
  }

  public int peg(Card cut){
    int total = 0;
    ArrayList<Card> play = new ArrayList<Card>(this.hand);
    play.add(cut);

    ArrayList<ArrayList<Integer>> sequences = Scoring.getStreaks(play);
    int streak_points =0;
    for(ArrayList<Integer> seq : sequences){
      streak_points+=seq.size();
    }


    int flushes = Scoring.getFlush(this.hand, cut);
    int pairs = Scoring.getPairs(play);
    int trips = Scoring.getTriples(play);
    int quads = Scoring.getQuadruples(play);
    int multis = pairs + trips + quads;
    int fifteens = Scoring.getFifteens(play) * 2;
    int bonus = Scoring.getBonus(this.hand,cut);
    total +=  flushes + multis + streak_points + fifteens + Scoring.getBonus(this.hand, cut);

    this.pegs += total;
    return total;
  }



  public static void main(String[] args){
    //System.out.println("Random name: "+ NameGenerator.getRandomName());

    /*Suit clubs = new Suit("club",'♣',"black");
    Suit spades = new Suit("spades",'♠',"black");
    Suit diamonds = new Suit("diamonds",'♦',"red");
    Suit hearts = new Suit("hearts",'♥',"red");

    StandardCard c1 = new StandardCard("6", diamonds);
    StandardCard c2 = new StandardCard("7", diamonds);
    StandardCard c3 = new StandardCard("7", diamonds);
    StandardCard c4 = new StandardCard("7", diamonds);
    StandardCard c5 = new StandardCard("7", diamonds);
    ArrayList<Card> hand = new ArrayList<Card>();

    //int x = Scoring.getStreaks(hand);
    //System.out.println("There are "+ x+" streaks in that hand.");


    CribbagePlayer player = new CribbagePlayer("Molly");
    player.add(c1); player.add(c2); player.add(c3); player.add(c4);
    System.out.println("Molly pegged "+ player.peg(c5));
    /*boolean jokers = false;
    StandardDeck deck = new StandardDeck(jokers);
    CribbagePlayer player = new CribbagePlayer("Judy");
    player.add(c1);
    player.add(c2);
    player.add(c3);
    player.add(c4);

    Card cut = deck.draw();
    player.print();
    System.out.println("A "+ cut.toString()+" was cut...");*/
  /*  int fifs = player.getFifteens(cut);
    int doubs = player.getPairs(cut);
    int trips = player.getTriples(cut);
    int quads = player.getQuadruples(cut);
    int flush = player.getFlush(cut);
    int bonus = player.bonus(cut);
    int total = 2*fifs + 2*doubs+ 6*trips + 12*quads;
    System.out.println("Scoring:\n" + player.getName() + " got " + total +
        " points: \n  " + fifs + " fifteens,\n  " + doubs + " pairs,\n  " +
        trips + " triples,\n  " + quads + " quadruples,\n  " + flush + " for " +
        " a flush, \n  and "+ bonus + " points for the hand containing a jack " +
        " of the same suit as the cut."
      );*/



  }

}
