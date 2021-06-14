import game.*;
import pieces.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class testPawn {
	private Board board;
	
	@Test
	void test1() {
		
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> pawnMethodSpaces;
		
		whatShouldBeThere.add(new Space(5,0));
		whatShouldBeThere.add(new Space(4,0));
	
		Space pawnSpace = TestingMethods.addPieceToBoard(new Pawn(true), board, 6, 0);
		pawnMethodSpaces = ((Pawn)pawnSpace.getPiece()).getMoveableSpaces(pawnSpace, board);
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere, pawnMethodSpaces));
	}
	
	@Test
	void test2() {
		
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> pawnMethodSpaces;
		
		whatShouldBeThere.add(new Space(5,0));
		
		Pawn p = new Pawn(true);
		p.setFirstMove(false);
		Space pawnSpace = TestingMethods.addPieceToBoard(p, board, 6, 0);
		
		pawnMethodSpaces = ((Pawn)pawnSpace.getPiece()).getMoveableSpaces(pawnSpace, board);
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere, pawnMethodSpaces));
	}
	
	@Test
	void test3() {
		
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> pawnMethodSpaces;
		
		whatShouldBeThere.add(new Space(4,0));
		whatShouldBeThere.add(new Space(3,0));
	
		Space pawnSpace = TestingMethods.addPieceToBoard(new Pawn(false), board, 2, 0);
		pawnMethodSpaces = ((Pawn)pawnSpace.getPiece()).getMoveableSpaces(pawnSpace, board);
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere, pawnMethodSpaces));
	}
	
	@Test
	void test4() {
		
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> pawnMethodSpaces;
		
		whatShouldBeThere.add(new Space(3,0));
		
		Pawn p = new Pawn(false);
		p.setFirstMove(false);
		Space pawnSpace = TestingMethods.addPieceToBoard(p, board, 2, 0);
		
		pawnMethodSpaces = ((Pawn)pawnSpace.getPiece()).getMoveableSpaces(pawnSpace, board);
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere, pawnMethodSpaces));
	}
	
	@Test
	void test5() {
		
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> pawnMethodSpaces;
		
		whatShouldBeThere.add(new Space(5,0));
		whatShouldBeThere.add(new Space(4,1));
		whatShouldBeThere.add(new Space(5,1));
	
		Space pawnSpace = TestingMethods.addPieceToBoard(new Pawn(true), board, 6, 1);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 5, 0);
		TestingMethods.addPieceToBoard(new Pawn(true), board, 5, 2);
		
		pawnMethodSpaces = ((Pawn)pawnSpace.getPiece()).getMoveableSpaces(pawnSpace, board);
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere, pawnMethodSpaces));
	}
	
	@Test
	void test6() {
		
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> pawnMethodSpaces;
		
		whatShouldBeThere.add(new Space(5,4));
		whatShouldBeThere.add(new Space(6,4));
		whatShouldBeThere.add(new Space(5,5));
	
		Space pawnSpace = TestingMethods.addPieceToBoard(new Pawn(false), board, 4, 4);
		TestingMethods.addPieceToBoard(new Pawn(true), board, 5, 5);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 5, 6);
		
		pawnMethodSpaces = ((Pawn)pawnSpace.getPiece()).getMoveableSpaces(pawnSpace, board);
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere, pawnMethodSpaces));
	}
	
	//in passing
	@Test
	void test7() {
		
		board = new Board(true);
		
		Space s = TestingMethods.addPieceToBoard(new Pawn(false), board, 1, 0);
		TestingMethods.addPieceToBoard(new Pawn(true), board, 6, 1);
		board.movePiece(false, s, board.getSpace(3, 0));
		board.movePiece(false, board.getSpace(3, 0), board.getSpace(4, 0));
		board.movePiece(true,board.getSpace(6, 1),board.getSpace(4, 1));
		assertTrue(board.movePiece(false, board.getSpace(4, 0), board.getSpace(5, 1)));
		
	}
	
	@Test
	void test8() {
		
		board = new Board(true);
		
		TestingMethods.addPieceToBoard(new Pawn(false), board, 1, 0);
		Space s = TestingMethods.addPieceToBoard(new Pawn(true), board, 6, 1);
		board.movePiece(true, s, board.getSpace(4, 1));
		board.movePiece(true, board.getSpace(4, 1), board.getSpace(3, 1));
		board.movePiece(false, board.getSpace(1, 0), board.getSpace(3, 0));
		assertTrue(board.movePiece(true, board.getSpace(3, 1), board.getSpace(2, 0)));
	}
	
	@Test
	void test9() {
		
		board = new Board(true);
		
		TestingMethods.addPieceToBoard(new Pawn(false), board, 1, 0);
		Space s = TestingMethods.addPieceToBoard(new Pawn(true), board, 6, 1);
		board.movePiece(true, s, board.getSpace(4, 1));
		board.movePiece(true, board.getSpace(4, 1), board.getSpace(3, 1));
		board.movePiece(false, board.getSpace(1, 0), board.getSpace(2, 0));
		board.movePiece(false, board.getSpace(2, 0), board.getSpace(3, 0));
		assertFalse(board.movePiece(true, board.getSpace(3, 1), board.getSpace(2, 0)));
	}
	
	@Test
	void test10() {
		
		board = new Board(true);
		
		TestingMethods.addPieceToBoard(new Pawn(false), board, 1, 0);
		Space s = TestingMethods.addPieceToBoard(new Pawn(true), board, 6, 1);
		board.movePiece(true, s, board.getSpace(4, 1));
		board.movePiece(true, board.getSpace(4, 1), board.getSpace(3, 1));
		board.movePiece(false, board.getSpace(1, 0), board.getSpace(3, 0));
		board.movePiece(true, board.getSpace(3, 1), board.getSpace(2, 0));
		assertTrue(board.getSpace(3, 0).getPiece() == null);
	}
	
	
	
	
}


