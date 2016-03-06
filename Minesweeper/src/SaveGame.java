import java.io.Serializable;

public class SaveGame implements Serializable{
	private String difficulty;
	private int width, height, mines, minesAtStart, minesLeft, pixelsPerButton;
	private double loadedTime;
	
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
	public String getHighscore() {
		return difficulty;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getMines() {
		return mines;
	}
	public int getMinesAtStart() {
		return minesAtStart;
	}
	public int getMinesLeft() {
		return minesLeft;
	}
	public int getPixels() {
		return pixelsPerButton;
	}
	public double getLoadedTime() {
		return loadedTime;
	}
}
