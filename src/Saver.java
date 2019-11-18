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

    public int getID(String str)
    {
        try {
            int j = 0;
            for (; !java.lang.Character.isDigit(str.charAt(j)); j++)
            if (java.lang.Character.isDigit(str.charAt(j))) {
                int i = j;
                for (; java.lang.Character.isDigit(str.charAt(i)) && (str.charAt(i) != '+'); i++) ;
                int id = Integer.getInteger(str.substring(0, i));
                return id;
            }
        } catch (NullPointerException e)
        {

        }
        return -1;
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
