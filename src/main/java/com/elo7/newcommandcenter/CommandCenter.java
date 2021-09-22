package com.elo7.newcommandcenter;

import com.elo7.newcommandcenter.field.Field;
import com.elo7.newcommandcenter.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.vehicle.Vehicle;
import com.elo7.newcommandcenter.vehicle.probe.Probe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CommandCenter {

    static int fieldId = 1;
    static int vehicleId = 1;
    private Field field;

    public void buildCommandCenter(int x, int y, int numberOfVehicles) {
        if(x * y < numberOfVehicles)
            throw new RuntimeException("Too many vehicles for this field");

        field = buildField(x, y);
        field = buildVehicles(field, numberOfVehicles);
        printFieldStatus();
    }

    public void executeCommand(int id, String command) {

        System.out.println("Executing command...");
        Vehicle vehicle = field.getVehicleById(id).get();

        for (char c: command.toLowerCase().toCharArray()) {
            if (isValidCommand(c)) {
                if (c == 'l' || c == 'r')
                    vehicle = vehicle.rotate(c);
                if (c == 'm')
                    vehicle = vehicle.move();

                field.saveVehicle(vehicle);
            } else
                throw new RuntimeException("Invalid Command");
        }
        System.out.println("Command executed! Loading field status...");
        printFieldStatus();
    }

    private Field buildField(int x, int y) {
        if (x > 0 && y > 0)
            return new Field(fieldId++, new Position(x, y) , new ArrayList<Vehicle>());
        else
            throw new RuntimeException("Invalid field size");
    }

    private Field buildVehicles(Field fieldInput, int numberOfVehicles) {
        for(int i = 0; i < numberOfVehicles; i++) {
            int idForProbeName = vehicleId;
            int randomX = ThreadLocalRandom.current().nextInt(0, fieldInput.getPosition().getX());
            int randomY = ThreadLocalRandom.current().nextInt(0, fieldInput.getPosition().getY());
            Probe probe = new Probe(vehicleId++, "Probe " + idForProbeName, new Position(randomX, randomY), Orientation.NORTH, 0, field);
            fieldInput = fieldInput.saveVehicle(probe);
        }
        return fieldInput;
    }

    private boolean isValidCommand(char command) {
        if (command == 'l' || command == 'r' || command == 'm')
            return true;
        else
            return false;
    }

    private void printFieldStatus() {
        System.out.println(field);
        field.getVehicles().forEach(System.out::println);
    }
}
