package no.game.tictactoe.model;


public class Board {
    private int turn = 0;
    private int[] board = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    public int winChecker(){

        for (int i = 0; i < 3; i++){
            if (board[i*3] == board[i*3+1] && board[i*3+1] == board[i*3+2]){
                return board[i*3];
            }

            if (board[i] == board[3+i] && board[3+i] == board[6+i]){
                return board[i];
            }
        }

        if ((board[0] == board[4] && board[4] == board[8]) || (board[2] == board[4] && board[4] == board[6])){
            return board[4];
        }

        return 2;
    }

    public boolean isSqEmpty(int sq){
        return board[sq] == 2;
    }
}
