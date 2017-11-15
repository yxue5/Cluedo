package code;
/**
 * Class for storing Weapon Card
 */
public class WeaponCard extends Card {
    public Library.WeaponToken weapon;
    /**
     * Create card
     *
     * @param type  Type from Library enums
     * @param owner Player who got this card
     * @param weapon Type from Library enums
     */
    public WeaponCard(Library.CardType type, Player owner, Library.WeaponToken weapon) {
        super(Library.CardType.Weapon, owner);
        this.weapon = weapon;
    }
}
