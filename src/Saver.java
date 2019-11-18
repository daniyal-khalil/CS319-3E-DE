package sample;

import java.io.*;

public class Saver
{
    // variables
    private final String fileName;

    // constructors
    public Saver(final String fileName)
    {
        this.fileName = fileName;
    }

    // methods
    private String readFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();

        String line = br.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = br.readLine();
        }

        String fileAsString = sb.toString();
        return fileAsString;
    }
    
    public ArrayList<String> getList()
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            String text = readFile(fileName);
            for(int i = 0; i < text.length(); i++)
            {
              char ch = text.charAt(i);
              if(Character.isDigit(ch) || (ch == '+'))
              {
                text = text.substring(0, i) + text.substring(i + 1, text.length());
                i--;
              }
            }
            int j = 0;
            for(int i = 0; i < text.length(); i++)
            {
              if(text.charAt(i) == '\n')
              {
                String str = text.substring(j, i);
                list.add(str);
                j = i + 1;
              }
            }
        } catch(IOException e)
        {
            
        }
        return list;
    }

    public void deleteAll()
    {
        try {
            File fout = new File(fileName);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("");
            bw.close();
        } catch (Exception e){}
    }

    public boolean writeNewUser( String userName, int id) {
        try {
            if(!this.getByID(id).equals("<unidentified>"))
                return false;
            File fout = new File(fileName);
            String content = readFile(fileName);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write(content + id + "+" + userName);
            bw.newLine();
            bw.close();
        } catch (Exception e){}
        return true;
    }

    public String getByID(int id) {
        BufferedReader reader;
        String userr = id + "+<unidentified>";
        try {
            reader = new BufferedReader(new FileReader(
                    fileName));
            String line = reader.readLine();
            while (line != null) {
                if(line.contains(id + "")) {
                    userr = line;
                    break;
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userr.replace(id + "+", "");
    }
}
