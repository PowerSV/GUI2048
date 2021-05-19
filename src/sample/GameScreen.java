package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class GameScreen {

    private Game game;
    private ImageView[][] imageViews;
    private Map<Integer, Image> pictures;
    private final int WIDTH = 400;
    private final int HEIGHT = 400;

    public GameScreen(Game game) throws FileNotFoundException {
        this.game = game;
        int side = game.getSide();
        imageViews = new ImageView[side][side];

        numberPng();
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                int value = game.getValueOfCell(i, j);
                ImageView newImage = new ImageView();
                newImage.setImage(pictures.get(value));
                newImage.setFitHeight(HEIGHT / side);
                newImage.setFitWidth(WIDTH / side);
                imageViews[j][i] = newImage;
            }
        }


    }

    public ImageView[][] getImageViews() {
        return imageViews;
    }

    private void numberPng() throws FileNotFoundException {
        pictures = new HashMap<Integer, Image>();
        Image image0 = new Image(new FileInputStream("src/pngForGame/0.png"));
        pictures.put(0, image0);
        Image image2 = new Image(new FileInputStream("src/pngForGame/2.png"));
        pictures.put(2, image2);
        Image image4 = new Image(new FileInputStream("src/pngForGame/4.png"));
        pictures.put(4, image4);
        Image image8 = new Image(new FileInputStream("src/pngForGame/8.png"));
        pictures.put(8, image8);
        Image image16 = new Image(new FileInputStream("src/pngForGame/16.png"));
        pictures.put(16, image16);
        Image image32 = new Image(new FileInputStream("src/pngForGame/32.png"));
        pictures.put(32, image32);
        Image image64 = new Image(new FileInputStream("src/pngForGame/64.png"));
        pictures.put(64, image64);
        Image image128 = new Image(new FileInputStream("src/pngForGame/128.png"));
        pictures.put(128, image128);
        Image image256 = new Image(new FileInputStream("src/pngForGame/256.png"));
        pictures.put(256, image256);
        Image image512 = new Image(new FileInputStream("src/pngForGame/512.png"));
        pictures.put(512, image512);
        Image image1024 = new Image(new FileInputStream("src/pngForGame/1024.png"));
        pictures.put(1024, image1024);
        Image image2048 = new Image(new FileInputStream("src/pngForGame/2048.png"));
        pictures.put(2048, image2048);
    }

    public void loseWinScreen() throws FileNotFoundException {

        imageViews[0][0].setFitHeight(500);
        imageViews[0][0].setFitWidth(400);
        if(!game.canUserMove())
            imageViews[0][0].setImage(new Image(new FileInputStream("src/pngForGame/gameOver.png")));
        if (game.winTest())
            imageViews[0][0].setImage(new Image(new FileInputStream("src/pngForGame/win.png")));
    }

    public void drawField()  {
        for (int i = 0; i < game.getSide(); i++) {
            for (int j = 0; j < game.getSide(); j++) {
                int value = game.getValueOfCell(i, j);
                imageViews[j][i].setImage(pictures.get(value));
            }
        }
    }
}
