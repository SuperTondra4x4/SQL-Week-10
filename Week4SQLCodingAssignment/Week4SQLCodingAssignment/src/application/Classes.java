package application;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PlayerDatabase;


public class Classes {
	private Scanner scanner = new Scanner(System.in);
	private List<String> choices = Arrays.asList(
			"1) Display Players",
			"2) Display Player",
			"3) Create Player",
			"4) Update Player",
			"5) Delete Player");
	
	public void start() {
		String selection = "";
		
		do {
			printOptions();
			selection = scanner.nextLine();
			
			if(selection.equals("1")) {
				displayPlayers();
				} else if(selection.equals("2")) {
				displayPlayer();
				} else if(selection.equals("3")) {
				createPlayer();
				} else if(selection.equals("4")) {
				updatePlayer();
				} else if(selection.equals("5")) {
				deletePlayer();
				}
			selection = "";
			end();
			
		}	while (selection.equals("1") || 
				   selection.equals("2") || 
				   selection.equals("3") || 
				   selection.equals("4") || 
				   selection.equals("5") ||
				   selection.equals("")
				   );
		
		restart();	
	}
	private void end() {
		System.out.println("ENTER to continue or type 'reset' to clear the database");
		String resetCheck = scanner.nextLine();	
		if(resetCheck.equals("reset")) {
			reset();
		}
	}
	private void restart() {
		System.out.println("You didn't type a valid selection..." + "\n" + "Restarting menu...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		start();
	}
	
	private void reset() {
		System.out.println("Reseting...");
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PlayerDatabase.startPlayer();
	}
	private void displayPlayers() {
		PlayerDatabase.getPlayers();
	}
	private void displayPlayer() {
		System.out.println("Type the ID of the player you'd like to see:");
		PlayerDatabase.getPlayer();
	}
	private void createPlayer() {
		System.out.println("Create a player:");
		PlayerDatabase.createPlayer();
	}
	private void updatePlayer() {
		System.out.println("Type the ID of the player you'd like to update:");
		PlayerDatabase.updatePlayer();
	}
	private void deletePlayer() {
		System.out.println("Type the ID of the player you'd like to delete:");
		PlayerDatabase.deletePlayer();
	}


	private void printOptions() {
		System.out.print("Type your selection: \n");
		for (int i = 0; i < choices.size(); i++) {
			System.out.println(choices.get(i));
		}		
	}
}
