import java.awt.Image;

public class Bishop extends ChessPiece{

	public Bishop(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}

	
	public boolean canMove(Board board, Space start, Space end) {
		return Bishop.bishopMovement(board,start,end);
	}

	
	public static boolean bishopMovement(Board board,Space start,Space end) {
		if(!(isAvailable(start, end))) return false;
		int rDiff = Math.abs(end.getRow() - start.getRow());
		int cDiff = Math.abs(end.getCol() - start.getCol());
		int currRow = start.getRow();
		int currCol = start.getCol();
		int rIncrement;
		int cIncrement;
		
		if(rDiff == cDiff) {
			if(end.getCol() < start.getCol()) {
				rIncrement = -1;
				cIncrement = -1;
			} else {
				rIncrement = 1;
				cIncrement = 1;
			}
		} else if((-1 * rDiff) == cDiff) {
			if(end.getCol() < start.getCol()) {
				rIncrement = 1;
				cIncrement = -1;
			} else {
				rIncrement = -1;
				cIncrement = 1;
			}
		} else {
			return false;
		}
		
		currRow += rIncrement;
		currCol += cIncrement;
		
		while(currRow != end.getRow() && currCol != end.getCol()) {
			if(board.getSpace(currRow, currCol).getPiece() != null) {
				return false;
			}
		}
		return true;
		
	}
}
