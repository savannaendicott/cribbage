
import java.util.*;

public class UI{

  public UI(ArrayList<Player> players){
    System.out.println("New Cribbage game with the following players:");
    for(Player p: players){
      System.out.println("  "+ p.getName());
    }
  }

  public void startTurn(Player p){
    System.out.println("\n"+ p.getName() +"'s turn...");
  }

  public void startRound(String r){
    System.out.println("\n\n" + r);
  }

  public void peg(Player p, int n){
    System.out.println(p.getName() + " pegged " + n);
  }



}
