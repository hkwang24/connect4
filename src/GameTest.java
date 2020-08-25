import static org.junit.Assert.*;
import java.io.IOException;
import javax.swing.*;
import org.junit.Test;

public class GameTest {
	
	@Test
	public void testWin1() throws IOException {
		JLabel status = new JLabel();
		Board board = new Board(status);
		board.drop(1);
		board.drop(1);
		board.drop(2);
		board.drop(2);
		board.drop(3);
		board.drop(3);
		board.drop(4);
		assertFalse(board.isPlaying());
	}
	
	@Test
	public void testWin2() throws IOException {
		JLabel status = new JLabel();
		Board board = new Board(status);
		board.drop(1);
		board.drop(2);
		board.drop(1);
		board.drop(2);
		board.drop(1);
		board.drop(2);
		board.drop(1);
		assertFalse(board.isPlaying());
	}
	
	@Test
	public void testWin3() throws IOException {
		JLabel status = new JLabel();
		Board board = new Board(status);
		board.drop(1);
		board.drop(2);
		board.drop(2);
		board.drop(3);
		board.drop(4);
		board.drop(3);
		board.drop(3);
		board.drop(4);
		board.drop(4);
		board.drop(5);
		board.drop(4);
		assertFalse(board.isPlaying());
	}
	
	@Test
	public void testUndo() throws IOException {
		JLabel status = new JLabel();
		Board board = new Board(status);
		board.drop(1);
		board.drop(2);
		board.drop(2);
		board.drop(3);
		board.drop(4);
		board.drop(3);
		board.drop(3);
		board.drop(4);
		board.drop(4);
		int[][] expected = board.copy();
		board.drop(5);
		board.undo();
		int[][] result = board.copy();
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				assertEquals(expected[x][y], result[x][y]);
			}
		}
	}
	
	@Test
	public void testReset() throws IOException {
		JLabel status = new JLabel();
		Board board = new Board(status);
		board.drop(1);
		board.drop(2);
		board.drop(2);
		board.drop(3);
		board.drop(4);
		board.drop(3);
		board.drop(3);
		board.drop(4);
		board.drop(4);
		int[][] expected = new int[6][6];
		board.reset();
		int[][] result = board.copy();
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				assertEquals(expected[x][y], result[x][y], 0);
			}
		}
	}
	
}