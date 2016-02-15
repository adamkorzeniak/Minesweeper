import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import javax.swing.*;

public class Minesweeper {

	Minesweeper game;
	boolean isSet;
	NewButton newGame, loadGame, initiate, reGame;
	JFrame frame;
	ButtonGroup difficulty;
	JRadioButton easy, pro, expert, custom;
	JPanel jradio, text, input, gameNorth;
	JTextField wText, hText, mText;
	JLabel w, h, m, timerLabel, minesLeft;
	int width, height, mines, heightM, widthM, minesAtStart, left;
	String error, highscore;
	ArrayList<NewButton> buttons, toCheck;
	GridLayout grid;
	JPanel field;
	MouseListener bl, fl;
	ArrayList<Integer> chooseFrom, chosen, neighbours;
	SaveGame saveGame;

	public static void main(String[] args) {
		Minesweeper game = new Minesweeper();
		game.preMainMenu();
	}

	public void preMainMenu() {
		newGame = new NewButton("New Game");
		loadGame = new NewButton("Load Game");
		newGame.addActionListener(new NewGameListener());
		loadGame.addActionListener(new LoadGameListener());

		frame = new JFrame("(Almost) The Best Minesweeper Game");
		frame.add(newGame, BorderLayout.CENTER);
		frame.add(loadGame, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setResizable(false);
		frame.setVisible(true);
		
		try {
			FileInputStream fileStream = new FileInputStream("GameSet.sav");
			ObjectInputStream is = new ObjectInputStream(fileStream);
			saveGame = (SaveGame) is.readObject();
			is.close();
			width = saveGame.width;
		} catch (Exception ex) {
			ex.printStackTrace();}
		if(!(width > 0)) {
			loadGame.setEnabled(false);
		}
	}

	public void mainMenu() {

		frame.getContentPane().removeAll();
		easy = new JRadioButton("Easy - 9x9 grid, 10 mines");
		pro = new JRadioButton("Pro - 16x16 grid, 40 mines");
		expert = new JRadioButton("Expert - 30x16 grid, 99 mines");
		custom = new JRadioButton("Custom");
		easy.setSelected(true);
		easy.addActionListener(new CustomListener());
		pro.addActionListener(new CustomListener());
		expert.addActionListener(new CustomListener());
		custom.addActionListener(new CustomListener());

		difficulty = new ButtonGroup();
		difficulty.add(easy);
		difficulty.add(pro);
		difficulty.add(expert);
		difficulty.add(custom);

		jradio = new JPanel();
		jradio.setLayout(new BoxLayout(jradio, BoxLayout.Y_AXIS));
		jradio.add(easy);
		jradio.add(pro);
		jradio.add(expert);
		jradio.add(custom);

		w = new JLabel("Width (9-30): ");
		h = new JLabel("Height (9-24): ");
		m = new JLabel("Mines (10-667): ");

		wText = new JTextField("");
		hText = new JTextField("");
		mText = new JTextField("");
		wText.setEditable(false);
		hText.setEditable(false);
		mText.setEditable(false);

		input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		input.add(w);
		input.add(wText);
		input.add(h);
		input.add(hText);
		input.add(m);
		input.add(mText);

		initiate = new NewButton("Start Game");
		initiate.setFont(new Font("SansSerif", Font.BOLD, 26));
		initiate.addActionListener(new InitiateListener());

		frame.add(initiate, BorderLayout.SOUTH);
		frame.add(input, BorderLayout.CENTER);
		frame.add(jradio, BorderLayout.NORTH);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public void loadGame() {
		buttons = new ArrayList<NewButton>();
		try {
			FileInputStream fileStream = new FileInputStream("Game.sav");
			ObjectInputStream is = new ObjectInputStream(fileStream);
			buttons = (ArrayList<NewButton>) is.readObject();
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();}

		System.out.println(left);
		
		frame.getContentPane().removeAll();
		timerLabel = new JLabel("Amount of sec: ");
		mines = saveGame.mines;
		minesLeft = new JLabel("Mines left: " + mines);
		gameNorth = new JPanel();
		gameNorth.add(timerLabel);
		gameNorth.add(minesLeft);
		grid = new GridLayout(saveGame.height, saveGame.width);
		grid.setHgap(1);
		grid.setVgap(1);
		field = new JPanel(grid);
		for (NewButton a: buttons) {
			field.add(a);
			if(a.isFlagged()){
				fl = new FlagListener();
				a.addMouseListener(fl);
			} else {
			bl = new ButtonListener();
			a.addMouseListener(bl);}
		}
		left = saveGame.left;
		minesAtStart = saveGame.minesAtStart;
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		isSet = true;
		frame.addWindowListener(new CloseFrameListener());
		frame.add(field, BorderLayout.CENTER);
		frame.add(gameNorth, BorderLayout.NORTH);
		frame.setSize(45 * saveGame.width, 45 * saveGame.height + 40);
		frame.setVisible(true);
	}
	public void saveGame() {
		try {
			FileOutputStream fileStream = new FileOutputStream("Game.sav");
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(buttons);
			objectStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		SaveGame saveGame = new SaveGame(highscore, width, height, mines, minesAtStart, left);
		try {
			FileOutputStream fileStream = new FileOutputStream("GameSet.sav");
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(saveGame);
			objectStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();}
		System.out.println(left);
	}

	public void setData() {
		if (easy.isSelected()) {
			width = 9;
			height = 9;
			mines = 10;
			highscore = "easy";
			setUpGame();
		} else if (pro.isSelected()) {
			width = 16;
			height = 16;
			mines = 40;
			highscore = "pro";
			setUpGame();
		} else if (expert.isSelected()) {
			width = 30;
			height = 16;
			mines = 99;
			highscore = "expert";
			setUpGame();
		} else {
			width = Integer.parseInt(wText.getText());
			height = Integer.parseInt(hText.getText());
			mines = Integer.parseInt(mText.getText());

			if ((width > 8 && width < 31) && (height > 8 && height < 25)
					&& (mines > 9 && mines < (width - 1) * (height - 1) + 1)) {
				setUpGame();
			} else {
				error = "";
				if (width < 9 || width > 30) {
					error += "Width out of bounds. Choose beetween 9 and 30\n";
					if (width < 9) {
						widthM = 9;
						wText.setText("9");
					} else {
						widthM = 30;
						wText.setText("30");
					}
				}
				if (height < 9 || height > 24) {
					error += "Height out of bounds. Choose beetween 9 and 24\n";
					if (height < 9) {
						heightM = 9;
						hText.setText("9");
					} else {
						heightM = 24;
						hText.setText("24");
					}
				}
				if (mines < 10 || mines > (widthM - 1) * (heightM - 1)) {
					if ((width > 8 && width < 31) && (height > 8 && height < 25)) {
						error += "Mines range depends on size. For current size choose beetween 10 and "
								+ (width - 1) * (height - 1);
						if (mines < 10) {
							mText.setText("10");
						} else {
							int text = (widthM - 1) * (heightM - 1);
							mText.setText(Integer.toString(text));
						}
					} else {
						error += "Mines out of bounds. Choose beetween 10 and 667";
						if (mines < 10) {
							mText.setText("10");
						} else {
							int text = (widthM - 1) * (heightM - 1);
							mText.setText(Integer.toString(text));
						}
					}
				}
				JOptionPane.showMessageDialog(null, error);
				width = 0;
				height = 0;
				mines = 0;
			}
		}
		minesAtStart = mines;
		left = width * height;
	}

	public void setUpGame() {
		frame.getContentPane().removeAll();
		timerLabel = new JLabel("Amount of sec: ");
		minesLeft = new JLabel("Mines left: " + mines);
		gameNorth = new JPanel();
		gameNorth.add(timerLabel);
		gameNorth.add(minesLeft);

		buttons = new ArrayList<NewButton>();
		grid = new GridLayout(height, width);
		grid.setHgap(1);
		grid.setVgap(1);
		field = new JPanel(grid);
		for (int i = 0; i < width * height; i++) {
			NewButton a = new NewButton();
			// a.setFont(new Font("SansSerif", Font.PLAIN, 12));
			buttons.add(a);
			field.add(a);
			bl = new ButtonListener();
			a.addMouseListener(bl);
		}
		if (isSet) {
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		frame.addWindowListener(new CloseFrameListener());
		frame.add(field, BorderLayout.CENTER);
		frame.add(gameNorth, BorderLayout.NORTH);
		frame.setSize(45 * width, 45 * height + 40);
		frame.setVisible(true);
	}
	
	public void loadSetUpGame() {
	}

	public void reveal(NewButton b) {
		System.out.println(buttons.indexOf(b));
		if (b.isMine) {
			bust();
		} else if (b.bombNeighbours > 0) {
			b.setText(Integer.toString(b.bombNeighbours));
			if (b.isEnabled() && !b.isFlagged()) {
				b.setEnabled(false);
				left--;
			}
		} else {
			if (b.isEnabled()) {
				b.setEnabled(false);
				left--;
			}
			neighbours(buttons.indexOf(b));
			for (int a : neighbours) {
				if (buttons.get(a).isEnabled()) {
					buttons.get(a).setEnabled(false);
					left--;
					if (buttons.get(a).bombNeighbours > 0) {
						buttons.get(a).setText(Integer.toString(buttons.get(a).bombNeighbours));
					} else {
						reveal(buttons.get(a));
					}
				}
			}
		}
	}

	public void bust() {
		System.out.println("done");
		for (NewButton b : buttons) {
			b.setEnabled(false);
			if (b.isMine) {
				b.setText("X");
			} else if (b.bombNeighbours>0){
				b.setText(Integer.toString(b.bombNeighbours));
			}
		}
		JOptionPane.showOptionDialog(null, "Przegra³eœ, Ty Lamusie", "Przegrana", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);
		setUpNextGame();
	}

	public void setUpNextGame() {
		isSet = false;
		frame.getContentPane().removeAll();
		reGame = new NewButton("Play New Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(reGame);
		reGame.addActionListener(new NewGameListener());
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public NewButton getButton(int x, int y) {
		int index = x + width * (y - 1) - 1;
		return buttons.get(index);
	}

	public int getIndex(int x, int y) {
		return x + width * (y - 1) - 1;
	}

	public int getX(NewButton b) {
		int index = buttons.indexOf(b);
		return (index) % width + 1;
	}

	public int getX(int index) {
		return (index) % width + 1;
	}

	public int getY(NewButton b) {
		double index = buttons.indexOf(b);
		return (int) ((index / width + 1));
	}

	public int getY(int index) {
		return (int) (int) ((index / width + 1));
	}

	public void neighbours(int index) {
		int x = getX(index);
		int y = getY(index);
		neighbours = new ArrayList<Integer>();
		if (x > 1 && x < width && y > 1 && y < height) {
			neighbours.add(index + width + 1);
			neighbours.add(index + width);
			neighbours.add(index + width - 1);
			neighbours.add(index + 1);
			neighbours.add(index);
			neighbours.add(index - 1);
			neighbours.add(index - width + 1);
			neighbours.add(index - width);
			neighbours.add(index - width - 1);
		} else if (x == 1 && y == 1) {
			neighbours.add(index + width + 1);
			neighbours.add(index + width);
			neighbours.add(index + 1);
			neighbours.add(index);
		} else if (x == width && y == height) {
			neighbours.add(index);
			neighbours.add(index - 1);
			neighbours.add(index - width);
			neighbours.add(index - width - 1);
		} else if (x == 1 && y == height) {
			neighbours.add(index + 1);
			neighbours.add(index);
			neighbours.add(index - width + 1);
			neighbours.add(index - width);
		} else if (x == width && y == 1) {
			neighbours.add(index + width);
			neighbours.add(index + width - 1);
			neighbours.add(index);
			neighbours.add(index - 1);
		} else if (x == 1) {
			neighbours.add(index + width + 1);
			neighbours.add(index + width);
			neighbours.add(index + 1);
			neighbours.add(index);
			neighbours.add(index - width + 1);
			neighbours.add(index - width);
		} else if (x == width) {
			neighbours.add(index + width);
			neighbours.add(index + width - 1);
			neighbours.add(index);
			neighbours.add(index - 1);
			neighbours.add(index - width);
			neighbours.add(index - width - 1);
		} else if (y == 1) {
			neighbours.add(index + width + 1);
			neighbours.add(index + width);
			neighbours.add(index + width - 1);
			neighbours.add(index + 1);
			neighbours.add(index);
			neighbours.add(index - 1);
		} else if (y == height) {
			neighbours.add(index + 1);
			neighbours.add(index);
			neighbours.add(index - 1);
			neighbours.add(index - width + 1);
			neighbours.add(index - width);
			neighbours.add(index - width - 1);
		}
	}

	public class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			mainMenu();
		}
	}

	public class LoadGameListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			loadGame();
		}
	}

	public class CustomListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (custom.isSelected()) {
				wText.setEditable(true);
				hText.setEditable(true);
				mText.setEditable(true);
			} else {
				wText.setEditable(false);
				hText.setEditable(false);
				mText.setEditable(false);
			}
		}
	}

