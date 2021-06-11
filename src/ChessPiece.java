import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.*;
public abstract class ChessPiece extends Piece implements Cloneable{
	private boolean white;
	
	public ChessPiece(boolean stateInput) {
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
	
	
	public boolean canMove(Board board, Space start, Space end) {
		ArrayList<Space> moveableSpaces = getMoveableSpaces(start,board);
		for(int i = 0; i < moveableSpaces.size(); i++) {
			if(moveableSpaces.get(i).equals(end)) return true;
		}
		return false;
	}
	
	public abstract Piece copy();
	
	public abstract ArrayList<Space> getMoveableSpaces(Space start,Board board);
	
	public abstract ArrayList<Space> getCaptureableSpaces(Space start,Board board);

}
