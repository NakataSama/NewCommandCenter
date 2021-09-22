package com.elo7.newcommandcenter.orientation;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
	
	NORTH(1, "North"),
	NORTHEAST(2, "Northeast"),
	EAST(3, "East"),
	SOUTHEAST(4, "Southeast"),
	SOUTH(5, "South"),
	SOUTHWEST(6, "Southwest"),
	WEST(7, "West"),
	NORTHWEST(8, "Northwest");
	
	private int id;
	private String description;
	private static Map<Integer, Orientation> BY_ID;

	Orientation(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public static Orientation getOrientationById(int id) {
		return Orientation.BY_ID.get(id);
	}

	public Orientation changeOrientationFromDirectionInput(char direction) {
		int orientationId = getId();
		Orientation orientation = getOrientationById(orientationId);

		if (direction == 'l') {
			if (orientation == Orientation.NORTH)
				orientationId = 8;
			else
				orientationId--;
		}

		if (direction == 'r') {
			if (orientation == Orientation.NORTHWEST)
				orientationId = 1;
			else
				orientationId++;
		}
		return getOrientationById(orientationId);
	}
	
	static {
		BY_ID = new HashMap<Integer, Orientation>();
	    for(Orientation orientation : values()) 
	    	BY_ID.put(orientation.getId(), orientation);	
	}
}
