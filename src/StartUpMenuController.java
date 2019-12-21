package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
public class StartUpMenuController
{
    private Audio music;
    private Audio gameAudio;
    @FXML
    private Button createProfile;
    @FXML
    private Button loadProfile;
    @FXML
    private TextField createName;
    @FXML
    private ListView list;
    @FXML
    private Button nameEntered;
    @FXML
    private Label invalid;
    @FXML
    private Button backFromCreate, backFromLoad;
    @FXML
    private ListView<String> players;
    @FXML
    private ListView<String> players1;
    @FXML
    private GridPane pane;
    /*@FXML
    private VBox playerSelection;*/

    public void onCreateProfile(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to create name");
        Stage window;
        window = (Stage) createProfile.getScene().getWindow();
        
        //load fxml and set audios
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("CreateProfilePopUp.fxml"));
        Parent root = (Parent) loader.load();
        StartUpMenuController startUpMenuController = new StartUpMenuController();
        startUpMenuController = loader.getController();
        startUpMenuController.setMusic(music);
        startUpMenuController.setAudioEffect(gameAudio);

        window.setScene(new Scene(root));
    }
    
    public void muteTheAudio(MouseEvent mouseEvent) throws Exception {

        if( music.getIsMuted()) {
            music.unmute();
            music.setIsMuted(false);

            mute.setVisible(false);
            play.setVisible(true);


        }
        else {
            music.mute();
            music.setIsMuted(true);


            mute.setVisible(true);
            play.setVisible(false);

        }
    }

    private class Info
    {
        private String username;
        private int score;

        public Info(String username, int score)
        {
            this.score = score;
            this.username = username;
        }

        public int getScore() {
            return score;
        }

        public String getUsername() {
            return username;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public void onLoadProfile(javafx.event.ActionEvent actionEvent) throws Exception
    {
        System.out.println("go to create name");
        Stage window;
        window = (Stage) loadProfile.getScene().getWindow();
        
        //loading fxml and setting audios
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("LoadProfile.fxml"));
        Parent root = (Parent) loader.load();
        UserMenuController userMenu = new UserMenuController();
        userMenu = loader.getController();
        System.out.println("passed to menu");
        userMenu.setMusic(music);
        userMenu.setAudioEffect(gameAudio);
        
        players = (ListView<String>) root.lookup("#players");
        players1 = (ListView<String>) root.lookup("#players1");

        //logging.setMaxWidth(300);
        //logging.setMaxHeight(150);


        // Create the Lists for the ListViews
        ObservableList<String> playerList = FXCollections.<String>observableArrayList();
        ObservableList<String> player1List = FXCollections.<String>observableArrayList();
        Saver s = new Saver("src\\sample\\a.txt");
        ArrayList<Player> pls = s.getUserList();
        int len = maxLen(pls);
        for(int i = 0; i < pls.size(); i++) {
            playerList.add(pls.get(i).getName());
            player1List.add( "" + pls.get(i).getHighScore());
        }
        // Set the Orientation of the ListView
        players.setOrientation(Orientation.VERTICAL);
        // Set the Size of the ListView
        players.setPrefSize(200, 100);
        // Add the items to the ListView
        players.getItems().addAll(playerList);
        players1.setOrientation(Orientation.VERTICAL);
        // Set the Size of the ListView
        players1.setPrefSize(200, 100);
        // Add the items to the ListView
        players1.getItems().addAll(player1List);


        // Update the message Label when the selected fruit changes
        players.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> ov,
                                final String oldvalue, final String newvalue)
            {
                try {
                    playerChanged(ov, oldvalue, newvalue);
                } catch (Exception e) {}
            }
        });
        pane = (GridPane) root.lookup("#pane");

        window.setScene(new Scene(root));
        window.show();
    }

    private int maxLen(ArrayList<Player> pls)
    {
        int max = 0;
        for(int i = 0; i < pls.size(); i++)
        {
            int l = pls.get(i).getName().length();
            if(max <= l)
                max = l;
        }
        return max;
    }

    private String makeSpace(int length, String name)
    {

        String s = name + "                                                  ";
        for(int i = 0; i < length - name.length(); i++)
            s = s + " ";
        return s;
    }

    private ArrayList<Player> sortByHS(ArrayList<Player> pls)
    {
        ArrayList<Player> ps = pls;
        int size = ps.size();
        for(int i = 0; i < size; i++)
        {
            Player min = new Player();
            int k = 0;
            for(int j = 0; j < size - i; j++)
            {
                Player p = ps.get(i);
                if(min.getHighScore() >= p.getHighScore())
                {
                    k = j;
                    min.setName(p.getName());
                    min.setCoins(p.getCoins());
                    min.setCurrentCharacter(p.getCurrentCharacter());
                    min.setCurrentWeapon(p.getCurrentWeapon());
                    min.setCharacters(p.getCharacters());
                    min.setWeapons(p.getWeapons());
                }
            }
            Player p = ps.remove(i);
            ps.add(i, min);
            ps.remove(k);
            ps.add(k, p);
            
        }
        return ps;
    }


    public void playerChanged(ObservableValue<? extends String> observable,String oldValue,String newValue) throws Exception
    {
        String oldText = oldValue == null ? "null" : oldValue.toString();
        String newText = newValue == null ? "null" : newValue.toString();

        Player p = (new Saver("src\\sample\\a.txt")).getUser(newText);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.fxml"));
        Parent root = (Parent) loader.load();
        UserMenuController userMenuController = new UserMenuController();
        userMenuController = loader.getController();
        userMenuController.setPlayer(p);
        userMenuController.setMusic(music);
        userMenuController.setAudioEffect(gameAudio);
        
        Stage stage =  (Stage) players.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private List<Info> parseUserList(ArrayList<Player> l)
    {
        List<Info> list = new ArrayList<Info>();
        for(int i = 0; i < l.size(); i++)
        {
            list.add(new Info(l.get(i).getName(), l.get(i).getHighScore()));
        }
        return list;
    }

    public void enterName(ActionEvent event) throws Exception
    {
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("UserMenu.fxml"));
        Parent root = (Parent) loader.load();
        UserMenuController userMenu = new UserMenuController();
        userMenu = loader.getController();
        System.out.println("passed to menu");
        userMenu.setMusic(music);
        userMenu.setAudioEffect(gameAudio);
        
        String username = createName.getText();

        if(username.contains("<UN>") ||
                username.contains("<cis>") ||
                username.contains("<cW>") ||
                username.contains("<dm>") ||
                username.contains("<nm>") ||
                username.contains("<pr>") ||
                username.contains("<cCh>") ||
                username.contains("<hS>") ||
                username.contains("<Ws>") ||
                username.contains("<pl>") ||
                username.equals("") ||
                username.contains("<cs>"))
        {
            invalid.setText("Invalid username format. Please try new!");
        }
        else
        {
            Saver s = new Saver("src\\sample\\a.txt");
            Player player = s.getUser(username);


            if(!player.getName().equals("undefined"))
            {
                invalid.setText("Username already taken please enter new!");
            }
            else
            {
                Player newP = new Player(username, 0, new Weapon(1, "shield", 0),
                        new Character("Captain America", 0), 0, new ArrayList<Weapon>(), new ArrayList<Character>());
                s.writeNewUser(newP);


                UserMenuController userMenuController = new UserMenuController();
                userMenuController = loader.getController();
                userMenuController.setPlayer(player);
                userMenuController.setMusic(music);
                userMenuController.setAudioEffect(gameAudio);

                Stage stage = (Stage) createName.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
        }
    }

    public void fromLoadToStart(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to start");
        Stage window;
        window = (Stage) backFromLoad.getScene().getWindow();
        
        //load fxml and set audios
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("StartUpMenu.fxml"));
        Parent root = (Parent) loader.load();
        StartUpMenuController startUpMenuController = new StartUpMenuController();
        startUpMenuController = loader.getController();
        startUpMenuController.setMusic(music);
        startUpMenuController.setAudioEffect(gameAudio);

        window.setScene(new Scene(root));
    }

    public void fromCreatToStart(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to start");
        Stage window;
        window = (Stage) backFromCreate.getScene().getWindow();
        System.out.println("get the window");
        
        //load fxml and set audios
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("StartUpMenu.fxml"));
        Parent root = (Parent) loader.load();
        StartUpMenuController startUpMenuController = new StartUpMenuController();
        startUpMenuController = loader.getController();
        startUpMenuController.setMusic(music);
        startUpMenuController.setAudioEffect(gameAudio);
        
        System.out.println("get the root");
              
        window.setScene(new Scene(root));
    }

    public void setMusic(Audio musicAudio) {
        System.out.println( "effect setted in start");
        music = musicAudio;
    }
     public void settleMute()
    {
        mute.setVisible(false);
        play.setVisible(true);
    }

    public void setAudioEffect(Audio audioEffect) {
        System.out.println( "music setted in start");
        gameAudio = audioEffect;

    }

}
