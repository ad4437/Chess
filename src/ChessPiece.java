import java.awt.Image;

public abstract class ChessPiece extends Piece{
	private boolean white;
	
	public ChessPiece(boolean stateInput, Image imageInput) {
		super(imageInput);
		white = stateInput;
	}
	
	
	public static boolean isAvailable(Space start, Space end) {
		return(end.getPiece() == null || end.getPiece() instanceof ChessPiece && ((ChessPiece)end.getPiece()).isWhite() != ((ChessPiece)start.getPiece()).isWhite());
	}
	
	
	public boolean isWhite() {
		return white;
	}
	
	public void setWhite(boolean stateInput) {
		white = stateInput;
	}
	
	
	public abstract boolean canMove(Board board, Space start, Space end);
	

}
