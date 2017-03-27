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

    public void startNewRound(){
        currentPlayers = new ArrayList<>(players);
        for(int i = 0; i < currentPlayers.size(); i++){
            Player player = currentPlayers.get(i);
            player.addCard(deck.takeTopCard());
            player.addCard(deck.takeTopCard());
            if(getBestValue(player.getHand()) == 21){
                player.setBet(player.getBet()*1.5);
                takeCards(player);
            }
        }
    }

    public void takeCards(Player player){
        player.takeCards();
        currentPlayers.remove(player);
    }

    public void playerHit(Player player){
        player.addCard(deck.takeTopCard());
        if(getBestValue(player.getHand()) > 21){
            player.takeCards();
        }
    }

    private int getBestValue(ArrayList<Card> cards){
        int value = 0;
        for(int i = 0; i < cards.size(); i++){
            value += cards.get(i).getValue1();
        }
        return value;
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
