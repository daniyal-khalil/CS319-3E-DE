package sample;

import javafx.scene.image.ImageView;

public class Mob extends ImageView
{
    // variables
    private String name;
    private int lives;
    private Weapon weapon;


    //default constructor
    public Mob() {
        name = "";
        lives = 1;
        weapon = new Weapon();
    }
    //constructor
    public Mob(String name, int lives, Weapon weapon) {
        this.name = name;
        this.lives = lives;
        this.weapon = weapon;
    }
    // methods

    /**
     * setter for life
     * @param lives is health of mob character
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * setter for name
     * @param name is name of mob character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for weapon
     * @param weapon is current weapon of mob character
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * getter for life
     * @return lives of mob character
     */
    public int getLives() {
        return lives;
    }

    /**
     * getter for name
     * @param name of mob character
     */
    public String getName() {
        return name;
    }

    /**
     * getter for weapon
     * @param weapon of mob character
     */
    public Weapon getWeapon() {
        return weapon;
    }
}