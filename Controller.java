package sample;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {

    @FXML
    private ImageView hero;

    private ImageView weapon;

    private Image img2;

    private static final double W = 1024, H = 700;

    @FXML
    private ImageView characterView;

    boolean running, goNorth, goSouth, goEast, goWest;

    public void onButtonDown(KeyEvent event) throws Exception {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        img2 = setImage("DOWN_R");
        hero = new ImageView(img2);
        Group dungeon = new Group(hero);

        moveHeroTo(W / 2, H / 2);

        Scene scene = new Scene(dungeon, W, H, Color.FORESTGREEN);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:  {
                        hero.setImage(setImage("UP"));
                        goNorth = true;
                    } break;
                    case DOWN:  {
                        hero.setImage(setImage("DOWN"));
                        goSouth = true; break;

                    }
                    case LEFT:  {
                        hero.setImage(setImage("LEFT"));
                        goWest  = true;
                    } break;
                    case RIGHT: {
                        hero.setImage(setImage("RIGHT"));
                        goEast  = true;
                    } break;
                    case SHIFT: running = true; break;

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
                    case SHIFT: running = false; break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast)  dx += 1;
                if (goWest)  dx -= 1;
                if (running) { dx *= 3; dy *= 3; }

                moveHeroBy(dx, dy);

            }
        };
        timer.start();
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
        }
    }

    private Image setImage(String direction) {
        Image img = null;

        if (direction.equals("DOWN")) {
            System.out.println("Here");
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
        return img;
    }

}
