# Heptalion-Simulator
A homework assignment for my Object Oriented Programming class which allows players to play a game of Heptalion.

Heptalion is a game played with 2-4 players. It consists of a board with 56 spaces, each having one of 7 possible symbols. Each player holds 10 dominos, each domino possessing 2 of these 7 symbols. In turn order, each player places a domino on top of two adjacent spaces, where the symbols on the spaces match the symbols on the domino placed. Once a player cannot place a domino, they lose the game.

This program uses five classes, Board, Domino, Game, Player, and Heptalion.

The Board class contains all information of what symbols are on the board, what dominos are on the board, and all functions involving the process of checking whether a symbol matches, and placing the domino on the board. It also has a print function for showing what is currently on the board.

The Domino class holds two symbols, and has a print function which is used in the game.

The Game class constructs a list of players based on how many were asked for, then alternates between each player, giving them the option to place the domino of their choice on the board.

The Player class has a hand of dominos, and the functions to add, remove, and get dominos. It also has a print function to show what the player currently has.

The Heptalion class is the main class, which simply creates a game and plays it.
