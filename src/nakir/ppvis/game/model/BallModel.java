package nakir.ppvis.game.model;

public class BallModel {
    private Integer ballSpeed;
    private Integer maxBallSpeed = 15;
    private Integer minBallSpeed = 1;

    public BallModel() {
        ballSpeed = 5;
    }

    public Integer getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int ballSpeed) {
        if (minBallSpeed <= ballSpeed && ballSpeed <= maxBallSpeed) {
            this.ballSpeed = ballSpeed;
        }
    }
}
