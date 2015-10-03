package nakir.ppvis.game.gameobjects;

import nakir.ppvis.game.model.BallModel;
import nakir.ppvis.game.Field;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Ball extends Circle implements GameObject {
    private Boolean isStoped = true;
    private Vector2f ballVelocity;
    private int height;
    private int width;
    private BallModel model;
    private Field field;

    public Ball(int width, int height, BallModel model, Field field) {
        super(width/2, height/2, 6);
        ballVelocity = new Vector2f(model.getBallSpeed(), 0);
        this.width = width;
        this.height = height;
        this.model = model;
        this.field = field;
    }

    public void setStoped(Boolean isStoped) {
        this.isStoped = isStoped;
    }

    public Boolean isStoped() {
        return isStoped;
    }

    public void update() {
        field.checkBallPosition(this);
        this.setLocation(this.getX() + ballVelocity.getX(), this.getY() + ballVelocity.getY());
    }

    public void toStart() {
        this.setX(width / 2 - 6);
        this.setY(height / 2 - 6);
        ballVelocity.x = model.getBallSpeed();
        ballVelocity.y = 0;
        this.setStoped(true);
    }

    public void reverseSpeedX() {
        ballVelocity.x = - ballVelocity.x;
    }

    public void reverseSpeedY() {
        ballVelocity.y = - ballVelocity.y;
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
