package no.game.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import no.game.tictactoe.model.Board;
import no.game.tictactoe.utility.GameMode;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class GameViewController implements Initializable {

    final static  File fileO = new File("src/main/resources/no/game/tictactoe/image/O.png");
    final static File fileX = new File("src/main/resources/no/game/tictactoe/image/X.png");
    @FXML private BorderPane pane;
    @FXML private GridPane grid;

    @FXML private ImageView square0;
    @FXML private ImageView square1;
    @FXML private ImageView square2;
    @FXML private ImageView square3;
    @FXML private ImageView square4;
    @FXML private ImageView square5;
    @FXML private ImageView square6;
    @FXML private ImageView square7;
    @FXML private ImageView square8;
    ImageView[] squares;


    private Board gameBoard;

    private GameMode gameMode;

    @FXML
    void onSquareClicked(MouseEvent event) {
        Node source = event.getPickResult().getIntersectedNode();
        ImageView image = (ImageView) source;
        Integer colIndex = (GridPane.getColumnIndex(source) == null) ?  0 : (GridPane.getColumnIndex(source));
        Integer rowIndex = (GridPane.getRowIndex(source) == null) ? 0 : (GridPane.getRowIndex(source));

        if (gameMode == GameMode.PLAYER_VS_COMPUTER) {
            if (!gameBoard.isSqEmpty(gridSquareToInt(colIndex, rowIndex))) return;
            gamePlayerAgainstComputer(gridSquareToInt(colIndex, rowIndex), image);
        } else if (gameMode == GameMode.OFFLINE) {
            if (!gameBoard.isSqEmpty(gridSquareToInt(colIndex, rowIndex))) return;
            gameAgainstPlayer(gridSquareToInt(colIndex, rowIndex), image);
        } else{
            gameComputerAgainstComputer();
        }
    }

    public void gameAgainstPlayer(int clickedSquare, ImageView squareImageView) {
        if (gameBoard.getTurn() == 0) {
            squareImageView.setImage(new Image(fileX.toURI().toString()));
        } else {
            squareImageView.setImage(new Image(fileO.toURI().toString()));
        }
        gameBoard.move(clickedSquare);
        isGameDone();
    }

    public void gamePlayerAgainstComputer(int clickedSquare, ImageView squareImageView) {
        squareImageView.setImage(new Image(fileX.toURI().toString()));
        gameBoard.move(clickedSquare);
        if (!isGameDone()) {
            // Computer should here make the best move.
            int bestMove = gameBoard.search();
            squares[bestMove].setImage(new Image(fileO.toURI().toString()));
            gameBoard.move(bestMove);
        }
        isGameDone(); //Checked again whether game is done.
    }

    public void gameComputerAgainstComputer() {
        int bestMove = gameBoard.search();

        if (gameBoard.getTurn() == 0){
            squares[bestMove].setImage(new Image(fileX.toURI().toString()));
        }else{
            squares[bestMove].setImage(new Image(fileO.toURI().toString()));
        }

        gameBoard.move(bestMove);

        isGameDone();
    }

    /**
     * Checks whether the game is done.
     * True: Displays winner, true is returned.
     * False: Nothing happens, false is returned.
     */
    public boolean isGameDone() {
        if (gameBoard.winChecker() != 2 || gameBoard.getRound() == 9) { //Game is Over.
            String winText;
            if (gameBoard.winChecker() == 0) {
                winText = "X has won this game!";
            } else if (gameBoard.winChecker() == 1){
                winText = "O has won this game!";
            }else{
                winText = "Draw";
            }
            ViewDisplayer.displayGameOverView(pane, gameMode, winText);
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard = new Board();
        squares = new ImageView[]{square0, square1, square2, square3, square4, square5, square6, square7, square8};
    }


    private int gridSquareToInt(int columnIndex, int rowIndex) {
        return columnIndex + 3*rowIndex;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
