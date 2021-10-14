package com.elo7.newcommandcenter.vehicle;

import com.elo7.newcommandcenter.vehicle.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;

public abstract class Vehicle {

    int id;
    String name;
    Position position;
    Orientation orientation;

    public Vehicle(int id, String name, Position position, Orientation orientation) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.orientation = orientation;
    }

    public abstract Vehicle move();
    public abstract Vehicle rotate(char direction);

    public int getId() {return this.id;}
    public String getName() {return this.name;}
    public Position getPosition() {return this.position;}
    public Orientation getOrientation() {return this.orientation;}

}
