package no.game.tictactoe.model;


public class Board {
    private int turn = 0;
    private int round = 0;
    private int[] board = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    /**
     * Returns current player to move
     * @return int
     */
    public int getTurn(){
        return turn;
    }

    /**
     * If 3 of the same symbol is in a row, it will return that symbol. Otherwise 2 (empty) will be returned.
     * @return int between 0 and 2
     */
    public int winChecker(){

        for (int i = 0; i < 3; i++){
            if (board[i*3] == board[i*3+1] && board[i*3+1] == board[i*3+2] && board[i*3] != 2){
                return board[i*3];
            }

            if (board[i] == board[3+i] && board[3+i] == board[6+i] && board[i] != 2){
                return board[i];
            }
        }

        if (board[4] != 2 && ((board[0] == board[4] && board[4] == board[8]) || (board[2] == board[4] && board[4] == board[6]))){
            return board[4];
        }

        return 2;
    }

    /**
     * Checks if a square on the board is empty
     * @param sq int between 0 and 8
     * @return boolean
     */
    public boolean isSqEmpty(int sq){
        return board[sq] == 2;
    }

    /**
     * Changes the specified square to the current player to move, changes the turn and adding 1 to round
     * @param sq int between 0 and 8
     */
    public void move(int sq){
        board[sq] = turn;
        turn ^= 1;
        round++;
    }

    /**
     * Takes back a move by making the square empty, changing the turn and subtracting 1 from round
     * @param sq int between 0 and 8
     */
    public void takeBackMove(int sq){
        board[sq] = 2;
        turn ^= 1;
        round--;
    }

    /**
     *
     * @param maxPlayer boolean
     * @param alpha int
     * @param beta int
     * @return the score of the best move as int
     */
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


    /**
     * Finds and returns the best move.
     * @return int between 0 and 8
     */
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

