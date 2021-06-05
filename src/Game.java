
public class Game {
	private Board board;
	String state;
	private boolean turn;
	
	public Game() {
		board = new Board(false);
		turn = true;
	}
	
	//returns true if the move has been made, false otherwise
	public boolean attemptMove(Space start, Space end) {
		return(board.movePiece(turn, start, end));
	}
	

	
	public boolean isInteractable(Space space) {
		if(space.getPiece() instanceof ChessPiece) {
			return(((ChessPiece)space.getPiece()).isWhite() == turn);
		}
		return false;
	}
	
	
	
	public boolean isGameOver() {
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
