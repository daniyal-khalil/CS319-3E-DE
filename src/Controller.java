package sample;

import java.util.Observable;
import java.util.Random;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private ImageView hero;

    private Stage gameStage;
    private Parent GameOver;

    @FXML
    private Button createProfile;
    @FXML
    private Button loadProfile;
    @FXML
    private TextField createName;
    @FXML
    private Button nameEntered;
    @FXML
    private TableView<Player> listedUsers;
    @FXML
    public TableColumn<Player, String> users;
    @FXML
    public TableColumn<Player, Integer> scores;
    @FXML
    private Button newGame, store, modes, settings;


    @FXML
    private Button backFromModes, backFromStore;
    @FXML
    private Button continueGame, exitGame, newGamePlay, exitApp;
    @FXML
    private Button pauseButton;


    private ImageView weapon;

    private Pane root;


    private int noOfEnemies = 0;

    private int score;

    private ArrayList<ImageView> enemy;
    private String direction, shootDirection;

    private Image img2;

    private double heroX, heroY;

    private ArrayList<String> enemyDirection;

    private static final double W = 1024, H = 650;

    @FXML
    private ImageView characterView;

    boolean running, goNorth, goSouth, goEast, goWest, shoot;


    public void popSettings(javafx.event.ActionEvent actionEvent) throws Exception
    {
        score = 0;
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

    public void pause(KeyEvent key) throws Exception{
        if ( key.getCode() == KeyCode.B ) {
            Parent root = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));

            Stage window = new Stage();
            System.out.println("Meow");
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();
        }
    }

    public void exittingGame(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) exitGame.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("StartUpMenu.fxml"));

        window.setScene(new Scene(root));
    }
/*
    public void exittingApp(javafx.event.ActionEvent actionEvent) throws Exception {
        Stage window;
        window = (Stage) exitApp.getScene().getWindow();
        window.setOnCloseRequest(e -> handleExit());
    }
*/

    private void handleExit()
    {
        Platform.exit();
        System.exit(0);
    }

    public void goingReplayGame(javafx.event.ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
        Stage window = new Stage();
        System.out.println("Meow");
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    public void goingToPlay(ActionEvent event)throws Exception{
//        System.out.println("From controller");
//        Stage window;
//        window = (Stage) newGame.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
//
//        window.setScene(new Scene(root));
        shootDirection = "down";
        direction = "down";
        enemyDirection = new ArrayList<String>();
        for (int i = 0; i < 10 ; i++) {
            enemyDirection.add("");
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        weapon = new ImageView(setImage("SHIELD")) ;
        enemy = new ArrayList<ImageView>();
        img2 = setImage("DOWN_R");
        hero = new ImageView(img2);
        enemy.add(new ImageView(setImage("Soldier")));
        enemy.get(0).setX(500);
        ImageView gameBack = new ImageView(setImage("gameBack"));
        gameBack.resize(900,400);
        Group dungeon = new Group(gameBack, hero, weapon);
        root = new Pane();
        root.getChildren().add(dungeon);
        GameOver = FXMLLoader.load(getClass().getResource("GameOver.fxml"));


        weapon.setVisible(false);
        moveHeroTo(W / 2, H / 2);

        Scene scene = new Scene(root, W, H, Color.FORESTGREEN);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:  {
                        if (!goNorth)
                            hero.setImage(setImage("UP"));
                        direction = "up";
                        goNorth = true;
                    } break;
                    case DOWN:  {

                        if (!goSouth)
                            hero.setImage(setImage("DOWN"));
                        direction = "down";
                        goSouth = true; break;

                    }
                    case LEFT:  {
                        if (!goWest)
                            hero.setImage(setImage("LEFT"));
                        direction = "left";
                        goWest  = true;
                    } break;
                    case RIGHT: {
                        if (!goEast)
                            hero.setImage(setImage("RIGHT"));
                        direction = "right";
                        goEast  = true;
                    } break;
//                    case SHIFT: running = true; break;
                    case Z: {
                        shoot = true;
                        shootDirection = direction;
                        weapon.setX(hero.getLayoutX()+(hero.getBoundsInLocal().getWidth()/2));
                        weapon.setY(hero.getLayoutY()+(hero.getBoundsInLocal().getHeight()/2 ));
                        weapon.relocate(hero.getLayoutX()+(hero.getBoundsInLocal().getWidth()/2),hero.getLayoutY()+(hero.getBoundsInLocal().getHeight()/2 ));
                        weapon.setVisible(true);
                        break;
                    }
                }
            }
        });



        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    {

                        hero.setImage(setImage("UP_R"));
                        goNorth = false;
                    } break;
                    case DOWN:  {
                        hero.setImage(setImage("DOWN_R"));
                        goSouth = false;
                    } break;
                    case LEFT:  {
                        hero.setImage(setImage("LEFT_R"));
                        goWest  = false;
                    } break;
                    case RIGHT: {
                        hero.setImage(setImage("RIGHT_R"));
                        goEast  = false;
                    } break;
