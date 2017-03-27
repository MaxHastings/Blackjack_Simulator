/**
 * Created by Maxwell on 3/26/2017.
 */
public class Card {

    public final static int ACE = 1;
    public final static int TWO = 2;
    public final static int THREE = 3;
    public final static int FOUR = 4;
    public final static int FIVE = 5;
    public final static int SIX = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE = 9;
    public final static int TEN = 10;
    public final static int JACK = 11;
    public final static int QUEEN = 12;
    public final static int KING = 13;

    private int type;

    private int value1;

    private int value2;

    private void setCardValue(){
        value2 = 99;
        switch(type) {
            case 1:
                value1 = 1;
                value2 = 11;
                break;
            case 2:
                value1 = 2;
                break;
            case 3:
                value1 = 3;
                break;
            case 4:
                value1 = 4;
                break;
            case 5:
                value1 = 5;
                break;
            case 6:
                value1 = 6;
                break;
            case 7:
                value1 = 7;
                break;
            case 8:
                value1 = 8;
                break;
            case 9:
                value1 = 9;
                break;
            case 10:
                value1 = 10;
                break;
            case 11:
                value1 = 11;
                break;
            case 12:
                value1 = 12;
                break;
            case 13:
                value1 = 13;
                break;
            default:
                value1 = 99;
        }
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public Card(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch(type) {
            case 1 :
                return "ACE";
            case 2 :
                return "2";
            case 3 :
                return "3";
            case 4 :
                return "4";
            case 5 :
                return "5";
            case 6 :
                return "6";
            case 7 :
                return "7";
            case 8 :
                return "8";
            case 9 :
                return "9";
            case 10 :
                return "10";
            case 11 :
                return "JACK";
            case 12 :
                return "QUEEN";
            case 13 :
                return "KING";
            default :
                return "ERR";
        }
    }
}
