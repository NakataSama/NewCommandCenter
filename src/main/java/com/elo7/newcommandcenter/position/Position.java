package com.elo7.newcommandcenter.position;

import com.elo7.newcommandcenter.orientation.Orientation;

import java.util.Objects;

public class Position {

	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append(" | Position X = ").append(this.x)
				.append(" , Y = ").append(this.y).toString();
	}
	
	public Position changeCurrentVehiclePosition(Orientation orientation, int numberOfSteps) {
		
		if (orientation.equals(Orientation.NORTH)) 
			return new Position(x, y + numberOfSteps);
		
		else if (orientation.equals(Orientation.NORTHEAST)) 
			return new Position(x + numberOfSteps, y + numberOfSteps);
		
		else if (orientation.equals(Orientation.EAST)) 
			return new Position(x + numberOfSteps, y);
		
		else if (orientation.equals(Orientation.SOUTHEAST)) 
			return new Position(x + numberOfSteps, y - numberOfSteps);
		
		else if (orientation.equals(Orientation.SOUTH)) 
			return new Position(x, y - numberOfSteps);
		
		else if (orientation.equals(Orientation.SOUTHWEST)) 
			return new Position(x + numberOfSteps, y - numberOfSteps);
		
		else if (orientation.equals(Orientation.WEST)) 
			return new Position(x - numberOfSteps, y);
		
		else if (orientation.equals(Orientation.NORTHWEST)) 
			return new Position(x - numberOfSteps, y + numberOfSteps);
		
		else 
			return new Position(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return x == position.x && y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}