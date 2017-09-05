import java.util.*;
public final class Scoring{
  public static int MIN_SEQUENCE_SIZE = 3;
  private Scoring(){}

  // points for multiples
  private static int getPointsForMultiples(SetOfCards cards){
    int points = 0;
    for (int frequency : cards.getMultiples().values()) {
      if(frequency == 2) points += 2;
      else if(frequency == 3) points += 6;
      else if(frequency == 4) points += 12;
    }
    return points;
  }

  // points for straights
  private static int getPointsForStraights(SetOfCards cards){
    int points =0;

    for(ArrayList<Integer> straight : cards.getStraights()){
      points += straight.size();
    }

    return points;
  }

  // points for flush
  private static int getPointsForFlush(SetOfCards cards, StandardCard cut){
    return cards.getFlush(cut);
  }

  // points for sums of 15
  private static int getPointsForFifteens(SetOfCards cards){
    return cards.getNumberOfSumsOf(15);
  }

  // bonus point
  public static int getBonusPoint(ArrayList<Card> hand, StandardCard cut){
    for(Card c : hand){
      if(c.getRank() == "J" ){
        StandardCard card = (StandardCard)c;
        StandardCard scut = (StandardCard)cut;
        if(card.getSuit().equals(scut.getSuit())) return 1;
      }
    }
    return 0;
  }

  // function to get all of the points for a played hand (showing round)
  public static int getTotalPointsForShow(ArrayList<Card> hand, StandardCard cut){
    int total = 0;
    total += getBonusPoint(hand, cut);

    SetOfCards cards = new SetOfCards(hand);
    total += getPointsForFlush(cards, cut);
    cards.add(cut);
    total += getPointsForMultiples(cards);
    total += getPointsForStraights(cards);
    total += getPointsForFifteens(cards);

    return total;
  }

  /*
    this method checks if there is a streak of 3 or more within the last cards.
    The streak can have cards in any order, but a streak of n only counts if it was the n last cards played.

    toughest algorithm so far
  */
  /*public static int onGoingStreak(ArrayList<Card> hand){
    int streak =0;

    ArrayList<Integer> sortedValues = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>();

    // create hand with values of cards and sort it
    for(Card c: hand) sortedValues.add(c.getIndexValue());
    Collections.sort(sortedValues);

    //for(int num : sortedValues){
    for(int startingIndex = 0; startingIndex < sortedValues.size(); startingIndex++){
      //if(startingIndex)
      ArrayList<Integer> temp = new ArrayList<Integer>();
      int streakSize = 1;
      int startingValue = sortedValues.get(startingIndex);
      for(int index = (startingIndex+1); index < sortedValues.length(); index++){
          if(sortedValues.get(index) == (num+streaksize){
            streaksize ++;
            temp.add(sortedValues.get(index));
          }
          else break;
      }
      if(temp.size() > MIN_SEQUENCE_SIZE - 1){
        if(sequences.size() == 0 || !isSubset(sequences.get(sequences.size()-1), temp))
          sequences.add(temp);
      }
      startingIndex = startingIndex + temp.size();
    }
    //System.out.println("Starting!");


    // find a streak  -- x, y, z where y = x +1 and z = x + 2;
    // save in set of streaks


    // find the streak with the most recent card in it -- the other ones don't matter


    // find the lowest indexed card in their hand that wasn't in that sequence
    // return that sequence



    //Card lastCard = hand.get(
    // should find what streak is in there, and then take the cards not in the streak
    // find the earliest index of these, and check if the cards before make a streak still. if yes, return its size
    while(streak < hand.size()){
      System.out.println("does "+hand.get(streak).getIndexValue()+" equal "+ hand.get(0).getIndexValue()+" + "+streak+"?");
      Card
      if(hand.get(streak).getIndexValue()== hand.get(0).getIndexValue()+streak){
        cardsInStreak.add()
        streak ++;
        System.out.println("yes! size is now "+ streak);}
      else{
      System.out.println("nope, streak over.");break;}
    }
    if(streak < 2) return 0;
    else return streak++;

  }*/

  public static void main(String[] args){
    ArrayList<Card> hand = new ArrayList<Card>();
    Suit clubs = new Suit("club",'♣',"black");
    hand.add(new StandardCard("7", clubs));
    hand.add(new StandardCard("8", clubs));
    hand.add(new StandardCard("10", clubs));
    hand.add(new StandardCard("9", clubs));
    //System.out.println(Scoring.onGoingStreak(hand));



  }

}
