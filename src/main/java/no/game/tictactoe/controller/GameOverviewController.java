package no.game.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import no.game.tictactoe.utility.GameMode;

import java.io.IOException;

public class GameOverviewController {

    @FXML private Label title;
    @FXML private BorderPane pane;
    private GameMode playedGameMode;

    @FXML
    void onMainMenu(ActionEvent event) throws IOException {
        ViewDisplayer.displayMainView(pane);
    }

    @FXML
    void onPlayAgain(ActionEvent event) {
        ViewDisplayer.displayGameView(pane, playedGameMode);
    }

    public void setTitleText(String text) {
        this.title.setText(text);
    }

    public void setPlayedGameMode(GameMode gameMode) {
        this.playedGameMode = gameMode;
    }

}
