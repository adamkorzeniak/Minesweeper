import java.io.Serializable;
import javax.swing.*;

public class NewButton extends JButton implements Serializable{
	private boolean mine, flagged;
	private int bombNeighbours;
	
	public NewButton() {
		super();
	}
	public NewButton(String s) {
		super(s);
	}
	public NewButton(Icon icon) {
		super(icon);
	}
	
	public void setBombNeighbours(int bombs) throws Exception {
		if (bombs >= 0 && bombs < 9) {
		bombNeighbours = bombs;
		} else {
			throw new Exception("Amount of bombs incorrect");
		}
	}
	public int getBombNeighbours() {
		return bombNeighbours;
	}
	
	public void setFlagged() {
		flagged = true;
	}
	public void deFlag() {
		flagged = false;
	}
	public boolean isFlagged() {
		return flagged;
	}
	public void setMine(boolean isIt) {
		mine = isIt;
	}
	public boolean getMine() {
		return mine;
	}
	
}