	public class InitiateListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			
			File file = new File("GameSet.sav");
			try {
				Files.deleteIfExists(file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			setData();
		}
	}

	public class ButtonListener implements MouseListener {

		public void mouseClicked(MouseEvent m) {
		}

		public void mouseEntered(MouseEvent m) {
		}

		public void mouseExited(MouseEvent m) {
		}

		public void mousePressed(MouseEvent m) {
			if (m.getButton() == MouseEvent.BUTTON1 && !isSet) {
				isSet = true;
				m.getComponent().setEnabled(false);
				left--;
				chooseFrom = new ArrayList<Integer>();
				chosen = new ArrayList<Integer>();
				int index = buttons.indexOf(m.getComponent());
				for (int i = 0; i < width * height; i++) {
					chooseFrom.add(i);
				}
				neighbours(index);
				for (int i : neighbours) {
					chooseFrom.remove(i);
				}
				neighbours = null;
				for (int i = 0; i < mines; i++) {
					int inde = (int) (Math.random() * chooseFrom.size());
					chosen.add(chooseFrom.get(inde));
					buttons.get(chooseFrom.get(inde)).isMine = true;
					chooseFrom.remove(chooseFrom.get(inde));
				}
				for (NewButton b : buttons) {
					int inde = buttons.indexOf(b);
					int count = 0;
					neighbours(inde);
					for (int i : neighbours) {
						if (buttons.get(i).isMine == true) {
							count++;
						}
						b.bombNeighbours = count;
					}
					neighbours = null;
				}
				reveal((NewButton) (m.getComponent()));
			} else if (isSet && m.getButton() == MouseEvent.BUTTON1 && m.getComponent().isEnabled()) {
				NewButton butt = (NewButton) (m.getComponent());
				if (!butt.isFlagged()) {
					reveal((NewButton) (m.getComponent()));
				}
			} else if (isSet && m.getButton() == MouseEvent.BUTTON3) {
				if (m.getComponent().isEnabled()) {
					NewButton flag = (NewButton) (m.getComponent());
					if (!flag.isFlagged()) {
						flag.setFlagged();
						flag.setText("!!");
						flag.done = true;
						mines--;
						minesLeft.setText("Mines left: " + mines);
						flag.removeMouseListener(bl);
						fl = new FlagListener();
						flag.addMouseListener(fl);
					}
				}
			}
			if (minesAtStart == left) {
				JOptionPane.showMessageDialog(null, "Gratulacje, mia³eœ farta");
				setUpNextGame();
			}
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

	public class FlagListener implements MouseListener {
		public void mouseClicked(MouseEvent m) {
		}

		public void mouseEntered(MouseEvent m) {
		}

		public void mouseExited(MouseEvent m) {
		}

		public void mousePressed(MouseEvent m) {
			if (isSet && m.getButton() == MouseEvent.BUTTON3) {
				if (m.getComponent().isEnabled()) {
					NewButton flag = (NewButton) (m.getComponent());
					if (flag.isFlagged()) {
						flag.deFlag();
						flag.setText("");
						flag.done = true;
						mines++;
						minesLeft.setText("Mines left: " + mines);
						flag.removeMouseListener(fl);
						flag.addMouseListener(bl);
					}
				}
			}
		}

		public void mouseReleased(MouseEvent m) {
		}
	}

	public class CloseFrameListener implements WindowListener {

		public void windowActivated(WindowEvent arg0) {
		}

		public void windowClosed(WindowEvent arg0) {
		}

		public void windowClosing(WindowEvent arg0) {
			if (isSet) {
				int result = JOptionPane.showConfirmDialog(null, "Save game?", "Do you want to save game",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					saveGame();
					System.exit(0);
				} else if (result == JOptionPane.NO_OPTION) {
					File file = new File("GameSet.sav");
					try {
						Files.deleteIfExists(file.toPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.exit(0);
				} else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
				}
			}
		}

		public void windowDeactivated(WindowEvent arg0) {
		}

		public void windowDeiconified(WindowEvent arg0) {
		}

		public void windowIconified(WindowEvent arg0) {
		}

		public void windowOpened(WindowEvent arg0) {
		}
	}

}
