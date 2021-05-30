import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class test {
	
	@Test
	@Tag("isAvailable")
	//@DisplayName("Testing isAvailable()")
	void test() {
		Board b = new Board(true);
		b.getSpace(2,1).setPiece(new Pawn(false,null));
		b.getSpace(3,2).setPiece(new Pawn(false,null));
		System.out.println(ChessPiece.isAvailable(b.getSpace(2, 1), b.getSpace(3,2)));
		assertFalse(ChessPiece.isAvailable(b.getSpace(2, 1), b.getSpace(3,2)));
		b.printBoard();
		b.printBoard();
	}
	
	

}
