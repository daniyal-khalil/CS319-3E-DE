package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    /**
     * starts the game itself
     * @param primaryStage is main stage of the game.
     */
    public void start(Stage primaryStage) throws Exception{
       Audio music = new Audio("src\\sample\\soundtrack.mp3");
       Audio gameAudio = new Audio("src\\sample\\soundtrack.mp3");

        FXMLLoader loader =new  FXMLLoader(getClass().getResource("StartUpMenu.fxml"));
        Parent root = (Parent) loader.load();

        StartUpMenuController startUpMenuController = new StartUpMenuController();
        startUpMenuController = loader.getController();
        System.out.println("passed to first scene");
        startUpMenuController.setAudioEffect(gameAudio);
        startUpMenuController.setMusic(music);
        startUpMenuController.settleMute();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1024, 720));
        primaryStage.show();

        music.playContinously();
    }


    public static void main(String[] args) {
        launch(args);
    }
}