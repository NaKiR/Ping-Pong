package nakir.ppvis.game;

import nakir.ppvis.game.gameobjects.Ball;
import nakir.ppvis.game.gameobjects.PlayerPaddle;
import nakir.ppvis.game.model.Model;
import org.newdawn.slick.geom.RoundedRectangle;

public class Field {
    private int upperBorder = 35;
    private int bottomBorder = 35;
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

    public Boolean checkUpperPaddlePosition(RoundedRectangle paddle) {
        if (paddle.getMinY() > upperBorder) {
            return true;
        } else {
            return false;
        }
    }

    public int getHeight() {
        return height;
    }

    public Boolean checkBottomPaddlePosition(RoundedRectangle paddle) {
        if (paddle.getMaxY() < height - bottomBorder) {
            return true;
        } else {
            return false;
        }
    }
}
