package no.game.tictactoe.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import no.game.tictactoe.utility.GameMode;

import java.io.IOException;


public class MainViewController {

    @FXML private BorderPane pane;

    @FXML
    void onComputerVsComputer(ActionEvent event) throws IOException {
        ViewDisplayer.displayGameView(pane, GameMode.COMPUTER_VS_COMPUTER);
    }

    @FXML
    void onPlayComputer(ActionEvent event) throws IOException {
        ViewDisplayer.displayGameView(pane, GameMode.PLAYER_VS_COMPUTER);
    }

    @FXML
    void onPlayOffline(ActionEvent event) throws IOException {
        ViewDisplayer.displayGameView(pane, GameMode.OFFLINE);
    }

}
