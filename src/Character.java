package sample;

import javafx.scene.image.ImageView;

public class Character extends ImageView
{
    // constants
    private final static int LIVES = 3;

    // variables
    private String name;
    private int lives;
    private int price;

    /**
     * constructor
     */
    public Character()
    {
        lives = LIVES;
        name = "unknown";
        price = -1;
    }

    /**
     * This is the constructor
     * @param price Unused.
     * @param name
     */
    public Character(String name, int price)
    {
        lives = LIVES;
        this.name = name;
        this.price = price;
    }

    // methods

    /**
     * setter for name
     * @param name to be setted.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * getter for name
     * @return name .
     */
    public String getName()
    {
        return name;
    }

    /**
     * getter for price
     * @return name .
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * setter for price
     * @param price to be setted.
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     * setter for lives
     * @param lives to be setted.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * getter for lives
     * @return live .
     */
    public int getLives() {
        return lives;
    }
}