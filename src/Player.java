package sample;

import java.util.ArrayList;

public class Player
{
    // variables
    private String name;
    private int coins;
    private Weapon currentWeapon;
    private int id;
    private Character currentCharacter;
    private int highScore;
    private ArrayList<Weapon> weapons;
    private ArrayList<Character> characters;

    // constructors
    public Player()
    {
        this.name = "undefined";
        this.coins = 0;
        this.currentWeapon = new Weapon();
        this.id = -1;
        this.currentCharacter = new Character();
        this.highScore = 0;
        this.weapons = new ArrayList<Weapon>();
        this.characters = new ArrayList<Character>();
    }

    public Player(String name, int coins, Weapon currentWeapon, int id, Character currentCharacter,
                  int highScore, ArrayList<Weapon> weapons, ArrayList<Character> characters)
    {
        this.name = name;
        this.coins = coins;
        this.currentWeapon = currentWeapon;
        this.id = id;
        this.currentCharacter = currentCharacter;
        this.highScore = highScore;
        this.weapons = weapons;
        this.characters = characters;
    }

    // methods
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCoins()
    {
        return coins;
    }

    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    public Weapon getCurrentWeapon()
    {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon)
    {
        this.currentWeapon = currentWeapon;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Character getCurrentCharacter()
    {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character currentCharacter)
    {
        this.currentCharacter = currentCharacter;
    }

    public void setHighScore(int highScore)
    {
        this.highScore = highScore;
    }

    public int getHighScore()
    {
        return highScore;
    }

    public boolean addWeapon(Weapon weapon)
    {
        for(Weapon w: weapons)
        {
            if(w.getName().equals(weapon.getName()))
            {
                return false;
            }
        }
        weapons.add(weapon);
        return true;
    }

    public boolean addCharacter(Character character)
    {
        for(Character ch: characters)
        {
            if(ch.getName().equals(character.getName()))
            {
                return false;
            }
        }
        characters.add(character);
        return true;
    }
}
