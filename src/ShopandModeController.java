package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShopandModeController {

    @FXML
    private Button backFromModes, backFromStore;

    public void fromStoreToUserMenu(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromStore.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
        window.setScene(new Scene(root));
    }

    public void goBackToUserMenu(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromModes.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));

        window.setScene(new Scene(root));
    }
}
