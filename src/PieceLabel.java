import javax.swing.*;

public class PieceLabel extends JLabel {
	private int r;
	private int c;

	public void setloc(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public int getRow() {
		return r;
	}
	
	public int getCol() {
		return c;
	}
}
