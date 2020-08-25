import java.util.*;
import java.io.*;

public class Io {

	//input
    ObjectInputStream input = null;
    //output
    ObjectOutputStream output = null;
    //list of scores
    private LinkedList<Score> scores;
    //file to read and write to
    private static final String readFile = "io.dat";
    
    //constructor
    public Io() {
        scores = new LinkedList<Score>();
    }
    
    //sort scores
    private void sort() {
        Compare compare = new Compare();
        Collections.sort(scores, compare);
    }
    
    //get scores
    public LinkedList<Score> getScores() {
        load();
        sort();
        return scores;
    }
    
    //add score
    public void addScore(String player, int score) {
        load();
        scores.add(new Score(player, score));
        update();
    }
    
    //load scores from file
	@SuppressWarnings("unchecked")
	public void load() {
		
		//try reading
        try {
        	
            input = new ObjectInputStream(new FileInputStream(readFile));
            scores = (LinkedList<Score>)input.readObject();
        
        //else catch exceptions
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file.");
        } catch (IOException e) {
            System.out.println("Caught IO exception.");
        } catch (ClassNotFoundException e) {
        	System.out.println("Couldn't find class.");
        	
        //else
        } finally {
        	
        	//try closing stream
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
                
            //else catch exception
            } catch (IOException e) {
                System.out.println("Caught IO exception.");
            }
        }
    }
    
    public void update() {
    	
    	//try writing
        try {
        	
            output = new ObjectOutputStream(new FileOutputStream(readFile));
            output.writeObject(scores);
        
        //else catch exceptions
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file, creating new one.");
        } catch (IOException e) {
            System.out.println("Caught IO exception.");
            
        //else
        } finally {
        	
        	//try closing stream
            try {
            	
                if (output != null) {
                    output.flush();
                    output.close();
                }
            
            //else catch exception
            } catch (IOException e) {
                System.out.println("IO exception.");
            }
        }
    }
    
    //return scores in string format
    public String getIo() {
    	
    	//get scores
        String result = "";
        LinkedList<Score> scores = getScores();
        
        //format top 5 scores
        for (int i = 0; i < Math.min(5, scores.size()); i++) {
        	Score score = scores.get(i);
            result += (i + 1) + ". " + score.getName() + ", " + score.getScore() + "\n";
        }
        return result;
    }
    
    //inner comparator class
    private class Compare implements Comparator<Score>{
    	
    	public int compare(Score s1, Score s2) {

            int one = s1.getScore();
            int two = s2.getScore();

            if (one < two) {
                return -1;
            } else if (one > two) {
                return 1;
            } else {
                return 0;
            }
        }

    }

}
