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
        if (this.getMinY() <= border)
            ballVelocity.y = -ballVelocity.getY();
        if (this.getMaxY() >= height - border)
            ballVelocity.y = -ballVelocity.getY();
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
        if (ballVelocity.y < model.getBallSpeed()) {
            ballVelocity.y -= (paddlePos - this.getCenterY()) / 10;
        }
        if (ballVelocity.x < 0) {
            ballVelocity.x = model.getBallSpeed() - Math.abs(ballVelocity.y);
        } else {
            ballVelocity.x = Math.abs(ballVelocity.y ) - model.getBallSpeed();
        }
    }
}
