package sample;
import java.util.ArrayList;
import java.lang.String;
import java.io.*;
/**
 * The Saver class:
 * getUserList(): returns list of all the users as ArrayList<PLayer>
 * deleteAll(): deletes everything inside txt file. (All users)
 * boolean writeNewUser( Player player): returns true if it successfully saved player. Can return false if player already exists.
 * Player getUser( String userName): returns PLayer with specified username.
 * boolean updateScore(String userName, int highScore): returns true if successfully updated score. This method
 *    automatically checks if the given score is higher than the high score. Can return false if score indicated is not a highest record.
 * boolean addCharacter(String userName, Character ch): adds given character to specified username. 
 *    If the character is already there it will not add it.
 * boolean addWeapon(String userName, Weapon w): adds given weapon to specified username. 
 *    If the weapon is already there it will not add it.
 * boolean setCurrentCharacter(String userName, Character ch): returns false if the specified character is not in the characters list.
 * boolean setCurrentWeapon(String userName, Weapon w): returns false if the specified Weapon is not in the characters list.
 */
public class Saver
{    
    // variables
    private final String fileName;
    private ProcessFile details;

    // constructors
    public Saver(final String fileName)
    {
        this.fileName = fileName;
        try
        {
            details = new ProcessFile(readFile(fileName));
        } catch (IOException e) { e.printStackTrace(); }
    }

    // methods
    private String readFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();

        String line = br.readLine();
        while (line != null) {
            sb.append(line);//.append("\n");
            line = br.readLine();
        }

