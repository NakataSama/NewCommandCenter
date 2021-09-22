package com.elo7.newcommandcenter;

import java.awt.event.TextEvent;
import java.util.Scanner;

public class App {

    public static void main( String[] args ) {

		int x = 20; //Type your field horizontal size here;
		int y = 20; //Type your field vertical size here;
		int numberOfVehicles = 2; //Type how many vehicles would you like to deploy on the field

		CommandCenter commandCenter = new CommandCenter();
		commandCenter.buildCommandCenter(x, y, numberOfVehicles);

		Scanner sc = new Scanner(System.in);
		int commandChoice = 0;
		while (commandChoice != 2) {
			System.out.println("Choose which vehicle to command by typing ID");
			int vehicleId = sc.nextInt();

			System.out.println("Type your command (valid characters = L, R, M)");
			String command = sc.next();

			commandCenter.executeCommand(vehicleId, command);
			System.out.println("Any number - More commands, 2 - Exit");
			commandChoice = sc.nextInt();
		}
    }
}
