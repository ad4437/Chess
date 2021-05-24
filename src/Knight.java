import java.awt.Image;

public class Knight extends ChessPiece {

	public Knight(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}


	public boolean canMove(Board board, Space start, Space end) {
		if(!(isAvailable(start, end))) return false;
		
		int rDiff = Math.abs(end.getRow() - start.getRow());
		int cDiff = Math.abs(end.getCol() - start.getCol());
		
		if(rDiff == 1 && cDiff == 2 || rDiff == 2 && cDiff == 1) return true;
		else return false;
		
	}
	

}
