package com.elo7.newcommandcenter.field;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.vehicle.Vehicle;

public class Field {
	
	private int id;
	private Position position;
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public Field() {}
	
	public Field(int id, Position position, List<Vehicle> vehicles) {
		this.id = id;
		this.position = position;
		this.vehicles = vehicles;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Field ID = ").append(id)
				.append(" | Max X = ").append(position.getX())
				.append(" | Max Y = ").append(position.getY())
				.append(" | Number of Vehicles = ").append(vehicles.size()).toString();
	}

	public Field saveVehicle(Vehicle vehicle) {

		if(!isVehiclePositionWithinFieldLimits(vehicle.getPosition()))
			throw new RuntimeException("This vehicle will be out of bounds!");

		if(!isPositionAvailable(vehicle.getId(), vehicle.getPosition())) {
			throw new RuntimeException("There's another vehicle in this position!");
		}

		if(getVehicleById(vehicle.getId()).isPresent()) {
			int index = vehicles.indexOf(getVehicleById(vehicle.getId()).get());
			vehicles.set(index, vehicle);
			return new Field(id, position, vehicles);
		} else {
			vehicles.add(vehicle);
			return new Field(id, position, vehicles);
		}
	}

	private boolean isVehiclePositionWithinFieldLimits(Position vehiclePosition) {
		if(vehiclePosition.getX() > position.getX()
				|| vehiclePosition.getY() > position.getY()
				|| vehiclePosition.getX() < 0
				|| vehiclePosition.getY() < 0)
			return false;
		else
			return true;
	}

	public boolean isPositionAvailable(int vehicleId, Position vehiclePosition) {
		Map<Integer, Position> mappedPositions = vehicles.stream()
				.collect(Collectors.toMap(Vehicle::getId, Vehicle::getPosition));

		boolean isVehiclePresentWithId = mappedPositions.containsKey(vehicleId);
		boolean isPositionOccupied = mappedPositions.containsValue(vehiclePosition);

		if (!isPositionOccupied)
			return true;
		else if (isPositionOccupied
				&& isVehiclePresentWithId
				&& mappedPositions.get(vehicleId).equals(vehiclePosition))
			return true;
		else
			return false;
//		for (Map.Entry<Integer, Position> vehicle : mappedPositions.entrySet()) {
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

	public Optional<Vehicle> getVehicleById(int id) {
		return this.vehicles.stream()
				.filter(v -> v.getId() == id)
				.findAny();
	}
}
