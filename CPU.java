
import java.util.*;

public class CPU extends CribbagePlayer{

  public CPU(){
    super(NameGenerator.getRandomName());
  }

  public ArrayList<Card> discardToCrib(){
    if(this.hand.size() == 6) return discardTwoToCrib();
    return discardOneToCrib();
  }

  public ArrayList<Card> discardOneToCrib(){
    ArrayList<Card> choices = new ArrayList<Card>();
    ArrayList<Integer> calcs = new ArrayList<Integer>();

    for(Card c : this.hand){
      ArrayList<Card> subset = new ArrayList<Card>(this.hand);
      subset.remove(c);
      calcs.add(hypotheticalPeg(subset));
    }

    int max = Collections.max(calcs);
    choices.add(this.hand.get((calcs.indexOf(max))));
    this.hand.remove(choices.get(0));
    return choices;
  }

  public ArrayList<Card> discardTwoToCrib(){
    ArrayList<Card> choices = new ArrayList<Card>();
    int[][] calcs = new int[5][5];

    int max = 0;
    for(int i =0; i < this.hand.size(); i++){
      for(int j =0; j < this.hand.size(); j++){
        if(i > j){
          ArrayList<Card> subset = new ArrayList<Card>(this.hand);
          subset.remove(i); subset.remove(j);
          int points = hypotheticalPeg(subset);
          if(points > max){
            choices.clear();
            choices.add(this.hand.get(i)); choices.add(this.hand.get(j));
            max = points;
          }
        }
      }
    }
    for(Card c : choices){
      this.hand.remove(c);
    }
    return choices;
  }

  public Card[] toArray(ArrayList<Card> handList){
    Card[] handArray = new Card[handList.size()];
    int i = 0;
    for(Card c : handList){
      handArray[i++] = c;
    }
    return handArray;
  }

  public int hypotheticalPeg(ArrayList<Card> hyp_hand){
    ArrayList<ArrayList<Integer>> sequences = Scoring.getStreaks(hyp_hand);
    int streak_points =0;
    for(ArrayList<Integer> seq : sequences) streak_points+=seq.size();
    int flushes = Scoring.getFlush(hyp_hand);
    int pairs = Scoring.getPairs(hyp_hand);
    int trips = Scoring.getTriples(hyp_hand);
    int quads = Scoring.getQuadruples(hyp_hand);
    int fifteens = Scoring.getFifteens(hyp_hand) * 2;

    return flushes + pairs + trips + quads + streak_points + fifteens;
  }

}
