package sample;

public class Character extends Object
{
    // constants
    private final static int LIVES = 3;

    // variables
    private String name;
    private int lives;
    private int price;

    public Character()
    {
        lives = LIVES;
        name = "unknown";
        price = -1;
    }

    public Character(String name, int price)
    {
        lives = LIVES;
        this.name = name;
        this.price = price;
    }

    // methods
    public int getLive()
    {
        return lives;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void dieOnce()
    {
        if(lives > 0)
            lives--;
    }

    public void resetLives()
    {
        lives = LIVES;
    }
}
