package com.elo7.newcommandcenter.probe;

import com.elo7.newcommandcenter.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;

public class Probe {
	
	private Position position;
	private Orientation orientation;
	private int movements;
	
	public Probe() {}
	
	public Probe(Position position, Orientation orientation, int movements) {
		this.position = position;
		this.orientation = orientation;
		this.movements = movements;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(this.position)
				.append(" | Facing = ").append(this.orientation.getDescription())
				.append(" | Num. of movements = ").append(this.movements).toString();
	}
	
	public Probe move() {
		Position newPosition = this.position.changeCurrentPosition(this.orientation, this.position);
		return new Probe(newPosition, orientation, movements+1);
	}
	
	public Probe rotate(String direction) {
		
		if (isValidDirection(direction)) 
			return new Probe(position, getOrientationFromDirection(direction), movements);
		 else 
			return new Probe(position, orientation, movements);
		
	}
	
	private boolean isValidDirection(String direction) {
		
		if (direction == "L") 
			return true;
		if (direction == "R") 
			return true;
		else 
			return false;
		
	}
	
	private Orientation getOrientationFromDirection(String direction) {
		
		int orientationId = this.orientation.getId();
		
		if (direction == "L") {
			if (this.orientation == Orientation.NORTH) {
				orientationId = 8;
			} else {
				orientationId--;
			}
		}
			
		if (direction == "R") {
			if (this.orientation == Orientation.NORTHWEST) {
				orientationId = 1;
			} else {
				orientationId++;
			}
		}
		
		return Orientation.getOrientationById(orientationId);
		
	}
	
}
