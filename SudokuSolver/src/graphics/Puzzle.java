package graphics;

import java.awt.Color;

import javax.swing.JButton;

public class Puzzle {

	public int[][] board;
	public int[][] copy;
	
	public Puzzle(int[][] board) {
		this.board = board;
		copy = new int[9][9];
	}
	
	public String toString() {
		String res = "";
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				res += board[r][c] + " ";
				copy[r][c] = board[r][c];
			}
			res += "\n";
		}
		return res;
	}
	
	// check if the puzzle is already solved
	boolean solved() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (board[r][c] == 0) {
					// if there is a 0 anywhere in the board, then it is not yet solved
					return false;
				}
			}
		}
		return true;
	}
	
	// analyze a given position in the puzzle to see which values can be placed there
	boolean[] analyze(int R, int C) {
		// possible[i] being true means that i is a possible value for the position at (R,C)
		boolean[] possible = new boolean[10];
		
		// automatically every value is possible but later remove those that are not possible
		for (int i = 0; i < possible.length; i++) {
			possible[i] = true;
		}
		
		// for every row with the same column index, analyze
		for (int r = 0; r < 9; r++) {
			if (board[r][C] != 0) {
				possible[board[r][C]] = false;
			}
		}
		
		// for every column with the same row index, analyze
		for (int c = 0; c < 9; c++) {
			if (board[R][c] != 0) {
				possible[board[R][c]] = false;
			}
		}
		
		// for the surrounding box, analyze
		int startR = R / 3 * 3;
		int startC = C / 3 * 3;
		
		for (int r = startR; r < startR + 3; r++) {
			for (int c = startC; c < startC + 3; c++) {
				if (board[r][c] != 0) {
					possible[board[r][c]] = false;
				}
			}
		}
		
		return possible;
	}
	
	// solve the board recursively
	public void solve() {
		if (solved()) {
			// base case: the board is already solved
			System.out.println("SOLVED");
			System.out.println(this);
			return;
		} else {
			// recursive step: analyze each position with 0 and check every possible value
			int R = 0;
			int C = 0;
			
			// find the first position with 0 at its value
			loop_A:
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					if (board[r][c] == 0) {
						R = r;
						C = c;
						break loop_A;
					}
				}
			}
			
			// possible holds all of the values that can be placed there
			boolean[] possible = analyze(R, C);
			
			// for every value that can be placed at this position, place it and solve
			for (int i = 1; i < 10; i++) {
				if (possible[i]) {
					board[R][C] = i;
					solve();
				}
			}
			
			// if all else fails, then set the value to 0, so the program will backtrack and remove a value
			board[R][C] = 0;
		}
	}
}