class testRook {
	private static Board board;

	@Test
	void test1() {
		board = new Board(true);
		
		Space rookSpace = TestingMethods.addPieceToBoard(new Rook(true),board,5,2);
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		//left
		whatShouldBeThere.add(new Space(5,1));
		whatShouldBeThere.add(new Space(5,0));
			

		//right
		whatShouldBeThere.add(new Space(5,3));
		whatShouldBeThere.add(new Space(5,4));
		whatShouldBeThere.add(new Space(5,5));
		whatShouldBeThere.add(new Space(5,6));
		whatShouldBeThere.add(new Space(5,7));
	
		//down
		whatShouldBeThere.add(new Space(6,2));
		whatShouldBeThere.add(new Space(7,2));	
	
		//up 
		whatShouldBeThere.add(new Space(4,2));
		whatShouldBeThere.add(new Space(3,2));
		whatShouldBeThere.add(new Space(2,2));
		whatShouldBeThere.add(new Space(1,2));
		whatShouldBeThere.add(new Space(0,2));
			
	ArrayList<Space> rookMethodSpaces = ((Rook)rookSpace.getPiece()).getMoveableSpaces(rookSpace,board);	
	assertTrue(TestingMethods.areEqual(whatShouldBeThere,rookMethodSpaces));
	}
	
	@Test
	void test2() {
	board = new Board(true);
		
	Space rookSpace = TestingMethods.addPieceToBoard(new Rook(true),board,5,2);
	TestingMethods.addPieceToBoard(new Pawn(true),board,5,5);
	ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
	//left
	whatShouldBeThere.add(new Space(5,1));
	whatShouldBeThere.add(new Space(5,0));
			

	//right
	whatShouldBeThere.add(new Space(5,3));
	whatShouldBeThere.add(new Space(5,4));

	//down
	whatShouldBeThere.add(new Space(6,2));
	whatShouldBeThere.add(new Space(7,2));	

	//up 
	whatShouldBeThere.add(new Space(4,2));
	whatShouldBeThere.add(new Space(3,2));
	whatShouldBeThere.add(new Space(2,2));
	whatShouldBeThere.add(new Space(1,2));
	whatShouldBeThere.add(new Space(0,2));
			
	ArrayList<Space> rookMethodSpaces = ((Rook)rookSpace.getPiece()).getMoveableSpaces(rookSpace,board);	

	assertTrue(TestingMethods.areEqual(whatShouldBeThere,rookMethodSpaces));
	}
	
