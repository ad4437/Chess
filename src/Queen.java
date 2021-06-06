import java.awt.Image;
import java.util.ArrayList;

public class Queen extends ChessPiece {

	public Queen(boolean stateInput) {
		super(stateInput, ChessPiece.getBufferedImage(stateInput,"Q"));
		
	}
		
	public String toString() {
		return "Q";
	}



	public ArrayList<Space> getMoveableSpaces(Space start, Board board) {
		return getCaptureableSpaces(start,board);
	}



	public ArrayList<Space> getCaptureableSpaces(Space start, Board board) {
		ArrayList<Space> spacesCanCapture = new ArrayList<Space>();
		ArrayList<Space> rookSpaces = Rook.getRookSpaces(start, board);
		ArrayList<Space> bishopSpaces = Bishop.getBishopSpaces(start, board);
		for(int i = 0; i < rookSpaces.size(); i++) {
			spacesCanCapture.add(rookSpaces.get(i));
		}
		for(int i = 0; i < bishopSpaces.size(); i++) {
			spacesCanCapture.add(bishopSpaces.get(i));
		}
		return spacesCanCapture;
	}
	
	public Piece copy() {
		 return new Queen(this.isWhite());
	}
	
	/* 
	 * Deprecated 
	public boolean canMove(Board board, Space start, Space end) {
		return(Rook.rookMovement(board, start, end) || Bishop.bishopMovement(board, start, end));
	}
	*/

}
