package nakir.ppvis.game.gamestates;

import nakir.ppvis.game.gameobjects.AIPaddle;
import nakir.ppvis.game.gameobjects.Paddle;
import nakir.ppvis.game.gameobjects.PlayerPaddle;
import nakir.ppvis.game.model.BallModel;
import nakir.ppvis.game.Field;
import nakir.ppvis.game.model.PaddleModel;
import nakir.ppvis.game.gameobjects.Ball;
import nakir.ppvis.game.model.Model;
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
    private Paddle paddlePlayer2 = new Paddle();;
    private Paddle plPaddle;
    private Paddle AIPaddle;
    private Image background;
    private Model model;
    private BallModel ballModel;
    private PaddleModel paddleModel;
    private Field field;

    public Play(int width, int height, Model model, BallModel ballModel, PaddleModel paddleModel) {
        this.model = model;
        this.height = height;
        this.width = width;
        this.ballModel = ballModel;
        this.paddleModel = paddleModel;
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.getInput().enableKeyRepeat();
        field = new Field(width, height, model);
        ball = new Ball(width, height, ballModel, field);
        paddlePlayer1 = new PlayerPaddle(5, height/2 - 40, gameContainer, Input.KEY_W, Input.KEY_S, paddleModel, field);
        plPaddle = new PlayerPaddle(width - 15, height / 2 - 40, gameContainer, Input.KEY_UP, Input.KEY_DOWN, paddleModel, field);
        AIPaddle = new AIPaddle(width - 15, height / 2 - 40, paddleModel, field, ball);
        background = new Image("res/background.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        gameContainer.setShowFPS(false);
        g.drawImage(background, 0, 0);
        if (model.getIsPvP()) {
            paddlePlayer2 = plPaddle;
        } else {
            paddlePlayer2 = AIPaddle;
        }
        g.fill(paddlePlayer1);
        g.fill(paddlePlayer2);
        g.fill(ball);
        g.drawString("Player2: " + model.getScorePlayer2().toString(), 50, 10);
        g.drawString("Player1: " + model.getScorePlayer1().toString(), width - 50 - g.getFont().getWidth("Player1: "
                + model.getScorePlayer1().toString()), 10);
        g.drawString("Ball speed: " + ballModel.getBallSpeed().toString(), 250, 0);
        g.drawString("Paddle speed: " + paddleModel.getPaddleSpeed().toString(), 250, 13);
        if (model.checkWinner()) {
            String tmp = model.getWinnerName() + " win!";
            g.getFont().getWidth(tmp);
            g.drawString(model.getWinnerName() + " win!", width / 2 - g.getFont().getWidth(tmp) / 2, 100);
            g.drawString("R - retart", width/2 - g.getFont().getWidth("R - retart")/2, 120);
            g.drawString("Esc - menu", width/2 - g.getFont().getWidth("Esc - menu")/2, 140);
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            stateBasedGame.enterState(0);
            restart();
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_R)) {
            restart();
        }

        if (!model.checkWinner()) {
            if (gameContainer.getInput().isKeyPressed(Input.KEY_SPACE)) {
                ball.setStoped(false);
            }
            paddlePlayer1.update();
            paddlePlayer2.update();
            if (!ball.isStoped()) {
                ball.update();
            } else {
                if (gameContainer.getInput().isKeyPressed(Input.KEY_F1)) {
                    ballModel.setBallSpeed(ballModel.getBallSpeed() - 1);
                }
                if (gameContainer.getInput().isKeyPressed(Input.KEY_F2)) {
                    ballModel.setBallSpeed(ballModel.getBallSpeed() + 1);
                }
                if (gameContainer.getInput().isKeyPressed(Input.KEY_F3)) {
                    paddleModel.setPaddleSpeed(paddleModel.getPaddleSpeed() - 1);
                }
                if (gameContainer.getInput().isKeyPressed(Input.KEY_F4)) {
                    paddleModel.setPaddleSpeed(paddleModel.getPaddleSpeed() + 1);
                }
            }
            if (ball.intersects(paddlePlayer1)) {
                ball.intersect(paddlePlayer1.getY());
            }
            if (ball.intersects(paddlePlayer2)) {
                ball.intersect(paddlePlayer2.getY());
            }
        }
    }

    public void restart() {
        ball.toStart();
        paddlePlayer1.toStart();
        paddlePlayer2.toStart();
        model.setScorePlayer1(0);
        model.setScorePlayer2(0);
    }
}
