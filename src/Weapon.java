package sample;

import javafx.scene.image.ImageView;

/**
 * This the class that stores weapons.
 */
public class Weapon extends ImageView
{
    // variables
    private int damage;
    private String name;
    private int price;


    // constructors

    public Weapon()
    {
        damage = 0;
        name = "undefined";
        price = 0;
    }

    public Weapon(int damage, String name, int price)
    {
        this.damage = damage;
        this.name = name;
        this.price = price;
    }

    // methods

    /**
     * This returns the damage
     * @return
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * This sets the damage with a given damage level
     * @param damage
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * This sets the name with a given name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This gets you the name of the weapon
     * @return weapon name
     */
    public String getName()
    {
        return name;
    }

    /**
     * This gives you the price for weapon
     * @return price
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * This sets the price
     * @param price
     */
    public void setPrice(int price)
    {
        this.price = price;
    }
}
