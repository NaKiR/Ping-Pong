package nakir.ppvis.game.gameobjects;

import nakir.ppvis.game.Model;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.RoundedRectangle;

public class Paddle extends RoundedRectangle implements GameObject{
    private int border = 30;
    private GameContainer gameContainer;
    private int keyUp;
    private int keyDown;
    private int frameHeight;
    private Model model;

    public Paddle(float x, float y, float width, float height, float cornerRadius, GameContainer gameContainer, int keyUp, int keyDown, int frameHeight, Model model) {
        super(x, y, width, height, cornerRadius);
        this.gameContainer = gameContainer;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.frameHeight = frameHeight;
        this.model = model;
    }

    public void update() {
        if (gameContainer.getInput().isKeyDown(keyUp)) {
            if (getMinY() > border)
                setY(getY() - model.getPaddleSpeed());
        } else if (gameContainer.getInput().isKeyDown(keyDown)) {
            if (getMaxY() < frameHeight - border - 10)
                setY(getY() + model.getPaddleSpeed());
        }
    }

    public void toStart() {
        setY(frameHeight / 2 - 40);
    }
}
