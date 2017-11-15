package code;
import java.util.ArrayList;
/**
 * Main class for all moving
 */
public class Hallway {
    public ArrayList<Room> rooms = new ArrayList<>();
    public Cell[][] array = new Cell[24][24];
    public Hallway() {
        //creating empty
        for (int i = 0; i < 24; i++) {
            for (int i2 = 0; i2 < 24; i2++) {
                array[i][i2] = new Cell();
            }
        }
        // deleting unreal cells
        for (int i = 0; i < 24; i = i + 23) {
            for (int i2 = 0; i2 < 24; i2 = i2 + 23) {
                array[i][i2].real = false;
            }
        }
        //adding cels for players
        array[8][0].real = true;
        array[17][0].real = true;
        array[0][6].real = true;
        array[23][7].real = true;
        array[23][17].real = true;
        array[0][18].real = true;
        array[23][17].real = true;
        array[0][18].real = true;
        //cell
        //  Ballroom
        Room Balroom = new Room(Library.RoomToken.Ballroom, false, 0);
        for (int i = 8; i < 16; i++) {
            for (int i2 = 16; i2 < 22; i2++) {
                array[i][i2].real = false;
            }
        }
        array[10][22].real = false;
        array[11][22].real = false;
        array[12][22].real = false;
        array[10][23].real = false;
        array[11][23].real = false;
        array[12][23].real = false;
        Balroom.addDoor(9, 15, this);
        Balroom.addDoor(14, 15, this);
        Balroom.addDoor(7, 18, this);
        Balroom.addDoor(16, 18, this);
        //Kitchen
        Room Kitchen = new Room(Library.RoomToken.Kitchen, true, 1);
        for (int i = 18; i < 24; i++) {
            for (int i2 = 17; i2 < 23; i2++) {
                array[i][i2].real = false;
            }
        }
        Kitchen.addDoor(19, 16, this);
        //Conservatory,
        Room Conservatory = new Room(Library.RoomToken.Conservatory, true, 2);
        for (int i = 0; i < 6; i++) {
            for (int i2 = 18; i2 < 23; i2++) {
                array[i][i2].real = false;
            }
        }
        array[5][22].real = true;
        Conservatory.addDoor(5, 22, this);
        Balroom.addDoor(14, 15, this);
        Balroom.addDoor(7, 18, this);
        Balroom.addDoor(16, 18, this);
        //DiningRoom
        Room DiningRoom = new Room(Library.RoomToken.DiningRoom, false, 0);
        for (int i = 16; i < 24; i++) {
            for (int i2 = 9; i2 < 16; i2++) {
                array[i][i2].real = false;
            }
        }
        array[16][15].real = true;
        array[17][15].real = true;
        array[18][15].real = true;
        DiningRoom.addDoor(17, 8, this);
        DiningRoom.addDoor(15, 12, this);
        //Billiard,
        Room Billiard = new Room(Library.RoomToken.Billiard, false, 0);
        for (int i = 0; i < 6; i++) {
            for (int i2 = 12; i2 < 16; i2++) {
                array[i][i2].real = false;
            }
        }
        Billiard.addDoor(1, 10, this);
        Billiard.addDoor(6, 14, this);
        //Library
        Room LibraryR = new Room(Library.RoomToken.Library, false, 0);
        for (int i = 0; i < 7; i++) {
            for (int i2 = 7; i2 < 11; i2++) {
                array[i][i2].real = false;
            }
        }
        array[6][6].real = true;
        array[6][10].real = true;
        LibraryR.addDoor(7, 11, this);
        LibraryR.addDoor(3, 12, this);
        //Hall,
        Room Hall = new Room(Library.RoomToken.Hall, false, 0);
        for (int i = 9; i < 14; i++) {
            for (int i2 = 0; i2 < 6; i2++) {
                array[i][i2].real = false;
            }
        }
        Hall.addDoor(8, 4, this);
        Hall.addDoor(11, 7, this);
        Hall.addDoor(12, 7, this);
        //Lounge,
        Room Lounge = new Room(Library.RoomToken.Lounge, true, 2);
        for (int i = 17; i < 24; i++) {
            for (int i2 = 0; i2 < 6; i2++) {
                array[i][i2].real = false;
            }
        }
        Lounge.addDoor(17, 6, this);
        //Study
        Room Study = new Room(Library.RoomToken.Study, true, 1);
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                array[i][i2].real = false;
            }
        }
        
        for (int i = 0; i < 24; i++) {
            for (int i2 = 0; i2 < 24; i2++) {
                if(array[i][i2].real != false)
                {
                	array[i][i2].real = true;
                	System.out.println("xPos:" +i+ "yPos"+i2); //Prints out all real cells
                }
            }
        }

        Study.addDoor(6, 4, this);
        rooms.add(Lounge);
        rooms.add(Kitchen);
        rooms.add(Conservatory);
        rooms.add(LibraryR);
        rooms.add(Hall);
        rooms.add(Study);
        rooms.add(DiningRoom);
        rooms.add(Billiard);
        rooms.add(Balroom);
        
        //properly allocates door
        for(Room r: rooms)
        {
        	for(Door d: r.doors)
        	{
        		int posX = d.coord[0];
        		int posY = d.coord[1];
        		array[posX][posY].door= d;
        		array[posX][posY].real= true;
        	}
        }
        

    }
    /**
     * Small private class for storing all points
     */
    class Cell {
        boolean real = true;
        Player player = null;
        Door door = null; 
    }
}
