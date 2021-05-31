import java.awt.Image;
import java.util.ArrayList;

public class King extends ChessPiece {

	public King(boolean stateInput, Image imageInput) {
		super(stateInput, imageInput);
		
	}

	public boolean canMove(Board board, Space start, Space end) {
		boolean inCheck = board.isCheck(isWhite(), start);
		return false;
	}

	public String toString() {
		return "K";
	}

	
	public ArrayList<Space> getMoveableSpaces(Space start, Board board) {
		return getCaptureableSpaces(start,board);
	}


	public ArrayList<Space> getCaptureableSpaces(Space start, Board board) {
		ArrayList<Space> spacesCanCapture = new ArrayList<Space>();
		
		spacesHelper(spacesCanCapture,start,board,-1,-1);
		spacesHelper(spacesCanCapture,start,board,-1,0);
		spacesHelper(spacesCanCapture,start,board,-1,1);
		
		spacesHelper(spacesCanCapture,start,board,0,1);
		spacesHelper(spacesCanCapture,start,board,0,-1);
		
		spacesHelper(spacesCanCapture,start,board,1,-1);
		spacesHelper(spacesCanCapture,start,board,1,0);
		spacesHelper(spacesCanCapture,start,board,1,1);
		
		return spacesCanCapture;
	}
	
	private void spacesHelper(ArrayList<Space> spacesCanCapture,Space start, Board board,final int ROW_ADDENDS, final int COL_ADDENDS) {
		final int UPPER_BOUNDS = 7;
		final int LOWER_BOUNDS = 0;
		int currentCol = start.getRow() + ROW_ADDENDS;
		int currentRow = start.getCol() + COL_ADDENDS;
		if((currentRow >= LOWER_BOUNDS && currentCol <= UPPER_BOUNDS) && (currentCol >= LOWER_BOUNDS && currentCol <= UPPER_BOUNDS) && isAvailable(start,board.getSpace(currentRow, currentCol))) {
			spacesCanCapture.add(board.getSpace(currentRow, currentCol));
		}
	}
}
