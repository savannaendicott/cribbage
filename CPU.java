
import java.util.*;

public class CPU extends CribbagePlayer{

  public CPU(){
    super(NameGenerator.getRandomName());
  }

  public ArrayList<Card> discardToCrib(StandardCard cut){
    if(this.hand.size() == 6) return discardTwoToCrib(cut);
    return discardOneToCrib(cut);
  }

  public Card selectCardToPlay(ArrayList<Card> hand, ArrayList<Card> play){
    Card choice = null;
    int max = 31 - getSum(play);

    for(Card c : hand){
      if(c.getIndexValue() <= max){
        if(choice == null) choice = c;
        else choice = getBestChoice(choice, c, play);
      }
    }
    return choice;
  }

  public Card getBestChoice(Card current, Card potential, ArrayList<Card> play){
    int[] scores = { 0,0 };
    ArrayList<Card> competitors = new ArrayList<Card>();
    Card lastInPlay = play.get(play.size()-1);
    Card secondLastInPlay = play.get(play.size()-2);

    competitors.add(potential); competitors.add(current);

    int num = 2;
    if(lastInPlay.getRank() == secondLastInPlay.getRank()) num =6;
    for(Card c : competitors){
      ArrayList<Card> temp = new ArrayList<Card>();
      temp.add(lastInPlay);
      temp.add(c);
      if(c.getRank() == lastInPlay.getRank() || c.getRank() == secondLastInPlay.getRank()){
        scores[competitors.indexOf(c)] += num;
      }
//      scores[competitors.indexOf(c)] += 2 * Scoring.getFifteens(temp);
      temp.add(secondLastInPlay);
//      scores[competitors.indexOf(c)] += 3 * Scoring.getStreaks(temp).size();

    }
    if(scores[0] > scores[1]) return potential;
    else return current;
  }

  public int getSum(ArrayList<Card> play){
    int n = 0;
    for(Card c : play){
      n += c.getIndexValue();
    }
    return n;
  }

  public ArrayList<Card> discardOneToCrib(StandardCard cut){
    ArrayList<Card> choices = new ArrayList<Card>();
    ArrayList<Integer> calcs = new ArrayList<Integer>();

    for(Card c : this.hand){
      ArrayList<Card> subset = new ArrayList<Card>(this.hand);
      subset.remove(c);
      calcs.add(Scoring.getTotalPointsForShow(hand, cut));
    }

    int max = Collections.max(calcs);
    choices.add(this.hand.get((calcs.indexOf(max))));
    this.hand.remove(choices.get(0));
    return choices;
  }

  public ArrayList<Card> discardTwoToCrib(StandardCard cut){
    ArrayList<Card> choices = new ArrayList<Card>();
    int[][] calcs = new int[5][5];

    int max = 0;
    for(int i =0; i < this.hand.size(); i++){
      for(int j =0; j < this.hand.size(); j++){
        if(i > j){
          ArrayList<Card> subset = new ArrayList<Card>(this.hand);
          subset.remove(i); subset.remove(j);
          int points = Scoring.getTotalPointsForShow(hand, cut);
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

  public int hypotheticalPeg(ArrayList<Card> hyp_hand, StandardCard cut){
    return Scoring.getTotalPointsForShow(hand, cut);
  }

}
