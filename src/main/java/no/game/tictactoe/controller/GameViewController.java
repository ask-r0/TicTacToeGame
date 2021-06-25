package no.game.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import no.game.tictactoe.model.Board;

import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML private GridPane grid;
    private Board gameBoard;

    @FXML
    void onSquareClicked(MouseEvent event) {
        Node source = event.getPickResult().getIntersectedNode();
        Integer colIndex = (GridPane.getColumnIndex(source) == null) ?  0 : (GridPane.getColumnIndex(source));
        Integer rowIndex = (GridPane.getRowIndex(source) == null) ? 0 : (GridPane.getRowIndex(source));
        int sq = colIndex + rowIndex*3;
        if(gameBoard.isSqEmpty(sq)) {
            gameBoard.move(sq);
        } else {

        }

        if (gameBoard.winChecker() != 2){
            // Game end
            if (gameBoard.winChecker() == 0){
                System.out.print("Player X won");
            }else{
                System.out.print("Player O won");
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard = new Board();


    }


}
