package com.elo7.newcommandcenter.field;

import java.util.*;
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
		vehicle.validatePosition();
		if(getVehicleById(vehicle.getId()).isPresent()) {
			int index = vehicles.indexOf(getVehicleById(vehicle.getId()).get());
			vehicles.set(index, vehicle);
			return new Field(id, position, vehicles);
		} else {
			vehicles.add(vehicle);
			return new Field(id, position, vehicles);
		}
	}

	public Optional<Vehicle> getVehicleById(int id) {
		return this.vehicles.stream()
				.filter(v -> v.getId() == id)
				.findAny();
	}

	public List<Position> getVehiclesPositionsByYCoordinate(int coordinate) {
		return this.vehicles.stream()
				.filter(v -> v.getPosition().getY() == coordinate)
				.map(Vehicle::getPosition)
				.collect(Collectors.toList());
	}
}
