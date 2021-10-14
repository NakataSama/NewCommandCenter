package com.elo7.newcommandcenter;

public class App {

    public static void main( String[] args ) {

		int x = 20; //Type your field horizontal size here;
		int y = 20; //Type your field vertical size here;
		int numberOfVehicles = 90; //Type how many vehicles would you like to deploy on the field

		String commandChoice = "";
		CommandCenter commandCenter = new CommandCenter();
		boolean isBuilt = false;
		do {
			try{
				isBuilt = commandCenter.buildCommandCenter(x, y, numberOfVehicles);
			} catch (Exception e) {}
		} while (!isBuilt);

		do {
			commandChoice = commandCenter.menu();
		} while (!commandChoice.equalsIgnoreCase("n"));
    }
}
