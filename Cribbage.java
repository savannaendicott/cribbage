import java.util.*;
public class Cribbage extends CardGame{

  private int NO_CARDS_IN_HAND = 4;
  private CribbageUI ui;

  private ArrayList<Card> crib;

  public Cribbage(){
    super(false);   // no jokers in cribbage
    this.crib = new ArrayList<Card>();
    this.ui = new CribbageUI();

    String name = this.ui.getUserName();
    this.user = new User(name);
    this.players.add(user);
  }

  public void printPlayers(){
    System.out.print("Players:   ");
    for(Player p : this.players)
    System.out.print(p.getName()+"   ");
  }

  /*
    dealer shuffles & deals 6 cards (for 2 player game), or 5 cards (for 3 player game) plus 1 to the crib
  */
  public void dealIn(CribbagePlayer dealer){
    this.deck.reset();
    int noPlayers = this.players.size();
    int noCards = (noPlayers == 3) ? 5 : 6;

    if(this.crib.size() < 4) this.crib.add(deck.draw());

    this.ui.deal(noCards, dealer);
    for(Player p : this.players){
      p.add(deck.draw(noCards));
      p.print();
    }
  }

  /*- starts with player to the left of the dealer and goes clockwise
  - x players flip card into the center face-up one at a time, accumulating the cards' worth
  - x play cannot go above 31, if a player cannot play they say "go" and the next player continues
  - x if total is 15, player pegs 2
  - 3-7 points for completing a run of 3-7, order doesn't matter
  - 2 points for laying a card with the same rank as the previous card, 6 for the third, 12 for the fourth
  - x more than one combination can be scored (eg. 4 points for the second ace creating a total of 31)
  - x if 31 is reached exactly the player pegs 2 points
  - x otherwise last player to lay a card down pegs 1 point
  - x then count resets to 0 and play continues, starting with player to the left of the player who played the last card in the former round
  - last round's cards are discarded and do not hold any carry-through
  - continues until all cards have been played*/
  public boolean pegging(CribbagePlayer dealer){
    boolean winner = false;
    ArrayList<Card> currentPlay = new ArrayList<Card> ();
    ArrayList<Card> laid = new ArrayList<Card>();
    int allCards = NO_CARDS_IN_HAND * this.players.size();

    CribbagePlayer turn = dealer;
    while(laid.size() < allCards && !winner){
      int sum = 0;
      while(sum < 31){
        int played = 0;
        turn = getNextPlayer(turn);
        ArrayList<Card> remaining_cards = turn.getRemainingHand(laid);
        Card choice = null;
        if(canPlay(sum,remaining_cards)){
          played ++;
          if(turn instanceof User){
            this.ui.displayHandToUser(remaining_cards);
            int rank = 60;
            while( (sum + rank) > 31 ){
              choice = promptDiscard(turn);
              if(choice != null) rank = choice.getIndexValue();
            }
          }else if(turn instanceof CPU){
            CPU cpu = (CPU) turn;
            choice = cpu.selectCardToPlay(remaining_cards, currentPlay);
          }
          sum += choice.getIndexValue();
          currentPlay.add(choice);
          laid.add(choice);

          if(sum == 15){
            if(peg(turn, 2, " for hitting 15")) return true;
          }

          if(played < 2) break;

        }
      }
      if(sum == 31){
        if(peg(turn, 2, " for hitting 31")) return true;
      }
      else{
        if(peg(turn, 1, " for playing last")) return true;
      }

      currentPlay.clear();
    }


    return winner;
  }

  public boolean canPlay(int n, ArrayList<Card> remaining_cards){
    int max_size = 31 - n;
    for(Card c : remaining_cards){
      if(c.getIndexValue() <= max_size) return true;
    }
    return false;
  }

  public boolean peg(CribbagePlayer player, int n, String reason){
    player.peg(n);
    this.ui.peg(player, n, reason);
    return checkForWinner(player);
  }

