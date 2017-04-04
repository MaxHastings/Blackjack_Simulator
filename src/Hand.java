import java.util.ArrayList;

/**
 * Created by Maxwell on 3/27/2017.
 */
public class Hand {

    private ArrayList<Card> cards = new ArrayList<>();

    private boolean stand;

    private double bet;

    private Player player;

    private int turn = 0;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void increaseTurn(){
        turn++;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public Hand(Player player){
        this.player = player;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public boolean isStand() {
        return stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }
}
