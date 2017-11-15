package code;
/**
 * Class for storing Person Card
 */
public class PersonCard extends Card {
    public Library.PersonToken person;
    /**
     * Create card
     *
     * @param type  Type from Library enums
     * @param owner Player who got this card
     * @param person Type from Library enums
     */
    public PersonCard(Library.CardType type, Player owner, Library.PersonToken person) {
        super(Library.CardType.Room, owner);
        this.person = person;
    }
}
