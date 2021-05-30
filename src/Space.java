
public class Space {
	private int row;
	private int col;
	private Piece piece; 
	
	public Space(int rowInput, int colInput) {
		row = rowInput; 
		col = colInput;
		piece = null;
	}
	
	public Space(int rowInput, int colInput, Piece pieceInput) {
		row  = rowInput; 
		col = colInput; 
		piece = pieceInput;
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
	
	
	public boolean equals(Object a) {
		if(a instanceof Space) {
			if(((Space)a).getRow() == this.getRow() &&  ((Space)a).getCol() == this.getCol()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	public String toString() {
		if(this.getPiece() == null) {
			return "*";
		} else {
			return this.getPiece().toString();
		}
	}
	
}
