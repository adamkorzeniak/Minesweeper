import java.util.ArrayList;

import javax.swing.*;

public class NewButton extends JButton{
	boolean isMine, flagged, done;
	int bombNeighbours;
	ArrayList<NewButton> neighbours;
	
	public NewButton() {
		super();
	}
	public NewButton(String s) {
		super(s);
	}
	public NewButton(Icon icon) {
		super(icon);
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
}
