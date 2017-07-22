import java.util.*;

public class User extends CribbagePlayer{
  private String realName;

  public User(String name){
    super(name + " (You)");
    this.realName = name;
  }

  public String getRealName(){
    return this.realName;
  }

  public ArrayList<Card> getHand(){
    return this.hand;
  }

  public void remove(Card c){
    this.hand.remove(c);
  }

  public Card getCardByString(String cardStr){
    for(Card c : this.hand){
      if(cardStr.equals(c.toString())){
        return c;
      }
    }
    return null;
  }

}
