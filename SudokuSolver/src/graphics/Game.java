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
import javax.swing.JOptionPane;

// graphics implementation of the Sudoku puzzle
public class Game {

	static int r;
	static int c;

	static JFrame options = new JFrame();

	static boolean errorChecking = false;

	static int clickedR;
	static int clickedC;

	static Puzzle attempt;
	
	static Puzzle solution;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("/Users/saibulusu/git/SudokuSolver/SudokuSolver/res/test2.txt"));
		int[][] board = new int[9][9];

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				board[r][c] = scan.nextInt();
			}
		}

		JButton[][] buttons = new JButton[9][9];

		Puzzle puzzle = new Puzzle(board);
		System.out.println(puzzle);
		
		puzzle.solve();
		
		solution = new Puzzle(board);
		solution.board = puzzle.copy;

		attempt = new Puzzle(board);
		
		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(new Dimension(250, 250));

		frame.setLocation(480, 180);

		frame.setLayout(new GridLayout(10, 9));

		int choice = JOptionPane.showConfirmDialog(null, "Enable Error Checking?");

		if (choice == JOptionPane.YES_OPTION) {
			errorChecking = true;
		}

		for (r = 0; r < 10; r++) {
			if (r == 9) {
				for (c = 0; c < 9; c++) {
					JButton button = new JButton();
					button.setFont(new Font("Arial", Font.PLAIN, 20));

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
								options.dispose();
								puzzle.solve();

								int[][] copy = puzzle.copy;
								System.out.println("COPY");
								for (int r = 0; r < 9; r++) {
									for (int c = 0; c < 9; c++) {
										System.out.print(copy[r][c] + " ");
										buttons[r][c].setText(copy[r][c] + " ");
										if (!buttons[r][c].getForeground().equals(Color.BLUE)) {
											buttons[r][c].setForeground(Color.BLACK);
										}
									}
									System.out.println();
								}
							}
						});
					}
					frame.add(button);
				}
			} else {
				for (c = 0; c < 9; c++) {
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
							for (int i = 0; i < 9; i++) {
								for (int j = 0; j < 9; j++) {
									if (buttons[i][j].getText().equals("10")) {
										buttons[i][j].setText("");
									}
								}
							}
							
							options.dispose();
							
							if (!button.getForeground().equals(Color.BLUE)) {
								options = new JFrame();
								
								for (int i = 0; i < 10; i++) {
									JButton value = new JButton();
									value.setFont(new Font("Arial", Font.PLAIN, 20));
									value.setText(i + "");

									value.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
//											System.out.println(clickedR + " " + clickedC);
											button.setText(value.getText());
											
											System.out.println("solution at [0][1]: " + solution.board[0][1]);
											
											System.out.println("solution: \n" + solution);
											
											loop_B:
											for (int r = 0; r < 9; r++) {
												for (int c = 0; c < 9; c++) {
													if (buttons[r][c].getText().equals("")) {
														attempt.board[r][c] = 0;
													} else {
														attempt.board[r][c] = Integer.parseInt(buttons[r][c].getText());
														System.out.println("attempt at [0][1] is " + attempt.board[0][1]);
													}
													
													System.out.print(attempt.board[r][c] + " ");
													if (errorChecking && attempt.board[r][c] != solution.board[r][c]) {
														button.setText("");
														attempt.board[r][c] = 0;
														JOptionPane.showMessageDialog(null, "That is incorrect");
														break loop_B;
													}
												}
												System.out.println();
											}
										}
									});

									options.add(value);
								}

								button.setText("10");
								
								options.setLocation(100, 0);
								options.setLayout(new GridLayout());

								options.pack();

								options.setVisible(true);
							}
						}
					});
					frame.add(button);
				}

				frame.pack();
			}
		}
	}

}
