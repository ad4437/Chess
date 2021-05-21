import java.awt.Image;

public class Piece {
	
	private Image s;
	
	
	public Piece() { 
		s = null;
	}
	
	public Piece(Image imageInput) {
		s = imageInput;
	}
	
	public Image getImage() {
		return s;
	}
	
}
