//-----------------------------------------------------------------------------
// Author: Quinn Whitaker
// Date: 2/4/17
// CS 3340 HW 4: Heptalion
// Player Class
//-----------------------------------------------------------------------------
package hw4;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public final String name;
    List<Domino> hand = new ArrayList();
    
    Player(String n) {
        name = n;
    }
    
    public void addDomino(Domino d) {
        hand.add(d);
    }
    
    
    public void removeDomino(int index) {
        hand.remove(index);
    }

    public Domino getDomino(int index) {
        return hand.get(index);
    }
    
    public void showHand() {
        int re = 0;
        for (int i = 0; i < hand.size(); i++) {
            System.out.print((i+1) + ": ");
            hand.get(i).Print();
            System.out.print(" ");
            re++;
            if (re == 7) {
                System.out.print("\n");
                re = 0;
            }
        }
    }
}

// End of class
//-----------------------------------------------------------------------------