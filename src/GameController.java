package sample;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameController {

    @FXML
    private Button unpause;
    @FXML
    private ImageView hero, heroMini;


    private Audio music;
    private Audio gameAudio;

    private ArrayList<Weapon> weapons;
    private ArrayList<String> shootDirection;

    private ArrayList<Weapon> enemyWeapons;
    private Stage gameStage;
    private Player player;

    private ArrayList<Human> humans;

    private ArrayList<Mob> mobs;



    @FXML
    private Button continueGame, exitGame, newGamePlay, exitApp;
    @FXML
    private Button pauseButton;


    private  Group dungeon, dungeonMini;

    private Pane root, rootMini;

    @FXML
    private Parent gamePane;



    private int score;

    private AnimationTimer timer;

    private ArrayList<Mob> enemy, enemyMini;
    private String direction;

    private int lives;

    private Scene pauseScene;

    private Image img2;

    private double heroX, heroY, heroMiniX, heroMiniY;

    private ArrayList<String> enemyDirection;

    private static final double W = 1000, H = 650;




    boolean goNorth, goSouth, goEast, goWest;


    public void pause(KeyEvent key) throws Exception{
        if ( key.getCode() == KeyCode.ENTER ) {
            timer.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Pause.fxml"));
            Parent root = loader.load();
            GameController gameController = new GameController();
            Stage window = new Stage();
            System.out.println("Meow");
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setResizable(false);
            window.showAndWait();
            timer.start();
        }
    }


    public void setPlayer(Player player) {
        this.player = player;
        System.out.println(this.player.getCurrentWeapon().getName());
    }


    private boolean over;

    public void resumeGame(ActionEvent event) throws Exception {
       Stage window =  (Stage) unpause.getScene().getWindow();
       window.close();



    }

    public void goingToPlay(ActionEvent event)throws Exception{
//        System.out.println("From controller");
//        Stage window;
//        window = (Stage) newGame.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
//
//        window.setScene(new Scene(root));
        lives = player.getCurrentCharacter().getLives();
        score = 0;
        shootDirection = new ArrayList<String>();
        enemyWeapons = new ArrayList<Weapon>();
        enemyWeaponsDirection = new ArrayList<String>();

        direction = "down";
        enemyDirection = new ArrayList<String>();
        for (int i = 0; i < 10 ; i++) {
            enemyDirection.add("");
        }

        humans = new ArrayList<Human>();

        loadVillians();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        weapons = new ArrayList<Weapon>();



        enemy = new ArrayList<Mob>();
        enemyMini = new ArrayList<Mob>();
        img2 = setImage(player.getCurrentCharacter().getName() + "_D_R.png");
        hero = new ImageView(img2);
        heroMini = new ImageView(img2);
        heroMini.setFitHeight(10);
        heroMini.setFitWidth(10);

        ImageView backGround = new ImageView(setImage("Backround.png"));
        backGround.resize(900,400);

        ImageView backGroundMini = new ImageView(setImage("Backround.png"));

        backGroundMini.setFitWidth(200);
        backGroundMini.setFitHeight(50);

        dungeonMini = new Group(backGroundMini, heroMini);
        dungeonMini.setManaged(false);
        dungeonMini.minWidth(200.0);
        dungeonMini.minHeight(50.0);
        dungeonMini.maxHeight(50.0);

        dungeon = new Group(backGround, hero);
        dungeon.setAutoSizeChildren(true);
        dungeon.setManaged(false);

        dungeon.minWidth(2000.0);
        dungeon.minHeight(500.0);
        dungeon.maxHeight(500.0);


        Text livesText = new Text("Lives: " + lives);
        Text scoreText = new Text("Score: " + score);
        HBox topBox = new HBox();
        HBox bottomBox = new HBox();
        Text highScore = new Text();
        highScore.setText("HighScore: " + player.getHighScore() + "");
        highScore.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));
        bottomBox.getChildren().add(livesText);
        topBox.getChildren().add(scoreText);
        topBox.setPrefSize(900, 60);
        root = new HBox(dungeon);
        livesText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        root.setMinSize(900, 400);
        root.setMaxSize(900, 400);

        rootMini = new HBox(dungeonMini);

        rootMini.setMinSize(90,40);
        rootMini.setMaxSize(90,40);

        heroX = hero.getLayoutX();
        heroY = hero.getLayoutY();
        heroMiniX = heroMini.getLayoutX();
        heroMiniY = heroMini.getLayoutY();


        gamePane = new AnchorPane(root, topBox, bottomBox, highScore, rootMini);
        AnchorPane.setBottomAnchor(rootMini, 10.0);
        AnchorPane.setLeftAnchor(rootMini, 400.0);
        AnchorPane.setBottomAnchor(highScore, 610.0);
        AnchorPane.setLeftAnchor(highScore, 400.0);
        AnchorPane.setTopAnchor(root, 60.0);
        AnchorPane.setBottomAnchor(topBox, 570.0);
        AnchorPane.setTopAnchor(bottomBox, 580.0);



        moveHeroTo(W/2, H/2);

        heroMini.setLayoutY(26);
        heroMini.setLayoutX(45);

        Scene scene = new Scene(gamePane, W, H, Color.FORESTGREEN);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event){
                switch (event.getCode()) {
                    case UP:  {
                        if (!goNorth) {
                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_U.gif"));
                            heroMini.setImage(hero.getImage());
                            heroMini.setFitHeight(10);
                            heroMini.setFitWidth(10);
                        }
                        direction = "up";
                        goNorth = true;
                    } break;
                    case DOWN:  {

                        if (!goSouth) {
                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_D.gif"));
                            heroMini.setImage(hero.getImage());
                            heroMini.setFitHeight(10);
                            heroMini.setFitWidth(10);
                        }
                        direction = "down";
                        goSouth = true; break;

                    }
                    case LEFT:  {
                        if (!goWest) {

                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_L.gif"));
                            heroMini.setImage(hero.getImage());
                            heroMini.setFitHeight(10);
                            heroMini.setFitWidth(10);
                        }
                        direction = "left";
                        goWest  = true;
                    } break;
                    case RIGHT: {
                        if (!goEast) {
                            hero.setImage(setImage(player.getCurrentCharacter().getName() + "_R.gif"));
                            heroMini.setImage(hero.getImage());
                            heroMini.setFitHeight(10);
                            heroMini.setFitWidth(10);
                        }
                        direction = "right";
                        goEast  = true;
                    } break;
//                    case SHIFT: running = true; break;
                    case Z: {

                        if (weapons.size() < 6) {
                            System.out.println("Here");
                            Weapon temp = new Weapon();
                            temp.setImage(setImage(player.getCurrentWeapon().getName() + ".gif"));
                            shootDirection.add(direction);
                            temp.setLayoutY(heroY + 30);
                            temp.setLayoutX(heroX);
                            temp.setVisible(true);
                            weapons.add(temp);
                            dungeon.getChildren().add(weapons.get(weapons.size() - 1));
//                        weapon.setLayoutY(heroY + 30);
//                        weapon.setLayoutX(heroX);
//                        weapon.setVisible(true);
                        }
                        break;
                    }

                    case ENTER: {
                        try {
                            pause(event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_U_R.png"));
                        heroMini.setImage(hero.getImage());
                        heroMini.setFitHeight(10);
                        heroMini.setFitWidth(10);
                        goNorth = false;
                    } break;
                    case DOWN:  {
                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_D_R.png"));
                        heroMini.setImage(hero.getImage());
                        heroMini.setFitHeight(10);
                        heroMini.setFitWidth(10);
                        goSouth = false;
                    } break;
                    case LEFT:  {
                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_L_R.png"));
                        heroMini.setImage(hero.getImage());
                        heroMini.setFitHeight(10);
                        heroMini.setFitWidth(10);
                        goWest  = false;
                    } break;
                    case RIGHT: {
                        hero.setImage(setImage(player.getCurrentCharacter().getName() + "_R_R.png"));
                        heroMini.setImage(hero.getImage());
                        heroMini.setFitHeight(10);
                        heroMini.setFitWidth(10);
                        goEast  = false;
                    } break;
//                    case SHIFT: running = false; break;
                }
            }
        });
        gameStage = stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

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
                scoreText.setText("Score: " + score);
                livesText.setText("Lives: " + lives);
                followTheUser();
                spawnHumans();
                enemiesShoot();
                enemyWeaponsDirection();
                enemyWeaponsMove();
                if (goNorth) {
                    if (heroY > 0) {
                        hero.relocate(hero.getLayoutX(), hero.getLayoutY() - 3);
                        heroMini.relocate(heroMini.getLayoutX(), heroMini.getLayoutY() - .3);
                        heroY -= 3;
                        heroMiniY -= .3;
                    }
                }
                if (goSouth) {
                    if (heroY < 409) {
                        hero.relocate(hero.getLayoutX(), hero.getLayoutY() + 3);
                        heroMini.relocate(heroMini.getLayoutX(), heroMini.getLayoutY() + .3);
                        heroY += 3;
                        heroMiniY -= .3;
                    }
                }
                if (goEast)  {

                    if (heroX < 1550 && heroX > 500)
                        dungeon.setTranslateX(dungeon.getTranslateX() - 3);
                    if (heroX < 2000) {
                        hero.setTranslateX(hero.getTranslateX() + 3);
                        heroX += 3;
                        heroMini.setTranslateX(heroMini.getTranslateX() + .3);
                        heroMiniX += .3;
                    }
                    System.out.println("HeroX" + heroX);

                }
                if (goWest)  {

                    if (heroX > 0 ) {
                        hero.setTranslateX(hero.getTranslateX() - 3);
                        heroMini.setTranslateX(heroMini.getTranslateX() - .3);
                        heroX -= 3;
                        heroMiniX -= .3;
                    }
                    if (heroX > 450 && heroX < 1500)
                        dungeon.setTranslateX(dungeon.getTranslateX() + 3 );
                    System.out.println("HeroX" + heroX);
                }
