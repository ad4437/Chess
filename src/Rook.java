import java.awt.Image;
import java.util.ArrayList;

public class Rook extends ChessPiece{
	boolean firstMove;
	public Rook(boolean stateInput) {
		super(stateInput, getBufferedImage(stateInput, "R"));
		firstMove = true;
	}

	
	
	public String toString() {
		return "R";
	}


	public boolean getMoveFirst() {
		return firstMove;
	}
	
	public void setFirstMove(boolean input) {
		firstMove = input;
	}
	
	public ArrayList<Space> getMoveableSpaces(Space start, Board board) {
		return getRookSpaces(start,board);
	}



	public ArrayList<Space> getCaptureableSpaces(Space start, Board board) {
		return getRookSpaces(start,board);
	}
	
	
	public static ArrayList<Space> getRookSpaces(Space start,Board board) {
		ArrayList<Space> spacesCanCapture = new ArrayList<Space>();
		spacesCanHelperRow(spacesCanCapture,start,board,1);
		spacesCanHelperRow(spacesCanCapture,start,board,-1);
		spacesCanHelperCol(spacesCanCapture,start,board,1);
		spacesCanHelperCol(spacesCanCapture,start,board,1);
		return null;
	}
	
	
	
	private static void spacesCanHelperRow(ArrayList<Space> spacesCanMove,Space start,Board board,final int ROW_INCREMENT) {
		final int UPPER_BOUNDS = 7;
		final int LOWER_BOUNDS = 0;
		int currentRow = start.getRow() + ROW_INCREMENT;
		int currentCol = start.getCol();
		
		while(currentRow >= LOWER_BOUNDS && currentRow <= UPPER_BOUNDS && board.getSpace(currentRow, currentCol) == null) {
			spacesCanMove.add(board.getSpace(currentRow, currentCol));
			currentRow += ROW_INCREMENT;
		}
		
		if((currentRow >= LOWER_BOUNDS && currentRow <= UPPER_BOUNDS) && (currentCol >= LOWER_BOUNDS && currentCol <= UPPER_BOUNDS) && isAvailable(start, board.getSpace(currentRow, currentCol))) {
			spacesCanMove.add(board.getSpace(currentRow, currentCol));
		}
		
	}
	
	private static void spacesCanHelperCol(ArrayList<Space> spacesCanMove,Space start,Board board,final int COL_INCREMENT) {
		final int UPPER_BOUNDS = 7;
		final int LOWER_BOUNDS = 0;
		int currentRow = start.getRow();
		int currentCol = start.getCol() + COL_INCREMENT;
		
		while(currentCol >= LOWER_BOUNDS && currentCol <= UPPER_BOUNDS && board.getSpace(currentRow, currentCol) == null) {
			spacesCanMove.add(board.getSpace(currentRow, currentCol));
			currentCol += COL_INCREMENT;
		}
		
		if((currentRow >= LOWER_BOUNDS && currentRow <= UPPER_BOUNDS) && (currentCol >= LOWER_BOUNDS && currentCol <= UPPER_BOUNDS) && isAvailable(start, board.getSpace(currentRow, currentCol))) {
			spacesCanMove.add(board.getSpace(currentRow, currentCol));
		}
	}
	
	
	/*
	public boolean canMove(Board board, Space start, Space end) {
		return Rook.rookMovement(board,start,end);
	}
	
	public static boolean rookMovement(Board board, Space start, Space end) {
		if(!(isAvailable(start, end))) return false;
		int rowDifference = Math.abs(end.getRow() - start.getRow());
		int colDifference = Math.abs(end.getCol() - start.getCol());
		final int INCREMENT;

		
		if(rowDifference != 0 && colDifference == 0) {
			if(end.getRow() < start.getRow()) {
				INCREMENT = -1;
			} else {
				INCREMENT = 1;
			}
			for(int i = start.getRow() + INCREMENT; i != end.getRow(); i += INCREMENT) {
				if(board.getSpace(i, start.getCol()).getPiece() != null) return false;
			}
			return true;
		} else if(rowDifference == 0 && colDifference != 0) {
			if(end.getCol() < start.getCol()) {
				INCREMENT = -1;
			} else {
				INCREMENT = 1;
			}
			for(int i = start.getCol() + INCREMENT; i != end.getCol(); i += INCREMENT) {
				if(board.getSpace(start.getRow(),i).getPiece() != null) return false;
			}
			return true;
		} else {
			return false;
		}
	}
	*/

}
