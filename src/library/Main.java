package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.BorrowBookControl;
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.FixBookControl;
import library.payfine.PayFineUI;
import library.payfine.PayFineControl;
import library.returnBook.ReturnBookUI;
import library.returnBook.ReturnBookControl;

//Author   : Dilanka
//Mediator : Chiranga
//Reviever : Nipuna

public class Main {
	
	private static Scanner IN;
	private static Library LIB;
	private static String MENU;
	private static Calendar CAL;
	private static SimpleDateFormat SDF;
	
	
	private static String getMenu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : Add member\n")
		  .append("  LM : List members\n")
		  .append("\n")
		  .append("  B  : Add book\n")
		  .append("  LB : List books\n")
		  .append("  FB : Fix books\n")
		  .append("\n")
		  .append("  L  : Take out a loan\n")
		  .append("  R  : Return a loan\n")
		  .append("  LL : List loans\n")
		  .append("\n")
		  .append("  P  : Pay fine\n")
		  .append("\n")
		  .append("  T  : Increment date\n")
		  .append("  Q  : Quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			IN = new Scanner(System.in);
			LIB = Library.getInstance();
			CAL = Calendar.getInstance();
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (Member m : LIB.listMembers()) {
				output(m);
			}
			output(" ");
			for (Book b : LIB.listBooks()) {
				output(b);
			}
						
			MENU = getMenu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.getDate()));
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember(); // Changed the method name 'ADD_MEMBER' to 'addMember'
					break;
					
				case "LM": 
					listMembers(); // Changed the method name 'LIST_MEMBERS' to 'addMember'
					break;
					
				case "B": 
					addBook(); // Changed the method name 'ADD_BOOK' to 'addMember'
					break;
					
				case "LB": 
					listBooks(); // Changed the method name 'LIST_BOOKS' to 'addMember'
					break;
					
				case "FB": 
					fixBooks(); // Changed the method name 'FIX_BOOKS' to 'addMember'
					break;
					
				case "L": 
					borrowBooks(); // Changed the method name 'BORROW_BOOK' to 'addMember'
					break;
					
				case "R": 
					returnBook(); // Changed the method name 'RETURN_BOOK' to 'addMember'
					break;
					
				case "LL": 
					listCurrentLoans(); // Changed the method name 'LIST_CURRENT_LOANS' to 'addMember'
					break;
					
				case "P": 
					payFines(); // Changed the method name 'PAY_FINES' to 'addMember'
					break;
					
				case "T": 
					incrementDate(); // Changed the method name 'INCREMENT_DATE' to 'addMember'
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.save();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void payFines() { // Chnaged themethod name 'PAY_FINES' to 'payFines'
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() { // Chnaged themethod name 'LIST_CURRENT_LOANS' to 'listCurrentLoans'
		output("");
		for (Loan loan : LIB.listCurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void listBooks() { // Chnaged themethod name 'LIST_BOOKS' to 'listBooks'
		output("");
		for (Book book : LIB.listBooks()) {
			output(book + "\n");
		}		
	}



	private static void listMembers() { // Chnaged themethod name 'LIST_MEMBERS' to 'listMembers'
		output("");
		for (Member member : LIB.listMembers()) {
			output(member + "\n");
		}		
	}



	private static void borrowBooks() { // Chnaged themethod name 'BORROW_BOOK' to 'borrowBooks'
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() { // Chnaged themethod name 'RETURN_BOOK' to 'returnBook'
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() { // Chnaged themethod name 'FIX_BOOKS' to 'fixBooks'
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() { // Chnaged themethod name 'INCREMENT_DATE' to 'incrementDate'
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();
			output(SDF.format(CAL.getDate()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() { // Chnaged themethod name 'ADD_BOOK' to 'addBook'
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNumber = input("Enter call number: ");
		Book book = LIB.addBook(author, title, callNumber);
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() { // Chnaged themethod name 'ADD_MEMBER' to 'addMember'
		try {
			String lastName = input("Enter last name: "); // Changed the varibale name 'LaSt_NaMe' to 'lastName'
			String firstName  = input("Enter first name: "); // Changed the varibale name 'FiRsT_NaMe' to 'lastName'
			String emailAddress = input("Enter email address: "); // Changed the varibale name 'EmAiL_AdDrEsS' to 'lastName'
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue(); // Changed the varibale name 'PhOnE_NuMbEr' to 'lastName'
			Member member = LIB.addMember(lastName, firstName, emailAddress, phoneNumber); // Changed the varibale name 'MeMbEr' to 'lastName'
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