//                if (running) { dx *= 3; dy *= 3; }
                for (int i = 0; i < weapons.size(); i++) {
//                    if (weapons.get(i).isVisible() && shoot) {
                        if (shootDirection.get(i).equals("up"))
                            weapons.get(i).setLayoutY(weapons.get(i).getLayoutY() - 9);
                        if (shootDirection.get(i).equals("down"))
                            weapons.get(i).setLayoutY(weapons.get(i).getLayoutY() + 9);
                        if (shootDirection.get(i).equals("left"))
                            weapons.get(i).setLayoutX(weapons.get(i).getLayoutX() - 9);
                        if (shootDirection.get(i).equals("right"))
                            weapons.get(i).setLayoutX(weapons.get(i).getLayoutX() + 9);
//                }
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
        String name = "src/sample/gameSprites/" + image;
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

        Mob tempMob = new Mob();
        if (score < 50) {
            tempMob = mobs.get(0);
        }
        if (score >= 50 && score <= 100) {
            tempMob = mobs.get(1);
        }
        if (score > 100 && score < 200) {
            tempMob = mobs.get(2);
        }

        int rand = random.nextInt(10000000);
        if (enemy.size() < 10 ) {
            if (enemy.size() < 3) {
                System.out.println(rand_x + "    " + rand_y);
                System.out.println("Sup");
                Mob temp = new Mob();
                Mob tempMini = new Mob();
                temp.setImage(tempMob.getImage());
                temp.setWeapon(tempMob.getWeapon());
                temp.relocate(rand_x, rand_y);
                temp.setName(tempMob.getName());
                temp.setLayoutY(rand_y);
                temp.setLayoutX(rand_x);
                temp.setWeapon(tempMob.getWeapon());
                tempMini.setImage(tempMob.getImage());
                tempMini.setWeapon(tempMob.getWeapon());
                tempMini.relocate(rand_x, rand_y);
                tempMini.setName(tempMob.getName());
                tempMini.setLayoutY(rand_y);
                tempMini.setLayoutX(rand_x);
                tempMini.setWeapon(tempMob.getWeapon());
                enemyMini.add(tempMini);
                dungeonMini.getChildren().add(enemyMini.get(enemyMini.size() - 1));
                enemy.add(temp);
                dungeon.getChildren().add(enemy.get(enemy.size() - 1));
                enemyMini.get(enemyMini.size()-1).setFitWidth(10);
                enemyMini.get(enemyMini.size()-1).setFitHeight(10);
                (enemyMini.get(enemyMini.size() - 1)).relocate(rand_x/10, rand_y/10);

                return;
            }
            if (enemy.size() <= 5 && enemy.size() >= 3) {
                if (rand % 2 == 0) {
                    System.out.println(rand_x + "    " + rand_y);
                    System.out.println("Sup");
                    Mob temp = new Mob();
                    Mob tempMini = new Mob();
                    temp.setImage(tempMob.getImage());
                    temp.setWeapon(tempMob.getWeapon());
                    temp.relocate(rand_x, rand_y);
                    temp.setName(tempMob.getName());
                    temp.setLayoutY(rand_y);
                    temp.setLayoutX(rand_x);
                    temp.setWeapon(tempMob.getWeapon());
                    tempMini.setImage(tempMob.getImage());
                    tempMini.setWeapon(tempMob.getWeapon());
                    tempMini.relocate(rand_x, rand_y);
                    tempMini.setName(tempMob.getName());
                    tempMini.setLayoutY(rand_y);
                    tempMini.setLayoutX(rand_x);
                    tempMini.setWeapon(tempMob.getWeapon());
                    enemyMini.add(tempMini);
                    dungeonMini.getChildren().add(enemyMini.get(enemyMini.size() - 1));
                    enemy.add(temp);
                    dungeon.getChildren().add(enemy.get(enemy.size() - 1));
                    enemyMini.get(enemyMini.size()-1).setFitWidth(10);
                    enemyMini.get(enemyMini.size()-1).setFitHeight(10);
                    (enemyMini.get(enemyMini.size() - 1)).relocate(rand_x/10, rand_y/10);
                }
            }
            if (enemy.size() > 5) {
                if (rand % 1000 == 0) {
                    System.out.println(rand_x + "    " + rand_y);
                    System.out.println("Sup");
                    Mob temp = new Mob();
                    Mob tempMini = new Mob();
                    temp.setImage(tempMob.getImage());
                    temp.setWeapon(tempMob.getWeapon());
                    temp.relocate(rand_x, rand_y);
                    temp.setName(tempMob.getName());
                    temp.setLayoutY(rand_y);
                    temp.setLayoutX(rand_x);
                    temp.setWeapon(tempMob.getWeapon());
                    tempMini.setImage(tempMob.getImage());
                    tempMini.setWeapon(tempMob.getWeapon());
                    tempMini.relocate(rand_x, rand_y);
                    tempMini.setName(tempMob.getName());
                    tempMini.setLayoutY(rand_y);
                    tempMini.setLayoutX(rand_x);
                    tempMini.setWeapon(tempMob.getWeapon());
                    enemyMini.add(tempMini);
                    dungeonMini.getChildren().add(enemyMini.get(enemyMini.size() - 1));
                    enemy.add(temp);
                    dungeon.getChildren().add(enemy.get(enemy.size() - 1));
                    enemyMini.get(enemyMini.size()-1).setFitWidth(10);
                    enemyMini.get(enemyMini.size()-1).setFitHeight(10);
                    (enemyMini.get(enemyMini.size() - 1)).relocate(rand_x/10, rand_y/10);

                }
            }

        }
    }

    public void followTheUser() {
       for (int i = 0; i < enemy.size(); i++) {
           if (enemy.get(i).getLayoutX() < heroX) {
               enemy.get(i).setLayoutX(enemy.get(i).getLayoutX()+0.5);
               enemyMini.get(i).relocate(enemyMini.get(i).getLayoutX() + 0.05, enemyMini.get(i).getLayoutY());
               if (!(enemyDirection.get(i)).equals("right")) {
                   enemy.get(i).setImage(setImage(enemy.get(i).getName()+ "_R.gif"));
                   enemyMini.get(i).setImage(setImage(enemy.get(i).getName() + "_R.gif"));
                   enemyMini.get(i).setFitHeight(10);
                   enemyMini.get(i).setFitWidth(10);
                   enemyDirection.set(i, "right");
               }
           }
           else if (enemy.get(i).getLayoutX() > heroX) {
               enemy.get(i).setLayoutX(enemy.get(i).getLayoutX()-0.5);
               enemyMini.get(i).relocate(enemyMini.get(i).getLayoutX() - 0.05, enemyMini.get(i).getLayoutY());
               System.out.println("Enemy" + i + "X: " + enemy.get(i).getLayoutX());
               if (!(enemyDirection.get(i)).equals("left")) {
                   enemyMini.get(i).setImage(setImage(enemy.get(i).getName() + "_L.gif"));
                   enemyMini.get(i).setFitHeight(10);
                   enemyMini.get(i).setFitWidth(10);
                   enemy.get(i).setImage(setImage(enemy.get(i).getName()+ "_L.gif"));
                   enemyDirection.set(i, "left");
               }
           }
           if (enemy.get(i).getLayoutY() > heroY) {
               enemy.get(i).setLayoutY(enemy.get(i).getLayoutY() - 0.5);
               enemyMini.get(i).relocate(enemyMini.get(i).getLayoutX() - 0.05, enemyMini.get(i).getLayoutY());

//               if (!(enemyDirection.get(i)).equals("up")) {
//                   enemy.get(i).setImage(setImage("HY_U"));
//                   enemyDirection.set(i, "up");
//               }
           }
           else if (enemy.get(i).getLayoutY() < heroY) {
               enemy.get(i).setLayoutY(enemy.get(i).getLayoutY() + 0.5);
               enemyMini.get(i).relocate(enemyMini.get(i).getLayoutX() + 0.05, enemyMini.get(i).getLayoutY());
//               if (!(enemyDirection.get(i)).equals("down")) {
//                   enemy.get(i).setImage(setImage("HY_D"));
//                   enemyDirection.set(i, "down");
//               }
           }
       }

    }

    public boolean collision(){

        for (int i = 0; i < enemy.size(); i++) {
            for (int j = 0; j < weapons.size() ; j++) {
                if ((weapons.get(j).getLayoutX() <= enemy.get(i).getLayoutX() + 70 && weapons.get(j).getLayoutX() >= enemy.get(i).getLayoutX()) && (weapons.get(j).getLayoutY() >= enemy.get(i).getLayoutY() && (weapons.get(j).getLayoutY() <= enemy.get(i).getLayoutY() + 100))) {
//                    if (weapons.get(j).isVisible()) {
                        dungeon.getChildren().remove(enemy.get(i));
                        enemy.remove(i);
                        System.out.println("YAHAN");
                        enemyDirection.set(i, "");
                        shootDirection.remove(j);
                        dungeonMini.getChildren().remove(enemyMini.get(i));
                        enemyMini.remove(i);
                        score++;
                        dungeon.getChildren().remove(weapons.get(j));
                        weapons.remove(j);
                        return false;
//                    }
                }
                if ((weapons.get(j).getLayoutX() < 0 || weapons.get(j).getLayoutX() > 2000 || weapons.get(j).getLayoutY() < 0 || weapons.get(j).getLayoutY() > 480)) {
                    dungeon.getChildren().remove(weapons.get(j));
                    weapons.remove(j);
                    shootDirection.remove(j);
                }
            }
            if (((heroX <= enemy.get(i).getLayoutX() + 10) && heroX >= enemy.get(i).getLayoutX() ) && (heroY >= enemy.get(i).getLayoutY() && (heroY <= enemy.get(i).getLayoutY() + 10))) {
                System.out.println("NYAH");
                dungeon.getChildren().remove(enemy.get(i));
                enemy.remove(i);
                dungeonMini.getChildren().remove(enemyMini.get(i));
                enemyMini.remove(i);
                lives--;
                if (lives == 0) {
                    dungeon.getChildren().removeAll(enemy);
                    enemy.removeAll(enemy);
                    heroX = -1000;
                    timer.stop();
                    return true;
                }
            }
        }

        for (int i = 0; i < enemyWeapons.size(); i++) {
            if ((heroX <= enemyWeapons.get(i).getLayoutX() + 10 && heroX >= enemyWeapons.get(i).getLayoutX() - 10) && (heroY >=  enemyWeapons.get(i).getLayoutY() && heroY<= enemyWeapons.get(i).getLayoutY() + 10)) {
                System.out.println("NYAH");
                dungeon.getChildren().remove(enemyWeapons.get(i));
                enemyWeapons.remove(i);
                enemyWeaponsDirection.remove(i);
                lives--;
                if (lives == 0) {
                    dungeon.getChildren().removeAll(enemy);
                    enemy.removeAll(enemy);
                    heroX = -1000;
                    timer.stop();
                    return true;
                }
            }
            for (int j = 0;  j < humans.size(); j++) {
                if ((humans.get(j).getLayoutX() <= enemyWeapons.get(i).getLayoutX()  && humans.get(j).getLayoutX() >= enemyWeapons.get(i).getLayoutX() - 10) && (humans.get(j).getLayoutY() >=  enemyWeapons.get(i).getLayoutY() && humans.get(j).getLayoutY()<= enemyWeapons.get(i).getLayoutY() + 10)) {
                    dungeon.getChildren().remove(humans.get(j));
                    dungeon.getChildren().remove(enemyWeapons.get(i));
                    enemyWeapons.remove(i);
                    humans.remove(j);
                    enemyWeaponsDirection.remove(i);
                    if (score > 0)
                        score--;
                }
            }
            if (enemyWeapons.get(i).getLayoutY() < 0 || enemyWeapons.get(i).getLayoutX() > 2000 || enemyWeapons.get(i).getLayoutY() < 0 || enemyWeapons.get(i).getLayoutY() > 490) {
                dungeon.getChildren().remove(enemyWeapons.get(i));
                enemyWeapons.remove(i);
                enemyWeaponsDirection.remove(i);
            }
            for (int j = 0; j < weapons.size(); j++) {
                if (((weapons.get(j).getLayoutX() <= enemyWeapons.get(i).getLayoutX() + 20  && weapons.get(j).getLayoutX() >= enemyWeapons.get(i).getLayoutX()) && (weapons.get(j).getLayoutY() >=  enemyWeapons.get(i).getLayoutY() && weapons.get(j).getLayoutY()<= enemyWeapons.get(i).getLayoutY() + 10))) {
                    dungeon.getChildren().remove(enemyWeapons.get(i));
                    enemyWeapons.remove(i);
                    enemyWeaponsDirection.remove(i);
                    dungeon.getChildren().remove(weapons.get(j));
                    weapons.remove(j);
                    shootDirection.remove(j);
                }
            }
        }

        return false;
    }

    public void spawnHumans() {
        Random random = new Random();
        int rand_x = random.nextInt(2000);
        if (humans.size() < 10) {
            Human temp = new Human();
            int rand = random.nextInt(3) + 1;
            temp.setImage(setImage("human" + rand + ".png"));
            temp.setLayoutY(450);
            temp.setLayoutX(rand_x);
            humans.add(temp);
            dungeon.getChildren().add(humans.get(humans.size() - 1));
        }
    }

    public void enemiesShoot() {
        Random random = new Random();
        int prob = random.nextInt(2000000);
        for (int i = 0; i < enemy.size(); i++) {
            if (prob % 203 == 0) {
                Weapon temp = new Weapon();
                temp.setImage((enemy.get(i).getWeapon().getImage()));
                temp.setName(enemy.get(i).getWeapon().getName());
                temp.setDamage(enemy.get(i).getWeapon().getDamage());
                temp.setLayoutX(enemy.get(i).getLayoutX());
                temp.setLayoutY(enemy.get(i).getLayoutY());
                temp.setVisible(true);
                enemyWeapons.add(temp);
                enemyWeaponsDirection.add("");
                dungeon.getChildren().add(enemyWeapons.get(enemyWeapons.size() - 1));
            }
        }
    }

    private ArrayList<String> enemyWeaponsDirection;
    public void enemyWeaponsDirection() {
        Random random = new Random();
        for (int i  = 0; i < enemyWeapons.size(); i++) {
            if (enemyWeaponsDirection.get(i).equals("")) {
                String direction = "";
                if (enemyWeapons.get(i).getLayoutX() > heroX) {
                    direction = "left";
                } else {
                    direction = "right";
                }
                if (enemyWeapons.get(i).getLayoutY() > heroY) {
                    direction = direction + "down";
                } else {
                    direction = direction + "up";
                }
                enemyWeaponsDirection.set(i, direction);
            }
        }
    }

    public void enemyWeaponsMove() {
        for (int i = 0; i < enemyWeapons.size(); i++) {
            if (enemyWeaponsDirection.get(i).contains("right")) {
                enemyWeapons.get(i).setLayoutX(enemyWeapons.get(i).getLayoutX() + 0.5);
            }
            if (enemyWeaponsDirection.get(i).contains("left")) {
                enemyWeapons.get(i).setLayoutX(enemyWeapons.get(i).getLayoutX() - 0.5);
            }
            if (enemyWeaponsDirection.get(i).contains("up")) {
                enemyWeapons.get(i).setLayoutY(enemyWeapons.get(i).getLayoutY() + 0.5);
            }
            if (enemyWeaponsDirection.get(i).contains("down")) {
                enemyWeapons.get(i).setLayoutY(enemyWeapons.get(i).getLayoutY() - 0.5);
            }
        }

    }

    public void setMusic(Audio musicAudio) {
        System.out.println( "effect setted in user");
        music = musicAudio;
    }

    public void setAudioEffect(Audio audioEffect) {
        System.out.println( "music setted in user");
        gameAudio = audioEffect;

    }

    public void loadVillians() {
        mobs = new ArrayList<Mob>();
        Weapon bullet = new Weapon(1, "Bullet", 0 );
        Weapon blueLaser = new Weapon(2, "Laser_Blue", 0);
        Weapon redLaser = new Weapon(3, "Laser_Red", 0);
        bullet.setImage(setImage(bullet.getName() + ".gif"));
        blueLaser.setImage(setImage(blueLaser.getName() + ".gif"));
        redLaser.setImage(setImage(redLaser.getName() + ".gif"));
        Mob redskull = new Mob("RS", 1, bullet );
        Mob ultronBlue = new Mob("UB", 2, blueLaser);
        Mob ultronRed = new Mob("UR", 3, redLaser);
        mobs.add(redskull);
        mobs.add(ultronBlue);
        mobs.add(ultronRed);
    }


}