        String fileAsString = sb.toString();
        return fileAsString;
    }

    public ArrayList<Player> getUserList()
    {
        return details.getPlayerList();
    }

    public void deleteAll()
    {
        try {
            File fout = new File(fileName);
            FileOutputStream fos = new FileOutputStream(fout);
            details = new ProcessFile("");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("");
            bw.close();
        } catch (Exception e){}
    }

    public boolean writeNewUser( Player player)
    {
        try
        {
            if(!( getUser(player.getName()).getName().equals("undefined") || player.getName().equals("undefined")) )
                return false;
            File fout = new File(fileName);
            String content = readFile(fileName);
            deleteAll();
            FileOutputStream fos = new FileOutputStream(fout);
            
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String newContent = "";
            /*for(int i = 0; i < list.size(); i++)
                newContent = newContent + encode(list.get(i));*/
            newContent = content + encode(player);
            details = new ProcessFile(newContent);
            int i = 0;
            for(; i + 100 <= newContent.length(); i = i + 100)
            {
                bw.write(newContent.substring(i, i + 100));
                bw.newLine();
            }
            details = new ProcessFile(newContent);
            bw.write(newContent.substring(i, newContent.length()));
            bw.newLine();
            bw.close();
        } catch (Exception e){}
        return true;
    }
    
    private String encode(Player player)
    {
        String line = "<pl>";
        line = line + "<UN>" + player.getName() + "<UN>";
        line = line + "<cis>" + player.getCoins() + "<cis>";
        line = line + "<cW>" + ( "<dm>" + player.getCurrentWeapon().getDamage() + "<dm>" ) + 
          ( "<nm>" + player.getCurrentWeapon().getName() + "<nm>" ) + 
          ( "<pr>" + player.getCurrentWeapon().getPrice() + "<pr>" ) + "<cW>";
        line = line + "<cCh>" + ( "<nm>" + player.getCurrentCharacter().getName() + "<nm>" ) + 
          ( "<pr>" + player.getCurrentCharacter().getPrice() + "<pr>" ) + "<cCh>";
        line = line + "<hS>" + player.getHighScore() + "<hS>";
        // Weapons
        ArrayList<Weapon> w = player.getWeapons();
        int length = w.size();
        line = line + "<Ws>";
        for(int i = 0; i < length; i++)
        {
            line = line + ( "|<dm>" + w.get(i).getDamage() + "<dm>" ) + 
              ( "<nm>" + w.get(i).getName() + "<nm>" ) + 
              ( "<pr>" + w.get(i).getPrice() + "<pr>|" );
        }
        line = line + "<Ws>";
        // Characters
        ArrayList<Character> c = player.getCharacters();
        length = c.size();
        line = line + "<cs>";
        for(int i = 0; i < length; i++)
        {
            line = line + ( "|<nm>" + c.get(i).getName() + "<nm>" ) + 
              ( "<pr>" + c.get(i).getPrice() + "<pr>|" );
            //System.out.println(( "|<nm>" + c.get(i).getName() + "<nm>" ) + 
              //( "<pr>" + c.get(i).getPrice() + "<pr>|" ));
        }
        //System.out.println("-------- " + line.length() + " -------");
        line = line + "<cs>" + "<pl>";
        return line;
    }
    
    public Player getUser( String userName)
    {
        return details.getPlayer(userName);
    }
    
    public boolean updateScore(String userName, int highScore)
    {
        Player player = getUser(userName);
        if(player.getName().equals("undefined"))
            return false;
        if(highScore > player.getHighScore())
        {
            player.setHighScore(highScore);
            details.changePlayer(userName, player);
            ArrayList<Player> temp = getUserList();
            deleteAll();
            int length = temp.size();
            for(int i = 0; i < length; i++)
            {
                writeNewUser(temp.get(i));
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean addCharacter(String userName, Character ch)
    {
        Player player = getUser(userName);
        if(player.getName().equals("undefined"))
            return false;
        boolean added = player.addCharacter(ch);
        if(!added)
            return false;
        //System.out.println(player.getCharacters());
        ArrayList<Player> temp = getUserList();
        for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getName().equals(userName))
            {
                temp.remove(i);
                temp.add(i, player);
            }
        }
        
        deleteAll();
        int length = temp.size();
        for(int i = 0; i < length; i++)
        {
            writeNewUser(temp.get(i));
        }
        return true;
    }
    
    public boolean addWeapon(String userName, Weapon w)
    {
        Player player = getUser(userName);
        if(player.getName().equals("undefined"))
            return false;
        boolean added = player.addWeapon(w);
        if(!added)
            return false;
        //System.out.println(player.getCharacters());
        ArrayList<Player> temp = getUserList();
        for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getName().equals(userName))
            {
                temp.remove(i);
                temp.add(i, player);
            }
        }
        
        deleteAll();
        int length = temp.size();
        for(int i = 0; i < length; i++)
        {
            writeNewUser(temp.get(i));
        }
        return true;
    }
    
    public boolean setCoins(String userName, int coins)
    {
        Player player = getUser(userName);
        if(player.getName().equals("undefined"))
            return false;
        player.setCoins(coins);
        //System.out.println(player.getCharacters());
        ArrayList<Player> temp = getUserList();
        for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getName().equals(userName))
            {
                temp.remove(i);
                temp.add(i, player);
            }
        }
        
        deleteAll();
        int length = temp.size();
        for(int i = 0; i < length; i++)
        {
            writeNewUser(temp.get(i));
        }
        return true;
    }
    
    public boolean setCurrentCharacter(String userName, Character ch)
    {
        Player player = getUser(userName);
        if(player.getName().equals("undefined"))
            return false;
        boolean added = player.setCurrentCharacter(ch);
        if(!added)
            return false;
        //System.out.println(player.getCharacters());
        ArrayList<Player> temp = getUserList();
        for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getName().equals(userName))
            {
                temp.remove(i);
                temp.add(i, player);
            }
        }
        
        deleteAll();
        int length = temp.size();
        for(int i = 0; i < length; i++)
        {
            writeNewUser(temp.get(i));
        }
        return true;
    }
    
    public boolean setCurrentWeapon(String userName, Weapon ch)
    {
        Player player = getUser(userName);
        if(player.getName().equals("undefined"))
            return false;
        boolean added = player.setCurrentWeapon(ch);
        if(!added)
            return false;
        //System.out.println(player.getCharacters());
        ArrayList<Player> temp = getUserList();
        for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getName().equals(userName))
            {
                temp.remove(i);
                temp.add(i, player);
            }
        }
        
        deleteAll();
        int length = temp.size();
        for(int i = 0; i < length; i++)
        {
            writeNewUser(temp.get(i));
        }
        return true;
    }
    
    private class ProcessFile
    {
        // variables
        private String content;
        private ArrayList<Player> list;
        
        // constructors
        public ProcessFile(String content)
        {
            this.content = content;
            list = processList();
        }
        
        public boolean changePlayer(String userName, Player newPlayer)
        {
            for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i).getName().equals(userName))
                {
                    list.remove(i);
                    list.add(i, newPlayer);
                    return true;
                }
            }
            return false;
        }
        
        public String getContent()
        {
            return content;
        }
        
        /*
        public String separate(String userName)
        {
            int i = content.indexOf(userName) + userName.length();
            int begin = i - 8;
            int final_ = content.substring(i, content.length()).indexOf("<pl>");
            return content.substring(begin, final_ + 4);
        }
        */
        public ArrayList<Player> getPlayerList()
        {
            return list;
        }
        
        public boolean addPlayer(Player p)
        {
            if(p.getName().equals("undefined"))
            {
                return false;
            }
            list.add(p);
            return true;
        }
        
        public Player getPlayer(String userName)
        {
            for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i).getName().equals(userName))
                    return list.get(i);
            }
            return new Player();
        }
        
        private ArrayList<Player> processList()
        {
            int i = 0;
            int j = 0;
            String line = "";
            ArrayList<Player> list = new ArrayList<Player>();
            while(i < content.length())
            {
                j = content.indexOf("<pl>", i);
                int final_ = content.indexOf("<pl>", j + 1);
                if(final_ < 0)
                    break;
                line = content.substring(j, final_ + 4);
                //System.out.println(line);
                ProcessLine p = new ProcessLine(line);
                list.add( p.getPlayer() );
                i = final_ + 4;
            }
            
            return list;
        }
    }
    
    private class ProcessLine
    {
        // variables
        private String line;
        private String userName;
        private int coins;
        private Weapon currentWeapon;
        private Character currentCharacter;
        private int highScore;
        private ArrayList<Weapon> weapons;
        private ArrayList<Character> characters;
        
        // constructors
        public ProcessLine(String line)
        {
            this.line = line;
        }
        
        public Player getPlayer()
        {
            userName = getRelContent("<UN>", this.line);
            coins = Integer.parseInt(getRelContent("<cis>", this.line));
            
            String cWStr = getRelContent("<cW>", this.line);
            currentWeapon = new Weapon(Integer.parseInt(getRelContent("<dm>", cWStr)), 
                                       getRelContent("<nm>", cWStr), 
                                       Integer.parseInt(getRelContent("<pr>", cWStr)) );
            
            String cChStr = getRelContent("<cCh>", this.line);
            currentCharacter = new Character(getRelContent("<nm>", cChStr), 
                                             Integer.parseInt(getRelContent("<pr>", cChStr)) );
            highScore = Integer.parseInt(getRelContent("<hS>", this.line));
            ArrayList<String> ws = getList(getRelContent("<Ws>", this.line));
            weapons = new ArrayList<Weapon>();
            for(int i = 0; i < ws.size(); i++)
            {
                weapons.add(new Weapon(Integer.parseInt(getRelContent("<dm>", ws.get(i))), 
                                       getRelContent("<nm>", ws.get(i)), 
                                       Integer.parseInt(getRelContent("<pr>", ws.get(i))) ));
            }
            ArrayList<String> chsStr = getList(getRelContent("<cs>", this.line));
            characters = new ArrayList<Character>();
            for(int i = 0; i < chsStr.size(); i++)
            {
                characters.add(new Character(getRelContent("<nm>", chsStr.get(i)), 
                                             Integer.parseInt(getRelContent("<pr>", chsStr.get(i))) ));
            }
            return new Player(userName, coins, currentWeapon, currentCharacter, highScore, weapons, characters);
        }
        
        private ArrayList<String> getList(String listStr)
        {
            String copy = listStr;
            ArrayList<String> list = new ArrayList<String>();
            if(listStr.equals(""))
                return new ArrayList<String>();
            firstLoop:
            for(int i = 0; i < copy.length(); i++)
            {
                if( copy.charAt(i) == '|' )
                {
                    secondLoop:
                    for(int j = i + 1; j < copy.length(); j++)
                    {
                        if( copy.charAt(j) == '|' )
                        {
                            list.add(copy.substring(i + 1, j));
                            //System.out.println(copy.substring(i + 1, j));
                            i = j;
                            
                            j = copy.length();
                        }
                    }
                }
            }
            return list;
        }
        
        private String getRelContent(String tag, String ln)
        {
            int i = 0, j;
            int size = tag.length();
            j = i + size;
            int indexB = 0;
            int indexF = j;
            boolean notFirst = false;
            boolean found = false;
            while( j <= ln.length() )
            {
                if(ln.substring(i, j).equals(tag))
                {
                    if(!notFirst)
                    {
                        indexB = j;
                        i = j - 1;
                        j = i + size;
                        notFirst = true;
                    }
                    else
                    {
                        indexF = i;
                        found = true;
                        break;
                    }
                }
                i++;
                j++;
            }
            if(found) {
                if(indexF <= indexB)
                {
                    return "";
                }
                return ln.substring(indexB, indexF);
            }
            else return "";
        }
    }
}