package game;

import java.util.ArrayList;
import java.util.HashMap;

import pieces.*;
import pieces.Record;

public class Board {
	private Space[][] boardSpaces;
	private ArrayList<Space> blackSpacePieces;
	private ArrayList<Space> whiteSpacePieces;
	private ArrayList<Record> recordings;
	private ArrayList<String> whiteCaptured;
	private ArrayList<String> blackCaptured;
	
	public Board(boolean empty) {
		blackSpacePieces = new ArrayList<Space>();
		whiteSpacePieces = new ArrayList<Space>();
		whiteCaptured = new ArrayList<String>();
		blackCaptured = new ArrayList<String>();
		
		boardSpaces = new Space[8][8];
		if(empty) initEmpty();
		else init();
	}
	
	private void init() {
		blackSpacePieces = new ArrayList<Space>();
		whiteSpacePieces = new ArrayList<Space>();
		recordings = new ArrayList<Record>();
		
		initHelper(0, false);
		initHelper(7, true); 
		
		//Add pawns to both sides of the board
		for(int i = 0; i < 8; i++) {
			boardSpaces[6][i] = new Space(6,i,new Pawn(true));
			whiteSpacePieces.add(boardSpaces[6][i]);
			boardSpaces[1][i] = new Space(1,i,new Pawn(false));
			blackSpacePieces.add(boardSpaces[1][i]);
		}
		
		//Initializes board with space objects
		for(int row = 2; row < 6; row++) {
			for(int col = 0; col < 8; col++) {
				boardSpaces[row][col] = new Space(row,col);
			}
		}

	}
	
	public void initEmpty() {
		blackSpacePieces = new ArrayList<Space>();
		whiteSpacePieces = new ArrayList<Space>();
		recordings = new ArrayList<Record>();
		
		for(int row = 0; row < boardSpaces.length; row++) {
			for(int col = 0; col < boardSpaces[0].length; col++) {
				boardSpaces[row][col] = new Space(row,col,null);
			}
		}
	}
	
	public ArrayList<Space> getColorSpacePieces(boolean color) {
		if(color) {
			return whiteSpacePieces;
		} else {
			return blackSpacePieces;
		}
	}
	 
	private void initHelper(int row, boolean color) {
		ArrayList<Space> colorSpacePieces = getColorSpacePieces(color);
		
		boardSpaces[row][0] = new Space(row,0,new Rook(color));
		colorSpacePieces.add(boardSpaces[row][0]);
		boardSpaces[row][7] = new Space(row,7,new Rook(color));
		colorSpacePieces.add(boardSpaces[row][7]);
		
		boardSpaces[row][1] = new Space(row,1,new Knight(color));
		colorSpacePieces.add(boardSpaces[row][1]);
		boardSpaces[row][6] = new Space(row,6,new Knight(color));
		colorSpacePieces.add(boardSpaces[row][6]);
		
		boardSpaces[row][2] = new Space(row,2,new Bishop(color));
		colorSpacePieces.add(boardSpaces[row][2]);
		boardSpaces[row][5] = new Space(row,5,new Bishop(color));
		colorSpacePieces.add(boardSpaces[row][5]);
		
		boardSpaces[row][3] = new Space(row,3,new Queen(color));
		colorSpacePieces.add(boardSpaces[row][3]);
		
		boardSpaces[row][4] = new Space(row,4,new King(color));
		colorSpacePieces.add(boardSpaces[row][4]);
		
	}
	
	
	public ArrayList<Space> getBlackSpacePieces() {
		return blackSpacePieces;
	}
	
	public ArrayList<Space> getWhiteSpacePieces() {
		return whiteSpacePieces;
	}

	public Space getSpace(int row, int col) {
		return boardSpaces[row][col];
	}
	
	//precondition start contains a chess piece
	public boolean movePiece(boolean startColor,Space start,Space end) {
		ArrayList<Space> currentColorSpacePieces = null;
		ArrayList<Space> otherColorSpacePieces = null;

		if(startColor) {
			currentColorSpacePieces = whiteSpacePieces;
			otherColorSpacePieces = blackSpacePieces;
		} else {
			currentColorSpacePieces = blackSpacePieces;
			otherColorSpacePieces = whiteSpacePieces;
		}
		
		if(((ChessPiece)start.getPiece()).canMove(this, start, end) && this.simulateMoveForCheck(start, end)) {
			moveMutator(currentColorSpacePieces, otherColorSpacePieces ,start, end);
			recordings.add(new Record(end));
			return true;
		} 
		return false;
	}
	
	
	
