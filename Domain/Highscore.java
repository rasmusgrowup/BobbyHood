package BobbyHood.Domain;

import java.io.*;
import java.util.ArrayList;

public class Highscore {
    String filepath;
    ArrayList<Integer> highscores = new ArrayList<>();
    int numberofscores = 5;

    public Highscore(String filepath) {
        this.filepath = filepath;
        readHighscores();
    }

    public int writeHighscore(int newscore){
        int newindex = -1;
        for(int i = 0; i < highscores.size(); i++) {
            if(newscore > highscores.get(i)) {
                newindex = i;
                break;
            }
        }
        if(newindex != -1) {
            highscores.add(newindex,newscore);
            if(highscores.size() > numberofscores) {
                highscores.remove(numberofscores);
            }
        } else {
            if(highscores.size() < numberofscores) {
                highscores.add(newscore);
            } else
                return -1;
        }
        try {
            File newscorefile = new File(filepath);
            FileWriter myWriter = new FileWriter(filepath,false);
            for(int i = 0; i < highscores.size(); i++) {
                myWriter.write(Integer.toString(highscores.get(i)));
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return newindex;
    }

    public ArrayList<Integer> getHighscores(){
        return highscores;
    }

    private void readHighscores(){
        try {
            File scorefile = new File(filepath);
            if(!scorefile.exists()) {
                scorefile.createNewFile();
            }
            FileReader fr = new FileReader(scorefile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                  highscores.add(Integer.parseInt(line));
            }
            fr.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}