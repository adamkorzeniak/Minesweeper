import java.io.*;
import java.util.ArrayList;

public class Highscore implements Serializable {
	private ArrayList<Double> easyList, mediumList, expertList;
	
	public void setHighscoreList(String difficulty, ArrayList<Double> highscoreList) {
		if(difficulty == "easy") {
			easyList = highscoreList;
		} else if(difficulty == "medium") {
			mediumList = highscoreList;
		} else if(difficulty == "expert") {
			expertList = highscoreList;
		}
	}
	
	public ArrayList<Double> getHighscoreList(String highscoreList) throws Exception{
		if(highscoreList == "easy") {
			return easyList;
		} else if(highscoreList == "medium") {
			return mediumList;
		} else if(highscoreList == "expert") {
			return expertList;
		} else {
			throw new Exception("There is no such highscoreList as " + highscoreList);
		}
	}

	public Highscore(){
	}

	public Highscore(ArrayList<Double> e, ArrayList<Double> m, ArrayList<Double> ex) {
		easyList = e;
		mediumList = m;
		expertList = ex;
	}

	public void go() {
		easyList = new ArrayList<Double>();
		expertList = new ArrayList<Double>();
		mediumList = new ArrayList<Double>();
		
		for (int i = 0; i < 5; i++) {
			easyList.add(99999.0);
			expertList.add(99999.0);
			mediumList.add(99999.0);
		}
		try {
			FileOutputStream fileStream = new FileOutputStream("data/Highscores.sav");
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(easyList);
			objectStream.writeObject(mediumList);
			objectStream.writeObject(expertList);
			objectStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}