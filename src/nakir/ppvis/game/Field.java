package nakir.ppvis.game;

import nakir.ppvis.game.gameobjects.Ball;
import nakir.ppvis.game.gameobjects.Paddle;
import nakir.ppvis.game.model.Model;

public class Field {
    private int upperBorder = 30;
    private int bottomBorder = 40;
    private int height;
    private int width;
    private Model model;

    public Field(int width, int height, Model model) {
        this.height = height;
        this.width = width;
        this.model = model;
    }

    public void checkBallPosition(Ball ball) {
        if (ball.getMinY() <= upperBorder || ball.getMaxY() >= height - bottomBorder)
            ball.reverseSpeedY();
        if (ball.getMinX() <= 0) {
            ball.toStart();
            ball.reverseSpeedX();
            model.setScorePlayer1(model.getScorePlayer1() + 1);
        }
        if (ball.getMaxX() >= width) {
            ball.toStart();
            model.setScorePlayer2(model.getScorePlayer2() + 1);
        }
    }

    public Boolean checkUpperPaddlePosition(Paddle paddle) {
        if (paddle.getMinY() > upperBorder) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkBottomPaddlePosition(Paddle paddle) {
        if (paddle.getMaxY() < height - bottomBorder) {
            return true;
        } else {
            return false;
        }
    }
}
