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

  public int peg(StandardCard cut){
    return peg(this.hand, cut);
  }

  public int peg(ArrayList<Card> hand, StandardCard cut){
    int points = Scoring.getTotalPointsForShow(hand, cut);
    peg(points);
    return points;
  }


}
