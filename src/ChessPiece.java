import java.awt.Image;

public abstract class ChessPiece extends Piece{
	private boolean white;
	
	public ChessPiece(boolean stateInput, Image imageInput) {
		super(imageInput);
		white = stateInput;
	}
	
	public boolean isWhite() {
		return white;
	}
	
	public  void setWhite(boolean stateInput) {
		white = stateInput;
	}
	
	
	public abstract boolean canMove(Board board, Space start, Space end);
	

}
