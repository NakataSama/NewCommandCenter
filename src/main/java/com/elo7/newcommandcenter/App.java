package com.elo7.newcommandcenter;

import java.awt.event.TextEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main( String[] args ) {

		int x = 20; //Type your field horizontal size here;
		int y = 20; //Type your field vertical size here;
		int numberOfVehicles = 2; //Type how many vehicles would you like to deploy on the field

		String commandChoice = "";

		CommandCenter commandCenter = new CommandCenter();
		commandCenter.buildCommandCenter(x, y, numberOfVehicles);

		while (!commandChoice.equalsIgnoreCase("n")) {
			commandChoice = commandCenter.menu();
		}
    }
}
