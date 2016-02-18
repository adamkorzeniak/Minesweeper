import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Highscore implements Serializable {
	ArrayList<Double> easy, medium, expert;

	public Highscore(){
	}

	public Highscore(ArrayList<Double> e, ArrayList<Double> m, ArrayList<Double> ex) {
		easy = e;
		medium = m;
		expert = ex;
	}

	public void go() {
		easy = new ArrayList<Double>();
		expert = new ArrayList<Double>();
		medium = new ArrayList<Double>();
		
		for (int i = 0; i < 5; i++) {
			easy.add(99999.0);
			expert.add(99999.0);
			medium.add(99999.0);
		}
		try {
			FileOutputStream fileStream = new FileOutputStream("data/Highscores.sav");
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(easy);
			objectStream.writeObject(medium);
			objectStream.writeObject(expert);
			objectStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}