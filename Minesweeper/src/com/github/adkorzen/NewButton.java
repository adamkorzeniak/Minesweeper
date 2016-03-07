package com.github.adkorzen;

import java.io.Serializable;
import javax.swing.*;

/**
 * <b>NewButton</b> <br>
 * <br>
 * Responsible for holding properties of buttons on game field. <br>
 * 
 * @author Adam
 *
 */
public class NewButton extends JButton implements Serializable {
	private static final long serialVersionUID = -8925417494225267121L;

	private boolean isMine, isFlagged;
	private int amountOfBombNeighbours;

	/**
	 * <b>setBombNeighbours</b> <br>
	 * <br>
	 * <i>public void setBombNeighbours(int bombs)</i> <br>
	 * <br>
	 * Assigns to button number of mines it is neighbouring.
	 * 
	 * @param bombs
	 *            - number of neighbouring bomb to assign as instance variable
	 * @throws Exception
	 */
	public void setBombNeighbours(int bombs) throws Exception {
		if (bombs >= 0 && bombs < 9) {
			amountOfBombNeighbours = bombs;
		} else {
			throw new Exception("Amount of bombs incorrect");
		}
	}

	/**
	 * <b>getBombNeighbours</b> <br>
	 * <br>
	 * <i>public int getBombNeighbours()</i> <br>
	 * <br>
	 * Returns amount of mines the button is neighbouring.
	 * 
	 * @return - amount of mines the button is neighbouring
	 */
	public int getBombNeighbours() {
		return amountOfBombNeighbours;
	}

	/**
	 * <b>setFlagged</b> <br>
	 * <br>
	 * <i>public void setFlagged()</i> <br>
	 * <br>
	 * Sets button as flagged.
	 * 
	 */
	public void setFlagged() {
		isFlagged = true;
	}

	/**
	 * <b>deFlag</b> <br>
	 * <br>
	 * <i>public void deFlag()</i> <br>
	 * <br>
	 * Sets button as unflagged.
	 * 
	 */
	public void deFlag() {
		isFlagged = false;
	}

	/**
	 * <b>isFlagged</b> <br>
	 * <br>
	 * <i>public boolean isFlagged()</i> <br>
	 * <br>
	 * Returns true if button is flagged, false otherwise
	 * 
	 * @return true if button is flagged, false otherwise
	 * 
	 */
	public boolean isFlagged() {
		return isFlagged;
	}

	/**
	 * <b>setMine</b> <br>
	 * <br>
	 * <i>public void setMine(boolean isIt)</i> <br>
	 * <br>
	 * Sets button as being mine or not being mine
	 * 
	 * @param isIt
	 *            - whether button is mine
	 */
	public void setMine(boolean isIt) {
		isMine = isIt;
	}

	/**
	 * <b>getMine</b> <br>
	 * <br>
	 * <i>public boolean getMine()</i> <br>
	 * <br>
	 * Returns true if button is a mine, false otherwise
	 * 
	 * @return true if button is a mine, false otherwise
	 * 
	 */
	public boolean getMine() {
		return isMine;
	}

}
