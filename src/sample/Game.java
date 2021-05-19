package sample  ;

import java.util.Random;

public class Game {

    private int SIDE = 4;
    private int[][] gameField = new int [SIDE][SIDE];
    private int score = 0;
    private int win = 2048;
    

    public Game() {
        for(int i = 0; i < SIDE; i++){
            for(int j = 0; j < SIDE; j++){
                gameField[i][j] = 0;
            }
        }
    }

    public int getSide() {
        return SIDE;
    }

    public String getScore(){
        return Integer.toString(score);
    }

    public void setScore(int value) {
        score = value;
    }

    public int getValueOfCell(int x, int y) {
        return gameField[x][y];
    }

    public void createNewNumber() {
        if (winTest()) {
            System.out.println("YOU WON!");
            return;
        }

        boolean isCreated = false;
        if (canUserMove()) {
            do  {
                int x = new Random().nextInt(SIDE);
                int y = new Random().nextInt(SIDE);
                if (gameField[x][y] == 0) {
                    gameField[x][y] = new Random().nextInt(10) < 9 ? 2: 4;
                    isCreated = true;
                }
            }
            while (!isCreated);
        }
    }

    public boolean canUserMove() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (gameField[x][y] == 0) return true;
                else if (x < SIDE - 1 && gameField[x][y] == gameField[x + 1][y]) return true;
                else if (y < SIDE - 1 && gameField[x][y] == gameField[x][y + 1]) return true;
            }
        }
        return false;
    }

    public boolean compressRow(int[] row) {
        int insertPosition = 0;
        boolean result = false;
        for (int x = 0; x < SIDE; x++) {
            if (row[x] > 0) {
                if (x != insertPosition) {
                    row[insertPosition] = row[x];
                    row[x] = 0;
                    result = true;
                }
                insertPosition++;
            }
        }
        return result;
    }

    public boolean mergeRow(int[] row) {
        boolean result = false;
        for (int x = 0; x < row.length - 1; x++) {
            if (row[x] != 0 && row[x] == row[x + 1]) {
                row[x] *= 2;
                row[x + 1] = 0;
                result = true;
                score += row[x];
            }
        }
        return result;
    }

    public void moveLeft() {
        boolean isNewNumberNeeded = false;
        for (int[] row : gameField) {
            boolean wasCompressed = compressRow(row);
            boolean wasMerged = mergeRow(row);

            if (wasMerged) {compressRow(row);}

            if (wasMerged || wasCompressed) {isNewNumberNeeded = true;}
        }
        if (isNewNumberNeeded) createNewNumber();
    }

    private void rotateClockwise() {
        int[][] result = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                result[j][SIDE - i - 1] = gameField[i][j];
            }
        }
        gameField = result;
    }

    public void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    public void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    public void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    public void newGame() {
        gameField = new int[SIDE][SIDE];
        createNewNumber();
        createNewNumber();
    }

    public boolean winTest() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] == win) return true;
            }
        }
        return false;
    }

}
