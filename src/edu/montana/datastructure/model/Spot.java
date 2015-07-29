package edu.montana.datastructure.model;

import edu.montana.datastructure.view.MinesweeperButton;

/**
 * Class that represents the spot on board
 * 
 * @author andre castro
 * @version 1 Feb 2015
 */
public class Spot {

	private Integer row; // row where the spot is
	private Integer column; // column where the spot is
	private Integer numberOfMineNear; // number of mine adjacent
	private Boolean mine; // has a mine or not
	
	private MinesweeperButton button; // bounded button

	public Spot(Boolean mine, Integer row, Integer column) {
		this.mine = mine;
		this.numberOfMineNear = 0;
		this.row = row;
		this.column = column;
	}

	// increment the number of mine near
	public void incrementNumberOfMineNear() {
		this.numberOfMineNear++;
	}

	
	// getters and setters
	
	public Integer getNumberOfMineNear() {
		return numberOfMineNear;
	}

	public void setNumberOfMineNear(Integer numberOfMineNear) {
		this.numberOfMineNear = numberOfMineNear;
	}

	public Boolean getMine() {
		return mine;
	}

	public void setMine(Boolean mine) {
		this.mine = mine;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
	
	public void setParentButton(MinesweeperButton button) {
		this.button = button;
	}
	
	public MinesweeperButton getButton() {
		return button;
	}
	
	// getters and setters

}
