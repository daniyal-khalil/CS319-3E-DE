package sample;

public class Weapon
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
    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
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
}
