//-----------------------------------------------------------------------------
// Author: Quinn Whitaker
// Date: 2/4/17
// CS 3340 HW 4: Heptalion
// Board Class
//-----------------------------------------------------------------------------
package hw4;

import hw4.Game.Symbol;

public final class Board {
    public final int width = 11;
    public final int height = 11;
    Symbol spaces[][] = new Symbol[width][height];
    
    // Adds the following symbol to the Board.
    // NOTE: Board must be initialized with blanks and occupied before
    // doing so.
    private void inse(Symbol s) {
        if (s != Symbol.occupied && s != Symbol.blank) {
            for (int i = 0; i < width; i++) {
                for (int i2 = 0; i2 < height; i2++) {
                    if (spaces[i][i2] == Symbol.occupied) {
                        spaces[i][i2] = s;
                        return;
                    }
                }
            }
        }
    }
    
    public Board() {
        Initialize();
    }
    
    // Sets the value of all spaces to their initial values.
    public void Initialize() {
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                if ((i + i2 < 5)
                        || (i - i2 < -5)
                        || (i2 - i < -5)
                        || (i + i2 > 15)
                        || ((i == 5 && (i2 == 4 || i2 == 5 || i2 == 6)))
                        || ((i2 == 5 && (i == 4 || i == 6)))) {
                    spaces[i][i2] = Symbol.blank;
                } else {
                    spaces[i][i2] = Symbol.occupied;
                }
            }
        }

        inse(Symbol.cross);
        inse(Symbol.circle);
        inse(Symbol.diamond);
        inse(Symbol.square);
        inse(Symbol.cross);
        inse(Symbol.dot);
        inse(Symbol.diamond);
        inse(Symbol.diamond);
        inse(Symbol.dot);
        inse(Symbol.clover);
        inse(Symbol.dot);
        inse(Symbol.dot);
        inse(Symbol.cross);
        inse(Symbol.diamond);
        inse(Symbol.clover);
        inse(Symbol.circle);
        inse(Symbol.dot);
        inse(Symbol.square);
        inse(Symbol.dot);
        inse(Symbol.plus);
        inse(Symbol.plus);
        inse(Symbol.clover);
        inse(Symbol.clover);
        inse(Symbol.clover);
        inse(Symbol.circle);
        inse(Symbol.square);
        inse(Symbol.square);
        inse(Symbol.diamond);
        inse(Symbol.circle);
        inse(Symbol.cross);
        inse(Symbol.cross);
        inse(Symbol.square);
        inse(Symbol.clover);
        inse(Symbol.square);
        inse(Symbol.circle);
        inse(Symbol.dot);
        inse(Symbol.square);
        inse(Symbol.cross);
        inse(Symbol.cross);
        inse(Symbol.plus);
        inse(Symbol.cross);
        inse(Symbol.circle);
        inse(Symbol.circle);
        inse(Symbol.square);
        inse(Symbol.plus);
        inse(Symbol.plus);
        inse(Symbol.clover);
        inse(Symbol.plus);
        inse(Symbol.circle);
        inse(Symbol.plus);
        inse(Symbol.plus);
        inse(Symbol.diamond);
        inse(Symbol.diamond);
        inse(Symbol.clover);
        inse(Symbol.dot);
        inse(Symbol.square);
    }
    
    public Symbol getSpace(int x, int y) {
        return spaces[x][y];
    }
    
    // This function checks whether a Symbol s is located adjacently to a given space.
    private boolean isAdjacent(Symbol sym, int space_y, int space_x) {
        if (width < 0 || height < 0) throw new java.lang.Error("width or height undefined.");
        if (space_y < 0 || space_y >= height) throw new java.lang.Error("invalid y value.");
        if (space_x < 0 || space_x >= width) throw new java.lang.Error("invalid x value.");
        
        // These bools determine whether the given space is located on any edge of the board.
        boolean isLeft = space_x == 0;
        boolean isRight = space_x == width - 1;
        boolean isTop = space_y == 0;
        boolean isBottom = space_y == height - 1;
        
        // Checks the left side
        if (!isLeft) {
            if (sym == spaces[space_y][space_x - 1]) return true;
        }
        // Checks top
        if (!isTop) {
            if (sym == spaces[space_y - 1][space_x]) return true;
        }
        // Checks right
        if (!isRight) {
            if (sym == spaces[space_y][space_x + 1]) return true;
        }
        // Checks bottom
        if (!isBottom) {
            if (sym == spaces[space_y + 1][space_x]) return true;
        }
        return false;
        
    }
    
    // This function determines whether a given Domino has a space to be placed on the board.
    public boolean isAvail(Domino d) {
        Symbol symbol1 = d.getSym1();
        Symbol symbol2 = d.getSym2();
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                if (symbol1 == spaces[i][i2]) {
                    if (isAdjacent(symbol2, i, i2)) return true;
                }
            }
        }
        return false;
    }
    
    public void placeDomino(int x1, int y1, int x2, int y2) {

        if ((x1 >= 0 && x1 < height) &&
            (x2 >= 0 && x2 < height) &&
            (y1 >= 0 && y1 < width) &&
            (y2 >= 0 && y2 < width)) {
            spaces[x1][y1] = Symbol.occupied;
            spaces[x2][y2] = Symbol.occupied;
        }
    }
    // Prints the board, including numbers along the borders to guide the player in selecting spaces.
    public void Print() {
        System.out.print("      ");
        for (int i = -width; i < width; i++) {
            if (i < 0) {
                System.out.print((i + width + 1) + " ");
                if ((i + width + 1) == width) System.out.print("< Columns");
            }
            else {
                if (i == 0) System.out.print("\n\n");
                for (int i2 = 0; i2 < height; i2++) {
                    if (i2 == 0) {
                        if ((i + 1) < 10) System.out.print(" ");
                        System.out.print(i + 1 + "    ");
                    }
                    System.out.print(spaces[i][i2].toChar() + " ");
                }
                System.out.print("\n");
            }
            
        }
        System.out.print(" ^\nRows\n");
    }
}

// End of class
//-----------------------------------------------------------------------------