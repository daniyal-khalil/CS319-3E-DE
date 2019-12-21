package sample;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameController {
    
    private Audio gameAudio;
    private Audio music;
    
    @FXML
    public Pane gamePanel;
    @FXML
    private ImageView hero;

    private Stage gameStage;
    private Player player;

    private ArrayList<Human> humans;



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

    private  Group dungeon;

    private Pane root;

    @FXML
    private Parent gamePane;


    private int noOfEnemies = 0;

    private int score;

    private AnimationTimer timer;

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

    public void setPlayer(Player player) {
        this.player = player;
        System.out.println(this.player.getCurrentWeapon().getName());
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




//    public void goingToPlay(ActionEvent event) throws  Exception {
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        hero = new ImageView(setImage("CA_DOWN_R.png"));
//
//        enemyDirection = new ArrayList<String>();
//        for (int i = 0; i < 10 ; i++) {
//            enemyDirection.add("");
//        }
//        enemy = new ArrayList<ImageView>();
//        enemy.add(new ImageView(setImage("HY_R.gif")));
//        enemy.get(0).relocate(-200, 400);
//        System.out.println(enemy.get(0).getLayoutX());
//
//
//        Rectangle background = new Rectangle(2000, 500, new LinearGradient(0, 0, 100, 0, false, CycleMethod.REPEAT, new Stop(0, Color.WHITE), new Stop(100, Color.BLUE)));
//        Group group = new Group(background, hero);
//        group.getChildren().addAll(enemy);
//        background.relocate(-500, 0);
//        group.setManaged(false);
//        Pane root = new Pane(group);
//        root.setPrefSize(1000, 500);
//        HBox topBox = new HBox();
//        HBox bottombox = new HBox();
//        AnchorPane.setTopAnchor(root, 60.0);
//        AnchorPane.setBottomAnchor(root, 60.0);
//        AnchorPane.setTopAnchor(bottombox, 600.0);
//        Label bottomLabel = new Label();
//        Label topLabel = new Label();
//        topLabel.setText("TOP");
//        bottomLabel.setText("BOTTOM");
//        bottombox.setPrefSize(900, 60);
//        bottombox.getChildren().add(bottomLabel);
//        bottombox.setVisible(true);
//        topBox.getChildren().add(topLabel);
//
//
//        AnchorPane pane = new AnchorPane(root, topBox, bottombox);
//        hero.relocate(500, 250);
//        pane.setPrefSize(1000, 600);
//
//        Scene scene = new Scene(pane);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case RIGHT: {
//                        heroX += 2;
//                        System.out.println(hero.getLayoutX());
//                        if (hero.getLayoutX() < 550) {
//                            System.out.println(hero.getLayoutX());
//                            hero.setLayoutX(hero.getLayoutX()+2);
//                        }
//                        else {
//                            group.setTranslateX(group.getTranslateX() - 2);
//                            hero.setTranslateX(hero.getTranslateX() + 2);
//                        }
//                    } break;
//                    case LEFT: {
//                        heroX -= 2;
//                        if (hero.getLayoutX() > 450)  {
//
//                            hero.setLayoutX(hero.getLayoutX() - 2);
//                        }
//                        else {
//                            group.setTranslateX(group.getTranslateX() + 2);
//                            System.out.println(enemy.get(0).getLayoutX() + "  " + heroX);
//
//                            hero.setTranslateX(hero.getTranslateX() - 2);
//                        }
//                    } break;
//                    case DOWN: {
//                        if (hero.getLayoutY() < 410)
//                        hero.setLayoutY(hero.getLayoutY() + 2);
//                    } break;
//                    case UP: {
//                        if (hero.getLayoutY() > 0)
//                        hero.setLayoutY(hero.getLayoutY() - 2);
//                    }break;
//                }
//
//            }
//        });
//
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                spawnEnemies();
//            }
//        };
//        timer.start();
//        stage.setScene(scene);
//        stage.show();
//    }

    private boolean over;

    public void goingToPlay(ActionEvent event)throws Exception{
//        System.out.println("From controller");
//        Stage window;
//        window = (Stage) newGame.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
//
//        window.setScene(new Scene(root));
        score = 0;
        shootDirection = "down";
        direction = "down";
        enemyDirection = new ArrayList<String>();
        for (int i = 0; i < 10 ; i++) {
            enemyDirection.add("");
        }

        humans = new ArrayList<Human>();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        weapon = new ImageView(setImage(player.getCurrentWeapon().getName() + ".gif")) ;
        enemy = new ArrayList<ImageView>();
        img2 = setImage(player.getCurrentCharacter().getName() + "_DOWN_R.png");
        hero = new ImageView(img2);
        enemy.add(new ImageView(setImage("HY_R.gif")));
        ImageView gameBack = new ImageView(setImage("gameBack.png"));
        gameBack.resize(900,400);
        Rectangle background = new Rectangle(2000, 500, new LinearGradient(0, 0, 100, 0, false, CycleMethod.REPEAT, new Stop(0, Color.WHITE), new Stop(100, Color.BLUE)));

        dungeon = new Group(background, hero, weapon);
        dungeon.setAutoSizeChildren(true);
        dungeon.setManaged(false);
        dungeon.getChildren().addAll(enemy);
        dungeon.minWidth(2000.0);
        dungeon.minHeight(500.0);
        dungeon.maxHeight(500.0);
        Label topLabel = new Label();
        topLabel.setText("Score: " + score);
        Label bottomLabel = new Label();
        bottomLabel.setText("bottom");
        HBox topBox = new HBox();
        HBox bottomBox = new HBox();
        topBox.getChildren().add(topLabel);
        topBox.setPrefSize(900, 60);
        root = new HBox(dungeon);
        root.autosize();

        root.setMinSize(900, 400);
        root.setMaxSize(900, 400);

        heroX = hero.getLayoutX();
        heroY = hero.getLayoutY();


        gamePane = new AnchorPane(root, topBox, bottomBox);
        AnchorPane.setTopAnchor(root, 60.0);
        AnchorPane.setBottomAnchor(topBox, 550.0);




        weapon.setVisible(false);
        moveHeroTo(W / 2, H / 2);

        Scene scene = new Scene(gamePane, W, H, Color.FORESTGREEN);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:  {
                        if (!goNorth)
                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_UP.gif"));
                        direction = "up";
                        goNorth = true;
                    } break;
                    case DOWN:  {

                        if (!goSouth)
                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_DOWN.gif"));
                        direction = "down";
                        goSouth = true; break;

                    }
                    case LEFT:  {
                        if (!goWest)
                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_LEFT.gif"));
                        direction = "left";
                        goWest  = true;
                    } break;
                    case RIGHT: {
                        if (!goEast)
                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_RIGHT.gif"));
                        direction = "right";
                        goEast  = true;
                    } break;
//                    case SHIFT: running = true; break;
                    case Z: {
                        shoot = true;
                        shootDirection = direction;
                        weapon.setLayoutY(heroY + 30);
                        weapon.setLayoutX(heroX);
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

                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_UP_R.png"));
                        goNorth = false;
                    } break;
                    case DOWN:  {
                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_DOWN_R.png"));
                        goSouth = false;
                    } break;
                    case LEFT:  {
                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_LEFT_R.png"));
                        goWest  = false;
                    } break;
                    case RIGHT: {
                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_RIGHT_R.png"));
                        goEast  = false;
                    } break;
//                    case SHIFT: running = false; break;
                }
            }
        });
        gameStage = stage;
        stage.setScene(scene);
        stage.show();

        timer = new AnimationTimer()  {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                spawnEnemies();
                    if (collision()) {
                        try {
                            gameOver();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                topLabel.setText("Score: " + score);
                followTheUser();
                if (goNorth) {
                    if (heroY > 0) {
                        hero.relocate(hero.getLayoutX(), hero.getLayoutY() - 3);
                        heroY -= 3;
                    }
                }
                if (goSouth) {
                    if (heroY < 409) {
                        hero.relocate(hero.getLayoutX(), hero.getLayoutY() + 3);
                        heroY += 3;
                    }
                }
                if (goEast)  {

                    if (heroX < 1550 && heroX > 500)
                        dungeon.setTranslateX(dungeon.getTranslateX() - 3);
                    if (heroX < 2000) {
                        hero.setTranslateX(hero.getTranslateX() + 3);
                        heroX += 3;
                    }
                    System.out.println("HeroX" + heroX);
                    System.out.println("Enemy" + enemy.get(0).getLayoutX());
                    System.out.println("Weapon" + weapon.getLayoutX());

                }
                if (goWest)  {

                    if (heroX > 0 ) {
                        hero.setTranslateX(hero.getTranslateX() - 3);
                        heroX -= 3;
                    }
                    if (heroX > 450 && heroX < 1500)
                        dungeon.setTranslateX(dungeon.getTranslateX() + 3 );

                }
//                if (running) { dx *= 3; dy *= 3; }
                if (shoot && weapon.isVisible())  {
                    if (shootDirection.equals("up"))
                        weapon.setLayoutY(weapon.getLayoutY()-9);
                    if (shootDirection.equals("down"))
                        weapon.setLayoutY(weapon.getLayoutY()+9);
                    if (shootDirection.equals("left"))
                        weapon.setLayoutX(weapon.getLayoutX()-9);
                    if (shootDirection.equals("right"))
                        weapon.setLayoutX(weapon.getLayoutX()+9);
                }
            //   moveHeroBy(dx, dy);
            }
        };
        timer.start();
    }




    public void gameOver() throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
            Parent root = loader.load();
            GameOverController gameOverController = new GameOverController();
            gameOverController = loader.getController();
            gameOverController.setPlayer(player);
            gameOverController.setScore(score);
            gameOverController.setScoreLabel(score);
            gameStage.setScene(new Scene(root));
    }

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

    private Image setImage(String image) {
        Image img = null;
        String name = "src/sample/" + image;
            try {
                img = new Image(new FileInputStream(name));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return img;
    }

    public void spawnEnemies() {
        Random random = new Random();
        int rand_x = random.nextInt(2000);
        while (rand_x < heroX + 500 && rand_x > heroX - 500) {
            rand_x = random.nextInt(2000);
        }
        int rand_y = random.nextInt(440);

        int rand = random.nextInt(10000000);
        if (enemy.size() < 2 ) {
            if (enemy.size() < 2) {
                System.out.println("Sup");
                enemy.add(new ImageView(setImage("HY_R.gif")));
                (enemy.get(enemy.size()-1)).relocate(rand_x, rand_y);
                dungeon.getChildren().add(enemy.get(enemy.size()-1));
            }
//            if (enemy.size() >= 2) {
//                if (rand % 1000001 == 0) {
//                    enemy.add(new ImageView(setImage("HY_D.gif")));
//
//                    (enemy.get(enemy.size()-1)).setX(rand_x);
//                    (enemy.get(enemy.size()-1)).setY(rand_y);
//                    root.getChildren().add(enemy.get(enemy.size()-1));
//                }
//            }
//            if (enemy.size() > 5) {
//                if (rand % 10000001 == 0) {
//                    enemy.add(new ImageView(setImage("HY_D.gif")));
//                    (enemy.get(enemy.size()-1)).setX(rand_x);
//                    (enemy.get(enemy.size()-1)).setY(rand_y);
//                    root.getChildren().add(enemy.get(enemy.size()-1));
//                }
//            }
        }
    }

    public void followTheUser() {
       for (int i = 0; i < enemy.size(); i++) {
           if (enemy.get(i).getLayoutX() < heroX) {
               enemy.get(i).relocate(enemy.get(i).getLayoutX()+0.5, enemy.get(i).getLayoutY());
               if (!(enemyDirection.get(i)).equals("right")) {
                    System.out.println("Enemy" + i + "X: " + enemy.get(i).getLayoutX());
                   enemy.get(i).setImage(setImage("HY_R.gif"));
                   enemyDirection.set(i, "right");
               }
           }
           else if (enemy.get(i).getLayoutX() > heroX) {
               enemy.get(i).relocate(enemy.get(i).getLayoutX()-0.5, enemy.get(i).getLayoutY());
               if (!(enemyDirection.get(i)).equals("left")) {
                   enemy.get(i).setImage(setImage("HY_L.gif"));
                   enemyDirection.set(i, "left");
               }
           }
           if (enemy.get(i).getLayoutY() > heroY) {
               enemy.get(i).relocate(enemy.get(i).getLayoutX(),enemy.get(i).getLayoutY() - 0.5);

//               if (!(enemyDirection.get(i)).equals("up")) {
//                   enemy.get(i).setImage(setImage("HY_U"));
//                   enemyDirection.set(i, "up");
//               }
           }
           else if (enemy.get(i).getLayoutY() < heroY) {
               enemy.get(i).relocate(enemy.get(i).getLayoutX(),enemy.get(i).getLayoutY() + 0.5);
//               if (!(enemyDirection.get(i)).equals("down")) {
//                   enemy.get(i).setImage(setImage("HY_D"));
//                   enemyDirection.set(i, "down");
//               }
           }
       }

    }

    public boolean collision(){

        for (int i = 0; i < enemy.size(); i++) {
            if ((weapon.getLayoutX() <= enemy.get(i).getLayoutX() + 70 && weapon.getLayoutX() >= enemy.get(i).getLayoutX() ) && (weapon.getLayoutY() >= enemy.get(i).getLayoutY() && (weapon.getLayoutY() <= enemy.get(i).getLayoutY() + 100)) ) {
                if (weapon.isVisible()) {
                    dungeon.getChildren().remove(enemy.get(i));
                    enemy.remove(i);
                    System.out.println("YAHAN");
                    enemyDirection.set(i, "");
                    score++;
                    weapon.setVisible(false);
                    return false;
                }
            }
            if (((heroX <= enemy.get(i).getLayoutX() + 10) && heroX >= enemy.get(i).getLayoutX() ) && (heroY >= enemy.get(i).getLayoutY() && (heroY <= enemy.get(i).getLayoutY() + 10))) {
                System.out.println("NYAH");
                heroX= -1000;
                return true;
            }
        }
        return false;
    }

    public void spawnHumans() {
        for (int i = 0; )
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
