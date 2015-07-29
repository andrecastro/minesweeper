package edu.montana.datastructure.view;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.montana.datastructure.controller.MinesweeperMouseListener;
import edu.montana.datastructure.model.Spot;

/**
 * Class that represents the view of a spot on the board
 * 
 * @author andre castro
 * @version 1 Feb 2015
 */
public class MinesweeperButton extends JButton {

	private static final long serialVersionUID = 1L;

	private Spot spot; 
	private Boolean flag; // the button is flagged or not
	private MouseListener listener; // controller listener
	
	public MinesweeperButton(Spot spot, MinesweeperMouseListener listener) {
		this.spot = spot;
		this.flag = false;
		this.listener = listener;
		this.spot.setParentButton(this); // bind the spot to the current button
		this.addMouseListener(listener);
	}
	
	// flag the button
	public void toogleFlag() {
		flag = !flag;
		setIcon((flag) ? new ImageIcon(getClass().getResource("../assets/flag.png")) : null);
	}
	
	// returns if the button is a mine
	public Boolean hasMine() {
		return spot.getMine();
	}
	
	// returns if the button has a mine adjacent
	public Boolean hasMineAdjacent() {
		return spot.getNumberOfMineNear() != 0;
	}
	
	// shows the value of the button
	// it can be a mine or just the number of adjacent mines
	public void showSpot() {
		setSelected(true);
		
		if(hasMine()) {
			setIcon(new ImageIcon(getClass().getResource("../assets/mine.png")));
			setForeground(Color.RED);
			return;
		} 
		
		setForeground(getColor());
		setText(spot.getNumberOfMineNear().toString());
		removeMouseListener(listener);
	}
	
	// returns if the button is flagged
	public Boolean getFlag() {
		return flag;
	}
	
	// returns the binded spot model
	public Spot getSpot() {
		return spot;
	}
	
	// gets the color according to the number of adjacent mines 
	private Color getColor() {
		if(!hasMine()) {
			if(spot.getNumberOfMineNear() == 1) {
				return Color.BLUE;
			} else if(spot.getNumberOfMineNear() == 2) {
				return Color.GREEN;
			} else if(spot.getNumberOfMineNear() >= 3) {
				return Color.RED;
			}
		}
		
		return Color.BLACK;
	}
	
}
