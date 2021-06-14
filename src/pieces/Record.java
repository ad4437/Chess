package pieces;
//only useful for pawn in passing/en passe and 
public class Record {
	private int row;
	private int col;
	private boolean color;
	private Piece piece;
	
	public Record(Space end) {
		row = end.getRow();
		col = end.getCol();
		piece = end.getPiece();
	}
	
	
	public Piece getPiece() {
		return piece;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	
	public boolean equals(Object a) {
		if(a instanceof Record) {
			return false;
		}
		return false;
	}
	
	
	public String toString() {
		String pieceColor = "";
		if(color) {
			pieceColor = "white";
		} else {
			pieceColor = "black";
		}
		
		return(String.format("[%s %s] moves to %d %d", pieceColor,piece,row,col));
	}
	
}
