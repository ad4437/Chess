import java.awt.Image;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
	private boolean firstMove;
	public Pawn(boolean stateInput) {
		super(stateInput, getBufferedImage(stateInput, "P"));
		firstMove = true;
	}

	
	public String toString() {
		return "P";
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
		final int LOWER_BOUNDS = 0;
		final int UPPER_BOUNDS = 7;
		final int INCREMENT;
		
		for(int i = 0; i < spacesCanCapture.size(); i++) {
			if(spacesCanCapture.get(i).getPiece() != null && ((ChessPiece)spacesCanCapture.get(i).getPiece()).isWhite() != ((ChessPiece)start.getPiece()).isWhite()) {
				spacesCanMove.add(spacesCanCapture.get(i));
			}
		}
		
		if(isWhite()) {
			INCREMENT = -1;
		} else {
			INCREMENT = 1;
		}
		
		
		if(firstMove) {
			if((start.getRow() + INCREMENT >= LOWER_BOUNDS && start.getRow() + INCREMENT <= UPPER_BOUNDS) && (board.getSpace(start.getRow() + INCREMENT,start.getCol()).getPiece()) == null) {
				spacesCanMove.add(board.getSpace(start.getRow() + INCREMENT, start.getCol()));
				if((start.getRow() + (INCREMENT * 2) >= LOWER_BOUNDS && start.getRow() + (INCREMENT * 2) <= UPPER_BOUNDS) && board.getSpace(start.getRow() + (INCREMENT * 2), start.getCol()).getPiece() == null) {
					spacesCanMove.add(board.getSpace(start.getRow() + (INCREMENT * 2), start.getCol()));
				}
			}
		} else {
			if((start.getRow() + INCREMENT >= LOWER_BOUNDS && start.getRow() + INCREMENT <= UPPER_BOUNDS) && (board.getSpace(start.getRow() + (INCREMENT * 2),start.getCol()).getPiece()) == null) {
				spacesCanMove.add(board.getSpace(start.getRow() + INCREMENT, start.getCol()));
			}
		}
		
		
		return spacesCanMove;
	}


	
	public ArrayList<Space> getCaptureableSpaces(Space start, Board board) {
		ArrayList<Space> spacesCanCapture = new ArrayList<Space>(); 
		final int INCREMENT;
		final int LOWER_BOUNDS = 0;
		final int UPPER_BOUNDS = 7;
		
		if(isWhite()) {
			INCREMENT = -1;
		} else {
			INCREMENT = 1;
		}
		
		if((start.getRow() + INCREMENT >= LOWER_BOUNDS && start.getRow() + INCREMENT <= UPPER_BOUNDS) && (start.getCol() + 1 >= LOWER_BOUNDS && start.getCol() + 1 <= UPPER_BOUNDS)) {
			spacesCanCapture.add(board.getSpace(start.getRow() + INCREMENT, start.getCol() + 1));
		}
		if((start.getRow() + INCREMENT >= LOWER_BOUNDS && start.getRow() + INCREMENT <= UPPER_BOUNDS) && (start.getCol() - 1  >= LOWER_BOUNDS && start.getCol() - 1 <= UPPER_BOUNDS)) {
			spacesCanCapture.add(board.getSpace(start.getRow() + INCREMENT, start.getCol() - 1));
		}
		
		return spacesCanCapture;
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
