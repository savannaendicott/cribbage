import java.util.*;
public class Cribbage extends CardGame{

  private int NO_CARDS_IN_HAND = 4;
  private CribbageUI ui;
  /*  The Rules of Cribbage :
  Players:      2 - 3
  Equipment:    Standard 52 card deck
  Gameplay:
    1. Start of the game
      - show main menu
      - have player choose number of players
      - display game and players

      - cut to see who goes first...
      - each player "cuts" a random card from the deck
      - person who flips the highest rank becomes the first dealer  (tie draws again);

    2. New Round
      - dealer shuffles & deals 6 cards (for 2 player game), or 5 cards (for 3 player game) plus 1 to the crib
      - each player chooses 4 cards to keep and donates the others to the crib
      - player on the dealer's left cuts the deck and the dealer flips the top card, the "cut"
      - if the dealer flips a jack, they peg 2. They can win on this peg

    3. Pegging
      - starts with player to the left of the dealer and goes clockwise
      - players flip card into the center face-up one at a time, accumulating the cards' worth
      - play cannot go above 31, if a player cannot play they say "go" and the next player continues
      - if total is 15, player pegs 2
      - 3-7 points for completing a run of 3-7, order doesn't matter
      - 2 points for laying a card with the same rank as the previous card, 6 for the third, 12 for the fourth
      - more than one combination can be scored (eg. 4 points for the second ace creating a total of 31)
      - if 31 is reached exactly the player pegs 2 points
      - otherwise last player to lay a card down pegs 1 point
      - then count resets to 0 and play continues, starting with player to the left of the player who played the last card in the former round
      - last round's cards are discarded and do not hold any carry-through
      - continues until all cards have been played

    4. Showing
      - starting with the player to the dealer's left, each player displays their hand
      - 2 points for each separate combination of cards totaling to 15 - can be any number of cards, every combination scores x
      - run of 3-5 consecutive ranks scores 3-5 pegs (suit irrelevant)
      - two points for a pair x
      - six points for 3 of a kind x
      - twelve points for 12 of a kind x
      - 4 points for all cards being the same suit (+1 if the cut is that suit as well) x
      - 1 point for having a jack of the same suit as the cut x
      - dealer plays the crib as a second hand, scored the same except flushes only count if the cut is the same suit
      - player to the left of the last dealer becomes the new one

    5. Winning
      - first player to peg 121 wins
      - the second they peg, they win.

      // can add match points later


  */


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
  - players flip card into the center face-up one at a time, accumulating the cards' worth
  - play cannot go above 31, if a player cannot play they say "go" and the next player continues
  - if total is 15, player pegs 2
  - 3-7 points for completing a run of 3-7, order doesn't matter
  - 2 points for laying a card with the same rank as the previous card, 6 for the third, 12 for the fourth
  - more than one combination can be scored (eg. 4 points for the second ace creating a total of 31)
  - if 31 is reached exactly the player pegs 2 points
  - otherwise last player to lay a card down pegs 1 point
  - then count resets to 0 and play continues, starting with player to the left of the player who played the last card in the former round
  - last round's cards are discarded and do not hold any carry-through
  - continues until all cards have been played*/
  public boolean pegging(CribbagePlayer dealer){
    boolean winner = false;



    return winner;
  }

  /*- starting with the player to the dealer's left, each player displays their hand
  - 2 points for each separate combination of cards totaling to 15 - can be any number of cards, every combination scores x
  - run of 3-5 consecutive ranks scores 3-5 pegs (suit irrelevant)
  - two points for a pair x
  - six points for 3 of a kind x
  - twelve points for 12 of a kind x
  - 4 points for all cards being the same suit (+1 if the cut is that suit as well) x
  - 1 point for having a jack of the same suit as the cut x
  - dealer plays the crib as a second hand, scored the same except flushes only count if the cut is the same suit
  - player to the left of the last dealer becomes the new one*/
  public boolean showing(CribbagePlayer dealer, Card cut){
    boolean winner = false;

    int index = this.players.indexOf(dealer) +1;

    for(int i =0; i < this.players.size(); i++){
      if(index == this.players.size()) index =0;
      CribbagePlayer turn = (CribbagePlayer)this.players.get(index++);
      int pegs = turn.peg(cut);
      this.ui.showHand(turn, pegs);
      if(pegs > 0) this.ui.updatePegBoard(turn);
      winner = checkForWinner(turn);
      if(turn == dealer){
        System.out.println("This is the dealer, they have the crib.");
        pegs = turn.peg(this.crib, cut);
        this.ui.revealCrib(turn,pegs, crib);
        if(pegs > 0) this.ui.updatePegBoard(turn);
      }
      winner = checkForWinner(turn);
    }
    return winner;
  }


  /* each player chooses 4 cards to keep and donates the others to the crib
  */
  public void createCrib(){
    this.ui.printInfo("...Everyone chooses 4 cards to keep...");
    for(Player p : this.players){
      if(p instanceof User){
        User usr = (User)p;
        this.ui.displayHandToUser(usr.getHand());
        int i =0;
        while(usr.getHand().size() > 4){
          String discardStr = this.ui.promptDiscard();
          Card discard = usr.getCardByString(discardStr);
          if(discard == null) this.ui.printInfo("Card not found. Please try again.");
          else{
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
        for(Card c : cpu.discardToCrib())
          this.crib.add(c);
      }
    }


  }

  /*player on the dealer's left cuts the deck and the dealer flips the top card, the "cut"
  if the dealer flips a jack, they peg 2. They can win on this peg*/
  public Card cut(CribbagePlayer dealer){
    Card cut = this.deck.draw();
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

  /* Start of the game
      - show main menu
      - have player choose number of players
      - display game and players

      - cut to see who goes first...
      - each player "cuts" a random card from the deck
      - person who flips the highest rank becomes the first dealer  (tie draws again);
    */
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
  public CribbagePlayer getNewDealer(Player currentDealer){
    int index = this.players.indexOf(currentDealer);
    int newDealerIndex = (index == this.players.size()-1)? 0 : (index+1);
    return (CribbagePlayer) this.players.get(newDealerIndex);
  }


  public void run(){
    boolean gameOver = false;
    CribbagePlayer dealer;
    Card cut;
    dealer = startGame();
    while(!gameOver){
      dealIn(dealer);
      createCrib();
      cut = cut(dealer);
      gameOver = checkForWinner(dealer);
      gameOver = pegging(dealer);
      gameOver = showing(dealer,cut);
      this.ui.endPlayRound();
      dealer = getNewDealer(dealer);
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
