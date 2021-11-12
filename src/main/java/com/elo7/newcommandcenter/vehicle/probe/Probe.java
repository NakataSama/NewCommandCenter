package com.elo7.newcommandcenter.vehicle.probe;

import com.elo7.newcommandcenter.field.Field;
import com.elo7.newcommandcenter.vehicle.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.vehicle.Vehicle;

public class Probe extends Vehicle {

	private int movements;

	public Probe(int id, String name, Position position, Orientation orientation, int movements, Field field) {
		super(id, name, position, orientation, field);
		this.movements = movements;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append(getName())
				.append(getPosition())
				.append(" | Facing = ").append(getOrientation().getDescription())
				.append(" | Num. of movements = ").append(movements).toString();
	}

	@Override
	public Probe move() {
		validatePosition();
		Position newPosition = getPosition().changePositionFromVehicleOrientation(getOrientation(), 1);
		return new Probe(getId(),getName(), newPosition, getOrientation(), movements+1, getField());
	}

	@Override
	public Probe rotate(char direction) {
		Orientation newOrientation = getOrientation().changeOrientationFromDirectionInput(direction);
		return new Probe(getId(), getName(), getPosition(), newOrientation, movements, getField());
	}
}
