package nclarkinwright.hibpractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nclarkinwright.hibpractice.db.EmployeeDb;
import nclarkinwright.hibpractice.domain.Employee;

public class HibernatePractice {
	
	Scanner scanner;
	EmployeeDb employeeDb;
	
	public HibernatePractice(String hibernateXmlPath) {
		scanner = new Scanner(System.in);
		employeeDb = new EmployeeDb(hibernateXmlPath);
	}
	
	public static void main(String[] args) {

		// Employee database path
		String hibernateXmlPath = "hibernate.cfg.xml";
		
		HibernatePractice hibPract = new HibernatePractice(hibernateXmlPath);
		
		while (true) {
			printPrompt();
			String command = hibPract.scanner.nextLine().toLowerCase();
			
			if (command.equals("quit")) {
				break;
			} else if (command.equals("add")) {
				hibPract.addEmployee();
			} else if (command.equals("find")) {
				hibPract.findById();
			} else if (command.equals("query")) {
				hibPract.query();
			} else {
				System.out.println("\nUnknown command");
			}
		}
		
		hibPract.scanner.close();
		hibPract.employeeDb.close();
	}

	private static void printPrompt() {
		System.out.print("\nAdd - Add an employee\n"
				+ "Find - Retrieves employee info by id\n"
				+ "Query - Search for employees\n"
				+ "Delete - delete an employee\n"
				+ "Quit - End the program\n"
				+ "Enter command > ");
	}
	
	private void addEmployee() {
		System.out.print("\nEmployee's first name: ");
		String firstName = scanner.nextLine();
		System.out.print("\nEmployee's last name: ");
		String lastName = scanner.nextLine();
		System.out.print("\nEmployee's company: ");
		String company = scanner.nextLine();
		
		Employee employee = new Employee(firstName, lastName, company);
		
		try {
			employeeDb.createEntry(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void query() {
		System.out.print("Enter text to search for: ");
		String search = scanner.nextLine();
		
		List<Employee> employeesFound = new ArrayList<>();
		try {
			employeesFound = employeeDb.query(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (employeesFound.isEmpty()) {
			System.out.println("\nNo employees found.");
		} else {
			System.out.println("\nEmployees found:");
			employeesFound.forEach(System.out::println);
		}
	}
	
	private void findById() {
		boolean numberNotEntered = true;
		int id = 0;
		
		while (numberNotEntered) {
			System.out.print("\nEnter id#: ");
			try {
				id = Integer.valueOf(scanner.nextLine());
			} catch (NumberFormatException Nfe) {
				System.out.println("\nNot a number");
			} finally {
				numberNotEntered = false;
			}
		}
		
		try {
			System.out.println(employeeDb.findById(id));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
