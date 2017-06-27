

public class StandardCard extends Card{
  private Suit suit;

  public StandardCard(String r, Suit s){
    super(r);
    this.suit = s;

  }

  public boolean equals(Object obj){
    if(obj == null) return false;
    if(obj == this) return true;

    if(!(obj instanceof StandardCard)) return false;
    StandardCard otherCard = (StandardCard)obj;
    if(otherCard.rank == this.rank && otherCard.suit == this.suit) return true;

    return false;
  }

  public String getSuit(){
    return this.suit.toString();
  }

  public boolean isBlack(){
    return this.suit.isBlack();
  }

  public boolean isRed(){
    return this.suit.isRed();
  }

  public String toString(){
    return this.rank + this.suit;
  }

}
