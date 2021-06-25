package no.game.tictactoe.model;


public class Board {
    private int turn = 0;
    private int round = 0;
    private int[] board = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    public int getTurn(){
        return turn;
    }

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

    public void move(int sq){
        board[sq] = turn;
        turn ^= 1;
        round++;
    }

    public void takeBackMove(int sq){
        board[sq] = 2;
        turn ^= 1;
        round--;
    }

    public int minMax(boolean maxPlayer, int alpha, int beta){

        if (round >= 9){
            return 0;
        }

        if (winChecker() != 2){
            if (winChecker() == 0){
                return 1;
            }else{
                return -1;
            }
        }

        if (maxPlayer){
            int value = -10000000;
            for (int i = 0; i < 9; i++){
                if (isSqEmpty(i)){
                    move(i);
                    value = Math.max(value, minMax(false, alpha, beta));
                    takeBackMove(i);

                    if (value >= beta){
                        break;
                    }
                    alpha = Math.max(alpha, value);
                }
            }
            return value;

        }else{
            int value = 10000000;
            for (int i = 0; i < 9; i++){
                if (isSqEmpty(i)){
                    move(i);
                    value = Math.min(value, minMax(true, alpha, beta));
                    takeBackMove(i);

                    if (value <= alpha){
                        break;
                    }
                    beta = Math.min(beta, value);
                }
            }
            return value;
        }
    }

    public int search(){

        int bestMove = -1;
        int score;
        boolean maxPlayer;
        maxPlayer = turn == 0;

        if (maxPlayer){
            int bestScore = -1000000;

            for (int i = 0; i < 9; i++){
                if (isSqEmpty(i)){
                    move(i);
                    score = Math.max(bestScore, minMax(false, -1000000, 1000000));
                    takeBackMove(i);

                    if (score > bestScore){
                        bestScore = score;
                        bestMove = i;
                    }
                }
            }
        }else{
            int bestScore = 1000000;

            for (int i = 0; i < 9; i++){
                if (isSqEmpty(i)){
                    move(i);
                    score = Math.min(bestScore, minMax(true, -1000000, 1000000));
                    takeBackMove(i);

                    if (score < bestScore){
                        bestScore = score;
                        bestMove = i;
                    }
                }
            }
        }

        return bestMove;
    }
}

