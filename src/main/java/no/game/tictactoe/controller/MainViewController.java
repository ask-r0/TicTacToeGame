package no.game.tictactoe.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.game.tictactoe.utility.GameMode;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainViewController {

    @FXML private BorderPane pane;

    @FXML
    void onComputerVsComputer(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/no/game/tictactoe/controller/GameView.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();

        GameViewController controller = fxmlLoader.getController();
        controller.setGameMode(GameMode.COMPUTER_VS_COMPUTER);

        Scene scene = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void onPlayComputer(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/no/game/tictactoe/controller/GameView.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();

        GameViewController controller = fxmlLoader.getController();
        controller.setGameMode(GameMode.PLAYER_VS_COMPUTER);

        Scene scene = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void onPlayOffline(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/no/game/tictactoe/controller/GameView.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();

        GameViewController controller = fxmlLoader.getController();
        controller.setGameMode(GameMode.OFFLINE);

        Scene scene = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);

    }

}
