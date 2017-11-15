package code;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class for initiating whole board
 */
public class Board {
	
    ArrayList<Player> players = new ArrayList<>();
	ArrayList<Card> cardList = new ArrayList<Card>();
    ArrayList<Card> winningCombo = new ArrayList<>();
	int playerIndex = 0;
    public Hallway hl;
    public Board() {
         hl=new Hallway();
    	 players.add(new Player(Library.PersonToken.MissScarlett,new int[]{8,0},hl));
    	 players.add(new Player(Library.PersonToken.ProfessorPlum,new int[]{16,0},hl));
    	 players.add(new Player(Library.PersonToken.MrsWhite,new int[]{0,5},hl));
    	 players.add(new Player(Library.PersonToken.MrsPeacock,new int[]{23,7},hl));
    	 players.add(new Player(Library.PersonToken.ReverendGreen,new int[]{17,23},hl));
    	 players.add(new Player(Library.PersonToken.ColonelMustard,new int[]{23,16},hl));
    	 distributeCards();
    }
    public void distributeCards(){
    	cardList.add(new WeaponCard(Library.CardType.Weapon, null, Library.WeaponToken.Candlestick));
    	cardList.add(new WeaponCard(Library.CardType.Weapon, null, Library.WeaponToken.Knife));
    	cardList.add(new WeaponCard(Library.CardType.Weapon, null, Library.WeaponToken.LeadPipe));
    	cardList.add(new WeaponCard(Library.CardType.Weapon, null, Library.WeaponToken.Revolver));
    	cardList.add(new WeaponCard(Library.CardType.Weapon, null, Library.WeaponToken.Rope));
    	cardList.add(new WeaponCard(Library.CardType.Weapon, null, Library.WeaponToken.Wrench));
    	cardList.add(new PersonCard(Library.CardType.Person, null, Library.PersonToken.ColonelMustard));
    	cardList.add(new PersonCard(Library.CardType.Person, null, Library.PersonToken.MissScarlett));
    	cardList.add(new PersonCard(Library.CardType.Person, null, Library.PersonToken.MrsPeacock));
    	cardList.add(new PersonCard(Library.CardType.Person, null, Library.PersonToken.MrsWhite));
    	cardList.add(new PersonCard(Library.CardType.Person, null, Library.PersonToken.ProfessorPlum));
    	cardList.add(new PersonCard(Library.CardType.Person, null, Library.PersonToken.ReverendGreen));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Ballroom));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Billiard));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Conservatory));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.DiningRoom));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Hall));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Kitchen));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Library));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Lounge));
    	cardList.add(new RoomCard(Library.CardType.Room, null, Library.RoomToken.Study));
    	
    	Random r = new Random();
    	boolean roomCard = false;
    	boolean personCard = false;
    	boolean weaponCard = false;
    	while(roomCard==false || personCard == false || weaponCard ==false){
        	int idx = r.nextInt(cardList.size());
        	Card c = cardList.get(idx);
        	if(c instanceof RoomCard && roomCard == false){
        		winningCombo.add(cardList.remove(idx));
        		roomCard = true;
        	}
        	else if(c instanceof PersonCard && personCard == false){
        		winningCombo.add(cardList.remove(idx));
        		personCard = true;
        	}
        	else if(c instanceof WeaponCard && weaponCard == false){
        		winningCombo.add(cardList.remove(idx));
        		weaponCard = true;
        	}
    	}
    	for( Player p : players){
    		for(int i = 0;i<3;i++){
    	    	int idx = r.nextInt(cardList.size());
    	    	p.cards.add(cardList.remove(idx));
    		}
    	}
    }
	public void showPlayers(){
		for(Player p:players){
			hl.array[p.coord[0]][p.coord[1]].player=p;
		}
	}
	
	public void assignCards(){
		ArrayList<Card> cardList = new ArrayList<>();
	}
    public int rollDice()
    {
    	Random r = new Random();
    	int dice = r.nextInt(6)+1;
    	players.get(playerIndex).dice=dice;
    	return dice;
    }
    public Player verifySuggestion(Player p,String person, String room, String weapon){
    	for(int j = players.indexOf(p);j<players.size();j++)
    	{
    		Player others = players.get(j);
    		for(int i = 0; i < 3; i++)
    		{
    			System.out.println(others.cards.get(i));
    			System.out.println(room);
    			if(others.cards.get(i)!= null && others.cards.get(i).name!=null &&
    			others.cards.get(i).name.equals(person) ||
    			others.cards.get(i).name.equals(room) || 
    			others.cards.get(i).name.equals(weapon))
    			{
    				return others;
    			}
    		}
    	}
    	for(int j =0;j<players.indexOf(p);j++)
    	{
    		Player others = players.get(j);
    		for(int i = 0; i < 3; i++)
    		{
    			if(others.cards.get(i)!= null && others.cards.get(i).name!=null &&
    			others.cards.get(i).name.equals(person) ||others.cards.get(i).name.equals(room) || others.cards.get(i).name.equals(weapon))
    			{
    				return others;
    			}
    		}
    	}
    	return null;
    }
}

