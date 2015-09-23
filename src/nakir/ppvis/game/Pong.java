package nakir.ppvis.game;

import nakir.ppvis.game.gamestates.Menu;
import nakir.ppvis.game.gamestates.Play;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Pong extends StateBasedGame {
    private static final String gameName = "Ping-Pong";
    private static final int menu = 0;
    private static final int play = 1;
    private static final int width = 640;
    private static final int height = 480;
    private static final Boolean isFullscreen = false;
    private Model model = new Model();

    Pong(String gameName) {
        super(gameName);
        addState(new Menu(width, height));
        addState(new Play(width, height, model));
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Pong(gameName));
            appgc.setDisplayMode(width, height, isFullscreen);
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);
    }
}
