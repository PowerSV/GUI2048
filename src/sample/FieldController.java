package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;


import java.io.FileNotFoundException;

public class FieldController {

    private Game game;
    private GameScreen gameScreen;
    public FlowPane flowPane;
    public Label score;
    private boolean isGameStopped = false;

    public void pngForCell() {
        flowPane.getChildren().clear();
        ImageView[][] imageViews = gameScreen.getImageViews();

        for (int i = 0; i < game.getSide(); i++) {
            for (int j = 0; j < game.getSide(); j++) {
                flowPane.getChildren().add(imageViews[j][i]);
            }
        }
    }

    @FXML
    public void onKeyPress(KeyEvent keyEvent) throws FileNotFoundException{
        if (!game.canUserMove()) {
            gameScreen.loseWinScreen();
            isGameStopped = true;
        }

        if (game.winTest()) {
            gameScreen.loseWinScreen();
            isGameStopped = true;
        }

        if (isGameStopped) {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                isGameStopped = false;
                game.setScore(0);
                game.newGame();
                ImageView[][] imageViews = gameScreen.getImageViews();
                imageViews[0][0].setFitWidth(400 / game.getSide());
                imageViews[0][0].setFitHeight(400 / game.getSide());
                gameScreen.drawField();
            } else return;
        }

        switch (keyEvent.getCode()) {
            case LEFT: {
                left();
                break;
            }
            case RIGHT: {
                right();
                break;
            }
            case UP: {
                up();
                break;
            }
            case DOWN: {
                down();
            }
        }

    }

    @FXML
    public void left() {
        game.moveLeft();
        gameScreen.drawField();
        score.setText(game.getScore());
    }

    @FXML
    public void right() {
        game.moveRight();
        gameScreen.drawField();
        score.setText(game.getScore());
    }

    @FXML
    public void up() {
        game.moveUp();
        gameScreen.drawField();
        score.setText(game.getScore());
    }

    @FXML
    public void down() {
        game.moveDown();
        gameScreen.drawField();
        score.setText(game.getScore());
    }

    public void createGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        pngForCell();
    }

    public void createGameField(Game game) {
        this.game = game;
    }



}
