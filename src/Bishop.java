import java.awt.Image;
import java.util.ArrayList;

public class Bishop extends ChessPiece{

	public Bishop(boolean stateInput) {
		super(stateInput, getBufferedImage(stateInput,"B") );
		
	}
	
	public String toString() {
		return "B";
	}
	
	public ArrayList<Space> getMoveableSpaces(Space start,Board board) {
		return getBishopSpaces(start,board);
	}
	


	public ArrayList<Space> getCaptureableSpaces(Space start,Board board) {
		return getBishopSpaces(start,board);
	}
	
	public static ArrayList<Space> getBishopSpaces(Space start, Board board) {
		ArrayList<Space> spacesCanCapture = new ArrayList<Space>();
		spacesHelper(spacesCanCapture,start,board,1,1); 
		spacesHelper(spacesCanCapture,start,board,-1,-1);
		spacesHelper(spacesCanCapture,start,board,-1,1);
		spacesHelper(spacesCanCapture,start,board,1,-1);
		return spacesCanCapture;
	}
	
	private static void spacesHelper(ArrayList<Space> spacesCanMove,Space start,Board board,final int ROW_INCREMENT, final int COL_INCREMENT) {
		final int UPPER_BOUNDS = 7;
		final int LOWER_BOUNDS = 0;
		int currentRow = start.getRow() + ROW_INCREMENT;
		int currentCol = start.getCol() + COL_INCREMENT;
		
		while((currentRow >= LOWER_BOUNDS && currentRow <= UPPER_BOUNDS) && (currentCol >= LOWER_BOUNDS && currentCol <= UPPER_BOUNDS) && board.getSpace(currentRow, currentCol).getPiece() == null) {
			spacesCanMove.add(board.getSpace(currentRow, currentCol));
			currentRow += ROW_INCREMENT;
			currentCol += COL_INCREMENT;
		}
		
		if((currentRow >= LOWER_BOUNDS && currentRow <= UPPER_BOUNDS) && (currentCol >= LOWER_BOUNDS && currentCol <= UPPER_BOUNDS) && isAvailable(start, board.getSpace(currentRow, currentCol))) {
			spacesCanMove.add(board.getSpace(currentRow, currentCol));
		}
		
	}
	


	public Piece copy() {
		return new Bishop(this.isWhite());
	}
	
	/*
	 * Deprecated
	public boolean canMove(Board board, Space start, Space end) {
		return Bishop.bishopMovement(board,start,end);
	}

	
	public static boolean bishopMovement(Board board,Space start,Space end) {
		if(!(isAvailable(start, end))) return false;
		int rowDifference = Math.abs(end.getRow() - start.getRow());
		int colDifference = Math.abs(end.getCol() - start.getCol());
		int currentRow = start.getRow();
		int currentCol = start.getCol();
		final int ROW_INCREMENT;
		final int COL_INCREMENT;
		
		if(rowDifference == colDifference) {
			if(end.getCol() < start.getCol()) {
				ROW_INCREMENT = -1;
				COL_INCREMENT = -1;
			} else {
				ROW_INCREMENT = 1;
				COL_INCREMENT = 1;
			}
		} else if((-1 * rowDifference) == colDifference) {
			if(end.getCol() < start.getCol()) {
				ROW_INCREMENT = 1;
				COL_INCREMENT = -1;
			} else {
				ROW_INCREMENT = -1;
				COL_INCREMENT = 1;
			}
		} else {
			return false;
		}
		
		currentRow += ROW_INCREMENT;
		currentCol += COL_INCREMENT;
		
		while(currentRow != end.getRow() && currentCol != end.getCol()) {
			if(board.getSpace(currentRow, currentCol).getPiece() != null) {
				return false;
			}
		}
		return true;
		
	}
	*/
	
}
