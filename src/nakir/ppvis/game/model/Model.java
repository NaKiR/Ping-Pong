package nakir.ppvis.game.model;

public class Model {
    private Integer scorePlayer1;
    private Integer scorePlayer2;

    public Model() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }

    public Integer getScorePlayer1() {
        return scorePlayer1;
    }

    public Integer getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer1(int scorePlayer) {
        scorePlayer1 = scorePlayer;
    }

    public void setScorePlayer2(int scorePlayer) {
        scorePlayer2 = scorePlayer;
    }
}
