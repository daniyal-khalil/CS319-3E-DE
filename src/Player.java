package sample;

import java.util.ArrayList;

public class Player
{
    // variables
    private String name;
    private int coins;
    private Weapon currentWeapon;
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
        this.currentCharacter = new Character();
        this.highScore = 0;
        this.weapons = new ArrayList<Weapon>();
        this.characters = new ArrayList<Character>();
    }

    public Player(String name, int coins, Weapon currentWeapon, Character currentCharacter,
                  int highScore, ArrayList<Weapon> weapons, ArrayList<Character> characters)
    {
        this.name = name;
        this.coins = coins;
        this.currentWeapon = currentWeapon;
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

    public boolean setCurrentWeapon(Weapon currentWeapon)
    {
        for(int i = 0; i < weapons.size(); i++)
        {
            if(weapons.get(i).getName().equals(currentWeapon.getName()))
            {
                changeWeapon(i);
                return true;
            }
        }
        return false;
    }

    public Character getCurrentCharacter()
    {
        return currentCharacter;
    }

    public boolean setCurrentCharacter(Character currentCharacter)
    {
        for(int i = 0; i < characters.size(); i++)
        {
            if(characters.get(i).getName().equals(currentCharacter.getName()))
            {
                changeCharacter(i);
                return true;
            }
        }
        return false;
    }

    public void setHighScore(int highScore)
    {
        this.highScore = highScore;
    }

    public int getHighScore()
    {
        return highScore;
    }
    
    public ArrayList<Character> getCharacters()
    {
        return characters;
    }
    
    public void setCharacters(ArrayList<Character> characters)
    {
        this.characters = characters;
    }
    
    public ArrayList<Weapon> getWeapons()
    {
        return weapons;
    }
    
    public void setWeapons(ArrayList<Weapon> weapons)
    {
        this.weapons = weapons;
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
    
    private void changeWeapon(int index)
    {
        if(0 <= index && index < weapons.size())
        {
            Weapon temp = currentWeapon;
            currentWeapon = weapons.get(index);
            weapons.remove(index);
            weapons.add(index, temp);
        }
    }
    
    private void changeCharacter(int index)
    {
        if(0 <= index && index < characters.size())
        {
            Character temp = currentCharacter;
            currentCharacter = characters.get(index);
            characters.remove(index);
            characters.add(index, temp);
        }
    }
}
