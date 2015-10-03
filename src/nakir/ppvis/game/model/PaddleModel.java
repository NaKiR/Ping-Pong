package nakir.ppvis.game.model;

public class PaddleModel {
    private Integer paddleSpeed;
    private Integer maxPaddleSpeed = 10;
    private Integer minPaddleSpeed = 3;

    public PaddleModel() {
        paddleSpeed = 10;
    }

    public Integer getPaddleSpeed() {
        return paddleSpeed;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        if (minPaddleSpeed <= paddleSpeed && paddleSpeed <= maxPaddleSpeed) {
            this.paddleSpeed = paddleSpeed;
        }
    }
}