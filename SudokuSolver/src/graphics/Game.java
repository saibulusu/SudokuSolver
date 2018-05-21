package graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

// graphics implementation of the Sudoku puzzle
public class Game {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("/Users/saibulusu/git/SudokuSolver/SudokuSolver/res/test1.txt"));
		int[][] board = new int[9][9];
		
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				board[r][c] = scan.nextInt();
			}
		}
		
		Puzzle puzzle = new Puzzle(board);
		System.out.println(puzzle);
//		puzzle.solve();
		
		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(250, 250));

		frame.setLocation(480, 180);

		frame.setLayout(new GridLayout(10, 9));
		
		JButton[][] buttons = new JButton[9][9];

		for (int r = 0; r < 10; r++) {
			if (r == 9) {
				for (int c = 0; c < 9; c++) {
					JButton button = new JButton();
					button.setFont(new Font("Arial", Font.PLAIN, 20));
//					button.setText("SOLVE");
					
					frame.add(button);
					
					if (c != 4) {
						button.setOpaque(false);
						button.setContentAreaFilled(false);
						button.setBorderPainted(false);
					} else {
						button.setText("SOLVE");
						button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								puzzle.solve(buttons);
							}
						});
					}
					frame.add(button);
				}
			} else {
				for (int c = 0; c < 9; c++) {
					JButton button = new JButton();
					buttons[r][c] = button;
					button.setFont(new Font("Arial", Font.PLAIN, 20));
					if (puzzle.board[r][c] != 0) {
						button.setText(board[r][c] + "");
						button.setForeground(Color.BLUE);
					}
					button.setPreferredSize(new Dimension(60, 60));
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
						}
					});
					frame.add(button);
				}

				frame.pack();

			}
		}
		
//		puzzle.solve(buttons);
	}
	
}
