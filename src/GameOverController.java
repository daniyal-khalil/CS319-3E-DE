package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverController {

    @FXML
    private Label scoreLabel;
    private Player player;
    private int Score;
    private String currentMode;
    private Audio gameAudio;
    private Audio music;


    @FXML
    private Button exitApp;

    /**
     * setter for game audio
     * @param gameAudio the sound effects
     */
    public void setGameAudio(Audio gameAudio) {
        this.gameAudio = gameAudio;
    }

    /**
     * setter for music
     * @param music the soundtrack audio
     */
    public void setMusic(Audio music) {
        this.music = music;
    }

    /**
     * setter for current mode
     * @param currentMode of the game
     */
    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
    }

    /**
     * setter for player
     * @param player of the game
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * setter for the score
     * @param score of the player
     */
    public void setScore(int score) {
        Score = score;
    }


    /**
     * setter for score label
     * @param score to be setted.
     */
    public void setScoreLabel(int score) {

        scoreLabel.setText(score + "");
        Saver saver = new Saver("src\\sample\\a.txt");
        saver.updateScore(player.getName(), score);
    }


    /**
     * this method plays game again
     * @param event is action event for listener of play again
     */
    public void goingToPlay(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePanel.fxml"));
        Parent root = (Parent) loader.load();
        GameController gameController = new GameController();
        gameController = loader.getController();
        System.out.println(player.getName());
        gameController.setPlayer(player);
        gameController.setCurrentMode(currentMode);
        gameController.setMusic(music);
        gameController.setAudioEffect(gameAudio);
        gameController.goingToPlay(event);
    }

    /**
     * this method exits app
     * @param event for listeenr to exit app.
     * @exception Exception on input
     */
    public void exittingApp(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.fxml"));
        Parent root1 = (Parent) loader.load();
        UserMenuController userMenuController = new UserMenuController();
        userMenuController = loader.getController();
        userMenuController.setPlayer(player);
        userMenuController.setCurrentMode(currentMode);
        userMenuController.setMusic(music);
        userMenuController.setAudioEffect(gameAudio);

        Stage stage = (Stage) exitApp.getScene().getWindow();
        Scene scene = new Scene(root1);
        stage.setScene(scene);

    }

}
