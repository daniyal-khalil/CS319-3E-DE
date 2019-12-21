package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image ;
import org.omg.CORBA.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class UserMenuController {

    private Audio gameAudio;
    private Audio music;
    private Player player;
    @FXML
    private Button newGame, store, modes, settings;
    @FXML
    Slider sound;
    @FXML
    Slider effects;
    @FXML
    private ListView<ImageView> images, images1;
    private ObservableList<ImageView> items, items1;
    private ImageView imageView, imageView1;
    ArrayList<String> informationsC;
    ArrayList<String> informationsW;

    public void playGame(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePanel.fxml"));
        Parent root = (Parent) loader.load();
        GameController gameController = new GameController();
        gameController = loader.getController();
        System.out.println(player.getName());
        gameController.setPlayer(player);
        gameController.goingToPlay(actionEvent);

    }


    public void popSettings(javafx.event.ActionEvent actionEvent) throws Exception
    {
        //try{gameAudio.mute();}catch(NullPointerException e){  System.out.println("cat died");}
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("startSettingsPopUp.fxml"));

        Parent root = (Parent) loader.load();
        UserMenuController userMenu = new UserMenuController();
        userMenu = loader.getController();
        System.out.println("passed to pop");
        userMenu.setAudioEffect(gameAudio);
        userMenu.setMusic(music);
        ((Slider)root.lookup("#musicAudio")).valueProperty().addListener((observable, oldValue, newValue) -> {
            changeVolume(newValue.intValue());

        });
        Stage window = new Stage();
        System.out.println("Meow");
        Scene scene = new Scene(root);
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        window.setScene(scene);

    }

    public void goingToModes(ActionEvent event)throws Exception{

        System.out.println("From controller");
        Stage window;
        window = (Stage) modes.getScene().getWindow();
        
        //load fxml and set audios
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("Modes.fxml"));
        Parent root = (Parent) loader.load();
        ShopandModeController shop = new ShopandModeController();
        shop = loader.getController();
        shop.setAudioEffect(gameAudio);
        shop.setMusic(music);
        
        window.setScene(new Scene(root));
    }

    public void setPlayer(Player player)
    {
        this.player = player;
        System.out.println(this.player.getName());
    }


    public void goingToStore(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;

        window = (Stage) store.getScene().getWindow();
        
        //load fxml and set audios
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("Shop.fxml"));
        Parent root = (Parent) loader.load();
        ShopandModeController shop = new ShopandModeController();
        shop = loader.getController();
        shop.setAudioEffect(gameAudio);
        shop.setMusic(music);

//       Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
        images = new ListView<ImageView>();
        images1 = new ListView<ImageView>();
        imageView = new ImageView();
        ArrayList<ImageView> imgs = new ArrayList<ImageView>();
        ArrayList<ImageView> imgs1 = new ArrayList<ImageView>();
        //imageView.setImage(new Image("https://images.app.goo.gl/fLcJN1Gt3wVfpc8h8"));

        Image img = null;
        informationsC = new ArrayList<String>();
        informationsC.add("captan america 0 \n price:100");
        informationsC.add("captan america 1 \n price:100");
        informationsC.add("captan america 2 \n price:100");
        informationsC.add("captan america 3 \n price:100");
        informationsC.add("captan america 4 \n price:100");
        informationsC.add("captan america 5 \n price:100");
        informationsC.add("captan america 6 \n price:100");
        //System.out.println(informations.get(0));
        informationsW = new ArrayList<String>();
        informationsW.add("weapon 0 \n power:1 \n price:100");
        informationsW.add("weapon 1 \n power:1 \n price:100");
        informationsW.add("weapon 2 \n power:1 \n price:100");
        informationsW.add("weapon 3 \n power:1 \n price:100");



        File folder = new File("src/sample/storeC");
        if (!folder.exists()) folder.mkdir();
        for (File file : folder.listFiles()) {
            String filename = file.getPath();
            System.out.println(filename);
            try
            {
                img = new Image(new FileInputStream(filename));
                imgs.add(new ImageView(img));
                System.out.println("working");
            }
            catch (FileNotFoundException e)
            {
                System.out.println("not working");
            }
        }
        System.out.println(imgs.size());
        items = FXCollections.observableArrayList(imgs);
        //ShopandModeController shop = new  ShopandModeController(informations);
        ((ListView)root.lookup("#images")).setItems(items);

        File folder1 = new File("src/sample/storeW");
        if (!folder1.exists()) folder1.mkdir();
        for (File file : folder1.listFiles()) {
            String filename = file.getPath();
            System.out.println(filename);
            try
            {
                img = new Image(new FileInputStream(filename));
                imgs1.add(new ImageView(img));
                System.out.println("working");
            }
            catch (FileNotFoundException e)
            {
                System.out.println("not working");
            }
        }
        System.out.println(imgs1.size());
        shop.setInformations(informationsC, informationsW);
        shop.setPlayer( player);
        items1 = FXCollections.observableArrayList(imgs1);
        ((ListView)root.lookup("#images1")).setItems(items1);
        window.setScene(new Scene(root));
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
