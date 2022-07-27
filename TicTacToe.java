package com.company;

import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int moves = 0;

        do {
            System.out.println ("Welcome players!");
            System.out.println ("The game will work with coordinate that range from [1[1] to [3][3]");
            System.out.println ("You, as a player must enter coordinates of the board in order to print your move. " +
                    "\nThe first player will make moves using the 'X' " +
                    "character and the second player will use the 'O' character");
            String player = "X";
            boolean gameOver = false;
            clearBoard();
            display();

            do {
                while (true) {
                    System.out.println ("Player " + player + ", you're up!");

                    int moveRow = SafeInput.getRangedInt (in, "In which row would you like to make your move?", 1, 3);
                    int moveCol = SafeInput.getRangedInt (in, "In which column would you like to make your move?", 1, 3);

                    moveRow = moveRow - 1;
                    moveCol = moveCol - 1;

                    if (!isValidMove (moveRow, moveCol)) {
                        System.out.println ("Sorry but it seems that you have entered an invalid move! Try again.");
                        break;
                    }

                    board[moveRow][moveCol] = player;
                    moves++;

                    display ();

                    if (moves >= 5) {
                        if (isWin (player)) {
                            gameOver = true;
                            System.out.println ("Great job player " + player + ", you've won!");
                            break;
                        }
                    }
                    if (moves >= 7 && !gameOver) {
                        if (isTie ()) {
                            System.out.println ("You are both tied!");
                            gameOver = true;
                            break;
                        }
                    }
                    if(player.equals ("X")){

                        player = "O";
                    }
                    else {
                        player = "X";
                    }
                }
            } while (!gameOver);

        } while (SafeInput.getYNConfirm(in, "\nDo you want to play again? "));
    }

    private static void display()
    {
        System.out.println("\n\n   1    2     3");
        for (int row = 0; row < 3; row++)
        {
            System.out.print(row + 1);
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + board[row][col] + " ");
                if (col != 2)
                    System.out.print(" ");
            }
            System.out.println();
            if (row != 2) {
                System.out.println(" ____|____|____");
                System.out.println("     |    |    ");
            }
        }
    }
    private static void clearBoard()
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }
    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if (board[row][col].equals (" ")){
            retVal = true;
        }
        return retVal;
    }
    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }
    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COL; col++){
            if (board[col][0].equals(player) && board[col][1].equals(player) && board[col][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROW; row++){
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player)
    {
        if (board[1][1].equals(player) && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) || board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
        {
            return true;
        }
        return false;
    }
    private static boolean isTie() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" "))
                    return false;
            }
        }
        return true;
    }
}