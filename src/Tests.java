




import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;






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
	System.out.println(rookMethodSpaces);
	System.out.println(whatShouldBeThere);
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

