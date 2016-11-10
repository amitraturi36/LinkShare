package src;

public enum Seriousness {
	SERIOUS, VERY_SERIOUS, CASUAL;

	public static Seriousness stringToEnum(String stringToEnumdata) {
        if (stringToEnumdata.compareToIgnoreCase("SERIOUS")==0) {
            return SERIOUS;
        } else if (stringToEnumdata.compareToIgnoreCase("VERY_SERIOUS")==0) {
            return VERY_SERIOUS;
        } else {
            return CASUAL;
        }

    }
}
