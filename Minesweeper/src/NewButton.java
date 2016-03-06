import java.io.Serializable;
import javax.swing.*;

public class NewButton extends JButton implements Serializable{
	private boolean isMine, isFlagged;
	private int amountOfBombNeighbours;
	
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
		amountOfBombNeighbours = bombs;
		} else {
			throw new Exception("Amount of bombs incorrect");
		}
	}
	public int getBombNeighbours() {
		return amountOfBombNeighbours;
	}
	
	public void setFlagged() {
		isFlagged = true;
	}
	public void deFlag() {
		isFlagged = false;
	}
	public boolean isFlagged() {
		return isFlagged;
	}
	public void setMine(boolean isIt) {
		isMine = isIt;
	}
	public boolean getMine() {
		return isMine;
	}
	
}
