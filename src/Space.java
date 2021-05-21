
public class Space {
	private int row;
	private int col;
	private Piece piece; 
	
	public Space(int rowInput, int colInput) {
		row = rowInput; 
		col = colInput;
	}
	
	public int getRow() {
		return row; 
	}
	
	public int getCol() {
		return col; 
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece pieceInput) {
		piece = pieceInput;
	}
	
	
}
