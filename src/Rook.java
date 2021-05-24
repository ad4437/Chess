import java.awt.Image;

public class Rook extends ChessPiece{

	public Rook(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
	}


	public boolean canMove(Board board, Space start, Space end) {
		return Rook.rookMovement(board,start,end);
	}
	
	public static boolean rookMovement(Board board, Space start, Space end) {
		if(!(isAvailable(start, end))) return false;
		int rDiff = Math.abs(end.getRow() - start.getRow());
		int cDiff = Math.abs(end.getCol() - start.getCol());
		int increment;

		
		if(rDiff != 0 && cDiff == 0) {
			if(end.getRow() < start.getRow()) {
				increment = -1;
			} else {
				increment = 1;
			}
			for(int i = start.getRow() + increment; i != end.getRow(); i += increment) {
				if(board.getSpace(i, start.getCol()).getPiece() != null) return false;
			}
			return true;
		} else if(rDiff == 0 && cDiff != 0) {
			if(end.getCol() < start.getCol()) {
				increment = -1;
			} else {
				increment = 1;
			}
			for(int i = start.getCol() + increment; i != end.getCol(); i += increment) {
				if(board.getSpace(start.getRow(),i).getPiece() != null) return false;
			}
			return true;
		} else {
			return false;
		}
		

		
	}

}
