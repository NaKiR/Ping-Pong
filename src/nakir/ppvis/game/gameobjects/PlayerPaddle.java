package nakir.ppvis.game.gameobjects;

import nakir.ppvis.game.Field;
import nakir.ppvis.game.model.PaddleModel;
import org.newdawn.slick.GameContainer;

public class PlayerPaddle extends Paddle implements GameObject {
    private GameContainer gameContainer;
    private int keyUp;
    private int keyDown;

    public PlayerPaddle(float x, float y, GameContainer gameContainer, int keyUp, int keyDown,
                        PaddleModel model, Field field) {
        super(x, y, model, field);
        this.gameContainer = gameContainer;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
    }

    public void update() {
        if (gameContainer.getInput().isKeyDown(keyUp)) {
            if (field.checkUpperPaddlePosition(this))
                setY(getY() - model.getPaddleSpeed());
        } else if (gameContainer.getInput().isKeyDown(keyDown)) {
            if (field.checkBottomPaddlePosition(this))
                setY(getY() + model.getPaddleSpeed());
        }
    }
}
