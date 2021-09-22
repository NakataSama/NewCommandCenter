package com.elo7.newcommandcenter;

import com.elo7.newcommandcenter.field.Field;
import com.elo7.newcommandcenter.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.vehicle.Vehicle;
import com.elo7.newcommandcenter.vehicle.probe.Probe;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CommandCenter {

    static int fieldId = 1;
    static int vehicleId = 1;
    private Field field;

    public String menu() {
        Scanner sc = new Scanner(System.in);
        int vehicleId;
        String command;

        try {
            System.out.println("Choose which vehicle to command by typing ID");
            vehicleId = sc.nextInt();

            System.out.println("Type your command (valid characters = L, R, M)");
            command = sc.next();

            executeCommand(vehicleId, command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "y";
        }

        System.out.println("Continue with more commands?");
        System.out.println("Respond with y [yes] or n [no]");
        System.out.println("Respond with p to print the field status");
        command = sc.next();

        if (command.equalsIgnoreCase("y"))
            return command;
        else if (command.equalsIgnoreCase("n"))
            return command;
        else if (command.equalsIgnoreCase("p")) {
            printFieldStatus();
            return command;
        } else
            throw new RuntimeException("Invalid command!");
    }

    public void buildCommandCenter(int x, int y, int numberOfVehicles) {
        if (x * y < numberOfVehicles)
            throw new RuntimeException("Too many vehicles for this field");

        if (x <= 0 || y <= 0)
            throw new RuntimeException("Field is too small!");

        if (numberOfVehicles <= 0)
            throw new RuntimeException("Number of vehicles is wrong! You should have at least 1");

        field = buildField(x, y);
        field = buildVehicles(field, numberOfVehicles);
        printFieldStatus();
    }

    private void executeCommand(int id, String command) {
        System.out.println("Executing command...");
        Vehicle vehicle = null;

        if (field.getVehicleById(id).isPresent()) {
            vehicle = field.getVehicleById(id).get();

            for (char c: command.toLowerCase().toCharArray()) {
                if (isValidCommand(c)) {
                    if (c == 'l' || c == 'r')
                        vehicle = vehicle.rotate(c);
                    if (c == 'm')
                        vehicle = vehicle.move();
                } else
                    throw new RuntimeException("Invalid Command");
            }

            field.saveVehicle(vehicle);
            System.out.println("Command executed!");
        } else
            throw new RuntimeException("Invalid Vehicle ID");
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
