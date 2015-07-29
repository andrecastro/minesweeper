package edu.montana.datastructure.view;

import javax.swing.JFrame;

/**
 * Minesweeper main class
 * 
 * @author andre castro
 * @version 1 Feb 2015
 */
public class Minesweeper extends JFrame {

	private static final long serialVersionUID = 1L;

	public Minesweeper(Integer rows, Integer columns, Integer numberOfMines) {
		super("Minesweeper");
		this.init(rows, columns, numberOfMines);
	}
	
	private void init(Integer rows, Integer columns, Integer numberOfMines) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new MinesweeperPanel(rows, columns, numberOfMines));
        pack();
        setLocationRelativeTo(null);  
        setResizable(false);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new Minesweeper(16, 30, 100); // create the Minesweeper game with rows, columns and number of mines
	}

}
