import java.awt.Image;
import java.util.ArrayList;

public class Knight extends ChessPiece {

	public Knight(boolean stateInput) {
		super(stateInput, getBufferedImage(stateInput, "N"));
		
	}

	
	public String toString() {
		return "N";
	}



	public ArrayList<Space> getMoveableSpaces(Space start, Board board) {
		return getCaptureableSpaces(start,board);
	}



	public ArrayList<Space> getCaptureableSpaces(Space start, Board board) {
		ArrayList<Space> spacesCanCapture = new ArrayList<Space>();
		spacesHelper(spacesCanCapture,start,board,1,3);
		spacesHelper(spacesCanCapture,start,board,-1,3);
		spacesHelper(spacesCanCapture,start,board,1,-3);
		spacesHelper(spacesCanCapture,start,board,-1,-3);
		spacesHelper(spacesCanCapture,start,board,3,1);
		spacesHelper(spacesCanCapture,start,board,-3,1);
		spacesHelper(spacesCanCapture,start,board,3,-1);
		spacesHelper(spacesCanCapture,start,board,-3,-1);
		return spacesCanCapture;
	}
		
	private void spacesHelper(ArrayList<Space> spacesCanMove,Space start, Board board,final int ROW_ADDENDS, final int COL_ADDENDS) {
		final int UPPER_BOUNDS = 7;
		final int LOWER_BOUNDS = 0;
		Space end = board.getSpace(start.getRow() + ROW_ADDENDS, start.getCol() + COL_ADDENDS);
		if((end.getCol() >= LOWER_BOUNDS && end.getCol() <= UPPER_BOUNDS) && (end.getRow() >= LOWER_BOUNDS && end.getRow() <= UPPER_BOUNDS) && isAvailable(start, end)) {
			spacesCanMove.add(end);
		}
	}
	
	
	/*
	 * Deprecated
	public boolean canMove(Board board, Space start, Space end) {
		if(!(isAvailable(start, end))) return false;
		
		int rowDifference = Math.abs(end.getRow() - start.getRow());
		int colDifference = Math.abs(end.getCol() - start.getCol());
		
		if(rowDifference == 1 && colDifference == 2 || rowDifference == 2 && colDifference == 1) return true;
		else return false;
		
	}
	*/
	

}
