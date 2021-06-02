import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.*;
public abstract class ChessPiece extends Piece{
	private boolean white;
	private static BufferedImage WHITE_PAWN_IMAGE;
	private static BufferedImage WHITE_ROOK_IMAGE;
	private static BufferedImage WHITE_KNIGHT_IMAGE;
	private static BufferedImage WHITE_BISHOP_IMAGE;
	private static BufferedImage WHITE_QUEEN_IMAGE;
	private static BufferedImage WHITE_KING_IMAGE;
	
	private static BufferedImage BLACK_PAWN_IMAGE;
	private static BufferedImage BLACK_ROOK_IMAGE;
	private static BufferedImage	BLACK_KNIGHT_IMAGE;
	private static BufferedImage BLACK_BISHOP_IMAGE;
	private static BufferedImage BLACK_QUEEN_IMAGE;
	private static BufferedImage BLACK_KING_IMAGE;
	public ChessPiece(boolean stateInput, Image imageInput) {
		super(imageInput);
		white = stateInput;

		try {
			WHITE_PAWN_IMAGE = ImageIO.read(new File("/Chess/chess_images/White/Pawn"));
			WHITE_ROOK_IMAGE = ImageIO.read(new File("/Chess/chess_images/White/Rook"));
			WHITE_KNIGHT_IMAGE = ImageIO.read(new File("/Chess/chess_images/White/Knight"));
			WHITE_BISHOP_IMAGE = ImageIO.read(new File("/Chess/chess_images/White/Bishop"));
			WHITE_QUEEN_IMAGE = ImageIO.read(new File("/Chess/chess_images/White/Queen"));
			WHITE_KING_IMAGE = ImageIO.read(new File("/Chess/chess_images/White/King"));
			
			BLACK_PAWN_IMAGE = ImageIO.read(new File("/Chess/chess_images/Black/Pawn"));
			BLACK_ROOK_IMAGE = ImageIO.read(new File("/Chess/chess_images/Black/Rook"));
			BLACK_KNIGHT_IMAGE = ImageIO.read(new File("/Chess/chess_images/Black/Knight"));
			BLACK_BISHOP_IMAGE = ImageIO.read(new File("/Chess/chess_images/Black/Bishop"));
			BLACK_QUEEN_IMAGE = ImageIO.read(new File("/Chess/chess_images/Black/Queen"));
			BLACK_KING_IMAGE = ImageIO.read(new File("/Chess/chess_images/Black/King"));
		} catch(Exception e) {
			
		} 
	}
	
	public static BufferedImage getBufferedImage(boolean color, String string) {
		if(color) {
			switch(string) {
			case "P":
				return WHITE_PAWN_IMAGE;
			case "R":
				return WHITE_ROOK_IMAGE;
			case "N":
				return WHITE_KNIGHT_IMAGE;
			case "Q":
				return WHITE_QUEEN_IMAGE;
			case "K":
				return WHITE_KING_IMAGE;
			case "B":
				return WHITE_BISHOP_IMAGE;
			}
		} else {
			switch(string) {
			case "P":
				return BLACK_PAWN_IMAGE;
			case "R":
				return BLACK_ROOK_IMAGE;
			case "N":
				return BLACK_KNIGHT_IMAGE;
			case "Q":
				return BLACK_QUEEN_IMAGE;
			case "K":
				return BLACK_KING_IMAGE;
			case "B":
				return BLACK_BISHOP_IMAGE;
			}
		}
		return null;
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
	
	public abstract ArrayList<Space> getMoveableSpaces(Space start,Board board);
	
	public abstract ArrayList<Space> getCaptureableSpaces(Space start,Board board);

}
