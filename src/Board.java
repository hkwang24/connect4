import java.util.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel {

	//current game board
	private int[][] board;
	//past game boards
	private LinkedList<int[][]> boards = new LinkedList<int[][]>();
	//tracks current player
	private boolean playerOne;
	//tracks game
	private boolean playing;
	//tracks player one's score
	private int scoreOne;
	//tracks player two's score
	private int scoreTwo;
	//file io
	private Io io;
	
	public Board(JLabel panel) {
		
        setBackground(Color.BLACK);
        io = new Io();
        scoreOne = 0;
        scoreTwo = 0;
		playerOne = true;
		playing = true;

        //initialize board
		board = new int[6][6];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j] = 0;
			}
		}

		//add board to past boards
		boards.add(new int[6][6]);

	}
	
	//undo last move
	public void undo() {
		
		//set board to last board
		boards.removeLast();
		board = boards.getLast();
		
		//undo player switch
		playerOne = !playerOne;
		
		repaint();
	}
	
	//reset
	public void reset() {
		
		//clear board
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j] = 0;
			}
		} 
		
		playerOne = true;
		scoreOne = 0;
		scoreTwo = 0;
		boards.clear();
		playing = true;
		
		repaint();
	}
	
	//check to see if a player has won
	public boolean won(int p) {
		
		//check horizontal
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 3; c++) {
				if (board[r][c] == p &&
					board[r][c + 1] == p &&
		            board[r][c + 2] == p &&
		            board[r][c + 3] == p) {
		            return true;
		        }
		    }
		}
		
		//check vertical
		for(int r = 0; r < 3; r++) {
			for (int c = 0; c < 6; c ++) {
				if (board[r][c] == p && 
					board[r + 1][c] == p &&
	                board[r + 2][c] == p &&
	                board[r + 3][c] == p) {
	                return true;
				}
			}
		}
		
		//check diagonal
		for (int r = 3; r < 6; r++) {
	        for (int c = 3; c < 6; c++) {
	            if (board[r][c] == p &&
	                board[r - 1][c - 1] == p &&
	                board[r - 2][c - 2] == p &&
	                board[r - 3][c - 3] == p) {
	                return true;
	            }
	        }
	    }	
		
		//check other diagonal
		for (int r = 3; r < 6; r++) {
	        for (int c = 0; c < 3; c++) {
	            if (board[r][c] == p &&
	                 board[r - 1][c + 1] == p &&
	                 board[r - 2][c + 2] == p &&
	                 board[r - 3][c + 3] == p) {
	                 return true;
	            }
	        }
	    }
		
		return false;		
	}
	
	//board is full
	public boolean full() {
		
		//check all slots
		for(int r = 0; r < 6; r++) {
			for (int c = 0; c < 6; c++) {
				if (board[r][c] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	//make static copy of board
	public int[][] copy(){
		int[][] result = new int[6][6];
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 6; c++) {
				result[r][c] = board[r][c];
			}
		}
		return result;
	}
    
    //drop piece
    public void drop(int c) {  
        
    	//while playing
    	if (playing) {
    		
    		//if player one
    		if (playerOne) {
    			for (int r = 5; r >= 0; r--) { 
    				if (board[c][r] == 0) { 
    					
    					//update board state
    					board[c][r] = 1; 
    	    			scoreOne++;
    	    			playerOne = false;
    	        		repaint();
    	        		
    	        		//check for win
    	        		if (won(1)) {
    	        			String user = JOptionPane.showInputDialog(null, "Player One has won! Please input your name.");
    	        	        io.addScore(user, scoreOne);
    	        			playing = false;
    	        		}
    	        		
    	        		//or full board
    	        		if (full()) {
    	        			playing = false;
    	        			JOptionPane.showMessageDialog(null, "The game is tied. Please restart the game.");
    	        		}
    	        		
    	        		//add board to past boards
    	        		int[][] copy = copy();
    	        		boards.add(copy);
    	        		
    	        		break;
    				} 
    			}
    			
    		//player two
    		} else {
    			for (int r = 5; r >= 0; r--) { 
    				if (board[c][r] == 0) { 
    					
    					//update board
    					board[c][r] = 2; 
    	    			scoreTwo++;
    	    			playerOne = true;
    	        		repaint();

    	        		//check for win
    	        		if (won(2)) {
    	        	    	String user = JOptionPane.showInputDialog(null, "Player Two has won! Please input your name.");
    	        	        io.addScore(user, scoreTwo);
    	        			playing = false;
    	        		}
    	        		
    	        		//or full board
    	        		if (full()) {
    	        			playing = false;
    	        			JOptionPane.showMessageDialog(null, "The game is tied. Please restart the game.");
    	        		}
    	        		
    	        		//add board to past boards
    	        		int[][] copy = copy();
    	        		boards.add(copy);
    	        		
    	        		break;
    				} 
    			}
    		}
    	}  	   	
    } 
    
	@Override
	public void paintComponent(Graphics g) {
		 
		super.paintComponent(g);
	
	 	//check all slots
	 	for (int r = 0; r < 6; r++) { 
	    	for (int c = 0; c < 6; c++) { 
	            int pos = board[r][c]; 
	            
	            //empty white circle
	            if (pos == 0) { 
	                g.setColor(Color.WHITE);
	                g.drawOval((r * 100) + 10, (c * 100) + 10, 80, 80);
	            } else { 
	            	
	            	//player one: blue circle
	                if (pos == 1) { 
	                    g.setColor(Color.BLUE);
	                    g.fillOval((r * 100) + 10, (c * 100) + 10, 80, 80);
	                    
	                //player two: pink circle
	                } else if (pos == 2){ 
	                	g.setColor(Color.PINK); 
	                    g.fillOval((r * 100) + 10, (c * 100) + 10, 80, 80);
	                } 
	            } 
	        } 
	    } 	        
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}
	
	//check if game is over
    public boolean isPlaying() {
    	if (playing) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
	//get string of top scores
    public String getScores() {
    	return io.getIo();
    }
}

    
