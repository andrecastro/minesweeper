package edu.montana.datastructure.controller;

import java.util.Random;

import edu.montana.datastructure.model.Spot;

/**
 * Class that generates the board
 * with random positions for the mines
 * 
 * @author andre castro
 * @version 1 Feb 2015
 */
public class MineGenerator {
	
	private static Random random = new Random();
	
	// generate the board
	public static Spot[][] generateBoard(Integer rows, Integer columns, Integer numberOfMines) {
		Spot[][] board = new Spot[rows][columns];
		
		initBoard(board, rows, columns);
		createMines(board, rows, columns, numberOfMines);
		return board;
	}
	
	// initialize the board with new values
	private static void initBoard(Spot[][] board, Integer rows, Integer columns) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				board[r][c] = new Spot(false, r, c);
			}
		}
	}
	
	// create random positions for the mines
	private static void createMines(Spot[][] board, Integer rows, Integer columns, Integer numberOfMines) {
		Integer minesCreated = 0;
		Integer numberOfAttempts = numberOfMines * 2; // maximum attempts to avoid infinite loop
		
		while (minesCreated != numberOfMines && numberOfAttempts > 0) {
			int row = random.nextInt(rows);
			int column = random.nextInt(columns);
			
			if(!board[row][column].getMine()) {
				board[row][column].setMine(true);
				updateSpots(board, row, column);
				minesCreated++;
			}
			
			numberOfAttempts--;
		}
	}

	// update the adjacent spots , incrementing the number of mines near
	//   +1 | +1 | +1
	//   +1 |  m | +1
	//   +1 | +1 | +1
	private static void updateSpots(Spot[][] board, Integer row, Integer column) {
		if((row - 1) >= 0) {
			board[row - 1][column].incrementNumberOfMineNear();
			
			if((column - 1) >= 0) {
				board[row - 1][column - 1].incrementNumberOfMineNear();
			}
			
			if((column + 1) < board[0].length) {
				board[row - 1][column + 1].incrementNumberOfMineNear();
			}
		}
		
		if((column - 1) >= 0) {
			board[row][column - 1].incrementNumberOfMineNear();
		}
		
		if((column + 1) < board[0].length) {
			board[row][column + 1].incrementNumberOfMineNear();	
		}
		
		if((row + 1) < board.length) {
			board[row + 1][column].incrementNumberOfMineNear();
			
			if((column - 1) >= 0) {
				board[row + 1][column - 1].incrementNumberOfMineNear();
			}
			
			if((column + 1) < board[0].length) {
				board[row + 1][column + 1].incrementNumberOfMineNear();
			}
		}
	}
	
	
}
