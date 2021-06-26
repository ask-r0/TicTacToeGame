package no.game.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import no.game.tictactoe.model.Board;

import java.io.File;
import java.io.IOException;
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

    private boolean isAgainstComputer;

    @FXML
    void onSquareClicked(MouseEvent event) {
        Node source = event.getPickResult().getIntersectedNode();
        ImageView image = (ImageView) source;
        Integer colIndex = (GridPane.getColumnIndex(source) == null) ?  0 : (GridPane.getColumnIndex(source));
        Integer rowIndex = (GridPane.getRowIndex(source) == null) ? 0 : (GridPane.getRowIndex(source));
        if (!gameBoard.isSqEmpty(gridSquareToInt(colIndex, rowIndex))) return;

        if (isAgainstComputer) {
            gameAgainstComputer(gridSquareToInt(colIndex, rowIndex), image);
        } else {
            gameAgainstPlayer(gridSquareToInt(colIndex, rowIndex), image);
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

    public void gameAgainstComputer(int clickedSquare, ImageView squareImageView) {
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

    /**
     * Checks whether the game is done.
     * True: Displays winner, true is returned.
     * False: Nothing happens, false is returned.
     */
    public boolean isGameDone() {
        if (gameBoard.winChecker() != 2 || gameBoard.getRound() == 9) { //Game is Over.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME OVER");
            if (gameBoard.getRound() == 9) {
                alert.setHeaderText("The game ended in a draw.");
            } else {
                if (gameBoard.winChecker() == 0) {
                    alert.setHeaderText("X has won this game!");
                } else {
                    alert.setHeaderText("O has won this game!");
                }
            }

            alert.show();
            try {
                displayMainView();
            } catch (IOException e) {
                //HANDLE!!!
            }



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

    public void setAgainstComputer(boolean isAgainstComputer) {
        this.isAgainstComputer = isAgainstComputer;
    }

    public void displayMainView() throws IOException {
        URL url = new File("src/main/resources/no/game/tictactoe/controller/MainView.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
    }


}
