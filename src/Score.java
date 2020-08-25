import java.io.Serializable;

@SuppressWarnings("serial")
public class Score implements Serializable {
	
	//name of player
	private String player;
	//score
	private int score;
	
	//constructor
    public Score(String p, int s) {
        player = p;
        score = s;
    }

    //get name of player
    public String getName() {
        return player;
    }
    
    //get score
    public int getScore() {
        return score;
    }
}