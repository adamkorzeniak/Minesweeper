import java.io.Serializable;

public class SaveGame implements Serializable{
	private String highscore;
	private int width, height, mines, minesAtStart, minesLeft, pixels;
	private double loadedTime;
	
	public SaveGame(String hscore, int w, int h, int m, int mAtStart, int ml, int p, double lt) {
		highscore = hscore;
		width = w;
		height = h;
		mines = m;
		minesAtStart = mAtStart;
		minesLeft = ml;
		pixels = p;
		loadedTime = lt;
	}
	public String getHighscore() {
		return highscore;
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
		return pixels;
	}
	public double getLoadedTime() {
		return loadedTime;
	}
}
