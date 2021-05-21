import java.awt.Image;

public class Rook extends ChessPiece{

	public Rook(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
	}


	public boolean canMove(Board board, Space start, Space end) {
		return false;
	}

}
