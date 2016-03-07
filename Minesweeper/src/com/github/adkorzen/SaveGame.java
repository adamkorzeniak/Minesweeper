package com.github.adkorzen;

import java.io.Serializable;

/**
 * <b>SaveGame</b> <br>
 * <br>
 * Used to pass game settings to file and to read it back.
 * 
 * @author Adam Korzeniak
 */
public class SaveGame implements Serializable {
	private static final long serialVersionUID = 9145482136725640973L;

	private String difficulty;
	private int width, height, mines, minesAtStart, minesLeft, pixelsPerButton;
	private double loadedTime;

	/**
	 * * <b>SaveGame</b> <br>
	 * <br>
	 * <i>public SaveGame(String hscore, int w, int h, int m, int mAtStart, int
	 * ml, int p, double lt)</i> <br>
	 * <br>
	 * Constructs object and passes it data from parameters
	 * 
	 * @param hscore - difficulty of a game
	 * @param w - width of game field
	 * @param h - height of game field
	 * @param m - number of unflagged mines
	 * @param mAtStart - number of mines at start of a game
	 * @param ml - number of squares not being revealed
	 * @param p - (approximate) size of square in pixels
	 * @param lt - timer output when game is being saved
	 */
	public SaveGame(String hscore, int w, int h, int m, int mAtStart, int ml, int p, double lt) {
		difficulty = hscore;
		width = w;
		height = h;
		mines = m;
		minesAtStart = mAtStart;
		minesLeft = ml;
		pixelsPerButton = p;
		loadedTime = lt;
	}
	/**
	 * * <b>getHighscore</b> <br>
	 * <br>
	 * <i>public String getHighscore()</i> <br>
	 * <br>
	 * Returns saved game difficulty
	 * @return String with saved game difficulty
	 */
	public String getHighscore() {
		return difficulty;
	}
	/**
	 * * <b>getWidth</b> <br>
	 * <br>
	 * <i>public int getWidth()</i> <br>
	 * <br>
	 * Returns saved game field width
	 * @return saved game field width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * * <b>getHeight</b> <br>
	 * <br>
	 * <i>public int getHeight()</i> <br>
	 * <br>
	 * Returns saved game field height
	 * @return saved game field height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * * <b>getMines</b> <br>
	 * <br>
	 * <i>public int getMines()</i> <br>
	 * <br>
	 * Returns saved game number of unflagged mines
	 * @return saved game number of unflagged mines
	 */
	public int getMines() {
		return mines;
	}
	/**
	 * * <b>getMinesAtStart</b> <br>
	 * <br>
	 * <i>public int getMinesAtStart()</i> <br>
	 * <br>
	 * Returns from saved game initial number of mines
	 * @return from saved game initial number of mines
	 */
	public int getMinesAtStart() {
		return minesAtStart;
	}
	/**
	 * * <b>getMinesLeft</b> <br>
	 * <br>
	 * <i>public int getMinesLeft()</i> <br>
	 * <br>
	 * Returns from saved game, number of squares not being revealed
	 * @return from saved game, number of squares not being revealed
	 */
	public int getMinesLeft() {
		return minesLeft;
	}
	/**
	 * * <b>getPixels</b> <br>
	 * <br>
	 * <i>public int getPixels()</i> <br>
	 * <br>
	 * Returns (approximate) size of square
	 * @return (approximate) size of square
	 */
	public int getPixels() {
		return pixelsPerButton;
	}
	/**
	 * * <b>getLoadedTime</b> <br>
	 * <br>
	 * <i>public int getLoadedTime()</i> <br>
	 * <br>
	 * Returns timer output when game was saved
	 * @return timer output when game was saved
	 */
	public double getLoadedTime() {
		return loadedTime;
	}
}
