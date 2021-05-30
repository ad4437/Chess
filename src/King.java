import java.awt.Image;
import java.util.ArrayList;

public class King extends ChessPiece {

	public King(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}

	public boolean canMove(Board board, Space start, Space end) {

		return false;
	}

	public String toString() {
		return "K";
	}

	
	public ArrayList<Space> spacesCanMove(Space start, Board board) {
		
		return null;
	}


	public ArrayList<Space> spacesCanCapture(Space start, Board board) {
		
		return null;
	}
}
