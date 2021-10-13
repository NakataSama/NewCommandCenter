package com.elo7.newcommandcenter;

public class App {

    public static void main( String[] args ) {

		int x = 2; //Type your field horizontal size here;
		int y = 2; //Type your field vertical size here;
		int numberOfVehicles = 2; //Type how many vehicles would you like to deploy on the field

		String commandChoice = "";

		CommandCenter commandCenter = new CommandCenter();
		commandCenter.buildCommandCenter(x, y, numberOfVehicles);

		do {
			commandChoice = commandCenter.menu();
		} while (!commandChoice.equalsIgnoreCase("n"));
    }
}
