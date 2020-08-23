package library.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Author   : Nipuna
// Reviewer : Dilanka
// Mediator : Chiranga

@SuppressWarnings("serial")
public class Library implements Serializable {
	
	// Changed variable names according to standards
	private static final String LIBRARY_FILE = "library.obj";
	private static final int LOAN_LIMIT = 2; 
	private static final int LOAN_PERIOD = 2;
	private static final double FINE_PER_DAY = 1.0; 
	private static final double MAX_FINES_OWED = 1.0;
	private static final double DAMAGE_FEE = 2.0;
	
	private static Library library; 

	private int bookId;
	private int memberId;
	private int loanId;

	private Date loanDate;
	private Map<Integer, Book> catelog;
	private Map<Integer, Member> members;
	private Map<Integer, Loan> loans;
	private Map<Integer, Loan> currentLoans;
	private Map<Integer, Book> damagedBooks;
	

	private Library() {
		catelog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookId = 1;
		memberId = 1;		
		loanId = 1;		
	}

	
	public static synchronized Library getInstance() {		
		if (library == null) {
			Path PATH = Paths.get(LIBRARY_FILE);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream libraryFile = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
					library = (Library) libraryFile.readObject();
					Calendar.getInstance().setDate(library.loanDate);
					libraryFile.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else library = new Library();
		}
		return library;
	}

	
	public static synchronized void save() {
		if (library != null) {
			library.loanDate = Calendar.getInstance().getDate();
			try (ObjectOutputStream libraryFile = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				libraryFile.writeObject(library);
				libraryFile.flush();
				libraryFile.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {
		return bookId;
	}
	
	
	public int getMemberId() {
		return memberId;
	}
	
	
	private int getNextBookId() {
		return bookId++;
	}

	
	private int getNextMemberId() {
		return memberId++;
	}

	
	private int getNextLoanId() {
		return loanId++;
	}

	
	public List<Member> listMembers() {		
		return new ArrayList<Member>(members.values()); 
	}


	public List<Book> listBooks() {		
		return new ArrayList<Book>(catelog.values()); 
	}


	public List<Loan> listCurrentLoans() {
		return new ArrayList<Loan>(currentLoans.values());
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) {	
		int memberId = getNextMemberId();
		Member member = new Member(lastName, firstName, email, phoneNo, memberId);
		int id = member.getId();
		members.put(id, member);		
		return member;
	}

	
	public Book addBook(String a, String t, String c) {	
		int bookId = getNextBookId()
		Book b = new Book(a, t, c, bookId);
		catelog.put(b.getId(), b);		
		return b;
	}

	
	public Member getMember(int memberId) {
		if (members.containsKey(memberId)) {
		    return members.get(memberId);
		}
		return null;
	}

	
	public Book getBook(int bookId) {
		if (catelog.containsKey(bookId)) {
		    return catelog.get(bookId);
		}
		return null;
	}

	
	public int getLoanLimit() {
		return LOAN_LIMIT;
	}

	
	public boolean isMemberBorrow(Member member) {		
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT) {
			return false;
		} 	
		if (member.finesOwned() >= MAX_FINES_OWED) {
			return false;
		} 
		for (Loan loan : member.getLoan()) {
			if (loan.isOverdue()) {
				return false;
			}
		}
		return true;
	}

	
	public int getNumberOfLoansRemainingForMember(Member member) {		
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}

	
	public Loan issueLoan(Book book, Member member) {
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);
		Loan loan = new Loan(getNextLoanId(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.getId(), loan);
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}
	
	
	public double calculateOverdueFine(Loan loan) {
		if (loan.isOverdue()) {
			long daysOverdue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverdue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) {
		Member member = currentLoan.getMember();
		Book book  = currentLoan.getBook();
		
		double overdueFine = calculateOverdueFine(currentLoan);
		member.addFine(overdueFine);	
		
		member.dischargeLoan(currentLoan);
		book.returnStatus(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			damagedBooks.put(book.getId(), book);
		}
		currentLoan.discharge();
		currentLoans.remove(book.getId());
	}


	public void checkCurrentLoans() {
		for (Loan loan : currentLoans.values()) {
			loan.checkOverdue();
		}
	}


	public void repairBook(Book currentBook) {
		if (damagedBooks.containsKey(currentBook.getId())) {
			currentBook.repair();
			damagedBooks.remove(currentBook.getId());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
	}
	
	
}
