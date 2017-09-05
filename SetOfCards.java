
import java.util.*;

public class SetOfCards {

  public final int MIN_SEQUENCE_SIZE = 3;

  protected ArrayList<Card> cards;

  public SetOfCards(){
    this.cards = new ArrayList<Card>();
  }

  public SetOfCards(ArrayList<Card> cards){
    this.cards = new ArrayList<Card>(cards);
  }

  public void add(Card c){
    this.cards.add(c);
  }

  public Card get(int index){
    return cards.get(index);
  }

  /*
   * getMultiples
   * @return map of ranks and their frequency
   */
  public HashMap<String,Integer> getMultiples() {
    HashMap<String,Integer> multiples = new HashMap<String,Integer>();
    ArrayList<String> values = getRanks();

    for(String value : values) {
      if(!multiples.containsKey(value)) {
        int frequency = Collections.frequency(values,value);
        if(frequency > 1)
          multiples.put(value,frequency);
      }
    }
    return multiples;
  }

  /*
   * getRanks
   * Used by many functions to calculate patterns
   * @return cards ranks only
   */
  private ArrayList<String> getRanks(){
    ArrayList<String> values = new ArrayList<String>();
    for(Card c : cards) values.add(c.getRank());
    return values;
  }

  /*
   * getFlush
   * checks if there is a flush of at least 4 cards
   * @return number of cards in the flush (4 or 5)
   */
  public int getFlush(StandardCard cut){
    String suit = "";
    int points = 0;
    for(Card c : cards){
      StandardCard card = (StandardCard)c;
      if(suit == "") suit = card.getSuit();
      else if(!card.getSuit().equals(suit)) return 0;
    }
    if(cut.getSuit().equals(suit)) points++;
    points += cards.size();
    return points;
  }

  /*
   * getValue
   * returns values for each rank. these are CRIBBAGE SPECIFIC.
   * @return cards ranks only
   */
  private int getValue(Card c){
    if(c.getRank() == "J" || c.getRank() == "Q" || c.getRank() == "K") return 10;
    else if(c.getRank() == "A") return 1;
    else return Integer.parseInt(c.getRank());
  }

  /*
   * getSumsOfSize
   * @return all unique combinations of cards whose values' sum up to SUM
   */
  public ArrayList<ArrayList<Card>> getSumsOf(int sum){
    ArrayList<ArrayList<Card>> results = new ArrayList<ArrayList<Card>>();
    int total =0;

    for(Card c : cards) total += getValue(c);
    if(total==sum){
      results.add(cards);
      return results;
    }
    else if(total < sum) return results;

    recursiveSum(cards,sum,new ArrayList<Card>(), results);
    return results;
  }

  /*
   * getNumberOfSumsOfSize
   * @return number of unique combinations summing up to SUM
   */
  public int getNumberOfSumsOf(int sum){
    return getSumsOf(sum).size();
  }

  /*
   * recursiveSum
   * Recursively finds all unique combinations of cards whose values sum up to target number
   */
  private void recursiveSum(ArrayList<Card> numbers, int target, ArrayList<Card> partial, ArrayList<ArrayList<Card>> solution) {
     int s = 0;
     for (Card x: partial) s += getValue(x);
     if (s == target) solution.add(partial);
     else if (s >= target) return;
     for(int i=0;i<numbers.size();i++) {
         ArrayList<Card> remaining = new ArrayList<Card>();
         Card n = numbers.get(i);
         for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
         ArrayList<Card> partial_rec = new ArrayList<Card>(partial);
         partial_rec.add(n);
         recursiveSum(remaining,target,partial_rec, solution);
     }
  }

  /*
   * getStraights
   * @return all straights of size 3 or more
   */
   public ArrayList<ArrayList<Integer>> getStraights(){
     ArrayList<Integer> sortedValues = new ArrayList<Integer>();
     ArrayList<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>();

     // create hand with values of cards and sort it
     for(Card c: cards){
       if(!sortedValues.contains(c.getIndexValue()))
         sortedValues.add(c.getIndexValue());
     }
     Collections.sort(sortedValues);

     for(int startingIndex = 0; startingIndex < sortedValues.size()-2; startingIndex++){
       ArrayList<Integer> temp = new ArrayList<Integer>();
       int streakSize = 1;
       int startingValue = sortedValues.get(startingIndex);
       temp.add(startingValue);
       for(int index = (startingIndex+1); index < sortedValues.size(); index++){
           if(sortedValues.get(index) == (startingValue + streakSize)){
             streakSize ++;
             temp.add(sortedValues.get(index));
           }
           else break;
       }
       if(streakSize >= MIN_SEQUENCE_SIZE){
           ArrayList<Card> temp2 = new ArrayList<Card>();
         int repetitions = 1;//(-1 * streakSize) + 1;
         for(Card c : cards){
           if(temp.contains(c.getIndexValue())) temp2.add(c);
         }
         SetOfCards tempSet = new SetOfCards(temp2);
         HashMap<String,Integer> multiplevalues = tempSet.getMultiples();
         if(multiplevalues.size() > 1){
             repetitions += multiplevalues.size() -1;
         }
         for(int n : multiplevalues.values()){
             repetitions+= (n -1);
         }

         while(repetitions > 0){
           sequences.add(temp);
           repetitions --;
         }
         startingIndex = startingIndex + temp.size();
       }
     }
     return sequences;
   }

  public static void main(String[] args){
    ArrayList<Card> hand = new ArrayList<Card>();
    Suit clubs = new Suit("club",'♣',"black");
    hand.add(new StandardCard("10", clubs));
    hand.add(new StandardCard("10", new Suit("heart",'x',"black")));
    hand.add(new StandardCard("10", clubs));
    hand.add(new StandardCard("J", new Suit("heart",'x',"black")));
    hand.add(new StandardCard("8", clubs));

    SetOfCards set = new SetOfCards(hand);
      int points = 0;
    for(Integer value : set.getMultiples().values()){
        if( value == 2) points += 2;
        if( value == 3) points += 6;
        if( value == 4) points += 12;

    }
    System.out.println(points + " points for multiples!");
  }
}
