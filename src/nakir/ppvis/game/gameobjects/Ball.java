package nakir.ppvis.game.gameobjects;

import nakir.ppvis.game.Model;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Ball extends Circle implements GameObject{
    private Boolean isStoped = true;
    private Vector2f ballVelocity;
    private int border = 30;
    private int height;
    private int width;
    private Model model;

    public Ball(float centerPointX, float centerPointY, float radius, int width, int height, Model model) {
        super(centerPointX, centerPointY, radius);
        ballVelocity = new Vector2f(model.getBallSpeed(), 0);
        this.width = width;
        this.height = height;
        this.model = model;
    }

    public void setStoped(Boolean isStoped) {
        this.isStoped = isStoped;
    }

    public Boolean isStoped() {
        return isStoped;
    }

    public void update() {
        if (getMinY() <= border)
            ballVelocity.y = -ballVelocity.getY();
        if (getMaxY() >= height - border)
            ballVelocity.y = -ballVelocity.getY();
        if (getMinX() <= 0) {
            toStart();
            reverseX();
            model.setScorePlayer1(model.getScorePlayer1() + 1);
        }
        if (getMaxX() >= width) {
            toStart();
            model.setScorePlayer2(model.getScorePlayer2() + 1);
        }
        this.setLocation(this.getX() + ballVelocity.getX(), this.getY() + ballVelocity.getY());
    }

    public void toStart() {
        this.setX(width / 2 - 6);
        this.setY(height / 2 - 6);
        ballVelocity.x = model.getBallSpeed();
        ballVelocity.y = 0;
        this.setStoped(true);
    }

    public void reverseX() {
        ballVelocity.x = - ballVelocity.x;
    }

    public void intersect(float paddlePos) {
        if (Math.abs(this.getCenterY() - paddlePos - 40) > 40) {
            return;
        }
        ballVelocity.y = (this.getCenterY() - paddlePos - 40) * model.getBallSpeed() / 50;
        if (ballVelocity.x < 0) {
            ballVelocity.x = (float) Math.sqrt(Math.pow(model.getBallSpeed(), 2) - Math.pow(ballVelocity.y, 2));
        } else {
            ballVelocity.x = - (float) Math.sqrt(Math.pow(model.getBallSpeed(), 2) - Math.pow(ballVelocity.y, 2));
        }
    }
}
