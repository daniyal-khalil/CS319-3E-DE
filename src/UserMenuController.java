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

    private final double MIN = 0d;
    private final double MAX = 100d;
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

    public void playGame(ActionEvent actionEvent) throws Exception
    {
        Stage window = (Stage) newGame.getScene().getWindow();
        GameController gameController = new GameController(actionEvent);
    }

    public ArrayList<String> getInformationsW()
    {
        return informationsW;
    }

    public ArrayList<String> getInformationsC()
    {
        return informationsC;
    }

    public void popSettings(javafx.event.ActionEvent actionEvent) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("startSettingsPopUp.fxml"));
        Stage window = new Stage();
        System.out.println("Meow");
        Scene scene = new Scene(root);
        sound = new Slider(0,1000,500);
        effects = new Slider(0,100,50);
        ((Slider)root.lookup("#sound")).setMin(MIN);
        ((Slider)root.lookup("#sound")).setMax(MAX);
        ((Slider)root.lookup("#sound")).setValue(50);
   //     ((Slider)root.lookup("#sound")).sliderValueProperty().addListener((ov) -> {
     //       if (((Slider)root.lookup("#sound")).isTheValueChanging()) {
       //     }
       // });

//        ((Slider)root.lookup("#sound")).setValue(50);
//        ((Slider)root.lookup("#sound")).setShowTickMarks(true);
//        //sound.setShowTickMarks(true);
//
//        ((Slider)root.lookup("#sound")).setShowTickLabels(true);
//        ((Slider)root.lookup("#effects")).setShowTickLabels(true);
//        ((Slider)root.lookup("#sound")).setMajorTickUnit(25f);
//        ((Slider)root.lookup("#sound")).setBlockIncrement(1f);
//        ((Slider)root.lookup("#effects")).setMajorTickUnit(25f);
//        ((Slider)root.lookup("#effects")).setBlockIncrement(1f);
       // ((Slider)root.lookup("#sound")).setPrefHeight(30);

        ((Slider)root.lookup("#sound")).setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        //slide.setShowTickLabels(true);
        ///slide.setShowTickMarks(true);
        //((Slider)root.lookup("#sound")).setSnapToTicks(true);
        ((Slider)root.lookup("#sound")).valueProperty().addListener((observable, oldValue, newValue) -> {

            System.out.println(newValue.intValue());
        });

        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        window.setScene(scene);

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
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("Shop.fxml"));
        Parent root = (Parent) loader.load();

        ShopandModeController shop = new ShopandModeController();
        shop = loader.getController();

//       Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
        images = new ListView<ImageView>();
        images1 = new ListView<ImageView>();
        imageView = new ImageView();
        ArrayList<ImageView> imgs = new ArrayList<ImageView>();
        ArrayList<ImageView> imgs1 = new ArrayList<ImageView>();
        //imageView.setImage(new Image("https://images.app.goo.gl/fLcJN1Gt3wVfpc8h8"));

        Image img = null;
        informationsC = new ArrayList<String>();
        informationsC.add("captan america 0 \n power:1 \n price:100 \n");
        informationsC.add("captan america 1 \n power:1 \n price:100 \n");
        informationsC.add("captan america 2 \n power:1 \n price:100 \n");
        informationsC.add("captan america 3 \n power:1 \n price:100 \n");
        informationsC.add("captan america 4 \n power:1 \n price:100 \n");
        informationsC.add("captan america 5 \n power:1 \n price:100 \n");
        informationsC.add("captan america 6 \n power:1 \n price:100 \n");
        //System.out.println(informations.get(0));
        informationsW = new ArrayList<String>();
        informationsW.add("weapon 0 \n power:1 \n price:100 \n");
        informationsW.add("weapon 1 \n power:1 \n price:100 \n");
        informationsW.add("weapon 2 \n power:1 \n price:100 \n");
        informationsW.add("weapon 3 \n power:1 \n price:100 \n");



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
        items1 = FXCollections.observableArrayList(imgs1);
        ((ListView)root.lookup("#images1")).setItems(items1);
        window.setScene(new Scene(root));
    }



}
