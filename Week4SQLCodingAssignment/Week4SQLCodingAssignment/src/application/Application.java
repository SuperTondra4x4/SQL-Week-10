package application;

import dao.PlayerDatabase;

public class Application {

	public static void main(String[] args) {
		Classes start = new Classes();
		run();
		start.start();
	}
	private static void run() {
		PlayerDatabase.startPlayer();
	}

}
