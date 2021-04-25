package nclarkinwright.hibpractice;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernatePractice {

	public static void main(String[] args) {
		
		// Create current session for application
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();

		// Session session = factory.getCurrentSession();
		
		// Run simple CLI for user
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			printPrompt();
			String command = scanner.nextLine();
			
			if (command.toLowerCase().equals("quit")) {
				break;
			} else {
				System.out.println("\nUnknown command");
			}
		}
		
		scanner.close();
		factory.close();
	}

	private static void printPrompt() {
		System.out.print("\nAdd - Add an employee\n"
				+ "Retrieve - Retrieves employee info by id\n"
				+ "Query - Search for employees\n"
				+ "Delete - delete an employee\n"
				+ "Quit - End the program\n"
				+ "Enter command > ");
	}
	
	

}
