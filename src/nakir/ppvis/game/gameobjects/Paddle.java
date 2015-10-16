package nakir.ppvis.game.gameobjects;

import nakir.ppvis.game.Field;
import nakir.ppvis.game.model.PaddleModel;
import org.newdawn.slick.geom.RoundedRectangle;

public class Paddle extends RoundedRectangle implements GameObject {
    protected PaddleModel model;
    protected Field field;

    public Paddle(float x, float y, PaddleModel model, Field field) {
        super(x, y, 10, 80, 3);
        this.model = model;
        this.field = field;
    }

    public  Paddle() {
        super(0, 0, 10, 80, 3);
    }

    public void update() {
    }

    public void toStart() {
        setY(field.getHeight() / 2 - 40);
    }
}
