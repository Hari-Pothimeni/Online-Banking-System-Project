
package org.jpmc.OBS.Main;

import org.jpmc.OBS.authentication.Login;

import org.jpmc.OBSUtil.HibernateUtil;



import org.hibernate.Session;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
	
	//To print output in colors in the console
	public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    
	public static void main(String[] args) {

		Session session = null;
		Login login = new Login();
		session = HibernateUtil.getSessionFactory().openSession();

		try(Scanner sc = new Scanner(System.in);){
			
			int maxChances = 3; // Maximum number of chances to choose an option
			int chances = 0; // Counter for chances

			System.out.println(ANSI_PURPLE);//purple color
			System.out.println("         Welcome to Online Banking System      ");
			System.out.println("*****************************************************");
			while (chances < maxChances) { 
				try {
				System.out.print(ANSI_GREEN);
				
				System.out.println("\n       Login       ");
				System.out.println("***********************");
				
				System.out.print(ANSI_RED);
				
				System.out.println("1. Admin Login");
				System.out.println("2. Customer Login");
				System.out.println("3. Exit");
				
				System.out.print(ANSI_BLUE);//Blue color
				
				System.out.print("\nEnter your choice: ");
				int s = sc.nextInt();
				switch(s) {
				case 1:		
					System.out.println(ANSI_GREEN);//purple color
					login.login();
					break;
					
				case 2:
					System.out.println(ANSI_GREEN);//purple color
					login.login();
					
					break;
								
				case 3:
					System.out.println(ANSI_PURPLE);//purple color
					System.out.println ("Exiting the Portal. Goodbye!");
					return;
				
				default:
					System.out.println(ANSI_CYAN);//skyblue color
					System.out.println("Invalid Choice. Please enter valid choice");
					break;
				}
				}catch (InputMismatchException e) {
					System.out.println(ANSI_RESET);
		            System.out.println("Invalid input. Please enter a valid integer.");
		            sc.nextLine(); // Consume the invalid input
		        }
				chances++;
			}
			// If user reaches maximum chances without choosing "Exit"
			System.out.println(ANSI_RESET);
			System.out.println("Maximum chances reached. Exiting the Portal. Goodbye!");
			
		}
	}
}
