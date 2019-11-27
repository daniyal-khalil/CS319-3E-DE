package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AudioTester extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        ArrayList<String> list = new ArrayList<String>();
        list.add("src\\sample\\musicAbba.mp3");
        list.add("src\\sample\\downloadfile.mp3");
        final Audio audio = new Audio(list);
        audio.play();

        Button button1 = new Button("Stop and Reset");
        button1.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.resetAndStop();
            }
        });
        Button button2 = new Button("Play");
        button2.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.play();
            }
        });
        Button button3 = new Button("Increase Volume");
        button3.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.increaseVol();
            }
        });
        Button button4 = new Button("Decrease Volume");
        button4.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.decreaseVol();
            }
        });
        Button button5 = new Button("Mute");
        button5.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.mute();
            }
        });
        Button button6 = new Button("UN-Mute");
        button6.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.unmute();
            }
        });
        Button button7 = new Button("Pause");
        button7.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.pause();
            }
        });
        Button button8 = new Button("Next Music");
        button8.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                audio.nextAudio();
            }
        });
        HBox hbox = new HBox(button1, button2, button3, button4, button5, button6, button7, button8);


        Scene scene = new Scene(hbox, 400, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing Audio");
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}