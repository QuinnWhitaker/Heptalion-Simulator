//-----------------------------------------------------------------------------
// Author: Quinn Whitaker
// Date: 2/4/17
// CS 3340 HW 4: Heptalion
// Domino Class
//-----------------------------------------------------------------------------
package hw4;

import hw4.Game.Symbol;

public class Domino {
    private final Symbol sym1;
    private final Symbol sym2;
    
    Domino(Symbol s1, Symbol s2) {
        sym1 = s1;
        sym2 = s2;
    }
    
    Symbol getSym1(){
        return sym1;
    }
    
    Symbol getSym2(){
        return sym2;
    }
    
    public void Print(){
        System.out.print("[" + sym1.toChar() + "|" + sym2.toChar() + "]");
    }
}

// End of class
//-----------------------------------------------------------------------------