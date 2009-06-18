package info.yalamanchili.gwt.ui;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Position {
	ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4), SIX(5), SEVEN(6), EIGHT(7), NINE(
			8), TEN(9);
	private static final Map<Position, Integer> lookup = new HashMap<Position, Integer>();
	static {
		for (Position position : EnumSet.allOf(Position.class)) {
			lookup.put(position, position.getValue());
		}
	}
	private int value;

	private Position(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static int getValue(Position pos) {
		return lookup.get(pos);
	}

}
