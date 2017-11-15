package code;
/**
 * Class for storing Doors - connection with Rooms and Hallway
 */
public class Door {
    public Room rooms;
    public int[] coord;
    /**
     * Create Door
     *
     * @param room1 Room of this Door
     * @param coord Coord on Hallway board
     *              auto add to Room list
     */
    public Door(Room room1, int[] coord) {
        rooms = room1;
        this.coord = coord;
        room1.doors.add(this);
    }
}