	private void moveMutator(ArrayList<Space> currentColorSpacePieces, ArrayList<Space> otherColorSpacePieces,Space start,Space end) {
		movePieceHelperFirstMove(end);
		movePieceHelperFirstMove(start);
		
		if(attemptingToPass(start,end)) {
			end.setPiece(start.getPiece());
			start.setPiece(null);
			replaceSpace(currentColorSpacePieces,start,end);
			Space s = this.getSpace(start.getRow(), end.getCol());
			s.setPiece(null);
			removeSpace(otherColorSpacePieces, s);
		} else if(end.getPiece() == null) {
			end.setPiece(start.getPiece());
			start.setPiece(null);
			replaceSpace(currentColorSpacePieces,start,end);
		} else if(movePieceHelperIsCastling(start,end)) {
			final Space ROOK_END_SPACE;
			final Space KING_END_SPACE;
			if(end.getCol() == 0) {
				KING_END_SPACE = getSpace(start.getRow(),2);
				ROOK_END_SPACE = getSpace(start.getRow(),3);
			} else {
				KING_END_SPACE = getSpace(start.getRow(),6);
				ROOK_END_SPACE = getSpace(start.getRow(),5);
			}
			KING_END_SPACE.setPiece(start.getPiece());
			start.setPiece(null);
			replaceSpace(currentColorSpacePieces,start,KING_END_SPACE);
			ROOK_END_SPACE.setPiece(end.getPiece());
			end.setPiece(null);
			replaceSpace(currentColorSpacePieces,end,ROOK_END_SPACE);
		} else if(end != null) {
			if (((ChessPiece)end.getPiece()).isWhite()) whiteCaptured.add(end.getPiece().toString());
			else blackCaptured.add(end.getPiece().toString());
			removeSpace(otherColorSpacePieces, end);
			end.setPiece(start.getPiece());
			start.setPiece(null);
			replaceSpace(currentColorSpacePieces,start,end);
		}
	}
	
	private void movePieceHelperFirstMove(Space s) {
		if(s.getPiece() instanceof Rook) {
			((Rook)s.getPiece()).setFirstMove(false);
		}
		else if(s.getPiece() instanceof King) {
			((King)s.getPiece()).setFirstMove(false);
		}
		else if(s.getPiece() instanceof Pawn) {
			((Pawn)s.getPiece()).setFirstMove(false);
		}
	}
	
