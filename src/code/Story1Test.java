package code;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Library.PersonToken;

public class Story1Test {
	
	//tests horizontal movement
	@Test
	public void testHorizontal() {
		Board b = new Board();
		Player p = new Player(Library.PersonToken.MrsWhite, new int[]{8,7}, b.hl);
		int[][] testMoves = new int[2][5];
		for(int i = 0; i <5;i++)
		{
			testMoves[0][i] = 8;
			testMoves[1][i] = 7+1+i;
		}
		assertTrue(p.move(5,testMoves));
	}
	
	@Test
	public void testDiagonal() {
			Board b = new Board();
			Player d = new Player(Library.PersonToken.ColonelMustard, new int[]{0,5}, b.hl);
			int[][] testMoves = new int[2][1];
			testMoves[0][0]=1;
			testMoves[1][0]=6;
			assertFalse(d.move(1,testMoves));
	}
	
	@Test
	public void testVertical() {
		Board b = new Board();
		Player p = new Player(Library.PersonToken.MrsWhite, new int[]{0,5}, b.hl);
		int[][] testMoves = new int[2][5];
		for(int i = 0; i <5;i++)
		{
			testMoves[0][i] = 0+i+1;
			testMoves[1][i] = 5;
		}
		assertTrue(p.move(5,testMoves));
	}
	
	@Test
	public void testVerticalHorizontal(){
		Board b = new Board();
		Player p = new Player(Library.PersonToken.MrsWhite, new int[]{0,5}, b.hl);
		int[][] testMoves = new int[2][7];
		for(int i = 0; i <6;i++)
		{
			testMoves[0][i] = 0+i+1;
			testMoves[1][i] = 5;
		}
		testMoves[0][6]=6;
		testMoves[1][6]=6;
		assertTrue(p.move(7,testMoves));
	}
	
	@Test
	public void testPassageway() {
		Board b = new Board();
		Player d = new Player(Library.PersonToken.ColonelMustard, new int[]{19,16}, b.hl);
		int[][] testMoves = new int[2][1];
		testMoves[0][0]=6;
		testMoves[1][0]=4;
		assertTrue(d.move(1,testMoves));
	}
	
	@Test
	public void testWall(){
		Board b = new Board();
		Player d = new Player(Library.PersonToken.ColonelMustard, new int[]{1,16}, b.hl);
		int[][] testMoves = new int[2][3];
		for(int i = 0; i <3;i++)
		{
		testMoves[0][0]=1;
		testMoves[1][0]=16+i;
		}
		assertFalse(d.move(3,testMoves));
	}
	@Test
	public void moveRoom(){
		Board b = new Board();
		Player d = new Player(Library.PersonToken.ColonelMustard, new int[]{9,14}, b.hl);
		int[][] testMoves = new int[2][1];
		testMoves[0][0]=9;
		testMoves[1][0]=15;
		assertNull(d.place);
		d.move(1, testMoves);
		assertEquals(d.place.type, Library.RoomToken.Ballroom);
	}
	@Test//not contiguous
	public void testContiguous(){
		Board b = new Board();
		Player p = new Player(Library.PersonToken.ColonelMustard, new int[]{17,7}, b.hl);
		int[][] testMove = new int[2][1];
		for(int i = 0; i<1;i++){
			testMove[i][0] = 0+2+i;
			testMove[1][i] = 1;
		}
		assertFalse(p.move(1, testMove));
	}
	@Test
	public void testDice(){
		Board b = new Board();
		Player p = new Player(Library.PersonToken.MrsWhite, new int[]{8,7}, b.hl);
		int[][] testMoves = new int[2][5];
		for(int i = 0; i <5;i++)
		{
			testMoves[0][i] = 8;
			testMoves[1][i] = 7+1+i;
		}
		assertFalse(p.move(3,testMoves));
	}
}
