import java.awt.Image;
import java.util.ArrayList;

public class King extends ChessPiece {
	private boolean firstMove;
	public King(boolean stateInput) {
		super(stateInput, getBufferedImage(stateInput, "K"));
		firstMove = true;
	}
	
	public boolean canMove(Board board,Space start,Space end) {
		boolean inCheck = board.isCheck(isWhite(), start);
		if(end.getPiece() instanceof Rook && ((ChessPiece)end.getPiece()).isWhite() == this.isWhite() && !(inCheck) && !(board.isCheck(isWhite(), end))) {
			if(((Rook)end.getPiece()).getMoveFirst() == true && this.getFirstMove() == true) {
				if(((Rook)end.getPiece()).canMove(board, end, start)) {
							return true;
				}
			} 
			return false;
		} else {
			if(!(board.isCheck(isWhite(), end)) && board.simulateMoveForCheck(start, end)) {
				return false;
			}
			return(super.canMove(board, start, end));
		}
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}
	
	public void setFirstMove(boolean input) {
		firstMove = input;
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
	
	public Piece copy() {
		 return new King(this.isWhite());
	}
	
	
	/*
	 * Deprecated
	public boolean canMove(Board board, Space start, Space end) {
		boolean inCheck = board.isCheck(isWhite(), start);
		if(end.getPiece() instanceof Rook && ((ChessPiece)end.getPiece()).isWhite() == this.isWhite() && !(inCheck)) {
			if(((Rook)end.getPiece()).getMoveFirst() == true && this.getFirstMove() == true) {
				if(((Rook)end.getPiece()).canMove(board, end, start)) {
					return true;
				}
			} 
			return false;
		} else {
			ArrayList<Space> moveableSpaces = getMoveableSpaces(start,board);
			for(int i = 0; i < moveableSpaces.size(); i++) {
				if(moveableSpaces.get(i).equals(end)) return true;
			}
			return false;
		}
	}
	*/
}
