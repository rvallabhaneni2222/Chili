package info.yalamanchili.gwt.ui;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Enum Position.
 */
public enum Position {
	
	/** The ONE. */
	ONE(0), 
 
 /** The TWO. */
 TWO(1), 
 
 /** The THREE. */
 THREE(2), 
 
 /** The FOUR. */
 FOUR(3), 
 
 /** The FIVE. */
 FIVE(4), 
 
 /** The SIX. */
 SIX(5), 
 
 /** The SEVEN. */
 SEVEN(6), 
 
 /** The EIGHT. */
 EIGHT(7), 
 
 /** The NINE. */
 NINE(
			8), 
 
 /** The TEN. */
 TEN(9);
	
	/** The Constant lookup. */
	private static final Map<Position, Integer> lookup = new HashMap<Position, Integer>();
	static {
		for (Position position : EnumSet.allOf(Position.class)) {
			lookup.put(position, position.getValue());
		}
	}
	
	/** The value. */
	private int value;

	/**
	 * Instantiates a new position.
	 * 
	 * @param value the value
	 */
	private Position(int value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the value.
	 * 
	 * @param pos the pos
	 * 
	 * @return the value
	 */
	public static int getValue(Position pos) {
		return lookup.get(pos);
	}

}
