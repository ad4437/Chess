package game;

import java.util.ArrayList;

import pieces.*;
import pieces.Record;

public class Game {
	private Board board;
	private String state;
	private boolean turn;
	public int moveRuleCount;

	public Game() {
		board = new Board(false);
		turn = true;
		moveRuleCount = 0;
	}
	
	public boolean attemptMove(Space start, Space end) {
		if (start.getPiece() == null) return false;
		Piece endPiece = end.getPiece();
		if(board.movePiece(turn, start, end)) {
			if(start.getPiece() instanceof Pawn || endPiece != null ) {
				moveRuleCount = 0;
			} else {
				moveRuleCount++;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void nextTurn() {
		turn = !turn;
	}
	
	public boolean isInteractable(Space space) {
		if(space.getPiece() instanceof ChessPiece) {
			return(((ChessPiece)space.getPiece()).isWhite() == turn);
		}
		return false;
	}
	
	public Space getThisTurnPawnForTransform() {
		return board.getAnyPawnAtEnd(turn);
	}
	
	//userInput values: 1 = Queen, 2 = Rook, 3 = Bishop, 4 = Knight
	public void transformPawn(Space pawnSpace, int userInput) {
		board.pawnTransform(turn, pawnSpace, userInput);
	}
	
	public boolean isGameOver() {
		 if(board.isCheckmate(!turn)) {
			if(turn) state = "white wins";
			else state = "black wins";
			return true;
		} else if(moveRuleCount > 50 || hasInsufficientPieces() || hasNoMoreMoves(!turn)){
			state = "draw";
			return true;
		} else {
			return false;
		}
	}
	
	
	private boolean hasInsufficientPieces() {
		ArrayList<Space> whiteList = board.getWhiteSpacePieces();
		ArrayList<Space> blackList = board.getBlackSpacePieces();
		
		return(insufficientPiecesHelper(whiteList, blackList) || insufficientPiecesHelper(blackList, whiteList));
	}
	
	private boolean insufficientPiecesHelper(ArrayList<Space> arrayList1,ArrayList<Space> arrayList2) {
		if (arrayList1.size() == 1 && arrayList2.size() == 1) {
			return true;
		} else if (arrayList1.size() == 2 && arrayList2.size() == 1){
			return(board.getMinorPieceCount(arrayList1) == 1);
		} else if (arrayList1.size() == 3 && arrayList2.size() == 1) {
			return(board.getKnightPieceCount(arrayList1) == 2);
		} else if (arrayList1.size() == 2 && arrayList2.size() == 2) {
			return((board.getMinorPieceCount(arrayList1) == 1) && (board.getMinorPieceCount(arrayList2) == 1));
		} else {
			return false;
		}
	}


	private boolean hasNoMoreMoves(boolean color) {
		ArrayList<Space> arrayList = board.getColorSpacePieces(color);
		for(int i = 0; i < arrayList.size(); i++) {
			if(((ChessPiece)arrayList.get(i).getPiece()).getMoveableSpaces(arrayList.get(i),board).size() != 0) return false; 	
		}
		return true;
	}
	
	/*
	private boolean hasRepetition() {
		ArrayList<Record> record = board.getRecordings();
		
		if(record.size() < 6 || record.size() % 2 == 1) return false;
		
		return(hasRepetionHelper(record, record.size() - 1) && hasRepetionHelper(record, record.size() - 2));
	}
	
	private boolean hasRepetionHelper(ArrayList<Record> record, int index) {
		return(record.get(index).equals(record.get(index - 2)) && record.get(index).equals(record.get(index - 4)));
	}
	*/

	public int getMoveRuleCount() {
		return moveRuleCount;
	}
	
	public void reset() {
		board = new Board(false);
		turn = true;
	}
	
	public String getState() {
		return state;
	}
	
	public Board getBoard() {
		return board;
	}
}