	private boolean movePieceHelperIsCastling(Space start, Space end) {
		if((start.getPiece() instanceof King && end.getPiece() instanceof Rook)) {
			if(((ChessPiece)start.getPiece()).isWhite() == ((ChessPiece)end.getPiece()).isWhite()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean attemptingToPass(Space start, Space end) {
		if(start.getPiece() instanceof Pawn && end.getPiece() == null) {
			Space s = this.getSpace(start.getRow(),end.getCol());
			if((s.getPiece()) instanceof Pawn && ((Pawn)start.getPiece()).isWhite() != ((Pawn)s.getPiece()).isWhite()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public boolean isCheck(boolean kingColor,Space kingLocation) {
		ArrayList<Space> colorSpacePieces;
		ArrayList<Space> currentCaptureSpaces;
		if(kingColor) {
			colorSpacePieces = blackSpacePieces;
		} else {
			colorSpacePieces = whiteSpacePieces;
		}
	
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			currentCaptureSpaces = ((ChessPiece)colorSpacePieces.get(i).getPiece()).getCaptureableSpaces(colorSpacePieces.get(i),this);
			for(int j = 0; j < currentCaptureSpaces.size(); j++) {
				if(currentCaptureSpaces.get(j).equals(kingLocation)) {
						return true;
				}
			}
		}
		return false;
	}
	
	
	//note to self, it doesn't check the starting position of the king space
	public boolean isCheckmate(boolean kingColor) {
		ArrayList<Space> colorSpacePieces;
		ArrayList<Space> enemySpaces;
		Space kingOriginalSpace;
		colorSpacePieces = getColorSpacePieces(kingColor);
		kingOriginalSpace = this.findKingSpace(colorSpacePieces);
		boolean first = (((King)kingOriginalSpace.getPiece()).getFirstMove());
		if(!(isCheck(kingColor, kingOriginalSpace))) return false;  
		enemySpaces = getEnemyCheckPieces(kingColor,kingOriginalSpace);
		if(checkmateCanMoveHelper(kingColor) || checkmateCanCaptureHelper(enemySpaces,kingColor) || (enemySpaces.size() == 1 && checkmateCanBlockHelper(enemySpaces.get(0), kingColor))) {
			((King)kingOriginalSpace.getPiece()).setFirstMove(first);
			return false;
		} 
		
		return true;

	}
	
	private boolean checkmateCanMoveHelper(boolean kingColor) {
		Board clonedBoard = this.copy();
		ArrayList<Space> colorSpacePieces = clonedBoard.getColorSpacePieces(kingColor);
		Space kingLocation = clonedBoard.findKingSpace(colorSpacePieces);
		ArrayList<Space> kingSpacesMove;
		
		kingSpacesMove = ((ChessPiece)kingLocation.getPiece()).getMoveableSpaces(kingLocation, clonedBoard);
		
		for(int i = 0; i < kingSpacesMove.size(); i++) {
			if(clonedBoard.simulateMoveForCheck(kingLocation, kingSpacesMove.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkmateCanCaptureHelper(ArrayList<Space> enemySpaces, boolean kingColor) {
		ArrayList<Space> colorSpacePieces = getColorSpacePieces(kingColor);
		
		if(enemySpaces.size() > 1)  {
			return false;
		}
		
		for(int i = 0; i < colorSpacePieces.size(); i++) {
				if(((ChessPiece)colorSpacePieces.get(i).getPiece()).canMove(this, colorSpacePieces.get(i), enemySpaces.get(0)) && this.simulateMoveForCheck(colorSpacePieces.get(i), enemySpaces.get(0))) {
					return true;
				}
		}
		return false;
		
	}
	
	private boolean checkmateCanBlockHelper(Space enemySpace, boolean kingColor) {
		ArrayList<Space> colorSpacePieces = getColorSpacePieces(kingColor);
		ArrayList<Space> enemyMoveableSpaces = ((ChessPiece)enemySpace.getPiece()).getCaptureableSpaces(enemySpace, this);
		
		
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			for(int j = 0; j < enemyMoveableSpaces.size(); j++) {
				if(((ChessPiece)colorSpacePieces.get(i).getPiece()).canMove(this, colorSpacePieces.get(i), enemyMoveableSpaces.get(j)) && this.simulateMoveForCheck(colorSpacePieces.get(i), enemyMoveableSpaces.get(j))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private ArrayList<Space> getEnemyCheckPieces(boolean kingColor,Space kingLocation) {
		ArrayList<Space> colorSpacePieces = getColorSpacePieces(!kingColor);
		ArrayList<Space> enemyPieces = new ArrayList<Space>();

		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(((ChessPiece)colorSpacePieces.get(i).getPiece()).canMove(this, colorSpacePieces.get(i), kingLocation) && this.simulateMoveForCheck(colorSpacePieces.get(i), kingLocation)) {
				enemyPieces.add(colorSpacePieces.get(i));
			}
		}
		
		return enemyPieces;
	}
	
	
	private int findSpaceIndex(ArrayList<Space> colorSpacePieces, Space s) {
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(s.equals(colorSpacePieces.get(i))) {
				return i;
			}
		}
		return -1;
	}
	

	public Space findKingSpace(ArrayList<Space> colorSpacePieces) {
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(colorSpacePieces.get(i).getPiece() instanceof King) {
				return colorSpacePieces.get(i);
			}
		}
		return null;
	}
	
	public int getMinorPieceCount(ArrayList<Space> colorSpacePieces) {
		int count = 0;
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(colorSpacePieces.get(i).getPiece() instanceof Bishop || colorSpacePieces.get(i).getPiece() instanceof Knight) {
				count++;
			}
		}
		return count;
	}
	 
	
	public int getKnightPieceCount(ArrayList<Space> colorSpacePieces) {
		int count = 0;
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(colorSpacePieces.get(i).getPiece() instanceof Knight) {
				count++;
			}
		}
		return count;
	}
	
	public int getBishopPieceCount(ArrayList<Space> colorSpacePieces) {
		int count = 0;
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(colorSpacePieces.get(i).getPiece() instanceof Knight) {
				count++;
			}
		}
		return count;
	}
	
	
	
	private void replaceSpace(ArrayList<Space> colorSpacePieces, Space oldSpace, Space newSpace) {
		int index = findSpaceIndex(colorSpacePieces,oldSpace);
		
		colorSpacePieces.remove(index);
		colorSpacePieces.add(newSpace);
	}
	
	private void removeSpace(ArrayList<Space> colorSpacePieces,Space beingRemoved) {
		int index = findSpaceIndex(colorSpacePieces,beingRemoved);
		
		colorSpacePieces.remove(index);
	}
	

	public boolean simulateMoveForCheck(Space start, Space end) {
		Board clonedBoard = this.copy();
		Space clonedStart = clonedBoard.getSpace(start.getRow(), start.getCol());
		Space clonedEnd = clonedBoard.getSpace(end.getRow(), end.getCol());
		
		ArrayList<Space> currentColorSpacePieces = null;
		ArrayList<Space> otherColorSpacePieces = null;
		
		boolean startColor = ((ChessPiece)start.getPiece()).isWhite();
		if(startColor) {
			currentColorSpacePieces = clonedBoard.getWhiteSpacePieces();
			otherColorSpacePieces = clonedBoard.getBlackSpacePieces();
		} else { 
			currentColorSpacePieces = clonedBoard.getBlackSpacePieces();
			otherColorSpacePieces = clonedBoard.getWhiteSpacePieces();
		}
		
		clonedBoard.moveMutator(currentColorSpacePieces,otherColorSpacePieces, clonedStart, clonedEnd);
		
		Space kingSpace = clonedBoard.findKingSpace(currentColorSpacePieces);

		return !(clonedBoard.isCheck(startColor,kingSpace));
	}
	
	public Board copy() {
		Board clonedBoard = new Board(true);
		copyArrayList(clonedBoard.getBlackSpacePieces(),blackSpacePieces);
		copyArrayList(clonedBoard.getWhiteSpacePieces(),whiteSpacePieces);
		addListPiecesToBoard(clonedBoard,blackSpacePieces);
		addListPiecesToBoard(clonedBoard,whiteSpacePieces);
		return clonedBoard;
	}
	
	private void addListPiecesToBoard(Board board,ArrayList<Space> list) {
		for(int i = 0; i < list.size(); i++) {
			Space space = list.get(i);
			board.getSpace(space.getRow(), space.getCol()).setPiece(space.getPiece());
		}
	}

	
	private void copyArrayList(ArrayList<Space> listCloned, ArrayList<Space> listOrginal) {
		for(int i = 0; i < listOrginal.size(); i++) {
			listCloned.add(listOrginal.get(i).copy());
		}
	}
	
	public ArrayList<String> getWhiteCaptured() {
		return whiteCaptured;
	}
	
	public ArrayList<String> getBlackCaptured() {
		return blackCaptured;
	}
	
	
	public void clearCaptured() {
		whiteCaptured = new ArrayList<String>();
		blackCaptured = new ArrayList<String>();
	}
	
	
	public void pawnTransform(boolean pieceColor, Space end, int input) {
		switch(input) {
		case 1:
			end.setPiece(new Queen(pieceColor));
			break;
		case 2:
			end.setPiece(new Rook(pieceColor));
			break;
		case 3:
			end.setPiece(new Bishop(pieceColor));
			break;
		case 4:
			end.setPiece(new Knight(pieceColor));
			break;
		}
	}
	
	
	public int pieceMoveCount(Piece piece) {
		int count = 0;
		for(int i = 0; i < recordings.size(); i++) {
			if(recordings.get(i).getPiece() != null && recordings.get(i).getPiece() == piece) count++; 
		}
		return count;
	}
	
	public ArrayList<Record> getRecordings() {
		return recordings;
	}
}
