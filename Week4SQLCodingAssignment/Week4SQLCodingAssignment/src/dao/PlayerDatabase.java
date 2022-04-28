package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class PlayerDatabase {

	static Scanner scanner = new Scanner(System.in);

	private static String BLANKURL = "jdbc:mysql://localhost:3306/";
	private static String USERNAME = "root";
	private static String PASSWORD = "MyNewPass";
	private static String URL = "jdbc:mysql://localhost:3306/players";
	
//  public static void createDatabase() {  
//	try(Connection conn = DriverManager.getConnection(BLANKURL, USERNAME, PASSWORD);
//            Statement stmt = conn.createStatement();
//         ) {		      
//            String sql = "CREATE DATABASE PLAYERS";
//            stmt.executeUpdate(sql);
//            System.out.println("Database created successfully...");   	  
//         } catch (SQLException e) {
//            e.printStackTrace();
//   		      }
//  }
	
	public static void getPlayers() {
		try {
			String playerID = "SELECT * FROM players";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement ps = conn.prepareStatement(playerID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("Player ID: " + rs.getInt(1) + "\n" + "Player Name: " + rs.getString(2) + "\n" + "Points by Player: " + rs.getInt(3));
			}
			
			} catch (SQLException e) {
				System.out.println("STOP GETTING EXCEPTIONS!!!");
				e.printStackTrace();
				}
	}
	
	public static void getPlayer() {
		try {
			String playerID = "SELECT * FROM players WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Player IDs: ");
			getPlayers();
			System.out.print("ID of Player: ");
			String playerId = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(playerID);
			ps.setString(1, playerId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("Player ID: " + rs.getInt(1) + "\n" + "Player Name: " + rs.getString(2) + "\n" + "Points by Player: " + rs.getInt(3));
			}
			
			} catch (SQLException e) {
				System.out.println("Exception...");
				e.printStackTrace();
				}
	}
	
	public static void createPlayer() {
		try {
			String createPlayer = "INSERT INTO players (name, points) VALUES (?,?)";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.print("Player Name: ");
			String playerName = scanner.nextLine();
			System.out.print("Points by Player: ");
			String playerPoints = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(createPlayer);
			ps.setString(1, playerName);
			ps.setInt(2, Integer.parseInt(playerPoints));
			ps.executeUpdate();
			
			} catch (SQLException e) {
				System.out.println("Stopped.....");
				e.printStackTrace();
				}
	}
	
	public static void updatePlayer() {
		try {
			String updatePlayerID = "UPDATE players SET name = ?, points = ? WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Player IDs: ");
			getPlayers();
			System.out.print("ID of Player: ");
			String playerId = scanner.nextLine();
			System.out.print("Player Name: ");
			String playerName = scanner.nextLine();
			System.out.print("Points by Player: ");
			String playerPoints = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(updatePlayerID);
			ps.setString(3, playerId);
			ps.setString(1, playerName);
			ps.setInt(2, Integer.parseInt(playerPoints));
			ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Stopped....");
				e.printStackTrace();
				}
	}
	
	public static void deletePlayer() {
		try {
			String deletePlayerID = "DELETE FROM players WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Player IDs: ");
			getPlayers();
			System.out.print("ID of Player to Delete: ");
			String playerId = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(deletePlayerID);
			ps.setString(1, playerId);
			ps.executeUpdate();	
			} catch (SQLException e) {
				System.out.println("Exception found....");
				e.printStackTrace();
				}
	}
	
	public static void startPlayer() {
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement dropStatement = conn.createStatement();
			dropStatement.execute("DROP DATABASE players");
		} catch (SQLException e) {
//			System.out.println("Stop getting exceptions!");
//			e.printStackTrace();
			}
		try {
			Connection conn = DriverManager.getConnection(BLANKURL, USERNAME, PASSWORD);
			Statement create = conn.createStatement();
			create.execute("CREATE DATABASE players");
			Statement use = conn.createStatement();
			use.execute("USE players");
			Statement createTb = conn.createStatement();
			createTb.execute("CREATE TABLE players (\n"
					+ "	id int(10) NOT NULL auto_increment,\n"
					+ "	name varchar(50) NOT null,\n"
					+ "	points int(50) NOT null,\n"
					+ "	PRIMARY KEY (id)\n"
					+ "	)");
			try {
				String createPlayer = "INSERT INTO players (name, points) VALUES (?,?)";
				System.out.println("Create your first player: ");
				System.out.print("Player Name: ");
				String PlayerName = scanner.nextLine();
				System.out.print("Points by Player: ");
				String playerPoints = scanner.nextLine();
				PreparedStatement ps = conn.prepareStatement(createPlayer);
				ps.setString(1, PlayerName);
				ps.setInt(2, Integer.parseInt(playerPoints));
				ps.executeUpdate();
				
				} catch (SQLException e) {
					System.out.println("An exception has been found.");
					e.printStackTrace();
					}
			} catch (SQLException e) {
				System.out.println("Stop getting exceptions!");
				e.printStackTrace();
				}
	}
}
