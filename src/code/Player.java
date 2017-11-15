package code;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import code.Hallway.Cell;
/**
 * CLass for storing and manipulating player
 */
public class Player {
    Library.PersonToken person;
   ArrayList<Card> cards = new ArrayList();
    Hallway hallway;
    Room place;
    int[] coord;
    int dice;
    Color playerColor;
    Icon playerIcon;
    /**
     * Main conctructor
     *
     * @param person Type of person
     * @param coord  Coord of beginning
     * @param hl     Main Hallway
     */
    public void setDice(int d){
    	dice = d;
    }
    public Player(Library.PersonToken person, int[] coord, Hallway hl) {
        this.person = person;
        this.coord = coord;
        System.out.println("myx:"+coord[0]+"my:"+coord[1]);
        hallway = hl;
        for(Room r: hl.rooms)
        {
        	for(Door d: r.doors)
        	{
        		int posX = d.coord[0];
        		int posY = d.coord[1];
        		if(coord[0]==posX && coord[1]==posY)
        		{
                	place = r;
        		}
        	}
        }
        switch(person){
        case MissScarlett:
        	playerColor = Color.RED;
        	playerIcon = new ImageIcon(new ImageIcon("src/code/ms scarlet.jpg").
					getImage().getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH));
        	break;
        case ProfessorPlum:
        	playerColor = Color.pink;
        	playerIcon = new ImageIcon(new ImageIcon("src/code/Professor Plum.jpg").
					getImage().getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH));
        	break;
        case ReverendGreen:
        	playerColor = Color.green;
        	playerIcon = new ImageIcon(new ImageIcon("src/code/mr green.jpg").
					getImage().getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH));
        	break;
        case MrsWhite:
        	playerColor = Color.white;
        	playerIcon = new ImageIcon(new ImageIcon("src/code/mrs white.jpg").
					getImage().getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH));
        	break;
        case MrsPeacock:
        	playerColor = Color.cyan;
        	playerIcon = new ImageIcon(new ImageIcon("src/code/mrs peacock.jpg").
					getImage().getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH));
        	break;
        case ColonelMustard:
        	playerColor = Color.ORANGE;
        	playerIcon = new ImageIcon(new ImageIcon("src/code/Colonel Mustard.jpg").
					getImage().getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH));
        	break;
        }
    }
 
    /**
     * Moving of player
     *
     * @param dice count of steps
     * @param room Room of destination
     * @return possibiblty of move
     */
    public //Story 1
    boolean move(int[][] movesMade) {
    	if(dice>=movesMade[0].length)
    	{
    		System.out.println(movesMade[0].length);
    		System.out.println(dice);
    		for(int i = 0; i < movesMade[0].length;i++)
    		{
    			System.out.println("move"+i);
    			//test the coordinates which are kept in a 2 x n array. xPos in first row, yPos in 2nd row.
    			int[] testCoord = {movesMade[0][i], movesMade[1][i]};
    			boolean g = moveSingle(testCoord);
    			if (g) {
    				dice--;
    				coord[0]= movesMade[0][i];
    				coord[1]=movesMade[1][i];
    			} 
    			else {
	            return false;
    			}
    		}
    		return true;
    	}
    	return false;
    }
    /**
     * Main checker for moving
     *
     * @param dice  count of moving
     * @param room  room of destination
     * @param place coord of destination
     * @return possibiblty of move
     */
    public boolean moveSingle(int[] destination)
    {
    	//coordinates for destination
	    Point2D dest = new Point2D.Double(destination[0],destination[1]);
	    //coordinates for valid locations to check destination against later on
	    ArrayList<Point2D> validDestinations = new ArrayList<Point2D>();
	    
	    //check array out of bounds
	    if(dest.getX() < 24 && dest.getY()<24)
	    {
	    	Cell c = hallway.array[destination[0]][destination[1]];
	    	//check if its a usable cell
	    	if(c.real==true)
	    	{	 
		    	if(place!=null)
		    	{
		    		//checks special ways to exit when there is a place
			    	if(place.type.equals(Library.RoomToken.Ballroom))
			    	{
			    		validDestinations.add(new Point2D.Double(9,15));
			    		validDestinations.add(new Point2D.Double(14,15));
			    		validDestinations.add(new Point2D.Double(7,18));
			    		validDestinations.add(new Point2D.Double(16,18));
			    	}
			    	else if(place.type.equals(Library.RoomToken.Kitchen))
			    	{
			    		//System.out.println("Kitchen!");
			    		validDestinations.add(new Point2D.Double(19, 16));
			    		validDestinations.add(new Point2D.Double(6,4));//secret passage to study
			    	}
			    	else if(place.type.equals(Library.RoomToken.Conservatory))
			    	{
			    		validDestinations.add(new Point2D.Double(5,22));
			    		validDestinations.add(new Point2D.Double(17,6));//secret passage to lounge
			    	}
			    	else if(place.type.equals(Library.RoomToken.DiningRoom))
			    	{
			    		validDestinations.add(new Point2D.Double(17,8));
			    		validDestinations.add(new Point2D.Double(15,12));
			    	}
			    	else if(place.type.equals(Library.RoomToken.Billiard))
			    	{
			    		validDestinations.add(new Point2D.Double(1,10));
			    		validDestinations.add(new Point2D.Double(6,14));
			    	}
			    	else if(place.type.equals(Library.RoomToken.Library))
			    	{
			    		validDestinations.add(new Point2D.Double(7,11));
			    		validDestinations.add(new Point2D.Double(3,12));
			    	}
			    	else if(place.type.equals(Library.RoomToken.Hall))
			    	{
			    		validDestinations.add(new Point2D.Double(8,4));
			    		validDestinations.add(new Point2D.Double(11,7));
			    		validDestinations.add(new Point2D.Double(12,7));
			    	}
			    	else if(place.type.equals(Library.RoomToken.Lounge))
			    	{
			    		validDestinations.add(new Point2D.Double(17,6));
			    		validDestinations.add(new Point2D.Double(5,22));//secret passage to conservatory
			    	}
			    	else if(place.type.equals(Library.RoomToken.Study))
			    	{
			    		validDestinations.add(new Point2D.Double(6,4));
			    		validDestinations.add(new Point2D.Double(19, 16));//secret passage to kitchen
			    	}
			    }
		    		//else check up, right, left,down
			    	//check right
			    	if(coord[0]<23 && hallway.array[coord[0]+1][coord[1]].real==true)
			    	{
			    		validDestinations.add(new Point2D.Double(coord[0]+1, coord[1]));
			    	}
			    	//check left
			    	if(coord[0]>0 && hallway.array[coord[0]-1][coord[1]].real==true)
			    	{
			    		validDestinations.add(new Point2D.Double(coord[0]-1, coord[1]));
			    	}
			    	//
			    	//check up
			    	if(coord[1]<23 && hallway.array[coord[0]][coord[1]+1].real==true)
			    	{
			    		validDestinations.add(new Point2D.Double(coord[0], coord[1]+1));
			    	}
			    	//check down
			    	if(coord[1]>0 && hallway.array[coord[0]][coord[1]-1].real==true)
			    	{
			    		validDestinations.add(new Point2D.Double(coord[0], coord[1]-1));
			    	}
		    	for(Point2D p:validDestinations)
		    	{
		    		System.out.println(p.getX());
		    		System.out.println(p.getY());
		    		System.out.println(dest.getX());
		    		System.out.println(dest.getY());
		    		//if the location the user inputted is one of the valid locations we calculated
		    		if(p.getX()==dest.getX()&&p.getY()==dest.getY()&&!(p.getX()==coord[0]&&p.getY()==coord[1]))
		    		{
		    			System.out.println("Here");
		    			if(c.door!=null && place!=null && c.door.rooms!=null && !(place.equals(c.door.rooms)))
		    			{
		    				place = c.door.rooms; //change room if using a secret passage to go to a different room
		    			}
		    			else if(place!=null)
		    			{
		    				place = null; //exits room if we were originally in one
		    			}
		    			else if(c.door!=null)
		    			{
		    				System.out.println(c.door.rooms);
		    				place = c.door.rooms; //enters room if the destination is at a door
		    				System.out.println(place.type.toString());
		    			}
		    			return true;
		    		}
		    	}
		    	return false;
	    	}
	    }
	    return false;
    }

    /**
     * Proving, if this some player has this cards
     *
     * @param players list of players from Hallway
     * @param card    Card of question
     * @return Possibility of truth
     */
    //Story 2
    Player proveSuggestionForCard(ArrayList<Player> players, Card card) {
        boolean prove = false;
        for (Player pl : players) {
            if (!(pl.equals(this))) {
                for (Card card2 : pl.cards) {
                    if (card.equals(card2)) {
                        return pl;
                    }
                }
            }
        }
        return null;
    }
}
