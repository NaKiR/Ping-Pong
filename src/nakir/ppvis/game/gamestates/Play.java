package nakir.ppvis.game.gamestates;

import nakir.ppvis.game.gameobjects.Ball;
import nakir.ppvis.game.Model;
import nakir.ppvis.game.gameobjects.Paddle;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
    private int width;
    private int height;
    private Ball ball;
    private Paddle paddlePlayer1;
    private Paddle paddlePlayer2;
    private Image background;
    private Model model;

    public Play(int width, int height, Model model) {
        this.model = model;
        this.height = height;
        this.width = width;
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.getInput().enableKeyRepeat();
        paddlePlayer1 = new Paddle(5, height/2 - 40, 10, 80, 0, gameContainer, Input.KEY_W, Input.KEY_S, height, model);
        paddlePlayer2 = new Paddle(width - 15, height/2 - 40, 10, 80, 0, gameContainer, Input.KEY_UP, Input.KEY_DOWN, height, model);
        ball = new Ball(width/2, height/2, 6, width, height, model);
        background = new Image("res/background.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        gameContainer.setShowFPS(false);
        g.drawImage(background, 0, 0);
        g.fill(paddlePlayer1);
        g.fill(paddlePlayer2);
        g.fill(ball);
        g.drawString(model.getScorePlayer2().toString(), 150, 10);
        g.drawString(model.getScorePlayer1().toString(), width - 150, 10);
        g.drawString("Ball speed: " + model.getBallSpeed().toString(), 250, 0);
        g.drawString("Paddle speed: " + model.getPaddleSpeed().toString(), 250, 13);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            stateBasedGame.enterState(0);
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_R)) {
            ball.toStart();
            paddlePlayer1.toStart();
            paddlePlayer2.toStart();
            model.setScorePlayer1(0);
            model.setScorePlayer2(0);
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_SPACE)) {
            ball.setStoped(false);
        }
        paddlePlayer1.update();
        paddlePlayer2.update();
        if (!ball.isStoped()) {
            ball.update();
        } else {
            if (gameContainer.getInput().isKeyPressed(Input.KEY_F1)) {
                model.setBallSpeed(model.getBallSpeed() - 1);
            }
            if (gameContainer.getInput().isKeyPressed(Input.KEY_F2)) {
                model.setBallSpeed(model.getBallSpeed() + 1);
            }
            if (gameContainer.getInput().isKeyPressed(Input.KEY_F3)) {
                model.setPaddleSpeed(model.getPaddleSpeed() - 1);
            }
            if (gameContainer.getInput().isKeyPressed(Input.KEY_F4)) {
                model.setPaddleSpeed(model.getPaddleSpeed() + 1);
            }
        }
        if (ball.getMinX() <= 0) {
            ball.reverseX();
            ball.toStart();
            model.setScorePlayer1(model.getScorePlayer1() + 1);
        }
        if (ball.getMaxX() >= width) {
            ball.reverseX();
            ball.toStart();
            model.setScorePlayer2(model.getScorePlayer2() + 1);
        }
        if (ball.intersects(paddlePlayer1)) {
            ball.intersect(paddlePlayer1.getY());
        }
        if (ball.intersects(paddlePlayer2)) {
            ball.intersect(paddlePlayer2.getY());
        }
    }
}
