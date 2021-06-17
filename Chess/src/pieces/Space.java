package pieces;

public class Space  {
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
	
	
	public Space copy() {
		Space s = new Space(row,col);
		if(this.getPiece() != null) {
			s.setPiece(((ChessPiece)this.getPiece()).copy());  
		}
		return s;
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
			return (String.format("%d,%d", this.getRow(),this.getCol()));
		} else {
			return this.getPiece().toString();
		}
	}

}
