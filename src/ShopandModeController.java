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
    private Audio gameAudio;
    private Audio music;
    @FXML
    private Button backFromModes, backFromStore;
    @FXML
    private ImageView choosen;
    @FXML
    private ListView<ImageView> images, images1;
    @FXML
    private Label information, error;
    @FXML
    private Button buyOrChoose;

    private ArrayList<String> informationsC;
    private ArrayList<String> informationsW;
    private Player player;
    Boolean isWeapon;
    String saver = "";

    public void fromStoreToUserMenu(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromStore.getScene().getWindow();
        
        
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("UserMenu.fxml"));
        Parent root = (Parent) loader.load();
        UserMenuController userMenu = new UserMenuController();
        userMenu = loader.getController();
        userMenu.setAudioEffect(gameAudio);
        userMenu.setMusic(music);
        
        Pane root = loader.load();
        UserMenuController userMenuController = new UserMenuController();
        userMenuController = loader.getController();
        userMenuController.setPlayer(player);
        window.setScene(new Scene(root));
    }

    public void setInformations( ArrayList<String> informationsC, ArrayList<String> informationsW) {
        System.out.println("Here");
        this.informationsC = informationsC;
        this.informationsW = informationsW;
    }

    public void setPlayer( Player player)
    {
        this.player = player;
        System.out.println(player.getName());
    }

    public void characterSelected(MouseEvent event)throws Exception
    {
        error.setText("");
        System.out.println(player.getName());
        //Parent root = FXMLLoader.load(getClass().getResource("shop.fxml"));
        choosen.setImage(images.getSelectionModel().getSelectedItem().getImage());
        //System.out.println(informationsC.get(images.getSelectionModel().getSelectedIndex()));
        information.setText(informationsC.get(images.getSelectionModel().getSelectedIndex()));
        System.out.println(images.getSelectionModel().getSelectedIndex());
        isWeapon = false;
    }



    public void weaponSelected(MouseEvent event)throws Exception
    {
        error.setText("");
        //Parent root = FXMLLoader.load(getClass().getResource("shop.fxml"));
        choosen.setImage(images1.getSelectionModel().getSelectedItem().getImage());
        //System.out.println(informations.get(images.getSelectionModel().getSelectedIndex()));
        information.setText(informationsW.get(images1.getSelectionModel().getSelectedIndex()));
        System.out.println(images.getSelectionModel().getSelectedIndex());
        isWeapon = true;
    }

    public void buyStuff(ActionEvent event)throws Exception
    {
        error.setText("");
        System.out.println("buy stuff or choose stuff");
        if( !isWeapon )
        {
            int a = information.getText().indexOf("price");
            String foo = information.getText().substring(a + 6);
            System.out.println(foo);
            //int price = Integer.parseInt(foo);
            int price = 0;
            if( player.getCoins() > price)
            {
                Character temp = new Character();
                temp.setImage(images.getSelectionModel().getSelectedItem().getImage());
                int i = information.getText().indexOf("price"); // 4
                String word = information.getText().substring(0, i);
                temp.setName(word);
                player.addCharacter(temp);
                Saver s = new Saver("src\\sample\\a.txt");
                s.addCharacter(player.getName(), temp);
                System.out.println(images.getSelectionModel().getSelectedIndex());
                System.out.println("aldiq");
            }
            else
            {
                error.setText("not enough coins!");
            }
        }
        else
        {
            int a = information.getText().indexOf("price"); // 4
            String foo1 = information.getText().substring(a + 6);
            System.out.println(foo1);
            int price = Integer.parseInt(foo1);
            if( player.getCoins() > price)
            {
                Weapon temp = new Weapon();
                temp.setImage(images1.getSelectionModel().getSelectedItem().getImage());
                int i = information.getText().indexOf("price"); // 4
                String word1 = information.getText().substring(0, i);
                temp.setName(word1);
                player.addWeapon(temp);
                Saver s1 = new Saver("src\\sample\\a.txt");
                s1.addWeapon(player.getName(), temp);
                System.out.println(images1.getSelectionModel().getSelectedIndex());
                System.out.println("aldiq");
            }
            else
            {
                error.setText("not enough coins!");
            }
        }
    }

    public void chooseStuff(ActionEvent event) throws Exception
    {
        error.setText("");
        System.out.println(error.getText());
        int i = information.getText().indexOf("price"); // 4
        Boolean found = false;
        String word = information.getText().substring(0, i);
        if( !isWeapon && !player.getCharacters().isEmpty())
        {
            for(int a = 0; a < player.getCharacters().size(); a++)
            {
                if(player.getCharacters().get(a).getName().equals(word) )
                {
                    player.setCurrentCharacter(player.getCharacters().get(a));
                    Saver s = new Saver("src\\sample\\a.txt");
                    s.setCurrentCharacter(player.getName(),player.getCharacters().get(a));
                    found = true;
                }
            }
        }
        else if(!player.getCharacters().isEmpty())
        {
            for(int a = 0; a < player.getWeapons().size(); a++)
            {
                if(player.getWeapons().get(a).getName().equals(word) )
                {
                    Saver s1 = new Saver("src\\sample\\a.txt");
                    s1.setCurrentWeapon(player.getName(), player.getWeapons().get(a));
                    player.setCurrentWeapon(player.getWeapons().get(a));
                    found = true;
                }
            }
        }
        if(!found)
        {
            error.setText("Not in your collection!");
        }
        System.out.println(found);
    }
    
    public void setMusic(Audio musicAudio) {
        System.out.println( "effect setted in user");
        music = musicAudio;
    }

    public void setAudioEffect(Audio audioEffect) {
        System.out.println( "music setted in user");
        gameAudio = audioEffect;

    }
}
