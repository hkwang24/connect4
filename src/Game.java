import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Game implements Runnable {
	
    public void run() {
    	
    	//set frame
        final JFrame frame = new JFrame("Connect 4");
        frame.setLocation(100, 100);

        //initialize board
        final JLabel status = new JLabel("Welcome to Connect 4!");
        final Board board = new Board(status);
        frame.add(board, BorderLayout.CENTER);
        
        //area for other buttons
        final JPanel south = new JPanel();
        frame.add(south, BorderLayout.SOUTH);

        //area for buttons to drop pieces
        final JPanel north = new JPanel();
        frame.add(north, BorderLayout.NORTH);
        
        //instructions
        String string = "Play Connect 4! If you don't know "
        		+ "\nhow Connect 4 works, then who even "
        		+ "\nare you? Click on High Scores to "
        		+ "\nsee past winners and how many moves "
        		+ "\nthey took to win.";
        final JButton instructions = new JButton("How to Play");
        instructions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, string);
        	}
        });
        south.add(instructions);
       
        //high scores
        final JButton hScores = new JButton("High Scores");
        hScores.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		if (board.getScores() == "") {
       			JOptionPane.showMessageDialog(null, "No scores to show!");
       		} else {
       			JOptionPane.showMessageDialog(null, board.getScores());
       		}
       	}
        });
        south.add(hScores);
       
       
        //undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		board.undo();
        	}
        });
        south.add(undo);
        
        //reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        south.add(reset);

        //drop piece in column 1
        final JButton c1 = new JButton("Drop Here!");
        c1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.drop(0);
            }
        });
        north.add(c1);

        //drop piece in column 2
        final JButton c2 = new JButton("Drop Here!");
        c2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.drop(1);
            }
        });
        north.add(c2);
        
        //drop piece in column 3
        final JButton c3 = new JButton("Drop Here!");
        c3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.drop(2);
            }
        });
        north.add(c3);
        
        //drop piece in column 4
        final JButton c4 = new JButton("Drop Here!");
        c4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.drop(3);
            }
        });
        north.add(c4);
        
        //drop piece in column 5
        final JButton c5 = new JButton("Drop Here!");
        c5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.drop(4);
            }
        });
        north.add(c5);
        
        //drop piece in column 6
        final JButton c6 = new JButton("Drop Here!");
        c6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.drop(5);
            }
        });
        north.add(c6);
        
        //put frame
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //start game
        board.reset();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}

