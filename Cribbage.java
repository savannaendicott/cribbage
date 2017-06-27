import java.util.*;
public class Cribbage extends CardGame{

  /*  The Rules of Cribbage :
  Players:      2 - 3
  Equipment:    Standard 52 card deck
  Gameplay:
    1. Start of the game
      - each player cuts a card, lowest plays first (tie draws again)
      - ace is low, face cards worth 1
      - for 2 players, each player gets 6 cards, 2 are discarded from each player to the crib
      - for 3 players, each player gets 5 cards, 1 is discarded by each player and from the deck to the crib
    2. Start of each round
      - dealer shuffles & deals (player to the left of last dealer, if not the first round)
      - each player chooses 4 cards to keep and donates the others to the crib
      - player on the dealer's left cuts the deck and the dealer flips the top card, the "cut"
      - if the dealer flips a jack, they peg 2. They can win on this peg
    3. The play / pegging
      - starts with player to the left of the dealer and goes clockwise
      - players flip card into the center face-up one at a time, accumulating the cards' worth
      - play cannot go above 31, if a player cannot play they say "go" and the next player continues
      - if total is 15, player pegs 2
      - 3-7 points for completing a run of 3-7, order doesn't matter
      - 2 points for laying a card with the same rank as the previous card, 6 for the third, 12 for the fourth
      - more than one combination can be scored (eg. 4 points for the second ace creating a total of 31)
      - if 31 is reach exactly the player pegs 2 points
      - otherwise last player to lay a card down pegs 1 point
      - then count resets to 0 and play continues, starting with player to the left of the player who played the last card in the former round
      - last round's cards are discarded and do not hold any carry-through
      - continues until all cards have been played
    4. The show
      - starting with the player to the dealer's left, each player displays their hand
      - 2 points for each separate combination of cards totaling to 15 - can be any number of cards, every combination scores x
      - run of 3-5 consecutive ranks scores 3-5 pegs (suit irrelevant)
      - two points for a pair x
      - six points for 3 of a kind x
      - twelve points for 12 of a kind x
      - 4 points for all cards being the same suit (+1 if the cut is that suit as well) x
      - 1 point for having a jack of the same suit as the cut x
      - dealer plays the crib as a second hand, scored the same except flushes only count if the cut is the same suit
    5. Winning
      - first player to peg 121 wins
      - the second they peg, they win.

      // can add match points later


  */


  private ArrayList<Card> crib;


  public Cribbage(int nP){
    super(nP, false);   // no jokers in cribbage
    crib = new ArrayList<Card>();

  }


  public void defineStages(){

  }

  public void run(){

  }



}
