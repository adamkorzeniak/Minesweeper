import java.io.Serializable;

public class SaveGame implements Serializable{
	String highscore;
	int width, height, mines, minesAtStart, left, pixels;
	double loadedTime;
	
	public SaveGame(String hscore, int w, int h, int m, int mAtStart, int l, int p, double lt) {
		highscore = hscore;
		width = w;
		height = h;
		mines = m;
		minesAtStart = mAtStart;
		left = l;
		pixels = p;
		loadedTime = lt;
	}
}
