import java.util.ArrayList;

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
            player.getHands().add(hand);
            if(getBestValue(hand) == 21){
                player.setCash(hand.getBet()*1.5);
                takeCards(player);
            }
        }
        dealerHand.getCards().clear();
        dealerHand.getCards().add(deck.takeTopCard());
        Main.showDealerHand(dealerHand);
        askPlayers();
        finishGame();
    }

    public void takeCards(Player player){
        player.takeCards();
        currentPlayers.remove(player);
    }

    public void askPlayers(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).nextMove();
        }
    }

    public void handBust(Player player, Hand hand){
        Main.handLost(hand);
        player.getHands().remove(hand);
    }

    public void finishGame(){
        while(getBestValue(dealerHand) < 17){
            dealerHand.getCards().add(deck.takeTopCard());
        }
        Main.showDealerHand(dealerHand);
        for(int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            for (int j = 0; j < player.getHands().size(); j++) {
                Hand hand = player.getHands().get(i);
                if(getBestValue(hand) > getBestValue(dealerHand) || getBestValue(dealerHand) > 21){
                    player.setCash(player.getCash() + hand.getBet()*2);
                    Main.handWon(hand);
                }else if(getBestValue(hand) == getBestValue(dealerHand)){
                    player.setCash(player.getCash() + hand.getBet());
                    Main.handTied(hand);
                }else{
                    Main.handLost(hand);
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
