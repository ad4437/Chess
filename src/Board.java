import java.util.ArrayList;

public class Board {
	private Space[][] boardSpaces;
	
	ArrayList<Space> blackSpacePieces;
	ArrayList<Space> whiteSpacePieces;
	
	public Board(boolean empty) {
		boardSpaces = new Space[8][8];
		if(empty) initEmpty();
		else init();
	}
	
	private void init() {
		blackSpacePieces = new ArrayList<Space>();
		whiteSpacePieces = new ArrayList<Space>();
		
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
		for(int row = 0; row < boardSpaces.length; row++) {
			for(int col = 0; col < boardSpaces[0].length; col++) {
				boardSpaces[row][col] = new Space(row,col,null);
			}
		}
	}
	
	//white = true, black = false 
	private void initHelper(int row, boolean color) {
		ArrayList<Space> colorSpacePieces;
		if(color) {
			colorSpacePieces = whiteSpacePieces;
		} else {
			colorSpacePieces = blackSpacePieces;
		}
		
		
		//Initializes board with rooks
		boardSpaces[row][0] = new Space(row,0,new Rook(color));
		colorSpacePieces.add(boardSpaces[row][0]);
		boardSpaces[row][7] = new Space(row,7,new Rook(color));
		colorSpacePieces.add(boardSpaces[row][7]);
		
		//Initializes board with knights
		boardSpaces[row][1] = new Space(row,1,new Knight(color));
		colorSpacePieces.add(boardSpaces[row][1]);
		boardSpaces[row][6] = new Space(row,6,new Knight(color));
		colorSpacePieces.add(boardSpaces[row][6]);
		
		//Initializes board with bishops
		boardSpaces[row][2] = new Space(row,2,new Bishop(color));
		colorSpacePieces.add(boardSpaces[row][2]);
		boardSpaces[row][5] = new Space(row,5,new Bishop(color));
		colorSpacePieces.add(boardSpaces[row][5]);
		
		//Initializes board with a queen
		boardSpaces[row][3] = new Space(row,3,new Queen(color));
		colorSpacePieces.add(boardSpaces[row][3]);
		
		//Initializes board with a king
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
		ArrayList<Space> currentColorSpacePieces;
		ArrayList<Space> otherColorSpacePieces;
		if(startColor) {
			currentColorSpacePieces = whiteSpacePieces;
			otherColorSpacePieces = blackSpacePieces;
		} else {
			currentColorSpacePieces = blackSpacePieces;
			otherColorSpacePieces = whiteSpacePieces;
		}
		movePieceHelperFirstMove(end);
		movePieceHelperFirstMove(start);
		if(((ChessPiece)start.getPiece()).canMove(this, start, end)) {
			if(end.getPiece() == null) {
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
				movePieceHelperFirstMove(start);
				start.setPiece(null);
				replaceSpace(currentColorSpacePieces,start,KING_END_SPACE);
				ROOK_END_SPACE.setPiece(end.getPiece());
				movePieceHelperFirstMove(end);
				end.setPiece(null);
				replaceSpace(currentColorSpacePieces,end,ROOK_END_SPACE);
			} else if(end != null) {
				removeSpace(otherColorSpacePieces, end);
				end.setPiece(start.getPiece());
				start.setPiece(null);
				replaceSpace(currentColorSpacePieces,start,end);
			}
			movePieceHelperPawn(end);
			return true;
		} 
		return false;
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
	
	private void movePieceHelperPawn(Space end) {
		if(end.getPiece() instanceof Pawn) {
			boolean color =  ((ChessPiece)end.getPiece()).isWhite();
			if((color == true && end.getRow() == 0) || (color == false && end.getRow() == 7)) {
				int input = 0; //placeholder code
				//add code to get input from user
				switch(input) {
				case 1:
					end.setPiece(new Queen(color));
				case 2:
					end.setPiece(new Rook(color));
				case 3:
					end.setPiece(new Bishop(color));
				case 4:
					end.setPiece(new Knight(color));
				}
			}
		}
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
		if(kingColor) {
			colorSpacePieces = whiteSpacePieces;
		} else {
			colorSpacePieces = blackSpacePieces;
		}
		
		kingOriginalSpace = this.findKingSpace(colorSpacePieces);
		enemySpaces = getEnemyCheckPieces(kingColor,kingOriginalSpace);
		
		if(checkmateCanMoveHelper(kingColor,kingOriginalSpace)) return false;
		if(checkmateCanCaptureHelper(enemySpaces,kingColor)) return false;
		if(enemySpaces.size() == 1 && checkmateCanBlockHelper(enemySpaces.get(0), kingColor))return false;
		return true;

	}
	
	private boolean checkmateCanMoveHelper(boolean kingColor,Space kingLocation) {
		ArrayList<Space> kingSpacesMove;
		kingSpacesMove = ((ChessPiece)kingLocation.getPiece()).getMoveableSpaces(kingLocation, this);
		
		for(int i = 0; i < kingSpacesMove.size(); i++) {
			if(!(isCheck(kingColor,kingSpacesMove.get(i)))) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkmateCanCaptureHelper(ArrayList<Space> enemySpaces, boolean kingColor) {
		ArrayList<Space> colorSpacePieces;
		if(kingColor) {
			colorSpacePieces = whiteSpacePieces;
		} else {
			colorSpacePieces = blackSpacePieces;
		}
		
		if(enemySpaces.size() > 1) return true;
		
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			for(int j = 0; j < enemySpaces.size(); j++) {
				if(((ChessPiece)colorSpacePieces.get(i).getPiece()).canMove(this, colorSpacePieces.get(i), enemySpaces.get(j))) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private boolean checkmateCanBlockHelper(Space enemySpace, boolean kingColor) {
		ArrayList<Space> colorSpacePieces;
		ArrayList<Space> enemyMoveableSpaces = ((ChessPiece)enemySpace.getPiece()).getCaptureableSpaces(enemySpace, this);
		if(kingColor) {
			colorSpacePieces = whiteSpacePieces;
		} else {
			colorSpacePieces = blackSpacePieces;
		}
		
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			for(int j = 0; j < enemyMoveableSpaces.size(); j++) {
				if(((ChessPiece)colorSpacePieces.get(i).getPiece()).canMove(this, colorSpacePieces.get(i), enemyMoveableSpaces.get(j))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private ArrayList<Space> getEnemyCheckPieces(boolean kingColor,Space kingLocation) {
		ArrayList<Space> colorSpacePieces;
		ArrayList<Space> enemyPieces = new ArrayList<Space>();
		if(kingColor) {
			colorSpacePieces = blackSpacePieces;
		} else {
			colorSpacePieces = whiteSpacePieces;
		}
		
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(((ChessPiece)colorSpacePieces.get(i).getPiece()).canMove(this, colorSpacePieces.get(i), kingLocation)) {
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
	

	private Space findKingSpace(ArrayList<Space> colorSpacePieces) {
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(colorSpacePieces.get(i).getPiece() instanceof King) {
				return colorSpacePieces.get(i);
			}
		}
		return null;
	}
	
	
	private void replaceSpace(ArrayList<Space> colorSpacePieces, Space oldSpace,Space newSpace) {
		int index = findSpaceIndex(colorSpacePieces,oldSpace);
	
		colorSpacePieces.remove(index);
		colorSpacePieces.add(newSpace);
	}
	
	private void removeSpace(ArrayList<Space> colorSpacePieces,Space beingRemoved) {
		int index = findSpaceIndex(colorSpacePieces,beingRemoved);
		
		colorSpacePieces.remove(index);
	}
	
	
	
	public void printBoard() {
		for(int row = 0; row < boardSpaces.length; row++) {
			for(int col = 0; col < boardSpaces[0].length; col++) {
				System.out.print(boardSpaces[row][col]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
