import java.awt.Image;

public class Knight extends ChessPiece {

	public Knight(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}


	public boolean canMove(Board board, Space start, Space end) {

		return false;
	}

}
