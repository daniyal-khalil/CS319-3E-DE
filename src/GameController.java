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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameController {

    @FXML
    private ImageView hero;

    private Stage gameStage;
    private Parent GameOver;


    @FXML
    private TableView<Player> listedUsers;
    @FXML
    public TableColumn<Player, String> users;
    @FXML
    public TableColumn<Player, Integer> scores;

    @FXML
    private Button continueGame, exitGame, newGamePlay, exitApp;
    @FXML
    private Button pauseButton;


    private ImageView weapon;

    private Pane root;

    private AnchorPane gamePane;


    private int noOfEnemies = 0;

    private int score;

    private ArrayList<ImageView> enemy;
    private String direction, shootDirection;

    private Image img2;

    private double heroX, heroY;

    private ArrayList<String> enemyDirection;

    private static final double W = 1000, H = 650;


    @FXML
    private ImageView characterView;



    boolean running, goNorth, goSouth, goEast, goWest, shoot;






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
        Pane root = FXMLLoader.load(getClass().getResource("StartUpMenu.fxml"));

        window.setScene(new Scene(root));
    }

    public void exittingApp(javafx.event.ActionEvent actionEvent) throws Exception {
        Stage window;
        window = (Stage) exitApp.getScene().getWindow();
        window.setOnCloseRequest(e -> handleExit());
    }


    private void handleExit()
    {
        Platform.exit();
        System.exit(0);
    }




    public void goingToPlay(ActionEvent event) throws  Exception {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        hero = new ImageView(setImage("DOWN_R"));
        Rectangle background = new Rectangle(2000, 500, new LinearGradient(0, 0, 100, 0, false, CycleMethod.REPEAT, new Stop(0, Color.WHITE), new Stop(100, Color.BLUE)));
        Group group = new Group(background, hero);
        group.setManaged(false);
        Pane root = new Pane(group);
        root.setPrefSize(900, 500);
        HBox topBox = new HBox();
        HBox bottombox = new HBox();
        AnchorPane.setTopAnchor(root, 60.0);
        AnchorPane.setBottomAnchor(root, 60.0);
        AnchorPane.setTopAnchor(bottombox, 540.0);
        Label bottomLabel = new Label();
        Label topLabel = new Label();
        topLabel.setText("TOP");
        bottomLabel.setText("BOTTOM");
        bottombox.setPrefSize(900, 60);
        bottombox.getChildren().add(bottomLabel);
        bottombox.setVisible(true);
        topBox.getChildren().add(topLabel);


        AnchorPane pane = new AnchorPane(root, topBox, bottombox);
        hero.relocate(450, 250);
        pane.setPrefSize(900, 600);

        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT: {
                        System.out.println(hero.getLayoutX());
                        if (hero.getLayoutX() < 550) {
                            System.out.println(hero.getScaleX());
                            hero.setX(hero.getX()+1);
                            hero.setLayoutX(hero.getLayoutX() + 2);
                        }
                        else {
                            hero.setX(hero.getX()+1);
                            group.setTranslateX(group.getTranslateX() - 2);
                            hero.setTranslateX(hero.getTranslateX() + 2);
                        }
                    } break;
                    case LEFT: {
                        if (hero.getLayoutX() > 350)  {
                            System.out.println("Here");
                            hero.setLayoutX(hero.getLayoutX() - 2);
                        }
                        else {
                            group.setTranslateX(group.getTranslateX() + 2);
                            hero.setTranslateX(hero.getTranslateX() - 2);
                        }
                    } break;
                    case DOWN: {
                        hero.setLayoutY(hero.getLayoutY() + 2);
                    } break;
                    case UP: {
                        hero.setLayoutY(hero.getLayoutY() - 2);
                    }break;
                }

            }
        });

        stage.setScene(scene);
        stage.show();
    }

//    public void goingToPlay(ActionEvent event)throws Exception{
////        System.out.println("From controller");
////        Stage window;
////        window = (Stage) newGame.getScene().getWindow();
////        Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
////
////        window.setScene(new Scene(root));
//        shootDirection = "down";
//        direction = "down";
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
//        gameBack.resize(900,400);
//        Group dungeon = new Group(gameBack, hero, weapon);
//        root = new Pane();
//        root.getChildren().add(dungeon);
//        GameOver = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
//
//
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
//                            hero.setImage(setImage("RIGHT"));
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
//        gameStage = stage;
//        stage.setScene(scene);
//        stage.show();
//
//        AnimationTimer timer = new AnimationTimer()  {
//            @Override
//            public void handle(long now) {
//                int dx = 0, dy = 0;
//                spawnEnemies();
//                collision();
//                followTheUser();
//                if (goNorth) {
//                    dy -= 3;
//                }
//                if (goSouth) {
//                    dy += 3;
//                }
//                if (goEast)  {
//                    dx += 3;
//                }
//                if (goWest)  {
//                    dx -= 3;
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
//        if (collision()) {
//            stage.setScene(new Scene(GameOver));
//        }
//
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
                img = new Image(new FileInputStream("src/sample/CA_SHIELD.gif"));
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
                if (rand % 1000001 == 0) {
                    enemy.add(new ImageView(setImage("HY_D")));

                    (enemy.get(enemy.size()-1)).setX(rand_x);
                    (enemy.get(enemy.size()-1)).setY(rand_y);
                    root.getChildren().add(enemy.get(enemy.size()-1));
                }
            }
            if (enemy.size() > 5) {
                if (rand % 10000001 == 0) {
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
               enemy.get(i).setX(enemy.get(i).getX()+1);
               if (!(enemyDirection.get(i)).equals("right")) {
                    System.out.println("Enemy" + i + "X: " + enemy.get(i).getX());
                   enemy.get(i).setImage(setImage("HY_R"));
                   enemyDirection.set(i, "right");
               }
           }
           else if (enemy.get(i).getX() > heroX) {
               enemy.get(i).setX(enemy.get(i).getX()-1);
               if (!(enemyDirection.get(i)).equals("left")) {
                   enemy.get(i).setImage(setImage("HY_L"));
                   enemyDirection.set(i, "left");
               }
           }
           if (enemy.get(i).getY() > heroY) {
               enemy.get(i).setY(enemy.get(i).getY() -1);
//               if (!(enemyDirection.get(i)).equals("up")) {
//                   enemy.get(i).setImage(setImage("HY_U"));
//                   enemyDirection.set(i, "up");
//               }
           }
           else if (enemy.get(i).getY() < heroY) {
               enemy.get(i).setY(enemy.get(i).getY() + 1);
//               if (!(enemyDirection.get(i)).equals("down")) {
//                   enemy.get(i).setImage(setImage("HY_D"));
//                   enemyDirection.set(i, "down");
//               }
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
                root.getChildren().removeAll();
                for (int j = 0; j < enemy.size(); j++)
                {
                    enemy.remove(j);
                    enemyDirection.set(j,"");
                }
                return true;
            }
        }
        return false;
    }


    public void gameOver() {
        try {
            GameOver = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameStage.setScene(new Scene(GameOver));

    }


}
