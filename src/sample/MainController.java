package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Game game;
    private FieldController fieldController;
    private GameScreen gameScreen;

    @FXML
    public void clickPlay() throws IOException {
        game = new Game();
        game.newGame();
        gameScreen = new GameScreen(game);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("playGame.fxml"));
        Parent root = fxml.load();
        fieldController = fxml.getController();
        fieldController.createGameField(game);
        fieldController.createGameScreen(gameScreen);

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        root.requestFocus();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
