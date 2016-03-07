package com.github.adkorzen;

import java.io.*;
import java.util.ArrayList;

/**
 * <b>Highscore</b> <br>
 * <br>
 * Responsible for showing and updating highscore results.
 * 
 * @author Adam Korzeniak
 */
public class Highscore implements Serializable {
	private static final long serialVersionUID = -1294704510001995826L;
	private ArrayList<Double> easyList, mediumList, expertList;

	/**
	 * <b>setHighscoreList</b> <br>
	 * <br>
	 * <i>public void setHighscoreList(String difficulty, ArrayList
	 * <Double> highscoreList)</i> <br>
	 * <br>
	 * Updates given highscore list.
	 * 
	 * @param difficulty
	 *            - which difficulty highscore list to update
	 * @param highscoreList
	 *            - new highscore list overriding previous one
	 */
	public void setHighscoreList(String difficulty, ArrayList<Double> highscoreList) {
		if (difficulty == "easy") {
			easyList = highscoreList;
		} else if (difficulty == "medium") {
			mediumList = highscoreList;
		} else if (difficulty == "expert") {
			expertList = highscoreList;
		}
	}

	/**
	 * <b>getHighscoreList</b> <br>
	 * <br>
	 * <i>public ArrayList<Double> getHighscoreList(String highscoreList)</i>
	 * <br>
	 * <br>
	 * Returns highscore list of given difficulty.
	 * 
	 * @param highscoreList
	 *            - which difficulty list to return
	 * @throws Exception
	 */
	public ArrayList<Double> getHighscoreList(String highscoreList) throws Exception {
		if (highscoreList == "easy") {
			return easyList;
		} else if (highscoreList == "medium") {
			return mediumList;
		} else if (highscoreList == "expert") {
			return expertList;
		} else {
			throw new Exception("There is no such highscoreList as " + highscoreList);
		}
	}

	/**
	 * <b>Highscore</b> <br>
	 * <br>
	 * <i>public Highscore()</i> <br>
	 * <br>
	 * Creates Highscore object that fills highscore lists with default (99999
	 * sec) results
	 */
	public Highscore() {
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

	/**
	 * <b>Highscore</b> <br>
	 * <br>
	 * <i>public Highscore(ArrayList<Double> e, ArrayList<Double> m, ArrayList
	 * <Double> ex)</i> <br>
	 * <br>
	 * Creates Highscore object that fills highscore lists with values passed in
	 * parameters
	 * 
	 * @param e
	 *            - list that holds results from easy difficulty
	 * @param m
	 *            - list that holds results from medium difficulty
	 * @param ex
	 *            - list that holds results from expert difficulty
	 */
	public Highscore(ArrayList<Double> e, ArrayList<Double> m, ArrayList<Double> ex) {
		easyList = e;
		mediumList = m;
		expertList = ex;
	}
}