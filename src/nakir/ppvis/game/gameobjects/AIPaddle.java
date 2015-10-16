package nakir.ppvis.game.gameobjects;

import nakir.ppvis.game.Field;
import nakir.ppvis.game.model.PaddleModel;

public class AIPaddle extends Paddle implements GameObject {
    private Ball ball;

    public AIPaddle(float x, float y, PaddleModel model, Field field, Ball ball) {
        super(x, y, model, field);
        this.ball = ball;
    }

    @Override
    public void update() {
        if (ball.getCenterY() < this.getCenterY() - 10) {
            if (field.checkUpperPaddlePosition(this))
                setY(getY() - model.getPaddleSpeed());
        } else if (ball.getCenterY() > this.getCenterY() + 10) {
            if (field.checkBottomPaddlePosition(this))
                setY(getY() + model.getPaddleSpeed());
        }
    }
}
