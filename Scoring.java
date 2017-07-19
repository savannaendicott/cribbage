
import java.util.*;
public final class Scoring{
  public static int MIN_SEQUENCE_SIZE = 3;
  private Scoring(){}

  public static int getQuadruples(ArrayList<Card> hand){
    return 12 * getMultiples(hand, 4);
  }

  public static int getTriples(ArrayList<Card> hand){
    return 6 * getMultiples(hand, 3);
  }

  public static int getPairs(ArrayList<Card> hand){
    return 2 * getMultiples(hand, 2);
  }

  public static int getMultiples(ArrayList<Card> hand, int n){
    ArrayList<String> values = new ArrayList<String>();
    for(Card c : hand) values.add(c.getRank());
    //System.out.println("getting multiples of size "+n+"\n Here are the values of the hand...");

    ArrayList<String> multis = new ArrayList<String>();
    for(String s : values){
      //System.out.println("looking at "+ s+"... it is seen "+Collections.frequency(values,s)+" times");
      if(Collections.frequency(values,s) == n){
        if(!multis.contains(s)) {
          multis.add(s);
          //System.out.print(s+" appears "+ n+ " times.\n");
        }
      }
    }
    return multis.size();
  }

  public static HashMap<String,Integer> getMultiples(ArrayList<Card> hand){
    ArrayList<String> values = new ArrayList<String>();
    HashMap<String,Integer> multiples = new HashMap<String,Integer>();

    for(Card c : hand) values.add(c.getRank());

    for(String s : values){
      if(!multiples.containsKey(s)){
        int n = Collections.frequency(values,s);
        if(n > 1) multiples.put(s,n);
      }
    }
    return multiples;
  }

  public static ArrayList<ArrayList<Integer>> getStreaks(ArrayList<Card> hand){
    ArrayList<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> values = new ArrayList<Integer>();

    for(Card c: hand) values.add(c.getIndexValue());
    Collections.sort(values);

    for(int i = 0; i < values.size()-(MIN_SEQUENCE_SIZE - 1); i++){
      int first = values.get(i);
      int count2 =0;
      ArrayList<Integer> temp = new ArrayList<Integer>();
      while(count2 < hand.size()){
        if(values.contains(first + count2))
          temp.add(first + count2++);
        else break;
      }
      if(temp.size() > MIN_SEQUENCE_SIZE - 1){
        if(sequences.size() == 0 || !isSubset(sequences.get(sequences.size()-1), temp)){
          int j;
          for(j =0; j < repetitions(values,temp); j++)
            sequences.add(temp);
          //System.out.println("sequence found "+j+"times");
        }
      }
    }

    return sequences;
  }

  public static ArrayList<ArrayList<Integer>> getPatterns(ArrayList<Card> hand){
    ArrayList<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> values = new ArrayList<Integer>();

    for(Card c: hand) values.add(c.getIndexValue());
    Collections.sort(values);

    for(int diff =1; diff < 8; diff++){
      for(int i = 0; i < values.size()-2; i++){
        int first = values.get(i);
        int count2 =0;
        ArrayList<Integer> temp = new ArrayList<Integer>();

        while(count2 < hand.size()){
          if(values.contains(first + (diff*count2))){
            temp.add(first + (diff*count2));
            count2 ++;
          }
          else break;
        }
        if(temp.size() >  MIN_SEQUENCE_SIZE - 1){
          if(sequences.size() == 0 || !isSubset(sequences.get(sequences.size()-1), temp)){
            int j;
            for(j =0; j < repetitions(values,temp); j++)
              sequences.add(temp);
            //System.out.println("sequence found "+j+"times");
          }
        }
      }
    }
    return sequences;
  }

  public static int repetitions(ArrayList<Integer> hand, ArrayList<Integer> seq){
    int reps =1;
    for(int x : seq){
      reps *= Collections.frequency(hand, x);
    //  System.out.println(x+" found "+ Collections.frequence(arr,x)+" times in the sequence");
    }
    return reps;

  }

  public static boolean isSubset(ArrayList<Integer> res, ArrayList<Integer> seq){
      if(res.size() < seq.size()) return false;

      int i = 0, j = 0;
      while(j < res.size() && i < seq.size()){
          if(res.get(j) < seq.get(i) ) j++;
          else if(res.get(j) == seq.get(i)){
            j++; i++;
          }
          else return false;
      }
      if(i < seq.size()) return false;
      else return true;
  }

  public static int getSize(int[] arr){
    int size =0;
    while(size < arr.length && arr[size]!=0){
      size ++;
    }
    return size;
  }


  public static int getFlush(ArrayList<Card> hand, Card cut){
    String suit = "";
    for(Card c : hand){
      StandardCard card = (StandardCard)c;
      if(suit == "") suit = card.getSuit();
      else if(!card.getSuit().equals(suit)) return 0;
    }
    StandardCard scut = (StandardCard)cut;
    if (scut.getSuit().equals(suit)) return 5;
    else return 4;
  }

  public static int getFlush(ArrayList<Card> hand){
    String suit = "";
    for(Card c : hand){
      StandardCard card = (StandardCard)c;
      if(suit == "") suit = card.getSuit();
      else if(!card.getSuit().equals(suit)) return 0;
    }
    return 4;
  }

  public static int getBonus(ArrayList<Card> hand, Card cut){
    for(Card c : hand){
      if(c.getRank() == "J" ){
        StandardCard card = (StandardCard)c;
        StandardCard scut = (StandardCard)cut;
        if(card.getSuit().equals(scut.getSuit())) return 1;
      }
    }
    return 0;
  }

  public static int getFifteens(ArrayList<Card> hand){
    ArrayList<String> results = new ArrayList<String>();
    int total =0;

    for(Card c : hand){
      total += getValue(c);
    }
    //System.out.println(" IS IT FIFTEEN? ... "+ total);
    if(total==15) return 1;
    else if(total < 15) return 0;

    // look for all possible combinations of 15 ...
    sum_up_recursive(hand,15,new ArrayList<Card>(), results);
    //System.out.println("Fifteen-"+results.size()+":"+results);
    return results.size();
  }

  static void sum_up_recursive(ArrayList<Card> numbers, int target, ArrayList<Card> partial, ArrayList<String> solution) {
     int s = 0;
     for (Card x: partial) s += getValue(x);
     if (s == target)
          solution.add(Arrays.toString(partial.toArray())+" ");
     else if (s >= target) return;
     for(int i=0;i<numbers.size();i++) {
           ArrayList<Card> remaining = new ArrayList<Card>();
           Card n = numbers.get(i);
           for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
           ArrayList<Card> partial_rec = new ArrayList<Card>(partial);
           partial_rec.add(n);
           sum_up_recursive(remaining,target,partial_rec, solution);
     }
  }

  /* only applies to cribbage games...*/
  public static int getValue(Card c){
    if(c.getRank() == "J" || c.getRank() == "Q" || c.getRank() == "K"){
      return 10;
    }
    else if(c.getRank() == "A"){
      return 1;
    }

    else return Integer.parseInt(c.getRank());

  }





}
