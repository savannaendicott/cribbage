import java.util.*;

public abstract class Card{

  protected String    rank;
  //private String  suit;

	public Card(String r) {//return one person 'a'
    this.rank = r;
    //this.suit = s;
	}

  abstract boolean isBlack();

  abstract boolean isRed();

  public String toString(){
    return this.rank;
  }

  public String getRank(){
    return this.rank;
  }

  public int getIndexValue(){
    if(this.rank == "J") return 11;
    if(this.rank == "Q") return 12;
    if(this.rank == "K") return 13;
    else if(this.rank == "A"){
      return 1;
    }
    else return Integer.parseInt(this.rank);
  }

}
