//-----------------------------------------------------------------------------
// Author: Quinn Whitaker
// Date: 2/4/17
// CS 3340 HW 4: Heptalion
// Game Class
//-----------------------------------------------------------------------------
package hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    
    List<Player> players = new ArrayList();
    List<Domino> pile = new ArrayList();
    Board board = new Board();
    
    Game() { // Constructor
        // One of each possible domino is added to the list of dominos.
        pile.add(new Domino(Symbol.clover, Symbol.clover));
        pile.add(new Domino(Symbol.clover, Symbol.plus));
        pile.add(new Domino(Symbol.clover, Symbol.cross));
        pile.add(new Domino(Symbol.clover, Symbol.circle));
        pile.add(new Domino(Symbol.clover, Symbol.square));
        pile.add(new Domino(Symbol.clover, Symbol.dot));
        pile.add(new Domino(Symbol.clover, Symbol.diamond));       
        pile.add(new Domino(Symbol.plus, Symbol.plus));
        pile.add(new Domino(Symbol.plus, Symbol.cross));
        pile.add(new Domino(Symbol.plus, Symbol.circle));
        pile.add(new Domino(Symbol.plus, Symbol.square));
        pile.add(new Domino(Symbol.plus, Symbol.dot));
        pile.add(new Domino(Symbol.plus, Symbol.diamond));        
        pile.add(new Domino(Symbol.cross, Symbol.cross));
        pile.add(new Domino(Symbol.cross, Symbol.circle));
        pile.add(new Domino(Symbol.cross, Symbol.square));
        pile.add(new Domino(Symbol.cross, Symbol.dot));
        pile.add(new Domino(Symbol.cross, Symbol.diamond));       
        pile.add(new Domino(Symbol.circle, Symbol.circle));
        pile.add(new Domino(Symbol.circle, Symbol.square));
        pile.add(new Domino(Symbol.circle, Symbol.dot));
        pile.add(new Domino(Symbol.circle, Symbol.diamond));        
        pile.add(new Domino(Symbol.square, Symbol.square));
        pile.add(new Domino(Symbol.square, Symbol.dot));
        pile.add(new Domino(Symbol.square, Symbol.diamond));       
        pile.add(new Domino(Symbol.dot, Symbol.dot));
        pile.add(new Domino(Symbol.dot, Symbol.diamond));
        pile.add(new Domino(Symbol.diamond, Symbol.diamond));
    }
    // The Symbol enum, used for the Dominos and Board.
    // Clover, diamond, plus, cross, square, circle, dot are the domino and board values
    // while blank and occupied are non-domino values used exclusively for the board.
    public enum Symbol {
        clover, diamond, plus, cross, square, circle, dot, blank, occupied;
        
        // This method returns the char equivalents of each Symbol,
        // which are used to print Symbols to the screen.
        public char toChar() {
            switch(this) {
                case clover: return '&';
                case diamond: return '$';
                case plus: return '+';
                case cross: return 'x';
                case square: return '#';
                case circle: return 'o';
                case dot: return '@';
                case blank: return ' ';
                case occupied: return '-';
                default: return '!';
            }
        }
    }
    
    // This method adds each player to the game, prompting each player to enter his or her name.
    private void addPlayers() {
        Scanner kb = new Scanner(System.in);
        String name;
        int numPlayers;
        
        // Number of players is set between 2 and 4.
        while (true) {
            System.out.print("Enter the number of players (2-4): ");
            numPlayers = kb.nextInt();
            if (numPlayers >= 2 && numPlayers <= 4) break;
        }
        
        // Each player's name is set and the player is added to the list of players.
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Player " + (i + 1) + ", enter name: ");
            name = kb.next();
            Player temp = new Player(name);
            players.add(temp);
        }
    }
    
    // This method distributes dominos to each player.
    private void drawPile() {
        Random rand = new Random();
        int rIndex; // Index used to pull a random Domino from the pile.
        int pIndex = 0; // Index used to mark the current player in distrubuting Dominos.
        // Each player takes a random Domino from the pile until the pile is empty.
        while (pile.size() > players.size() % 2) {
            rIndex = rand.nextInt(pile.size());
            players.get(pIndex).addDomino(pile.get(rIndex));
            pile.remove(rIndex);
            pIndex = (pIndex + 1) % players.size();
        }
    }
    
    // This method sets up the game by adding players then by giving each of them dominos.
    private void setUp() {
        board.Initialize();
        addPlayers();
        drawPile();
    }
    
    private Domino chooseDomino(Player p) {
        Scanner kb = new Scanner(System.in);
        Domino ret_domino;
        int input;
        
        while (true) {
            System.out.print("\nChoose a domino in hand (");
            if (p.hand.size() > 1) {
                System.out.print("1-" + p.hand.size() + "): ");
            }
            else System.out.print("1): ");
            input = kb.nextInt();
            if (input > 0 && input <= p.hand.size()) {
                ret_domino = p.hand.get(input-1);
                if (board.isAvail(ret_domino)) return ret_domino;
                else System.out.println("The chosen domino cannot be played. Choose another domino.");
            }
            else System.out.println("Invalid input.");
        }
    }
    
    // Given a domino, prompts user to find a space on the board for the domino, 
    // then places the domino there.
    private void chooseSpaceAndPlace(Domino domino) {
        Scanner kb = new Scanner(System.in);
        int x1 = 0; // x position of first space
        int y1 = 0; // y position of first space
        int x2 = 0; // x position of second space
        int y2 = 0; // y position of second space
        String line; // String read for keyboard when determining direction
        boolean isUp; // whether the user chose to place the second tile above the first tile
        boolean isDown; // whether they chose down
        boolean isLeft; // and so on
        boolean isRight;
        Symbol s1 = domino.getSym1();
        Symbol s2 = domino.getSym2();
        
        // Space of first symbol
        while (true) {
            System.out.println("Select a board space to place " + s1.toChar() + " (First tile of the chosen Domino).");
            System.out.print("Enter in the format ROW COLUMN (Ex. 3 5): ");
            
            x1 = kb.nextInt();
            y1 = kb.nextInt();
            x1--;
            y1--;
            
            // First checks whether it's valid input, then checks whether it's in the range of the board.
            if (x1 < 0 || x1 >= board.height || y1 < 0 || y1 >= board.width) System.out.println("Invalid input.");
            else if (board.spaces[x1][y1] != s1) System.out.println("The first tile of the Domino does not match this space. ");
            else break;
        }
        // Space of second symbol
        System.out.println("Now choose an adjacent spot to the one you picked.");
        while (true) {
            System.out.println("Pick a direction to place " + s2.toChar() + " (Second tile of the chosen Domino).");
            System.out.print("Either up, down, left, or right (U D L or R) : ");
            line = kb.next();
            
            isUp = "U".equals(line);
            isDown = "D".equals(line);
            isLeft = "L".equals(line);
            isRight = "R".equals(line);
            
            if (!isUp && !isDown && !isLeft && !isRight) System.out.println("Invalid input.");
            else if ((isUp && x1 == 0) ||
                    (isLeft && y1 == 0) ||
                    (isDown && x1 == board.height - 1) ||
                    (isRight && y1 == board.width - 1))
                System.out.println("This location is off of the board. Choose another direction.");
            else {
                // x2 and y2 values are set based on chosen direction
                if (isUp) {
                    x2 = x1 - 1;
                    y2 = y1;
                }
                else if (isDown) {
                    x2 = x1 + 1;
                    y2 = y1;
                }
                else if (isLeft) {
                    x2 = x1;
                    y2 = y1 - 1;
                }
                else if (isRight) {
                    x2 = x1;
                    y2 = y1 + 1;
                }
                
                // checks whether the chosen position matches the affiliated tile
                if (board.spaces[x2][y2] != s2) 
                    System.out.println("The second tile of this Domino does not match this location. Choose another direction.");
                else break;
            }
        }
        
        board.placeDomino(x1, y1, x2, y2);
    };
    
    public void play() {
        int pIndex = 0;
        Domino currDomino;
        Player currPlayer;

        setUp();
        
        while (true) {
            Scanner kb = new Scanner(System.in);
            String line;
            currPlayer = players.get(pIndex);
            System.out.println("Current player: " + currPlayer.name);
            board.Print();
            boolean noMoves;
            System.out.print("\n");
            
            
            while (true) {
                // Increment through player's hand to check whether a single domino can be placed.
                noMoves = true;
                for (int i = 0; i < currPlayer.hand.size(); i++) {
                    if (board.isAvail(currPlayer.hand.get(i))) {
                        noMoves = false;
                        break;
                    }
                }
                
                if (noMoves) {
                    System.out.println("No moves available. " + currPlayer.name + " loses!");
                    players.remove(currPlayer);
                    break;
                }
                
                currPlayer.showHand();
                // Concede prompt
                System.out.print("Press ENTER to continue OR type 'quit' to concede: ");
                line = kb.nextLine();
                if (line.equals("quit")) {
                    System.out.println(currPlayer.name + " concedes!");
                    players.remove(currPlayer);
                    break;
                }
                
                // Choosing the domino in hand
                currDomino = chooseDomino(currPlayer);
                
                board.Print();
                System.out.println("Domino selected: ");
                currDomino.Print();
                System.out.print("\n");
                
                
                // Choosing the space, then placing the domino on that location
                chooseSpaceAndPlace(currDomino);
                currPlayer.hand.remove(currDomino);
                break;
            }
            
            if (players.size() == 1) break;
            else pIndex = (pIndex + 1) % players.size();
        }
        
        System.out.println(players.get(0).name + " wins!");
                   
    }
}

// End of class
//-----------------------------------------------------------------------------
