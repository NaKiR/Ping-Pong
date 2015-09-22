package nakir.ppvis.game;

public class Model {
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private Integer ballSpeed;
    private Integer paddleSpeed;
    private Integer maxBallSpeed = 15;
    private Integer minBallSpeed = 1;
    private Integer maxPaddleSpeed = 10;
    private Integer minPaddleSpeed = 3;

    public Model() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        ballSpeed = 5;
        paddleSpeed = 10;
    }

    public Integer getScorePlayer1() {
        return scorePlayer1;
    }

    public Integer getScorePlayer2() {
        return scorePlayer2;
    }

    public Integer getBallSpeed() {
        return ballSpeed;
    }

    public Integer getPaddleSpeed() {
        return paddleSpeed;
    }

    public void setScorePlayer1(int scorePlayer) {
        scorePlayer1 = scorePlayer;
    }

    public void setScorePlayer2(int scorePlayer) {
        scorePlayer2 = scorePlayer;
    }

    public void setBallSpeed(int ballSpeed) {
        if (minBallSpeed <= ballSpeed && ballSpeed <= maxBallSpeed) {
            this.ballSpeed = ballSpeed;
        }
    }

    public void setPaddleSpeed(int paddleSpeed) {
        if (minPaddleSpeed <= paddleSpeed && paddleSpeed <= maxPaddleSpeed) {
            this.paddleSpeed = paddleSpeed;
        }
    }
}
