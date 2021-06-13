package pieces;

import game.*;
import java.awt.Image;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
	private boolean firstMove;
	public Pawn(boolean stateInput) {
		super(stateInput);
		firstMove = true;
	}

	
	public String toString() {
		return "Pawn";
	}

	public boolean getFirstMove() {
		return firstMove;
	}
	
	public void setFirstMove(boolean input) {
		firstMove = input;
	}
		
	public ArrayList<Space> getMoveableSpaces(Space start, Board board) {
		ArrayList<Space> spacesCanMove = new ArrayList<Space>();
		ArrayList<Space> spacesCanCapture = getCaptureableSpaces(start, board);
		final int INCREMENT;
		
		for(int i = 0; i < spacesCanCapture.size(); i++) {
			if(isInPassing(start,spacesCanCapture.get(i),board) || (spacesCanCapture.get(i).getPiece() != null && ((ChessPiece)spacesCanCapture.get(i).getPiece()).isWhite() != ((ChessPiece)start.getPiece()).isWhite())) {
				spacesCanMove.add(spacesCanCapture.get(i));
			}
		}
		
		if(isWhite()) {
			INCREMENT = -1;
		} else {
			INCREMENT = 1;
		}
		
		
		if(firstMove) {
			if( isWithinBounds(start.getRow() + INCREMENT) && (board.getSpace(start.getRow() + INCREMENT,start.getCol()).getPiece()) == null) {
				spacesCanMove.add(board.getSpace(start.getRow() + INCREMENT, start.getCol()));
				if( isWithinBounds((start.getRow() + (INCREMENT * 2))) && board.getSpace(start.getRow() + (INCREMENT * 2), start.getCol()).getPiece() == null) {
					spacesCanMove.add(board.getSpace(start.getRow() + (INCREMENT * 2), start.getCol()));
				}
			}
		} else {
			if( isWithinBounds(start.getRow() + INCREMENT) && (board.getSpace(start.getRow() + (INCREMENT),start.getCol()).getPiece()) == null) {
				spacesCanMove.add(board.getSpace(start.getRow() + INCREMENT, start.getCol()));
			}
		}
		
		return spacesCanMove;
	}

	private boolean isInPassing(Space start,Space end,Board board) {
		if(isWhite()) {
			if(6 - start.getRow() != 3) return false;
		} else {
			if(start.getRow() - 1 != 3) return false;
		}
		Piece piece = board.getSpace(start.getRow(), end.getCol()).getPiece();
		if(!(piece instanceof Pawn)) return false;
		if(((ChessPiece)piece).isWhite() == this.isWhite()) return false;
		if(board.pieceMoveCount(piece) != 1) return false;
		
		return true;
	}
	
	public ArrayList<Space> getCaptureableSpaces(Space start, Board board) {
		ArrayList<Space> spacesCanCapture = new ArrayList<Space>(); 
		final int INCREMENT;
		
		if(isWhite()) {
			INCREMENT = -1;
		} else {
			INCREMENT = 1;
		}
		
		if(isWithinBounds(start.getRow() + INCREMENT) && isWithinBounds(start.getCol() + 1)) {
			spacesCanCapture.add(board.getSpace(start.getRow() + INCREMENT, start.getCol() + 1));
		}
		if(isWithinBounds(start.getRow() + INCREMENT) && isWithinBounds(start.getCol() - 1)) {
			spacesCanCapture.add(board.getSpace(start.getRow() + INCREMENT, start.getCol() - 1));
		}
		
		return spacesCanCapture;
	}
	
	
	public Piece copy() {
		 return new Pawn(this.isWhite());
	}
	
	/*
	 * Deprecated
	public boolean canMove(Board board, Space start, Space end) {
		if(!(isAvailable(start, end))) {
			return false;
		}
		
		final int increment;
		
		if(isWhite()) {
			increment = -1;
		} else {
			increment = 1;
		}
		
		if(end.getPiece() != null) {
			return(start.getRow() + increment == end.getRow() && Math.abs(end.getCol() - start.getCol()) == 1);
		} else {
			if(firstMove) {
				if(start.getRow() + (increment * 2) == end.getRow() && start.getCol() == end.getCol()) {
					if(board.getSpace(start.getRow() + increment, start.getCol()).getPiece() != null) {
						return false;
					} else {
						return true;
					}
				} else {
					return (start.getRow() + increment == end.getRow() && start.getCol() == end.getCol());
				}
			} else {
				return(start.getRow() + increment == end.getRow() && start.getCol() == end.getCol());
			}
		}
	}
	*/




}
