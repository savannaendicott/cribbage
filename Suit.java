
public class Suit{
  private String suit;
  private char symbol;
  private String color;

  public Suit(String s, char sym, String c){
    this.suit = s;
    this.symbol = sym;
    this.color =c;
  }

  public String toString(){
    return this.symbol+"";
  }

  public String fullString(){
    return this.suit;
  }

  public boolean equals(Object obj){
    if(obj == null) return false;
    if(obj == this) return true;

    if((obj instanceof String)){
      String other = (String) obj;
      if(other == this.suit) return true;
      else return false;
    }
    else if((obj instanceof Suit)){
      Suit other = (Suit) obj;
      if(other.suit == this.suit) return true;
      else return false;
    }
    return false;

  }

  public boolean isRed(){
    return this.color == "red";
  }
  public boolean isBlack(){
    return this.color == "black";
  }
}
