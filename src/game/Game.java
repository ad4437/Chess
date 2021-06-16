package game;

import java.util.ArrayList;

import pieces.*;

public class Game {
	private Board board;
	private String state;
	private boolean turn;
	private boolean whiteSurrendered = false;
	private boolean blackSurrendered = false;
	public int moveRuleCount;

	public Game() {
		board = new Board(false);
		turn = true;
		moveRuleCount = 0;
	}
	
	public boolean attemptMove(Space start, Space end) {
		if (start.getPiece() == null) return false;
		
		Piece startPiece = start.getPiece();
		Piece endPiece = end.getPiece();
		if(board.movePiece(turn, start, end)) {
			if(startPiece instanceof Pawn || endPiece != null ) {
				moveRuleCount = 0;
			} else {
				moveRuleCount++;
			}
			return true;
		} else {
			return false;
		}
	}
	
	//precondition use after attemptMove returns true
	public boolean pieceCanTransform(Space end) {
		if(!(end.getPiece() instanceof Pawn)) return false;
		
		if(turn == true) {
			return(end.getRow() == 0);
		} else {
			return(end.getRow() == 7); 
		}
	}
	
	public boolean kingInCheck() {
		return(board.isCheck(turn,getKingSpace()));
	}
	
	public Space getKingSpace() {
		ArrayList<Space> currentTurnPieces =  board.getColorSpacePieces(turn);
		return(board.findKingSpace(currentTurnPieces));
	}
	
	public void nextTurn() {
		turn = !turn;
	}
	
	public boolean isWhiteTurn() {
		return turn;
	}
	
	public boolean isInteractable(Space space) {
		if(space.getPiece() instanceof ChessPiece) {
			return(((ChessPiece)space.getPiece()).isWhite() == turn);
		}
		return false;
	}
	
	//userInput values: 1 = Queen, 2 = Rook, 3 = Bishop, 4 = Knight
	public void transformPawn(Space pawnSpace, int userInput) {
		board.pawnTransform(turn, pawnSpace, userInput);
	}
	
	public boolean isGameOver() {
		 if(board.isCheckmate(!turn)) {
			if(turn) state = "White Wins!";
			else state = "Black Wins!";
			return true;
		} else if(moveRuleCount > 50 || hasInsufficientPieces() || hasNoMoreMoves(!turn)){
			state = "Draw";
			return true;
		} else if (whiteSurrendered || blackSurrendered) {
			if (blackSurrendered) state = "White Wins!";
			else state = "Black Wins!";
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
	
	public void setSurrendered(boolean surrendered) {
		if (turn) setWhiteSurrendered(surrendered);
		else setBlackSurrendered(surrendered);
	}
	
	public void setWhiteSurrendered(boolean surrendered) {
		whiteSurrendered = surrendered;
	}
	
	public void setBlackSurrendered(boolean surrendered) {
		blackSurrendered = surrendered;
	}

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
