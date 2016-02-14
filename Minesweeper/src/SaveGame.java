import java.io.Serializable;

public class SaveGame implements Serializable{
	String highscore;
	int width, height, mines, minesAtStart, left;
	
	public SaveGame(String hscore, int w, int h, int m, int mAtStart, int l) {
		highscore = hscore;
		width = w;
		height = h;
		mines = m;
		minesAtStart = mAtStart;
		left = l;
	}
}
