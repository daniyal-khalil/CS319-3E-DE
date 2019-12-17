package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserMenuController {

    @FXML
    private Button newGame, store, modes, settings;




    public void popSettings(javafx.event.ActionEvent actionEvent) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("startSettingsPopUp.fxml"));
        Stage window = new Stage();
        System.out.println("Meow");
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    public void goingToModes(ActionEvent event)throws Exception{

        System.out.println("From controller");
        Stage window;
        window = (Stage) modes.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Modes.fxml"));

        window.setScene(new Scene(root));
    }


    public void goingToStore(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) store.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
        window.setScene(new Scene(root));
    }






}
