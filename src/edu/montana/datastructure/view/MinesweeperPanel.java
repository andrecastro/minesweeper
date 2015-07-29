package edu.montana.datastructure.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.montana.datastructure.controller.MineGenerator;
import edu.montana.datastructure.controller.MinesweeperMouseListener;
import edu.montana.datastructure.model.Spot;

/**
 * Minesweeper panel
 * 
 * @author andre castro
 * @version 1 Feb 2015
 */
public class MinesweeperPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Spot[][] board; // matrix with the spot model
	
	private JPanel mainPanel; // center panel
	private JPanel topPanel; // north panel
	private JButton gameButton; // face button
	
	private MinesweeperMouseListener listener; // listener that represents the controller
	
	// some assets
	private ImageIcon happy = new ImageIcon(getClass().getResource("../assets/happy.png"));
	private ImageIcon sad = new ImageIcon(getClass().getResource("../assets/sad.png"));
	
	public MinesweeperPanel(Integer rows, Integer columns, Integer numberOfMines) {
		setLayout(new BorderLayout());
		createTopPanel(rows, columns, numberOfMines);
		createMainPanel(rows, columns, numberOfMines);
	}
	
	// creates the top panel with the face button
	// the face button can restart the game
	private void createTopPanel(final Integer rows, final Integer columns, final Integer numberOfMines) {
		gameButton = new JButton(happy);
		gameButton.setPreferredSize(new Dimension(20,20));
		
		// restart the game
		gameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MinesweeperPanel.this.remove(mainPanel);
				MinesweeperPanel.this.createMainPanel(rows, columns, numberOfMines);
				MinesweeperPanel.this.updateUI();
				gameButton.setIcon(happy);
			}
			
		});
		
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(640, 30));
		topPanel.add(gameButton, BorderLayout.CENTER);
		
		add(topPanel, BorderLayout.NORTH);
	}
	
	// create the center panel with the board
	private void createMainPanel(final Integer rows, final Integer columns, final Integer numberOfMines) {
		mainPanel =  new JPanel(new GridLayout(rows, columns));
		mainPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mainPanel.setPreferredSize(new Dimension(640, 480));
		
		// generate the board with random mines
		board = MineGenerator.generateBoard(rows, columns, numberOfMines); 
		listener = new MinesweeperMouseListener(this); // controller listener
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				mainPanel.add(new MinesweeperButton(board[i][j], listener));
			}
		}
		
		add(mainPanel, BorderLayout.CENTER);
	}
	
	// disable the listener of the buttons
	private void disableMouseListener() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c].getButton().removeMouseListener(listener);
			}
		}
	}
	
	// show all mines of the game
	private void showAllMines() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if(board[r][c].getMine()) {
					board[r][c].getButton().showSpot();
				}
			}
		}
	}
	
	// game over
	public void gameOver() {
		gameButton.setIcon(sad);
		showAllMines();
		disableMouseListener();
	}
	
	// win message
	public void win() {
		JOptionPane.showMessageDialog(this, "You Win!!", "", JOptionPane.INFORMATION_MESSAGE);
		disableMouseListener();
	}
	
	public Spot[][] getBoard() {
		return board;
	}

}
