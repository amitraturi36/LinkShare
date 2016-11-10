package src;

public enum Visibility {
	 PUBLIC, PRIVATE;

	    public static Visibility stringToEnum(String string) {

	        if (string.equalsIgnoreCase("PRIVATE")) {
	            return PRIVATE;
	        } else {
	        	 return  PUBLIC;
	        }

	    }
}
