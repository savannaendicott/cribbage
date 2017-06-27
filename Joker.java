

public class Joker extends Card{

  String color;

  public Joker(String c){
    super("joker");
    this.color = c;
  }

  public boolean isBlack(){
    if(this.color.equals("black"))
      return true;
    return false;
  }

  public boolean isRed(){
    if(this.color.equals("red"))
      return true;
    return false;
  }

  public boolean equals(Object obj){
    if(obj == null) return false;
    if(obj == this) return true;

    if(!(obj instanceof Joker)) return false;
    Joker otherCard = (Joker)obj;
    if(otherCard.rank == this.rank && otherCard.color == this.color) return true;

    return false;
  }

  public String toString(){
    return this.rank;
  }

}