//                    case SHIFT: running = false; break;
                }
            }
        });
        gameStage = stage;
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer()  {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                spawnEnemies();
                collision();
                followTheUser();
                if (goNorth) {
                    dy -= 1;
                }
                if (goSouth) {
                    dy += 1;
                }
                if (goEast)  {
                    dx += 1;
                }
                if (goWest)  {
                    dx -= 1;
                }
//                if (running) { dx *= 3; dy *= 3; }
                if (shoot)  {
                    if (shootDirection.equals("up"))
                        weapon.setY(weapon.getY()-9);
                    if (shootDirection.equals("down"))
                        weapon.setY(weapon.getY()+9);
                    if (shootDirection.equals("left"))
                        weapon.setX(weapon.getX()-9);
                    if (shootDirection.equals("right"))
                        weapon.setX(weapon.getX()+9);
                }
                moveHeroBy(dx, dy);

            }
        };
        timer.start();
        if (collision()) {
            stage.setScene(new Scene(GameOver));
        }

    }


    public void onCreateProfile(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to create name");
        Stage window;
        window = (Stage) createProfile.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CreateProfilePopUp.fxml"));

        window.setScene(new Scene(root));
    }

    public void goingToStore(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) store.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));

        window.setScene(new Scene(root));
    }

    public void fromStoreToUserMenu(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromStore.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));

        window.setScene(new Scene(root));
    }

    public void onLoadProfile(javafx.event.ActionEvent actionEvent) throws Exception {
        System.out.println("go to create name");
        Stage window;
        window = (Stage) loadProfile.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LoadProfilePopUp.fxml"));

        window.setScene(new Scene(root));
    }

    public void goBackToUserMenu(ActionEvent event)throws Exception{
        System.out.println("From controller");
        Stage window;
        window = (Stage) backFromModes.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));

        window.setScene(new Scene(root));
    }

    public void enterName(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
        String username = createName.getText();

        Saver txtFile = new Saver("src\\sample\\text.txt");
        int id = (int)(Math.random()  * 10000);
        txtFile.writeNewUser(new Player(username, 0, new Weapon(1, "shield", 10),
                                  new Character("Captain", 100), 0, new ArrayList<Weapon>(), new ArrayList<Character>()));
        //System.out.print(username);
        Stage stage = (Stage) createName.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


//    public void onButtonDown(KeyEvent event) throws Exception {
//        enemyDirection = new ArrayList<String>();
//        for (int i = 0; i < 10 ; i++) {
//            enemyDirection.add("");
//        }
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        weapon = new ImageView(setImage("SHIELD")) ;
//        enemy = new ArrayList<ImageView>();
//        img2 = setImage("DOWN_R");
//        hero = new ImageView(img2);
//        enemy.add(new ImageView(setImage("Soldier")));
//        enemy.get(0).setX(500);
//        ImageView gameBack = new ImageView(setImage("gameBack"));
//        Group dungeon = new Group(gameBack, hero, weapon);
//        root = new Pane();
//        root.getChildren().add(dungeon);
//
//        root.setPrefSize(1024, 650);
//        weapon.setVisible(false);
//        moveHeroTo(W / 2, H / 2);
//
//        Scene scene = new Scene(root, W, H, Color.FORESTGREEN);
//
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case UP:  {
//                        if (!goNorth)
//                            hero.setImage(setImage("UP"));
//                        direction = "up";
//                        goNorth = true;
//                    } break;
//                    case DOWN:  {
//
//                        if (!goSouth)
//                            hero.setImage(setImage("DOWN"));
//                        direction = "down";
//                        goSouth = true; break;
//
//                    }
//                    case LEFT:  {
//                        if (!goWest)
//                            hero.setImage(setImage("LEFT"));
//                        direction = "left";
//                        goWest  = true;
//                    } break;
//                    case RIGHT: {
//                        if (!goEast)
//                          hero.setImage(setImage("RIGHT"));
//                        direction = "right";
//                        goEast  = true;
//                    } break;
////                    case SHIFT: running = true; break;
//                    case Z: {
//                        shoot = true;
//                        shootDirection = direction;
//                        weapon.setX(hero.getLayoutX()+(hero.getBoundsInLocal().getWidth()/2));
//                        weapon.setY(hero.getLayoutY()+(hero.getBoundsInLocal().getHeight()/2 ));
//                        weapon.relocate(hero.getLayoutX()+(hero.getBoundsInLocal().getWidth()/2),hero.getLayoutY()+(hero.getBoundsInLocal().getHeight()/2 ));
//                        weapon.setVisible(true);
//                        break;
//                    }
//                }
//            }
//        });
//
//
//
//        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case UP:    {
//
//                        hero.setImage(setImage("UP_R"));
//                        goNorth = false;
//                    } break;
//                    case DOWN:  {
//                        hero.setImage(setImage("DOWN_R"));
//                        goSouth = false;
//                    } break;
//                    case LEFT:  {
//                        hero.setImage(setImage("LEFT_R"));
//                        goWest  = false;
//                    } break;
//                    case RIGHT: {
//                        hero.setImage(setImage("RIGHT_R"));
//                        goEast  = false;
//                    } break;
////                    case SHIFT: running = false; break;
//                }
//            }
//        });
//
//        stage.setScene(scene);
//        stage.show();
//
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                int dx = 0, dy = 0;
//                spawnEnemies();
//                collision();
//                followTheUser();
//                if (goNorth) {
//                    dy -= 1;
//                }
//                if (goSouth) {
//                    dy += 1;
//                }
//                if (goEast)  {
//                    dx += 1;
//                }
//                if (goWest)  {
//                    dx -= 1;
//                }
////                if (running) { dx *= 3; dy *= 3; }
//                if (shoot)  {
//                    if (shootDirection.equals("up"))
//                        weapon.setY(weapon.getY()-9);
//                    if (shootDirection.equals("down"))
//                        weapon.setY(weapon.getY()+9);
//                    if (shootDirection.equals("left"))
//                        weapon.setX(weapon.getX()-9);
//                    if (shootDirection.equals("right"))
//                        weapon.setX(weapon.getX()+9);
//                }
//                moveHeroBy(dx, dy);
//
//            }
//        };
//        timer.start();
//    }
    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;

        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            hero.relocate(x - cx, y - cy);
            heroX = x - cx;
            heroY = y - cy;
        }
    }

    private Image setImage(String direction) {
        Image img = null;

        if (direction.equals("DOWN")) {
            try {
                img = new Image(new FileInputStream("src/sample/CA_DOWN.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (direction.equals("DOWN_R"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_DOWN_R.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("UP_R"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_UP_R.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("LEFT_R"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_LEFT_R.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("RIGHT_R"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_RIGHT_R.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("RIGHT"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_RIGHT.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("LEFT"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_LEFT.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("UP"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_UP.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("SHIELD"))
            try {
                img = new Image(new FileInputStream("src/sample/CA_SHIELD.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("HY_D"))
            try {
                img = new Image(new FileInputStream("src/sample/HY_D.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("HY_U"))
            try {
                img = new Image(new FileInputStream("src/sample/HY_U.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("HY_R"))
            try {
                img = new Image(new FileInputStream("src/sample/HY_R.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("HY_L"))
            try {
                img = new Image(new FileInputStream("src/sample/HY_L.gif"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (direction.equals("gameBack"))
            try {
                System.out.println("HEREEE");
                img = new Image(new FileInputStream("src/sample/Game_background.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return img;
    }

    public void spawnEnemies() {
        Random random = new Random();
        int rand_x = random.nextInt(950);
        int rand_y = random.nextInt(600);

        int rand = random.nextInt(10000000);
        if (enemy.size() < 10 ) {
            if (enemy.size() < 2) {
                enemy.add(new ImageView(setImage("HY_D")));
                (enemy.get(enemy.size()-1)).setX(rand_x);
                (enemy.get(enemy.size()-1)).setY(rand_y);
                root.getChildren().add(enemy.get(enemy.size()-1));
            }
            if (enemy.size() >= 2) {
                if (rand % 997 == 0) {
                    enemy.add(new ImageView(setImage("HY_D")));

                    (enemy.get(enemy.size()-1)).setX(rand_x);
                    (enemy.get(enemy.size()-1)).setY(rand_y);
                    root.getChildren().add(enemy.get(enemy.size()-1));
                }
            }
            if (enemy.size() > 5) {
                if (rand % 1541 == 0) {
                    enemy.add(new ImageView(setImage("HY_D")));
                    (enemy.get(enemy.size()-1)).setX(rand_x);
                    (enemy.get(enemy.size()-1)).setY(rand_y);
                    root.getChildren().add(enemy.get(enemy.size()-1));
                }
            }
        }
    }

    public void followTheUser() {
       for (int i = 0; i < enemy.size(); i++) {
           if (enemy.get(i).getX() < heroX) {
               enemy.get(i).setX(enemy.get(i).getX()+0.5);
               if (!(enemyDirection.get(i)).equals("right")) {
                   System.out.println("Meow");
                   enemy.get(i).setImage(setImage("HY_R"));
                   enemyDirection.set(i, "right");
               }
           }
           else if (enemy.get(i).getX() > heroX) {
               enemy.get(i).setX(enemy.get(i).getX()-0.5);
               if (!(enemyDirection.get(i)).equals("left")) {
                   enemy.get(i).setImage(setImage("HY_L"));
                   enemyDirection.set(i, "left");
               }
           }
           else if (enemy.get(i).getY() > heroY) {
               enemy.get(i).setY(enemy.get(i).getY() -0.5);
               if (!(enemyDirection.get(i)).equals("up")) {
                   enemy.get(i).setImage(setImage("HY_U"));
                   enemyDirection.set(i, "up");
               }
           }
           else if (enemy.get(i).getY() < heroY) {
               enemy.get(i).setY(enemy.get(i).getY() + 0.5);
               if (!(enemyDirection.get(i)).equals("down")) {
                   enemy.get(i).setImage(setImage("HY_D"));
                   enemyDirection.set(i, "down");
               }
           }
       }

    }

    public boolean collision(){

        for (int i = 0; i < enemy.size(); i++) {
            if ((weapon.getX() <= enemy.get(i).getX() + 70 && weapon.getX() >= enemy.get(i).getX() ) && (weapon.getY() >= enemy.get(i).getY() && (weapon.getY() <= enemy.get(i).getY() + 100)) ) {
                root.getChildren().remove(enemy.get(i));
                enemy.remove(i);
                enemyDirection.set(i, "");
                score++;
                return false;
            }
            if (((heroX <= enemy.get(i).getX() + 10) && heroX >= enemy.get(i).getX() ) && (heroY >= enemy.get(i).getY() && (heroY <= enemy.get(i).getY() + 10))) {
                System.out.println("GameOVER");
                return true;
            }
        }
        return false;
    }


}
