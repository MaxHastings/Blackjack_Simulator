import java.util.ArrayList;

/**
 * Created by Maxwell on 3/26/2017.
 */
public class Player {

    private static final int HIT = 1;
    private static final int STAY = 0;

    private ArrayList<Card> hand = new ArrayList<>();

    private double cash = 0;

    private double bet = 0;

    private boolean AI = false;

    private int status = HIT;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public Player(double cash, boolean AI){
        this.cash = cash;
        this.AI = AI;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public void takeCards(){
        hand.clear();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int askPlayer(){
        if(AI){
            return 1;
        }else{

        }
        return 0;
    }
}
