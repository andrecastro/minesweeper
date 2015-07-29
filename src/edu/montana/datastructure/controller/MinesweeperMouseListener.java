package edu.montana.datastructure.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import edu.montana.datastructure.model.Spot;
import edu.montana.datastructure.view.MinesweeperButton;
import edu.montana.datastructure.view.MinesweeperPanel;

/**
 * Class that connect the front-end to the back end
 * 
 * @author andre castro
 * @version 1 Feb 2015
 */
public class MinesweeperMouseListener implements MouseListener {

	private Spot[][] board; // board of the game
	private MinesweeperPanel panel;
	
	public MinesweeperMouseListener(MinesweeperPanel panel) {
		this.panel = panel;
		this.board = panel.getBoard();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MinesweeperButton button = (MinesweeperButton) e.getComponent();

		if(e.getButton() == MouseEvent.BUTTON3) {
			button.toogleFlag(); // right button, flag the spot
		} else if(e.getButton() == MouseEvent.BUTTON1 && !button.getFlag()) {
			button.showSpot(); // show value
			flooding(button); // flood to others spot
			checkEndGame(button); // check if the game is over
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	// recursive method to flood when click on a spot with 0 mines adjacent
	private void flooding(MinesweeperButton button) {
		if(button.hasMineAdjacent() || button.hasMine()) { // base case
			return;
		}
		
		List<MinesweeperButton> buttons = getAdjacentButtons(button); // get the mine adjacent
	
		for (MinesweeperButton minesweeperButton : buttons) {
			if(!minesweeperButton.isSelected()) { // avoid infinite loop
				if(!minesweeperButton.hasMine()) {
					minesweeperButton.showSpot();
				}
				
				flooding(minesweeperButton);
			}
		}
	}
	
	// checks if the game is over
	private void checkEndGame(MinesweeperButton button) {
		if(button.hasMine()) {
			panel.gameOver();
			return;
		}
		
		if(win()) {
			panel.win();
		}
	}
	
	// verifies if there is only mines
	private Boolean win() {
		boolean win = true;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if(!board[r][c].getButton().isSelected()) {
					win &= board[r][c].getMine();
				}
			}
		}
		
		return win;
	}
	
	// returns the adjacent mines
	private List<MinesweeperButton> getAdjacentButtons(MinesweeperButton button) {
		int row = button.getSpot().getRow();
		int column = button.getSpot().getColumn();
		
		List<MinesweeperButton> buttons = new ArrayList<MinesweeperButton>();
		
		if((row - 1) >= 0) {
			buttons.add(board[row - 1][column].getButton());
			
			if((column - 1) >= 0) {
				buttons.add(board[row - 1][column - 1].getButton());
			}
			
			if((column + 1) < board[0].length) {
				buttons.add(board[row - 1][column + 1].getButton());
			}
		}
		
		if((column - 1) >= 0) {
			buttons.add(board[row][column - 1].getButton());
		}
		
		if((column + 1) < board[0].length) {
			buttons.add(board[row][column + 1].getButton());	
		}
		
		if((row + 1) < board.length) {
			buttons.add(board[row + 1][column].getButton());
			
			if((column - 1) >= 0) {
				buttons.add(board[row + 1][column - 1].getButton());
			}
			
			if((column + 1) < board[0].length) {
				buttons.add(board[row + 1][column + 1].getButton());
			}
		}
		
		return buttons;
	}

}
