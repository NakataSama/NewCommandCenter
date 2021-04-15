package com.elo7.newcommandcenter.position;

import com.elo7.newcommandcenter.orientation.Orientation;

public class Position {

	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position X = " + x + ", Y = " + y;
	}
	
	public Position changeCurrentPosition(Orientation orientation, Position currentPosition) {
		
		if (orientation.equals(Orientation.NORTH)) 
			return new Position(currentPosition.getX(), currentPosition.getY()+1);
		
		else if (orientation.equals(Orientation.NORTHEAST)) 
			return new Position(currentPosition.getX()+1, currentPosition.getY()+1);
		
		else if (orientation.equals(Orientation.EAST)) 
			return new Position(currentPosition.getX()+1, currentPosition.getY());
		
		else if (orientation.equals(Orientation.SOUTHEAST)) 
			return new Position(currentPosition.getX()+1, currentPosition.getY()-1);
		
		else if (orientation.equals(Orientation.SOUTH)) 
			return new Position(currentPosition.getX(), currentPosition.getY()-1);
		
		else if (orientation.equals(Orientation.SOUTHWEST)) 
			return new Position(currentPosition.getX()+1, currentPosition.getY()-1);
		
		else if (orientation.equals(Orientation.WEST)) 
			return new Position(currentPosition.getX()-1, currentPosition.getY());
		
		else if (orientation.equals(Orientation.NORTHWEST)) 
			return new Position(currentPosition.getX()-1, currentPosition.getY()+1);
		
		else 
			return new Position(currentPosition.getX(), currentPosition.getY());
		
	}
	
}