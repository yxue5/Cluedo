package code;
import java.util.ArrayList;
/**
 * Main work class for Room
 */
public class Room {
    Library.RoomToken type;
    public ArrayList<Door> doors = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>();
    boolean hallway;
    int hallwayN = 0;
    /**
     * @return have room secret , or not
     */
    public boolean isHallway() {
        return hallway;
    }
    /**
     * Adding players
     * @param players player for add
     */
    public void addPlayers(Player players) {
        this.players.add(players);
    }
    /** Remove players
     * @param players players for removing
     */
    public void removePlayers(Player players) {
        this.players.remove(players);
    }
    /** Main constructor for Room
     * @param type which Room is
     * @param hallway is secret passage is being
     * @param hallwayN number for passage (0 - if no)
     */
    public Room(Library.RoomToken type, boolean hallway, int hallwayN) {
        this.type = type;
        this.hallway = hallway;
        this.hallwayN = hallwayN;
    }
    /** Return number of secret passage
     * @returnnumber of secret passage
     */
    public int HallwayN() {
        return hallwayN;
    }
    /**
     * Adding door for this room
     * @param i X coord
     * @param i1 Y coord
     * @param hallway Main Hallway, for better feedback of all components
     */
    public void addDoor(int i, int i1, Hallway hallway) {
        Door door = new Door(this, new int[]{i, i1});
         hallway.array[i][i1].door=door;
    }
}
