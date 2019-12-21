package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Audio music = new Audio("C:\\Users\\Asus\\Desktop\\starman.mp3");
        Audio gameAudio = new Audio("C:\\Users\\Asus\\Desktop\\starman.mp3");

        FXMLLoader loader =new  FXMLLoader(getClass().getResource("StartUpMenu.fxml"));
        Parent root = (Parent) loader.load();

        StartUpMenuController startUpMenuController = new StartUpMenuController();
        startUpMenuController = loader.getController();
        System.out.println("passed to first scene");
        startUpMenuController.setAudioEffect(gameAudio);
        startUpMenuController.setMusic(music);
        music.play();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1024, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