  public boolean showing(CribbagePlayer dealer, StandardCard cut){
    boolean winner = false;

    CribbagePlayer turn = dealer;

    for(int i =0; i < this.players.size(); i++){
      turn = getNextPlayer(turn);
      int pegs = turn.peg(cut);
      this.ui.showHand(turn, pegs);
      if(pegs > 0) this.ui.updatePegBoard(turn);
      winner = checkForWinner(turn);
      if(turn == dealer){
        pegs = turn.peg(this.crib, cut);
        this.ui.revealCrib(turn,pegs, crib);
        if(pegs > 0) this.ui.updatePegBoard(turn);
      }
      winner = checkForWinner(turn);
    }
    return winner;
  }

  public Card promptDiscard(Player p){
    String discardStr = this.ui.promptDiscard();
    User user = (User)p;
    Card discard = user.getCardByString(discardStr);
    if(discard == null) this.ui.printInfo("Card not found. Please try again.");
    return discard;
  }


  /* each player chooses 4 cards to keep and donates the others to the crib
  */
  public void createCrib(StandardCard cut){
    this.ui.printInfo("...Everyone chooses 4 cards to keep...");
    for(Player p : this.players){
      if(p instanceof User){
        User usr = (User)p;
        this.ui.displayHandToUser(usr.getHand());
        int i =0;
        while(usr.getHand().size() > 4){
          Card discard = promptDiscard(usr);
          if(discard != null){
            this.crib.add(discard);
            usr.remove(discard);
            i++;
          }
        }
        this.ui.printInfo("O.K.! Everyone made a decision. Here is your new hand: ");
        this.ui.displayHandToUser(usr.getHand());
      }
      else if(p instanceof CPU){
        CPU cpu = (CPU)p;
        for(Card c : cpu.discardToCrib(cut))
          this.crib.add(c);
      }
    }


  }

  public StandardCard cut(CribbagePlayer dealer){
    StandardCard cut = (StandardCard) this.deck.draw();
    this.ui.cardFlip(dealer, cut);
    if(cut.getRank() == "J"){
      dealer.peg(2);
      this.ui.updatePegBoard(dealer);
    }
    return cut;
  }

  public boolean checkForWinner(Player player){
    CribbagePlayer p = (CribbagePlayer)player;
    if(p.getPegs() > 121){
      return true;
    }
    else return false;
  }

  public CribbagePlayer startGame(){
    CribbagePlayer dealer;
    int numComps = this.ui.mainMenu();
    while(numComps > 0){
      this.players.add(new CPU());
      numComps --;
    }
    this.ui.startGame(this.players);
    dealer = (CribbagePlayer)cutToFindWhoGoesFirst(this.ui);
    this.ui.printInfo(dealer.getName()+" deals first");
    return dealer;
  }

  public void cleanupRound(){
    for(Player p : this.players){
      p.clearHand();
    }
    this.crib.clear();
  }

  public CribbagePlayer getNextPlayer(Player currentPlayer){
    int index = this.players.indexOf(currentPlayer);
    int newDealerIndex = (index == this.players.size()-1)? 0 : (index+1);
    return (CribbagePlayer) this.players.get(newDealerIndex);
  }


  public void run(){
    boolean gameOver = false;
    CribbagePlayer dealer;
    StandardCard cut;
    dealer = startGame();
    while(!gameOver){
      dealIn(dealer);
      cut = cut(dealer);
      createCrib(cut);
      gameOver = checkForWinner(dealer);
      gameOver = pegging(dealer);
      gameOver = showing(dealer,cut);
      this.ui.endPlayRound();
      dealer = getNextPlayer(dealer);
      System.out.println(dealer.getName()+" is the new dealer!");
      cleanupRound();
    }
  }
  public static void main(String[] args){
    //System.out.println("Random name: "+ NameGenerator.getRandomName());
    Cribbage game = new Cribbage();
    game.run();



  }

}
