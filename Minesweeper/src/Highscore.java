import java.io.*;
import java.util.ArrayList;

public class Highscore implements Serializable {
	private ArrayList<Double> easy, medium, expert;
	
	public void setHighscoreList(String difficulty, ArrayList<Double> highscoreList) {
		if(difficulty == "easy") {
			easy = highscoreList;
		} else if(difficulty == "medium") {
			medium = highscoreList;
		} else if(difficulty == "expert") {
			expert = highscoreList;
		}
	}
	
	public ArrayList<Double> getHighscoreList(String highscoreList) throws Exception{
		if(highscoreList == "easy") {
			return easy;
		} else if(highscoreList == "medium") {
			return medium;
		} else if(highscoreList == "expert") {
			return expert;
		} else {
			throw new Exception("There is no such highscoreList as " + highscoreList);
		}
	}

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