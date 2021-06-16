package pieces;

import game.*;
import java.awt.Image;
import java.util.ArrayList;

public class King extends ChessPiece {
	private boolean firstMove;
	public King(boolean stateInput) {
		super(stateInput);
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
			if( (!(board.isCheck(isWhite(), end))) && isAvailable(start,end)) ) {
				return(super.canMove(board, start, end));
			}
			return false;
		}
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}
	
	public void setFirstMove(boolean input) {
		firstMove = input;
	}

	public String toString() {
		return "king";
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
	
	private void spacesHelper(ArrayList<Space> spacesCanCapture, Space start, Board board,final int ROW_ADDENDS, final int COL_ADDENDS) {
		int currentRow = start.getRow() + ROW_ADDENDS;
		int currentCol = start.getCol() + COL_ADDENDS;
		if(isWithinBounds(currentRow) && isWithinBounds(currentCol) && isAvailable(start,board.getSpace(currentRow, currentCol))) {
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
