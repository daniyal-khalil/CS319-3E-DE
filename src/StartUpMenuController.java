package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartUpMenuController {

    @FXML
    private Button createProfile;
    @FXML
    private Button loadProfile;
    @FXML
    private TextField createName;
    @FXML
    private Button nameEntered;

    @FXML
    private Button backFromCreate, backFromLoad;

    public void onCreateProfile(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to create name");
        Stage window;
        window = (Stage) createProfile.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CreateProfilePopUp.fxml"));

        window.setScene(new Scene(root));
    }

    public void onLoadProfile(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to create name");
        Stage window;
        window = (Stage) loadProfile.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LoadProfilePopUp.fxml"));

        window.setScene(new Scene(root));
    }

    public void enterName(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
        String username = createName.getText();

        Saver txtFile = new Saver("src\\sample\\a.txt");
        int id = (int)(Math.random()  * 10000);
        txtFile.writeNewUser(username, id);
        System.out.print(username);
        Stage stage = (Stage) createName.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void fromLoadToStart(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to start");
        Stage window;
        window = (Stage) backFromLoad.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StartUpMenu.fxml"));

        window.setScene(new Scene(root));
    }

    public void fromCreatToStart(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to start");
        Stage window;
        window = (Stage) backFromCreate.getScene().getWindow();
        System.out.println("get the window");
        Parent root = FXMLLoader.load(getClass().getResource("StartUpMenu.fxml"));
        System.out.println("get the root");
        window.setScene(new Scene(root));
    }

}


