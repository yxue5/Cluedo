package code;

import static org.junit.Assert.*;

import org.junit.Test;

public class Story2Test {
	Board b = new Board();

	@Test
	public void testPlayerSuggestion() {
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "ProfessorPlum"),
				new Card(Library.CardType.Person, disprover, "MrsWhite"), 
				new Card(Library.CardType.Person, disprover, "MrsScarlett"));
		b.players.add(disprover);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		assertEquals(disprover, b.verifySuggestion(suggester, "ProfessorPlum", "Lounge","Rope" ));
	}


	@Test
	public void testWeaponSuggestion() {
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "ProfessorPlum"),
				new Card(Library.CardType.Weapon, disprover, "Rope"), 
				new Card(Library.CardType.Room, disprover, "Kitchen"));
		b.players.add(disprover);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		assertEquals(disprover, b.verifySuggestion(suggester, "MrsWhite", "Lounge","Rope" ));
	}
	
	@Test
	public void testRoomSuggestion() {
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "ProfessorPlum"),
				new Card(Library.CardType.Weapon, disprover, "Rope"), 
				new Card(Library.CardType.Room, disprover, "Kitchen"));
		b.players.add(disprover);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		assertEquals(disprover, b.verifySuggestion(suggester, "MissScarlet", "Kitchen","LeadPipe" ));
	}
	
	@Test
	public void testTwoMatching(){
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "MissScarlet"),
				new Card(Library.CardType.Weapon, disprover, "Rope"), 
				new Card(Library.CardType.Room, disprover, "Kitchen"));
		b.players.add(disprover);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		assertEquals(disprover, b.verifySuggestion(suggester, "MissScarlet", "Kitchen","LeadPipe" ));
	}
	
	@Test
	public void testPersonAfter(){
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "ProfessorPlum"),
				new Card(Library.CardType.Weapon, disprover, "Rope"), 
				new Card(Library.CardType.Room, disprover, "Kitchen"));
		b.players.add(disprover);
		assertEquals(disprover, b.verifySuggestion(suggester, "MissScarlet", "Kitchen","LeadPipe" ));
	}
	
	@Test
	public void testPersonBefore(){
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "ProfessorPlum"),
				new Card(Library.CardType.Weapon, disprover, "Rope"), 
				new Card(Library.CardType.Room, disprover, "Kitchen"));
		b.players.add(disprover);
		assertEquals(b.verifySuggestion(suggester, "MissScarlet", "Kitchen","LeadPipe" ),disprover);
	}
	@Test
	public void personHasOwnCard(){
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "ProfessorPlum"),
				new Card(Library.CardType.Weapon, disprover, "Rope"), 
				new Card(Library.CardType.Room, disprover, "Kitchen"));
		b.players.add(disprover);
		assertTrue(b.verifySuggestion(suggester, "MissScarlet", "Conservatory","LeadPipe" ).equals(suggester));
	}
	
	@Test
	public void NoOneHasCard(){
		Player suggester = new Player(Library.PersonToken.ColonelMustard, new int[]{9,16}, b.hl);
		suggester.setCards(new Card(Library.CardType.Person, suggester, "ReverendGreen"),
				new Card(Library.CardType.Weapon, suggester, "Candlestick"), 
				new Card(Library.CardType.Room, suggester, "Conservatory"));
		b.players.add(suggester);
		Player random = new Player(Library.PersonToken.MrsPeacock, new int[]{8,8}, b.hl);
		random.setCards(new Card(Library.CardType.Person, random, "ColonelMustard"),
				new Card(Library.CardType.Weapon, random, "Dagger"), 
				new Card(Library.CardType.Room, random, "DiningRoom"));
		b.players.add(random);
		Player disprover = new Player(Library.PersonToken.MissScarlett, new int[]{8,7}, b.hl);
		disprover.setCards(new Card(Library.CardType.Person, disprover, "ProfessorPlum"),
				new Card(Library.CardType.Weapon, disprover, "Rope"), 
				new Card(Library.CardType.Room, disprover, "Kitchen"));
		b.players.add(disprover);
		assertTrue(b.verifySuggestion(suggester, "MissScarlet", "Lounge","LeadPipe" )==null);
	}
	
	
}
