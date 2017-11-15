package code;
/**
 * Abstract Card class
 */
public class Card {
    public Library.CardType type;
    public Player Owner;
    public String name;
    /** Create card
     * @param type Type from, Library enums
     * @param owner Player who got this card
     */
    public Card(Library.CardType type, Player owner) {
        this.type = type;
        Owner = owner;
    }
    
    public Card(Library.CardType type, Player owner, String n) {
        this.type = type;
        Owner = owner;
        name = n;
    }
}
