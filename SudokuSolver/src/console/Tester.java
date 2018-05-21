package console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("/Users/saibulusu/myworkspace/workspace/SudokuSolver/res/test1.txt"));
		int[][] board = new int[9][9];
		
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				board[r][c] = scan.nextInt();
			}
		}
		
		Puzzle puzzle = new Puzzle(board);
		puzzle.solve();
		
		
		scan = new Scanner(new File("/Users/saibulusu/myworkspace/workspace/SudokuSolver/res/test2.txt"));

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				board[r][c] = scan.nextInt();
			}
		}
		
		puzzle = new Puzzle(board);
		puzzle.solve();
		
		
		scan = new Scanner(new File("/Users/saibulusu/myworkspace/workspace/SudokuSolver/res/test3.txt"));

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				board[r][c] = scan.nextInt();
			}
		}
		
		puzzle = new Puzzle(board);
		puzzle.solve();
	}
	
}
