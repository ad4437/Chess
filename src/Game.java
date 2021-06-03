
public class Game {
	private Board board;
	String state;
	private boolean turn;
	
	public Game() {
		board = new Board(false);
		turn = true;
	}
	
	public boolean Turn(Space start, Space end) {
		if(board.movePiece(turn, start, end)) {
			if(isGameOver()) {
				return true;
			} else {
				turn = !turn;
			}
		}
		return false;
	}
	
	private boolean isGameOver() {
		if(board.getBlackSpacePieces().size() == 1 && board.getWhiteSpacePieces().size() == 1) {
			state = "tie";
			return true;
		} else if(board.isCheckmate(turn)) {
			if(turn) state = "white wins";
			else state = "black wins";
			return true;
		}
		return false;
	}
	
	public boolean isInteractable(Space space) {
		if(space.getPiece() instanceof ChessPiece) {
			return(((ChessPiece)space.getPiece()).isWhite() == turn);
		}
		return false;
	}
	
	
	public void reset() {
		board = new Board(false);
		turn = true;
	}
	

	
	public String getState() {
		return state;
	}
	
	public Board getBoard() {
		return board;
	}
}
