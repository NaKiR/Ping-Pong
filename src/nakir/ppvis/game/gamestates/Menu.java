package nakir.ppvis.game.gamestates;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
    private int width;
    private int height;

    private Rectangle background;
    private Rectangle menuBar;
    private Image exitButton;
    private Image playButton;
    private Image exitButtonFocus;
    private Image playButtonFocus;
    private Boolean isFocusedPlay = false;
    private Boolean isFocusedExit = false;

    public Menu(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setShowFPS(false);
        background = new Rectangle(0, 0, width, height);
        menuBar = new Rectangle(width / 10, 0, 200, height);
        playButton = new Image("res/play.png");
        exitButton = new Image("res/exit.png");
        playButtonFocus = new Image("res/playP.png");
        exitButtonFocus = new Image("res/exitP.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setColor(Color.darkGray);
        g.fill(background);
        g.setColor(Color.gray);
        g.fill(menuBar);
        g.setColor(Color.white);
        String gameName = "Ping-Pong!";
        g.drawString(gameName, width / 10 + 10, 50);
        if (isFocusedPlay) {
            g.drawImage(playButtonFocus, width / 10, 100);
        } else {
            g.drawImage(playButton, width / 10, 100);
        }
        if (isFocusedExit) {
            g.drawImage(exitButtonFocus, width / 10, 150);
        } else {
            g.drawImage(exitButton, width / 10, 150);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if ((posX > width / 10 && posX < width / 10 + 200) && (posY < height - 100 && posY > height - 150)) {
            isFocusedPlay = true;
            if (Mouse.isButtonDown(0)) {
                stateBasedGame.enterState(1);
            }
        } else {
            isFocusedPlay = false;
        }
        if ((posX > width / 10 && posX < width / 10 + 200) && (posY < height - 150 && posY > height - 200)) {
            isFocusedExit = true;
            if (Mouse.isButtonDown(0)) {
                System.exit(0);
            }
        } else {
            isFocusedExit = false;
        }
    }
}
