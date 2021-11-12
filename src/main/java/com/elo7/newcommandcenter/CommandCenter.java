package com.elo7.newcommandcenter;

import com.elo7.newcommandcenter.field.Field;
import com.elo7.newcommandcenter.vehicle.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.vehicle.Vehicle;
import com.elo7.newcommandcenter.vehicle.probe.Probe;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CommandCenter {

    private Field field;

    public String menu() {
        Scanner sc = new Scanner(System.in);
        int vehicleId;
        String command;

        try {
            printFieldStatus();
            System.out.println("Choose which vehicle to command by typing ID");
            vehicleId = Integer.parseInt(sc.next());

            System.out.println("Type your command (valid characters = L, R, M)");
            command = sc.next();

            executeCommand(vehicleId, command,field);
            printFieldStatus();

            System.out.println("Continue with more commands?");
            System.out.println("Respond with y [yes] or n [no]");

            command = sc.next();

            if (command.equalsIgnoreCase("y"))
                return command;
            else if (command.equalsIgnoreCase("n"))
                return command;
            else
                throw new RuntimeException("Invalid command!");
        } catch (Exception e) {
            System.out.println(e.toString());
            return "";
        }
    }

    public boolean buildCommandCenter(int x, int y, int numberOfVehicles) {
        if (x * y < numberOfVehicles)
            throw new RuntimeException("Too many vehicles for this field");

        if (x <= 0 || y <= 0)
            throw new RuntimeException("Field is too small!");

        if (numberOfVehicles <= 0)
            throw new RuntimeException("Number of vehicles is wrong! You should have at least 1");

        field = buildField(x, y);
        field = buildVehicles(field, numberOfVehicles);
        return true;
    }

    private Field buildField(int x, int y) {
        return new Field(1, new Position(x, y) , new ArrayList<Vehicle>());
    }

    private Field buildVehicles(Field fieldInput, int numberOfVehicles) {
        for(int i = 0; i < numberOfVehicles; i++) {
            int probeId = i+1;
            int randomX = ThreadLocalRandom.current().nextInt(0, fieldInput.getPosition().getX());
            int randomY = ThreadLocalRandom.current().nextInt(0, fieldInput.getPosition().getY());
            Probe probe = new Probe(probeId, "Probe " + probeId, new Position(randomX, randomY), Orientation.NORTH, 0, field);
            fieldInput = fieldInput.saveVehicle(probe);
        }
        return fieldInput;
    }

    private void executeCommand(int id, String command, Field field) {
        System.out.println("Executing command...");
        Vehicle vehicle;

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

    private boolean isValidCommand(char command) {
        if (command == 'l' || command == 'r' || command == 'm')
            return true;
        else
            return false;
    }

    private void printFieldStatus() {
        StringBuilder status = new StringBuilder();
        status.append("===== FIELD ").append(field.getId()).append(" STATUS =====").append("\n");
        for (int i = field.getPosition().getY()-1; i >=0 ; i--) {
            List<Position> occupiedPositionsByRow = field.getVehiclesPositionsByYCoordinate(i);

            for (int j = 0; j < field.getPosition().getX(); j++) {
                int coordinate = j;
                boolean isXCoordinateOccupied = occupiedPositionsByRow.stream()
                        .anyMatch(position -> position.getX() == coordinate);

                if (isXCoordinateOccupied)
                    status.append("[ ☗ ] ");
                else
                    status.append("[   ] ");
            }
            status.append("\n");
        }
        System.out.println(status);
        field.getVehicles()
                .stream()
                .sorted(Comparator.comparing(Vehicle::getId))
                .forEach(System.out::println);
        System.out.println("========= END =========");
    }
}
