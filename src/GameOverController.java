package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController {

    @FXML
    private Label scoreLabel;
    private Player player;
    private int Score;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setScoreLabel(int score) {

        scoreLabel.setText(score + "");
        Saver saver = new Saver("src\\sample\\a.txt");
        saver.updateScore(player.getName(), score);
    }


    public void goingToPlay(ActionEvent event) {
    }

    public void exittingApp(ActionEvent event) {
    }

}
