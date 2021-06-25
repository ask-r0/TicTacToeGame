package no.game.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        int sq = 0;

        ImageView image = (ImageView) source;
        Image img = new Image("/no/game/tictactoe/image/O.png");
        image.setImage(img);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard = new Board();
    }


}
