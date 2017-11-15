package code;
/**
 * Library class, with enums (for better understanding and group work)
 */
public class Library {
    public enum RoomToken {
        Ballroom,
        Kitchen,
        Conservatory,
        DiningRoom,
        Billiard,
        Library,
        Hall,
        Lounge,
        Study
    }

    public enum PersonToken {
        MissScarlett, ProfessorPlum, MrsPeacock, ReverendGreen, ColonelMustard, MrsWhite
    }

    public enum WeaponToken {
        Candlestick, Wrench, LeadPipe, Revolver, Rope, Knife
    }

    public enum CardType {
        Weapon, Person, Room
    }
}