	@Test
	void test3() {
	board = new Board(true);
		
	Space rookSpace = TestingMethods.addPieceToBoard(new Rook(true),board,5,2);
	TestingMethods.addPieceToBoard(new Pawn(false),board,5,5);
	ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
	//left
	whatShouldBeThere.add(new Space(5,1));
	whatShouldBeThere.add(new Space(5,0));
			

	//right
	whatShouldBeThere.add(new Space(5,3));
	whatShouldBeThere.add(new Space(5,4));
	whatShouldBeThere.add(new Space(5,5));

	//down
	whatShouldBeThere.add(new Space(6,2));
	whatShouldBeThere.add(new Space(7,2));	

	//up 
	whatShouldBeThere.add(new Space(4,2));
	whatShouldBeThere.add(new Space(3,2));
	whatShouldBeThere.add(new Space(2,2));
	whatShouldBeThere.add(new Space(1,2));
	whatShouldBeThere.add(new Space(0,2));
			
	ArrayList<Space> rookMethodSpaces = ((Rook)rookSpace.getPiece()).getMoveableSpaces(rookSpace,board);	

	assertTrue(TestingMethods.areEqual(whatShouldBeThere,rookMethodSpaces));
	}
}

class testBishop {
	private static Board board;
	
	
	@Test
	void test1() {
		board = new Board(true);
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> bishopMethodSpaces;
		
		// North west
		whatShouldBeThere.add(new Space(4,1));
		whatShouldBeThere.add(new Space(3,0));

		//South west
		whatShouldBeThere.add(new Space(6,1));
		whatShouldBeThere.add(new Space(7,0));

		//North east
		whatShouldBeThere.add(new Space(4,3));
		whatShouldBeThere.add(new Space(3,4));
		whatShouldBeThere.add(new Space(2,5));
		whatShouldBeThere.add(new Space(1,6));
		whatShouldBeThere.add(new Space(0,7));


		//South east
		whatShouldBeThere.add(new Space(6,3));
	whatShouldBeThere.add(new Space(7,4));

		
		Space bishopSpace = TestingMethods.addPieceToBoard(new Bishop(true),board,5,2);
		bishopMethodSpaces= ((Bishop)bishopSpace.getPiece()).getMoveableSpaces(bishopSpace,board);

		assertTrue(TestingMethods.areEqual(whatShouldBeThere,bishopMethodSpaces));
	}
	
	@Test
	void test2() {
		board = new Board(true);
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> bishopMethodSpaces;
		
		// North west
		whatShouldBeThere.add(new Space(4,1));
		whatShouldBeThere.add(new Space(3,0));

		//South west
		whatShouldBeThere.add(new Space(6,1));
		whatShouldBeThere.add(new Space(7,0));

		//North east
		whatShouldBeThere.add(new Space(4,3));
		whatShouldBeThere.add(new Space(3,4));


		//South east
		whatShouldBeThere.add(new Space(6,3));
		whatShouldBeThere.add(new Space(7,4));

		
		Space bishopSpace = TestingMethods.addPieceToBoard(new Bishop(true),board,5,2);
		TestingMethods.addPieceToBoard(new Bishop(true),board,5,2);
		TestingMethods.addPieceToBoard(new Pawn(true), board, 2, 5);
		bishopMethodSpaces= ((Bishop)bishopSpace.getPiece()).getMoveableSpaces(bishopSpace,board);

		assertTrue(TestingMethods.areEqual(whatShouldBeThere,bishopMethodSpaces));
	}
	
	@Test
	void test3() {
		board = new Board(true);
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> bishopMethodSpaces;
		
		// North west
		whatShouldBeThere.add(new Space(4,1));
		whatShouldBeThere.add(new Space(3,0));

		//South west
		whatShouldBeThere.add(new Space(6,1));
		whatShouldBeThere.add(new Space(7,0));

		//North east
		whatShouldBeThere.add(new Space(4,3));
		whatShouldBeThere.add(new Space(3,4));
		whatShouldBeThere.add(new Space(2,5));

		//South east
		whatShouldBeThere.add(new Space(6,3));
		whatShouldBeThere.add(new Space(7,4));

		
		Space bishopSpace = TestingMethods.addPieceToBoard(new Bishop(true),board,5,2);
		TestingMethods.addPieceToBoard(new Bishop(true),board,5,2);
		TestingMethods.addPieceToBoard(new Pawn(false),board,2,5);
		bishopMethodSpaces= ((Bishop)bishopSpace.getPiece()).getMoveableSpaces(bishopSpace,board);

		assertTrue(TestingMethods.areEqual(whatShouldBeThere,bishopMethodSpaces));
	}
}




