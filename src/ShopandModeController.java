package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShopandModeController extends UserMenuController
{
    @FXML
    private Button backFromModes, backFromStore;
    @FXML
    private ImageView choosen;
    @FXML
    private ListView<ImageView> images, images1;
    @FXML
    private Label information;
    @FXML
    private Button buyOrChoose;

    private ArrayList<String> informationsC;
    private ArrayList<String> informationsW;
    Boolean isWeapon;
    public void fromStoreToUserMenu(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromStore.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
        window.setScene(new Scene(root));
    }

    public void setInformations( ArrayList<String> informationsC, ArrayList<String> informationsW) {
        System.out.println("Here");
        this.informationsC = informationsC;
        this.informationsW = informationsW;
    }

    public void goBackToUserMenu(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromModes.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
        window.setScene(new Scene(root));
    }

    public void characterSelected(MouseEvent event)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("shop.fxml"));
        choosen.setImage(images.getSelectionModel().getSelectedItem().getImage());
        System.out.println(informationsC.get(images.getSelectionModel().getSelectedIndex()));
        information.setText(informationsC.get(images.getSelectionModel().getSelectedIndex()));
        System.out.println(images.getSelectionModel().getSelectedIndex());
        isWeapon = false;
    }



    public void weaponSelected(MouseEvent event)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("shop.fxml"));
        choosen.setImage(images1.getSelectionModel().getSelectedItem().getImage());
        //System.out.println(informations.get(images.getSelectionModel().getSelectedIndex()));
        information.setText(informationsW.get(images1.getSelectionModel().getSelectedIndex()));
        System.out.println(images.getSelectionModel().getSelectedIndex());
        isWeapon = true;
    }

    public void buyStuff(ActionEvent event)throws Exception{
        System.out.println("buy stuff or choose stuff");
        if( !isWeapon)
        {
            System.out.println(images.getSelectionModel().getSelectedIndex());
        }
        else
        {
            System.out.println(images1.getSelectionModel().getSelectedIndex());
        }
    }
}
