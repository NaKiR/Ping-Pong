package nakir.ppvis.game.gameobjects;

import nakir.ppvis.game.Field;
import nakir.ppvis.game.model.PaddleModel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.RoundedRectangle;

public class Paddle extends RoundedRectangle implements GameObject {
    private GameContainer gameContainer;
    private int keyUp;
    private int keyDown;
    private int frameHeight;
    private PaddleModel model;
    private Field field;

    public Paddle(float x, float y,GameContainer gameContainer, int keyUp, int keyDown, int frameHeight,
                  PaddleModel model, Field field) {
        super(x, y, 10, 80, 3);
        this.gameContainer = gameContainer;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.frameHeight = frameHeight;
        this.model = model;
        this.field = field;
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

    public void toStart() {
        setY(frameHeight / 2 - 40);
    }
}
