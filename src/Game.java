
public class Game {
	private Board board;
	private String state;
	private boolean turn;
	
	public Game() {
		board = new Board(false);
		turn = true;
	}
	
	public boolean attemptMove(Space start, Space end) {
		if (start.getPiece() == null) return false;
		return(board.movePiece(turn, start, end));
	}
	
	public void nextTurn() {
		turn = !turn;
	}
	
	public boolean isInteractable(Space space) {
		if(space.getPiece() instanceof ChessPiece) {
			return(((ChessPiece)space.getPiece()).isWhite() == turn);
		}
		return false;
	}
	
	public Space getThisTurnPawnForTransform() {
		return board.getAnyPawnAtEnd(turn);
	}
	
	//userInput values: 1 = Queen, 2 = Rook, 3 = Bishop, 4 = Knight
	public void transformPawn(Space pawnSpace, int userInput) {
		board.pawnTransform(turn, pawnSpace, userInput);
	}
	
	public boolean isGameOver() {
		if(board.getBlackSpacePieces().size() == 1 && board.getWhiteSpacePieces().size() == 1) {
			state = "tie";
			return true;
		} else if(board.isCheckmate(!turn)) {
			if(turn) state = "white wins";
			else state = "black wins";
			return true;
		} else {
			return false;
		}
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
