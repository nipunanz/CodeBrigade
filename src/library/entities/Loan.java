package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//Author : Dilanka
//Reviewer : Chiranga
//Mediator : Subhashini

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Changed the enum 'lOaN_sTaTe' to 'LoanState'
	
	private int loanId; // Changed the variable 'LoAn_Id' to 'loanId'
	private Book book; // Changed the variable 'BoOk' to 'book'
	private Member member; // Changed the variable 'MeMbEr' to 'member'
	private Date date; // Changed the variable 'DaTe' to 'date'
	private LoanState state; // Changed the variable 'StAtE' to 'state'
  
	
	public Loan(int loanId, Book book, Member member, Date dueDate) {
		this.loanId = loanId; // Changed the variable 'LoAn_Id' to 'loanId'
		this.book = book; // Changed the variable 'BoOk' to 'book'
		this.member = member; // Changed the variable 'MeMbEr' to 'member'
		this.date = dueDate; // Changed the variable 'DaTe' to 'date'
		this.state = LoanState.CURRENT; // Changed the variable 'StAtE' to 'state'
	}

	
	public void checkOverdue() { // Changed the method 'cHeCk_OvEr_DuE' to 'checkOverdue'
		if (state == LoanState.CURRENT && Calendar.getInstance().getDate().after(date)) {
			this.state = LoanState.OVER_DUE;			
		}
	}

	
	public boolean isOverdue() { // Changed the method 'Is_OvEr_DuE' to 'isOverdue'
		return state == LoanState.OVER_DUE;
	}

	
	public Integer getId() { // Changed the method 'GeT_Id' to 'getId'
		return loanId;
	}


	public Date getDueDate() { // Changed the method 'GeT_DuE_DaTe' to 'getDueDate'
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Changed the variable 'sdf' to 'simpleDateFormat'

		StringBuilder stringBuilder = new StringBuilder();  // Changed the variable 'sb' to 'stringBuilder'
		int memberId = member.getId(); // Created variable to retrieve member id
		String memberLastName = member.getLastName(); // Created variable to retrieve member last name
		String memberFirstName = member.getFirstName(); // Created variable to retrieve member first name
		int bookId = book.getId(); // Created variable to retrieve member id
		String bookTitle = book.getTitle(); // Created variable to retrieve book title

		stringBuilder.append("Loan:  ").append(loanId).append("\n")
		  .append("  Borrower ").append(memberId).append(" : ")
		  .append(memberLastName).append(", ").append(memberFirstName).append("\n")
		  .append("  Book ").append(bookId).append(" : " )
		  .append(bookTitle).append("\n")
		  .append("  DueDate: ").append(simpleDateFormat.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return stringBuilder.toString();
	}


	public Member getMember() { // Changed the method 'GeT_MeMbEr' to 'getMember'
		return member;
	}


	public Book getBook() { // Changed the method 'GeT_BoOk' to 'getBook'
		return book;
	}


	public void discharge() { // Changed the method 'DiScHaRgE' to 'isDischarge'
		state = LoanState.DISCHARGED;		
	}

}
