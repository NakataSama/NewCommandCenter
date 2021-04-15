package com.elo7.newcommandcenter.probe;

import com.elo7.newcommandcenter.field.Field;
import com.elo7.newcommandcenter.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;

public class Probe {
	
	private int id;
	private Position position;
	private Orientation orientation;
	private int movements;
	private Field field;
	
	public Probe() {}
	
	public Probe(int id, Position position, Orientation orientation, int movements, Field field) {
		this.id = id;
		this.position = position;
		this.orientation = orientation;
		this.movements = movements;
		this.field = field;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("Probe ID = ").append(id)
				.append(position)
				.append(" | Facing = ").append(orientation.getDescription())
				.append(" | Num. of movements = ").append(movements).toString();
	}
	
	public Probe move() {
		Position newPosition = this.position.changeCurrentPosition(orientation, position);
		return new Probe(id, newPosition, orientation, movements+1, field);
	}
	
	public Probe rotate(String direction) {
		
		if (isValidDirection(direction)) 
			return new Probe(id, position, changeOrientationIdFromDirection(direction), movements, field);
		 else 
			return new Probe(id, position, orientation, movements, field);
		
	}
	
	private boolean isValidDirection(String direction) {
		
		if (direction.toUpperCase().equals("L")) 
			return true;
		if (direction.toUpperCase().equals("R")) 
			return true;
		else 
			return false;
		
	}
	
	private Orientation changeOrientationIdFromDirection(String direction) {
		
		int orientationId = this.orientation.getId();
		
		if (direction.toUpperCase().equals("L")) {
			if (this.orientation == Orientation.NORTH) 
				orientationId = 8;
			else 
				orientationId--;
		}
			
		if (direction.toUpperCase().equals("R")) {
			if (this.orientation == Orientation.NORTHWEST) 
				orientationId = 1;
			else 
				orientationId++;
		}
		
		return Orientation.getOrientationById(orientationId);
		
	}
	
}
