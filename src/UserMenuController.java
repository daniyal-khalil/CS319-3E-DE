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
import javafx.scene.input.MouseEvent;
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
import java.io.IOException;
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
    @FXML
    private ImageView backFromMenu;
    private ObservableList<ImageView> items, items1;
    private ImageView imageView, imageView1;
    ArrayList<String> informationsC;
    ArrayList<String> informationsW;

    private String currentMode;
    @FXML private ImageView modeJustice, modeMarvel ;//#
    /**
   * This is the main method which makes use of addNum method.
   * @param actionEvent
   * @return Nothing.
   * @exception Exception On input error.
   */
    public void playGame(ActionEvent actionEvent) throws Exception {
       // music.pause();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePanel.fxml"));
        Parent root = (Parent) loader.load();
        GameController gameController = new GameController();
        gameController = loader.getController();
        System.out.println(player.getName());
        gameController.setPlayer(player);
        gameController.setAudioEffect(gameAudio);
        gameController.setMusic(music);
        gameController.setCurrentMode(currentMode);
        gameController.goingToPlay(actionEvent);

        gameAudio.play();

    }

   /**
   * transferring method
   * @param currentMode
   * @exception Exception On input error.
   */
    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
    }

    public void popSettings(javafx.event.ActionEvent actionEvent) throws Exception
    {

        //try{gameAudio.mute();}catch(NullPointerException e){  System.out.println("cat died");}
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("startSettingsPopUp.fxml"));

        Parent root = (Parent) loader.load();

        double volume = 100 * music.getVol();
        int vol = (int) volume;
        ((Slider)root.lookup("#musicAudio")).setValue(volume);

        volume = 100 * gameAudio.getVol();
        vol = (int) volume;
        ((Slider)root.lookup("#sound")).setValue(volume);

        UserMenuController userMenu = new UserMenuController();
        userMenu = loader.getController();
        System.out.println("passed to pop");
        userMenu.setAudioEffect(gameAudio);
        userMenu.setMusic(music);
        ((Slider)root.lookup("#musicAudio")).valueProperty().addListener((observable, oldValue, newValue) -> {
            music.changeVolume(newValue.intValue());
        });

        ((Slider)root.lookup("#sound")).valueProperty().addListener((observable, oldValue, newValue) -> {
            gameAudio.changeVolume(newValue.intValue());
        });

        Stage window = new Stage();
        System.out.println("Meow");
        Scene scene = new Scene(root);

        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        window.setScene(scene);

    }

    public void menuToStart(MouseEvent mouseEvent) throws IOException {
        Stage window;
        window = (Stage) backFromMenu.getScene().getWindow();


        FXMLLoader loader =new  FXMLLoader(getClass().getResource("StartUpMenu.fxml"));
        Parent root = (Parent) loader.load();

        StartUpMenuController start = new StartUpMenuController();
        start = loader.getController();
        start = loader.getController();
        start.setAudioEffect(gameAudio);
        start.setMusic(music);
        window.setScene(new Scene(root));
    }


    public void goingToModes(ActionEvent event)throws Exception{

        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromMenu.getScene().getWindow();

        //load fxml and set audios
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("Modes.fxml"));
        Parent root = (Parent) loader.load();
        ShopandModeController shop = new ShopandModeController();
        shop = loader.getController();
        shop.setAudioEffect(gameAudio);
        shop.setMusic(music);
        shop.radioInitialize();
        shop.setPlayer(player);
        shop.setCurrentMode(currentMode);
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
        shop.setCurrentMode(currentMode);

//       Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
        images = new ListView<ImageView>();
        images1 = new ListView<ImageView>();
        imageView = new ImageView();
        ArrayList<ImageView> imgs = new ArrayList<ImageView>();
        ArrayList<ImageView> imgs1 = new ArrayList<ImageView>();
        //imageView.setImage(new Image("https://images.app.goo.gl/fLcJN1Gt3wVfpc8h8"));

        Image img = null;
        informationsC = new ArrayList<String>();
        informationsC.add("BW  price:100");
        informationsC.add("CA  price:100");
        informationsC.add("CM  price:100");
        informationsC.add("HU  price:100");
        informationsC.add("IM  price:100");
        informationsC.add("SM  price:100");
        informationsC.add("TH  price:100");
        //System.out.println(informations.get(0));
        informationsW = new ArrayList<String>();
        informationsW.add("Bullet \n power:1 \n price:100");
        informationsW.add("Hammer \n power:1 \n price:100");
        informationsW.add("IM_Laser \n power:1 \n price:100");
        informationsW.add("shield \n power:1 \n price:100");
        informationsW.add("stone \n power:1 \n price:100");
        informationsW.add("Web \n power:1 \n price:100");

        File folder = new File("src/sample/storeC");
        if(!currentMode.equals("Marvel Universe"))
        {
            folder = new File("src/sample/DC_characters_store");
            informationsC.set(0, "AQ  price:100");
            informationsC.set(1, "BT  price:100");
            informationsC.set(2, "FL  price:100");
            informationsC.set(3, "SP  price:100");
            informationsC.set(4, "WW  price:100");
        }

        if (!folder.exists()) folder.mkdir();
        for (File file : folder.listFiles()) {
            String filename = file.getPath();
            System.out.println(filename);
            try
            {
                img = new Image(new FileInputStream(filename));
                ImageView imageView = new ImageView(img);

                imageView.setFitWidth(70);
                imageView.setPreserveRatio(true);
                imgs.add(imageView);
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
        //((ListView)root.lookup("#images")).setPrefWidth(items.size() * 55);
        File folder1 = new File("src/sample/storeW");
        if(!currentMode.equals("Marvel Universe"))
        {
            folder1 = new File("src/sample/DC_weapons_store");
        }
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

    //############################################
    public void setModeImage( String text ) throws FileNotFoundException {
        Image modeImage;
        System.out.println(text + "bal");
        modeMarvel.setVisible(false);
        modeJustice.setVisible(false);
        if( text.equals("Justice League" ) )
        {
            System.out.println(text + "bal");
            //modeJpg.setImage(new Image(new FileInputStream("src//sample//images//justiceL.jpg")));
            modeJustice.setVisible(true);
            modeMarvel.setVisible(false);
        }
        else
        {
            modeMarvel.setVisible(true);
            modeJustice.setVisible(false);
        }
    }//#


    public void setMusic(Audio musicAudio) {
        System.out.println( "effect setted in user");
        music = musicAudio;
    }

    public void setAudioEffect(Audio audioEffect) {
        System.out.println( "music setted in user");
        gameAudio = audioEffect;

    }
}
