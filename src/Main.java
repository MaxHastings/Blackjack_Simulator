import com.sun.media.jfxmedia.events.PlayerEvent;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.initializeStack(2);
        System.out.println(deck.toString());

        Dealer dealer = new Dealer(deck);
        dealer.getPlayers().add(new Player(1, dealer, 100, false));
        while(true){
            dealer.startNewRound();
        }
    }

    public static void handWon(Hand hand){
        System.out.println("Player: " + hand.getPlayer().getId() + " WON Bet:" + hand.getBet() + " Player Cash: $" + hand.getPlayer().getCash());
    }

    public static void handLost(Hand hand){
        System.out.println("Player: " + hand.getPlayer().getId() + " Lost Bet:" + hand.getBet() + " Player Cash: $" + hand.getPlayer().getCash());
    }

    public static void handTied(Hand hand){
        System.out.println("Player: " + hand.getPlayer().getId() + " Tie Bet:" + hand.getBet() + " Player Cash: $" + hand.getPlayer().getCash());
    }

    public static void showDealerHand(Hand hand){
        System.out.println("Dealer Hand: ");
        for(int i = 0; i < hand.getCards().size(); i++){
            System.out.print(hand.getCards().get(i).toString() + " | ");
        }
        System.out.print("\n");
    }

    public static void showPlayerHand(Player player, int handNum){
        System.out.print(player.getPlayerHandString(handNum));
    }


    public static void playerHand(int i, Hand hand){
        if(hand.getCards().get(0).getType() == hand.getCards().get(1).getType()){
            //Two cards that are the same. Player can split hand.
            System.out.println("Hit(1) Stand(2) Double Down (3) Split(4)");
        }else if(hand.getTurn() < 1){
            System.out.println("Hit(1) Stand(2) Double Down(3)");
        }else {
            System.out.println("Hit(1) Stand(2)");
        }
        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        if(command == 1){
            hand.getPlayer().hitHand(i);
        }else if(command == 2){
            hand.setStand(true);
        }else if(command == 3){
            hand.getPlayer().doubleDown(i);
        }else{
            if(hand.getCards().get(0).getType() == hand.getCards().get(1).getType()){
                hand.getPlayer().split(i);
            }else{
                System.out.println("Invalid Command");
            }
        }
    }

    public static double enterBet(Player player){
        System.out.println("Enter Bet for player: " + player.getId() + " Cash Reserve $" + player.getCash());
        Scanner sc = new Scanner(System.in);
        double amount = sc.nextDouble();
        return amount;
    }
}
