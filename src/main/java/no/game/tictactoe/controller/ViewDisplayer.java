package no.game.tictactoe.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import no.game.tictactoe.utility.GameMode;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ViewDisplayer {
    private static final String GAME_VIEW_PATH = "src/main/resources/no/game/tictactoe/controller/GameView.fxml";
    private static final String MAIN_VIEW_PATH = "src/main/resources/no/game/tictactoe/controller/MainView.fxml";
    private static final String GAME_OVERVIEW_PATH = "src/main/resources/no/game/tictactoe/controller/GameOverView.fxml";

    public static void displayGameView(Pane pane, GameMode gameMode) {
        try {
            URL url = new File(GAME_VIEW_PATH).toURI().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();

            GameViewController controller = fxmlLoader.getController();
            controller.setGameMode(gameMode);

            Scene scene = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            //HANDLE
        }
    }

    public static void displayMainView(Pane pane) {
        try {
            URL url = new File(MAIN_VIEW_PATH).toURI().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            //HANDLE
        }
    }

    public static void displayGameOverView(Pane pane,GameMode playedGameMode, String title) {
        try {
            URL url = new File(GAME_OVERVIEW_PATH).toURI().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();

            GameOverviewController controller = fxmlLoader.getController();
            controller.setPlayedGameMode(playedGameMode);
            controller.setTitleText(title);

            Scene scene = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            //HANDLE
        }
    }
}
