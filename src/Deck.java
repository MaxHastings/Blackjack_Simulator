import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Maxwell on 3/26/2017.
 */
public class Deck {

    private Card[] stack;

    private int pos = 0;

    public void initializeStack(int numofDecks){
        stack = new Card[52 * numofDecks];
        int x = 0;
        for(int i = 0; i < numofDecks; i++) {
            for (int j = 1; j < 14; j++) {
                for (int k = 1; k < 5; k++) {
                    stack[x] = new Card(j);
                    x++;
                }
            }
        }
        shuffleStack();
        pos = 0;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "stack=" + Arrays.toString(stack) +
                '}';
    }

    public Card takeTopCard(){
        if(pos > stack.length){
            shuffleStack();
            pos = 0;
        }
        int remove = pos;
        pos++;
        return stack[remove];
    }

    public void shuffleStack(){
        Collections.shuffle(Arrays.asList(stack));
    }

    public Card[] getStack() {
        return stack;
    }

    public void setStack(Card[] stack) {
        this.stack = stack;
    }
}
