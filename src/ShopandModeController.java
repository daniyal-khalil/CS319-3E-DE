package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShopandModeController
{
    private Audio gameAudio;
    private Audio music;
    @FXML
    private ImageView choosen, back, select, buy, backfromModes;
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

    private String currentMode;
    String saver = "";

    @FXML
    private RadioButton marvel;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton dc;
    String modes;

    /**
     * This method is used to choose mode
     * @param event This is the parameter for listener
     * @exception Exception On input error.
     */
    public void onModeSelected(ActionEvent event) throws Exception{
        RadioButton selectedMode = (RadioButton) toggleGroup.getSelectedToggle();
        String value = selectedMode.getText();
        Stage window = (Stage) marvel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.fxml"));
        Parent root = (Parent) loader.load();
        UserMenuController userMenuController = new UserMenuController();
        userMenuController = loader.getController();
        userMenuController.setPlayer(player);
        userMenuController.setAudioEffect(gameAudio);
        userMenuController.setModeImage(selectedMode.getText());
        userMenuController.setMusic(music);
        System.out.println(value);
        userMenuController.setCurrentMode(value);
        window.setScene(new Scene(root));

    }

    /**
     * This method is used to initialize mode radio buttons
     */
    public void radioInitialize(){

        toggleGroup = new ToggleGroup();
        marvel.setToggleGroup(toggleGroup);
        marvel.setSelected(true);
        dc.setToggleGroup(toggleGroup);
    }

    /**
     * This method is used to transfer the data of currentMode to shopandMode controler
     * @param currentMode is chosen mode theme
     */
    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
    }


    /**
     * This method is used go back from mode to userMenu
     * @param event This parameter is for listener
     * @exception Exception On input error.
     */
    public void fromModeToUserMenu(MouseEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) back.getScene().getWindow();


        FXMLLoader loader =new  FXMLLoader(getClass().getResource("UserMenu.fxml"));

        Pane root = loader.load();
        UserMenuController userMenuController = new UserMenuController();
        userMenuController = loader.getController();
        userMenuController.setPlayer(player);
        userMenuController.setAudioEffect(gameAudio);
        userMenuController.setMusic(music);
        userMenuController.setCurrentMode(currentMode);
        window.setScene(new Scene(root));
    }

    /**
     * This method is used to transfer information of character and weapons
     * @param informationsC is arraylist that store information of characters to show in label
     * @param informationsW is arraylist that store information of weapons to show in label
     */
    public void setInformations( ArrayList<String> informationsC, ArrayList<String> informationsW) {
        System.out.println("Here");
        this.informationsC = informationsC;
        this.informationsW = informationsW;
    }

    /**
     * This method is used to transfer player
     * @param player is parameter to transfer the player value
     */
    public void setPlayer( Player player)
    {
        this.player = player;
        System.out.println(player.getName());
    }

    /**
     * This method chooses character in listview
     * @param event is parameter that is used as listener in listview
     */
    public void characterSelected(MouseEvent event)throws Exception
    {
        error.setText("");
        System.out.println(player.getName());
        //Parent root = FXMLLoader.load(getClass().getResource("shop.fxml"));
        choosen.setImage(images.getSelectionModel().getSelectedItem().getImage());
        choosen.setFitWidth(150);
        choosen.setFitHeight(150);
        //System.out.println(informationsC.get(images.getSelectionModel().getSelectedIndex()));
        information.setText(informationsC.get(images.getSelectionModel().getSelectedIndex()));
        System.out.println(images.getSelectionModel().getSelectedIndex());
        isWeapon = false;
    }


    /**
     * This method chooses weapon in listview
     * @param event is parameter that is used as listener in listview
     */
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

    /**
     * This method buys weapon and characters in shop
     * @param event is parameter that is used as listener for buy imageview
     */
    public void buyStuff(MouseEvent event)throws Exception
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
                int i = information.getText().indexOf(" "); // 4
                String word = information.getText().substring(0, i);
                if(!player.getCharacters().contains(new Character(word, price)))
                {
                    temp.setName(word);
                    player.addCharacter(temp);
                    Saver s = new Saver("src\\sample\\a.txt");
                    s.addCharacter(player.getName(), temp);
                    System.out.println(images.getSelectionModel().getSelectedIndex());
                    System.out.println("aldiq");
                }

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
                boolean found = false;
                Weapon temp = new Weapon();
                temp.setImage(images1.getSelectionModel().getSelectedItem().getImage());
                int i = information.getText().indexOf(" "); // 4
                String word1 = information.getText().substring(0, i);
                for(int c = 0; a < player.getWeapons().size(); a++)
                {
                    if(player.getWeapons().get(a).getName().equals(word1) )
                    {
                        found = true;
                    }
                }
                if(!found)
                {
                    temp.setName(word1);
                    player.addWeapon(temp);
                    Saver s1 = new Saver("src\\sample\\a.txt");
                    s1.addWeapon(player.getName(), temp);
                    System.out.println(images1.getSelectionModel().getSelectedIndex());
                    System.out.println("aldiq");
                }

            }
            else
            {
                error.setText("not enough coins!");
            }
        }
    }

    /**
     * This method chooses weapon and characters from collection of player
     * @param event is parameter that is used as listener for choose imageview
     */
    public void chooseStuff(MouseEvent event) throws Exception
    {
        error.setText("");
        System.out.println(error.getText());
        int i = information.getText().indexOf(" "); // 4
        Boolean found = false;
        String word = information.getText().substring(0, i);
        if( !isWeapon && !player.getCharacters().isEmpty())
        {
            for(int a = 0; a < player.getCharacters().size(); a++)
            {
                if(player.getCharacters().get(a).getName().equals(word) )
                {
                    System.out.println(player.getCharacters().get(a).getName() + "ooooooooooooooo");
                    player.setCurrentCharacter(player.getCharacters().get(a));
                    Saver s = new Saver("src\\sample\\a.txt");
                    s.setCurrentCharacter(player.getName(),player.getCharacters().get(a));
                    found = true;
                }
            }
            if(player.getCurrentCharacter().getName().equals(word))
            {
                found = true;
            }
        }
        else if(!player.getWeapons().isEmpty())
        {
            for(int a = 0; a < player.getWeapons().size(); a++)
            {
                if(player.getWeapons().get(a).getName().equals(word) ) {
                    Saver s1 = new Saver("src\\sample\\a.txt");
                    s1.setCurrentWeapon(player.getName(), player.getWeapons().get(a));
                    player.setCurrentWeapon(player.getWeapons().get(a));
                    found = true;
                }
            }
            if(player.getCurrentWeapon().getName().equals(word))
            {
                found = true;
            }
        }
        if(!found)
        {
            error.setText("Not in your collection!");
        }
        System.out.println(found);
    }

    /**
     * This method transfers musicAudio to ShopAndModeControlleer
     * @param musicAudio is parameter that is transfered
     */
    public void setMusic(Audio musicAudio) {
        System.out.println( "effect setted in user");
        music = musicAudio;
    }

    /**
     * This method transfers audioEffects to ShopAndMode controller
     * @param audioEffect is parameter that is transferred
     */
    public void setAudioEffect(Audio audioEffect) {
        System.out.println( "music setted in user");
        gameAudio = audioEffect;

    }


    /**
     * This method goes back to userMenu screen
     * @param mouseEvent is parameter that is event for listening the back button of shop
     * @exception IOException on loader
     */
    public void goBack(MouseEvent mouseEvent) throws IOException {
        System.out.println("From controller");
        Stage window;
        window = (Stage) back.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.fxml"));
        Pane root = loader.load();
        UserMenuController userMenuController = new UserMenuController();
        userMenuController = loader.getController();
        userMenuController.setPlayer(player);
        userMenuController.setAudioEffect(gameAudio);
        userMenuController.setMusic(music);
        userMenuController.setCurrentMode(currentMode);
        window.setScene(new Scene(root));
    }
}