import java.awt.Image;

public class King extends ChessPiece {

	public King(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}

	public boolean canMove(Board board, Space start, Space end) {

		return false;
	}

}