class testMisc {
	private Game game;
	
	@Test
	void test1() {
		game = new Game();
		game.getBoard().initEmpty();
		TestingMethods.addPieceToBoard(new King(true), game.getBoard(), 4, 4);
		TestingMethods.addPieceToBoard(new Rook(true), game.getBoard(), 3,2);
		TestingMethods.addPieceToBoard(new Rook(false), game.getBoard(), 4, 2);
		TestingMethods.addPieceToBoard(new King(false), game.getBoard(), 1, 1);


		assertTrue(game.attemptMove(game.getBoard().getSpace(3, 2),game.getBoard().getSpace(4, 2)));
	}
	/*
	@Test
	void test2() {
		game = new Game();
		game.getBoard().initEmpty();
		TestingMethods.addPieceToBoard(new King(true), game.getBoard(), 4, 4);
		TestingMethods.addPieceToBoard(new Rook(true), game.getBoard(), 3,2);
		TestingMethods.addPieceToBoard(new King(false), game.getBoard(), 1, 1);

		assertFalse(game.isGameOver());
	}
	*/
	
	@Test
	void test3() {
		game = new Game();
		game.getBoard().initEmpty();
		TestingMethods.addPieceToBoard(new King(true), game.getBoard(), 4, 4);
		TestingMethods.addPieceToBoard(new Rook(true), game.getBoard(), 3,2);
		TestingMethods.addPieceToBoard(new King(false), game.getBoard(), 0, 0);
		TestingMethods.addPieceToBoard(new Queen(true), game.getBoard(), 1, 0);
		TestingMethods.addPieceToBoard(new Queen(true), game.getBoard(), 1, 1);
		TestingMethods.addPieceToBoard(new Queen(true), game.getBoard(), 0, 1);

		assertTrue(game.isGameOver());
	}
	
	@Test
	void test4() {
		Board board = new Board(true);
		
		Space s = TestingMethods.addPieceToBoard(new King(true), board, 7, 4);
		Space end = TestingMethods.addPieceToBoard(new Rook(true), board, 7,0);
		System.out.println(((ChessPiece)end.getPiece()).getMoveableSpaces(end, board));
		assertTrue(board.movePiece(true, s, end));
	}
	@Test
	void test5() {
		game = new Game();
		game.getBoard().initEmpty();
		Space s = TestingMethods.addPieceToBoard(new King(true), game.getBoard(), 0, 4);
		Space end = TestingMethods.addPieceToBoard(new Rook(true), game.getBoard(), 0,0);
		((King)s.getPiece()).setFirstMove(false);
		
		assertFalse(((King)s.getPiece()).canMove(game.getBoard(), s, end));
	}
	@Test
	void test6() {
		game = new Game();
		game.getBoard().initEmpty();
		Space s = TestingMethods.addPieceToBoard(new King(true), game.getBoard(), 0, 4);
		Space end = TestingMethods.addPieceToBoard(new Rook(true), game.getBoard(), 0,0);
		game.attemptMove(s, end);
		
		assertTrue((game.getBoard().getSpace(0, 2).getPiece() != null) && ((game.getBoard().getSpace(0, 3).getPiece() != null)));
	}
}


class testKnight {
	private Board board;
	
