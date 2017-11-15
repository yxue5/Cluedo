package code;
/**
 * Class for storing Room Card
 */
public class RoomCard extends Card {
    public Library.RoomToken room;
    /**
     * Create card
     *
     * @param type  Type from Library enums
     * @param owner Player who got this card
     * @param room Type from Library enums
     */
    public RoomCard(Library.CardType type, Player owner, Library.RoomToken room) {
        super(Library.CardType.Room, owner);
        this.room=room;
    }
}
