import java.awt.Image;

public class Bishop extends ChessPiece{

	public Bishop(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}

	
	public boolean canMove(Board board, Space start, Space end) {
		
		return false;
	}

}