	@Test
	void test1() {
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> knightMethodSpaces;

		//right 
		whatShouldBeThere.add(new Space(2,5));
		whatShouldBeThere.add(new Space(3,6));
		whatShouldBeThere.add(new Space(5,6));
		whatShouldBeThere.add(new Space(6,5));
				

		//up 
		whatShouldBeThere.add(new Space(2,3));
		whatShouldBeThere.add(new Space(3,2));

		//left
		whatShouldBeThere.add(new Space(6,3));
		
		//south
		whatShouldBeThere.add(new Space(5,2));

		Space knightSpace = TestingMethods.addPieceToBoard(new Knight(true), board, 4, 4);
		knightMethodSpaces = ((Knight)knightSpace.getPiece()).getMoveableSpaces(knightSpace,board);
		
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere,knightMethodSpaces));

	}
	
	
	@Test
	void test2() {
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> knightMethodSpaces;

		whatShouldBeThere.add(new Space(6,2));
		whatShouldBeThere.add(new Space(5,1));

		Space knightSpace = TestingMethods.addPieceToBoard(new Knight(true), board, 7, 0);
		knightMethodSpaces = ((Knight)knightSpace.getPiece()).getMoveableSpaces(knightSpace,board);
		
		assertTrue(TestingMethods.areEqual(whatShouldBeThere,knightMethodSpaces));

	}
	

	@Test
	void test3() {
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> knightMethodSpaces;

		whatShouldBeThere.add(new Space(6,2));


		Space knightSpace = TestingMethods.addPieceToBoard(new Knight(true), board, 7, 0);
		TestingMethods.addPieceToBoard(new Pawn(true), board, 5, 1);
		knightMethodSpaces = ((Knight)knightSpace.getPiece()).getMoveableSpaces(knightSpace,board);

		assertTrue(TestingMethods.areEqual(whatShouldBeThere,knightMethodSpaces));

	}
	
	@Test
	void test4() {
		board = new Board(true);
		
		ArrayList<Space> whatShouldBeThere = new ArrayList<Space>();
		ArrayList<Space> knightMethodSpaces;

		whatShouldBeThere.add(new Space(6,2));
		whatShouldBeThere.add(new Space(5,1));

		Space knightSpace = TestingMethods.addPieceToBoard(new Knight(true), board, 7, 0);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 5, 1);
		knightMethodSpaces = ((Knight)knightSpace.getPiece()).getMoveableSpaces(knightSpace,board);

		assertTrue(TestingMethods.areEqual(whatShouldBeThere,knightMethodSpaces));

	}
}

class testKing {
	private Board board;
	
	@Test
	void Test1() {
		board = new Board(true);
		TestingMethods.addPieceToBoard(new King(false), board, 0, 0);
		TestingMethods.addPieceToBoard(new Queen(true), board, 1, 0);
		TestingMethods.addPieceToBoard(new Queen(true), board, 1, 1);
		TestingMethods.addPieceToBoard(new Queen(true), board, 0, 1);
		
		assertFalse(board.movePiece(false,board.getSpace(0, 0),board.getSpace(1, 0)));
	}
	
	@Test
	void Test2() {
		board = new Board(true);
		TestingMethods.addPieceToBoard(new King(false), board, 0, 0);
		TestingMethods.addPieceToBoard(new Queen(true), board, 1, 0);
		TestingMethods.addPieceToBoard(new Queen(true), board, 1, 1);
		TestingMethods.addPieceToBoard(new Queen(true), board, 0, 1);
		
		assertTrue(board.isCheckmate(false));
	}
	
	@Test
	void Test3() {
		board = new Board(true);
		TestingMethods.addPieceToBoard(new King(false), board, 0, 0);
		TestingMethods.addPieceToBoard(new Rook(true), board, 3, 0);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 1, 1);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 0, 1);
	
		assertTrue(board.isCheckmate(false));
	}
	@Test
	void Test4() {
		board = new Board(true);
		TestingMethods.addPieceToBoard(new King(false), board, 0, 0);
		TestingMethods.addPieceToBoard(new Rook(true), board, 3, 0);
		TestingMethods.addPieceToBoard(new Rook(false), board, 1, 1);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 0, 1);
		
		assertFalse(board.isCheckmate(false));
	}
	@Test
	void Test5() {
		board = new Board(true);
		TestingMethods.addPieceToBoard(new King(false), board, 0, 0);
		TestingMethods.addPieceToBoard(new Rook(true), board, 3, 0);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 1, 1);
		TestingMethods.addPieceToBoard(new Pawn(false), board, 0, 1);
		TestingMethods.addPieceToBoard(new Rook(false), board, 3, 1);
		
		assertFalse(board.isCheckmate(false));
	}
	
	@Test
	void Test6() {
		board = new Board(true);
		TestingMethods.addPieceToBoard(new Pawn(true), board, 1, 1);
		TestingMethods.addPieceToBoard(new King(true), board, 2, 1);
		TestingMethods.addPieceToBoard(new King(false), board, 0, 1);
		
		assertFalse(board.isCheckmate(false));
	}
}

