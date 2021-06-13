import game.*;
import pieces.*;

import java.util.ArrayList;

public class TestingMethods {
	public static boolean areEqual(ArrayList<Space> list1, ArrayList<Space> list2) {
		if(list1.size() != list2.size()) {
			return false;
		}
		for(int i = 0; i < list1.size(); i++) {	
			for(int j = 0; j < list2.size();j++) {
				if(list1.get(i).equals(list2.get(j))) break;
				if(j == list2.size() - 1) return false;
			}
		}
		return true;
	}
	
	public static Space addPieceToBoard(Piece piece,Board board,int row, int col) {
		Space s = board.getSpace(row,col);
		s.setPiece(piece);
		if(((ChessPiece) s.getPiece()).isWhite()) {
			board.getWhiteSpacePieces().add(s);
		} else {
		    board.getBlackSpacePieces().add(s);
		}
		return s;
	}
}
