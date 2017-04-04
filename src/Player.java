import java.util.ArrayList;

/**
 * Created by Maxwell on 3/26/2017.
 */
public class Player {

    private static final int HIT = 1;
    private static final int STAY = 0;

    private double defaultBet = 0;

    private ArrayList<Hand> hands = new ArrayList<>();

    private int id;

    private double cash = 0;

    private boolean AI = false;

    private int status = HIT;

    private Dealer dealer;

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

    public Player(int id, Dealer dealer, double cash, boolean AI){
        this.cash = cash;
        this.AI = AI;
        this.dealer = dealer;
        this.id = id;
    }

    public void hitHand(int i){
        Card card = dealer.requestCard();
        Hand hand = hands.get(i);
        hand.getCards().add(card);
        if(dealer.getBestValue(hand) > 21){
            dealer.handBust(hand);
        }
    }

    public void addCash(double amount){
        this.cash += amount;
    }

    public void stayHand(int i){
        hands.get(i).setStand(true);
    }

    public void nextMove(int i, Hand hand){
        Main.playerHand(i, hand);
    }

    public String getPlayerHandString(int x){
        String handString = "";
        Hand hand = getHands().get(x);
        handString += "Player: " + hand.getPlayer().getId() + " Hand: " + x + "\n Cards: \n";
        for(int j = 0; j < hand.getCards().size(); j++){
            handString += hand.getCards().get(j).toString() + " | ";
        }
        handString += "\n";
        return handString;
    }

    public double getDefaultBet() {
        return defaultBet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDefaultBet(double defaultBet) {
        this.defaultBet = defaultBet;
    }

    public double askForBet(){
        if(AI){

        }else{
            defaultBet = Main.enterBet(this);
            cash -= defaultBet;
        }
        return defaultBet;
    }

    public void doubleDown(int i){
        Hand hand = getHands().get(i);
        cash -= hand.getBet();
        hand.setBet(hand.getBet()*2);
    }

    public void split(int i){
        Hand hand = hands.remove(i);
        Card card = hand.getCards().get(1);
        Hand splitHand = new Hand(this);
        splitHand.getCards().add(card);
        hands.add(splitHand);
    }

    public void takeCards(){
        hands.clear();
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public void setHands(ArrayList<Hand> hands) {
        this.hands = hands;
    }

}
