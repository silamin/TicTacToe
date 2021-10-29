package tictactoe.bll;

import java.util.Random;

public class GameBoard implements IGameModel {
    int winner = -1;
    boolean turn=false;
    int[][] arr = {
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
    };

    public int currentPlayer = 1;
    int player=0;

    public int getNextPlayer() {
        if (currentPlayer == 1) {
            return currentPlayer--;
        } else {
            return currentPlayer++;
        }
     }

    public boolean play(int col, int row) {
        if (arr[row][col] == -1 && getWinner() == -1) {
            arr[row][col] = currentPlayer;
            return true;
        } else
            return false;
    }

    public boolean isGameOver() {
        boolean testHorizontal = false;
        boolean testVertical = false;
        boolean testDiagonal1 = false;
        boolean testDiagonal2 = false;
        int player=1;

        int lineHori = 0;
        int lineCol = 0;
        int lineDiag = 0;
        int lineInvDiag = 0;

        if (currentPlayer==1)
            player=0;
        else
            player =1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == player)
                    lineHori++;
                else lineHori = 0;
                testHorizontal = player == arr[i][j];
                if (arr[j][i] == player)
                    lineCol++;
                else lineCol = 0;
                testVertical = player == arr[j][i];
                if (i == j) {
                    if (arr[i][i] == player)
                        lineDiag++;
                    else lineDiag = 0;
                    testDiagonal1 = player == arr[i][j];
                    if (arr[(arr.length - 1) - i][i] == player)
                        lineInvDiag++;
                    else lineInvDiag = 0;
                    testDiagonal2 = player == arr[(arr.length - 1) - i][i];
                }
            }
            if ((lineHori == 3) || (lineCol == 3) || (lineInvDiag == 3) || (lineDiag == 3)) {
                winner = currentPlayer;
                return true;
            } else {
                lineHori = 0;
                lineCol = 0;
            }
        }
        if (isDraw())
            return true;
        return false;

    }

    private boolean isDraw() {
        for (int i = 0; i < arr.length; i++) {
            for (int y = 0; y < arr[i].length; y++) {
                if (arr[i][y] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getWinner() {
        return winner;
    }

    public void newGame() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = -1;
        }
        currentPlayer = 0;
        winner = -1;
    }
}