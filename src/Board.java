
public class Board {
	private Space[][] arr;
	
	public Board() {
		arr = new Space[8][8];
		init();
	}
	
	
	public void init() {
		
		initHelper(0, true);
		initHelper(7, false); 
		
		//Add pawns to both sides of the board
		for(int i = 0; i < 8; i++) {
			arr[6][i] = new Space(6,i,new Pawn(false,null));
			arr[1][i] = new Space(1,i,new Pawn(true,null));
		}
		
		//Initializes board with space objects
		for(int row = 2; row < 6; row++) {
			for(int col = 0; col < 8; col++) {
				arr[row][col] = new Space(row,col);
			}
		}

	}
	
	//white = true, black = false 
	private void initHelper(int row, boolean color) {
		
		//Initializes board with rooks
		arr[row][0] = new Space(row,0,new Rook(color,null));
		arr[row][7] = new Space(row,7,new Rook(color,null));
		
		//Initializes board with knights
		arr[row][1] = new Space(row,1,new Knight(color,null));
		arr[row][6] = new Space(row,6,new Knight(color,null));
		
		//Initializes board with bishops
		arr[row][2] = new Space(row,2,new Bishop(color,null));
		arr[row][5] = new Space(row,5,new Bishop(color,null));
		
		//Initializes board with a queen
		arr[row][3] = new Space(row,3,new Queen(color,null));
		
		//Initializes board with a king
		arr[row][4] = new Space(row,4,new Queen(color,null));
		
		
	}
}
