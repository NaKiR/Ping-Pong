package nakir.ppvis.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.RoundedRectangle;

public class Paddle extends RoundedRectangle {
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
            if (this.getMinY() > border)
                this.setY(this.getY() - model.getPaddleSpeed());
        } else if (gameContainer.getInput().isKeyDown(keyDown)) {
            if (this.getMaxY() < frameHeight - border - 10)
                this.setY(this.getY() + model.getPaddleSpeed());
        }
    }
}
