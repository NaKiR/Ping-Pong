package nakir.ppvis.game.gamestates;

import nakir.ppvis.game.model.Model;
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
    private Image onePlayerButton;
    private Image twoPlayersButton;
    private Image exitButtonFocus;
    private Image onePlayerButtonFocus;
    private Image twoPlayersButtonFocus;
    private Model model;
    private Boolean isFocused1Player = false;
    private Boolean isFocused2Players = false;
    private Boolean isFocusedExit = false;

    public Menu(int width, int height, Model model) {
        this.height = height;
        this.width = width;
        this.model = model;
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
        onePlayerButton = new Image("res/1Player.png");
        twoPlayersButton = new Image("res/2Players.png");
        exitButton = new Image("res/exit.png");
        onePlayerButtonFocus = new Image("res/1playerP.png");
        twoPlayersButtonFocus = new Image("res/2playersP.png");
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
        if (isFocused1Player) {
            g.drawImage(onePlayerButtonFocus, width / 10, 100);
        } else {
            g.drawImage(onePlayerButton, width / 10, 100);
        }
        if (isFocused2Players) {
            g.drawImage(twoPlayersButtonFocus, width / 10, 150);
        } else {
            g.drawImage(twoPlayersButton, width / 10, 150);
        }
        if (isFocusedExit) {
            g.drawImage(exitButtonFocus, width / 10, 200);
        } else {
            g.drawImage(exitButton, width / 10, 200);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if ((posX > width / 10 && posX < width / 10 + 200) && (posY < height - 100 && posY > height - 150)) {
            isFocused1Player = true;
            if (Mouse.isButtonDown(0)) {
                model.setIsPvP(false);
                stateBasedGame.enterState(1);
            }
        } else {
            isFocused1Player = false;
        }
        if ((posX > width / 10 && posX < width / 10 + 200) && (posY < height - 150 && posY > height - 200)) {
            isFocused2Players = true;
            if (Mouse.isButtonDown(0)) {
                model.setIsPvP(true);
                stateBasedGame.enterState(1);
            }
        } else {
            isFocused2Players = false;
        }
        if ((posX > width / 10 && posX < width / 10 + 200) && (posY < height - 200 && posY > height - 250)) {
            isFocusedExit = true;
            if (Mouse.isButtonDown(0)) {
                System.exit(0);
            }
        } else {
            isFocusedExit = false;
        }
    }
}
