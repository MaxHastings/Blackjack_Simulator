import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxwell on 3/26/2017.
 */
public class Dealer {

    private Deck deck;

    private ArrayList<Player> players = new ArrayList<>();

    private ArrayList<Player> currentPlayers = new ArrayList<>();

    public Dealer(Deck deck){
        this.deck = deck;
    }

    public Hand dealerHand = new Hand(null);

    public void startNewRound(){
        currentPlayers = new ArrayList<>(players);
        for(int i = 0; i < currentPlayers.size(); i++){
            Player player = currentPlayers.get(i);
            double bet = player.askForBet();
            Hand hand = new Hand(player);
            hand.getCards().add(deck.takeTopCard());
            hand.getCards().add(deck.takeTopCard());
            hand.setBet(bet);
            if(getBestValue(hand) == 21){
                blackJack(hand);
            }else {
                player.getHands().add(hand);
            }
        }
        dealerHand.getCards().clear();
        dealerHand.getCards().add(deck.takeTopCard());
        Main.showDealerHand(dealerHand);
        askPlayers();
        finishGame();
    }

    public boolean isBust(Hand hand){
        return getBestValue(hand) > 21;
    }

    public boolean is21(Hand hand){
        return getBestValue(hand) == 21;
    }

    public void handWon(Hand hand){
        hand.getPlayer().addCash(hand.getBet()*2);
        Main.handWon(hand);
    }

    public void blackJack(Hand hand){
        hand.getPlayer().addCash(hand.getBet() * 3/2);
    }

    public void handLost(Hand hand){
        Main.handLost(hand);
    }

    public void askPlayers(){
        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            ArrayList<Hand> hands = player.getHands();
            for(int j = 0; j < hands.size(); j++){
                Hand hand = hands.get(j);
                while(true){
                    Main.showPlayerHand(player, j);
                    if(isBust(hand)){
                        handLost(hand);
                        return;
                    }else if(is21(hand)) {
                        handWon(hand);
                        return;
                    }else if(hand.isStand()){
                        return;
                    }else{
                        player.nextMove(j, hand);
                        hand.increaseTurn();
                    }
                }
            }
        }
    }

    public void handBust(Hand hand){
        Main.handLost(hand);
        hand.getPlayer().getHands().remove(hand);
    }

    public void handTied(Hand hand){
        hand.getPlayer().addCash(hand.getBet());
        Main.handTied(hand);
    }

    public void finishGame(){
        while(getBestValue(dealerHand) < 17){
            dealerHand.getCards().add(deck.takeTopCard());
            Main.showDealerHand(dealerHand);
        }
        for(int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            for (int j = 0; j < player.getHands().size(); j++) {
                Hand hand = player.getHands().get(i);
                if(getBestValue(hand) > getBestValue(dealerHand) || getBestValue(dealerHand) > 21){
                    handWon(hand);
                }else if(getBestValue(hand) == getBestValue(dealerHand)){
                    handTied(hand);
                }else{
                    handLost(hand);
                }
                player.getHands().remove(j);
            }
        }
    }

    public Card requestCard(){
        return getDeck().takeTopCard();
    }

    public int getBestValue(Hand hand){
        int bestValue = 0;
        for(int j = 0; j < hand.getCards().size(); j++) {
            int value = 0;
            int n = j;
            for (int i = 0; i < hand.getCards().size(); i++) {
                if (hand.getCards().get(i).getType() == Card.ACE && n > 0) {
                    value += hand.getCards().get(i).getValue2();
                    n--;
                } else {
                    value += hand.getCards().get(i).getValue1();
                }
            }
            if(value <= 21 || j == 0) {
                bestValue = value;
            }
        }
        return bestValue;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
