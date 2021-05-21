import java.awt.Image;

public class Queen extends ChessPiece {

	public Queen(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}


	public boolean canMove(Board board, Space start, Space end) {

		return false;
	}

}
