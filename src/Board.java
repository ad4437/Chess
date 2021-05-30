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
	
	
	public void initEmpty() {
		for(int row = 0; row < boardSpaces.length; row++) {
			for(int col = 0; col < boardSpaces[0].length; col++) {
				boardSpaces[row][col] = new Space(row,col,null);
			}
		}
	}
	
	
	// returns -1 if space is not found
	private int findSpaceIndex(ArrayList<Space> colorSpacePieces, Space s) {
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(s.equals(colorSpacePieces.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	// returns -1 if space is not found(not needed)
	private int findKingIndex(ArrayList<Space> colorSpacePieces) {
		for(int i = 0; i < colorSpacePieces.size(); i++) {
			if(colorSpacePieces.get(i).getPiece() instanceof King) {
				return i;
			}
		}
		return -1;
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
	
	
	

	
	
	
	public void init() {
		blackSpacePieces = new ArrayList<Space>();
		whiteSpacePieces = new ArrayList<Space>();
		
		initHelper(0, false);
		initHelper(7, true); 
		
		//Add pawns to both sides of the board
		for(int i = 0; i < 8; i++) {
			boardSpaces[6][i] = new Space(6,i,new Pawn(true,null));
			whiteSpacePieces.add(boardSpaces[6][i]);
			boardSpaces[1][i] = new Space(1,i,new Pawn(false,null));
			blackSpacePieces.add(boardSpaces[1][i]);
		}
		
		//Initializes board with space objects
		for(int row = 2; row < 6; row++) {
			for(int col = 0; col < 8; col++) {
				boardSpaces[row][col] = new Space(row,col);
			}
		}

	}
	
	
	
	public Space getSpace(int row, int col) {
		return boardSpaces[row][col];
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
		boardSpaces[row][0] = new Space(row,0,new Rook(color,null));
		colorSpacePieces.add(boardSpaces[row][0]);
		boardSpaces[row][7] = new Space(row,7,new Rook(color,null));
		colorSpacePieces.add(boardSpaces[row][7]);
		
		//Initializes board with knights
		boardSpaces[row][1] = new Space(row,1,new Knight(color,null));
		colorSpacePieces.add(boardSpaces[row][1]);
		boardSpaces[row][6] = new Space(row,6,new Knight(color,null));
		colorSpacePieces.add(boardSpaces[row][6]);
		
		//Initializes board with bishops
		boardSpaces[row][2] = new Space(row,2,new Bishop(color,null));
		colorSpacePieces.add(boardSpaces[row][2]);
		boardSpaces[row][5] = new Space(row,5,new Bishop(color,null));
		colorSpacePieces.add(boardSpaces[row][5]);
		
		//Initializes board with a queen
		boardSpaces[row][3] = new Space(row,3,new Queen(color,null));
		colorSpacePieces.add(boardSpaces[row][3]);
		
		//Initializes board with a king
		boardSpaces[row][4] = new Space(row,4,new King(color,null));
		colorSpacePieces.add(boardSpaces[row][4]);
		
		
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
