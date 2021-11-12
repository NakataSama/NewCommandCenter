package com.elo7.newcommandcenter.vehicle;

import com.elo7.newcommandcenter.field.Field;
import com.elo7.newcommandcenter.vehicle.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class Vehicle {

    private int id;
    private String name;
    private Position position;
    private Orientation orientation;
    private Field field;

    public Vehicle(int id, String name, Position position, Orientation orientation, Field field) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.field = field;
    }

    public abstract Vehicle move();
    public abstract Vehicle rotate(char direction);

    public int getId() {return this.id;}
    public String getName() {return this.name;}
    public Position getPosition() {return this.position;}
    public Orientation getOrientation() {return this.orientation;}
    public Field getField() {return this.field;}

    public void validatePosition() {

        if(!isVehiclePositionWithinFieldLimits(position))
            throw new RuntimeException("This vehicle will be out of bounds!");

        if(!isPositionAvailable(id, position))
            throw new RuntimeException("There's another vehicle in this position!");
    }

    private boolean isVehiclePositionWithinFieldLimits(Position position) {
        if(position.getX() >= field.getPosition().getX()
                || position.getY() >= field.getPosition().getY()
                || position.getX() < 0
                || position.getY() < 0)
            return false;
        else
            return true;
    }

    private boolean isPositionAvailable(int id, Position position) {
        Map<Integer, Position> vehicleIdToPosition = field.getVehicles().stream()
                .collect(Collectors.toMap(Vehicle::getId, Vehicle::getPosition));

        boolean isVehiclePresentWithId = vehicleIdToPosition.containsKey(id);
        boolean isPositionOccupied = vehicleIdToPosition.containsValue(position);

        if (!isPositionOccupied)
            return true;
        else if (isPositionOccupied
                && isVehiclePresentWithId
                && vehicleIdToPosition.get(id).equals(position))
            return true;
        else
            return false;

//		for (Map.Entry<Integer, Position> vehicle : vehicleIdToPosition.entrySet()) {
//			if (vehicle.getValue().equals(vehiclePosition)) {
//				if (vehicle.getKey().equals(vehicleId))
//					return true;
//				else
//					return false;
//			}
//		}

//		for (Vehicle v: vehicles) {
//			if(v.getPosition().equals(vehiclePosition)) {
//
//				if(v.getId() == vehicleId)
//					return true;
//				else
//					return false;
//			}
//		}
//		return true;
    }

}
