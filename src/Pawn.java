import java.awt.Image;

public class Pawn extends ChessPiece {

	public Pawn(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
	}


	public boolean canMove(Board board, Space start, Space end) {
		return false;
	}

}
