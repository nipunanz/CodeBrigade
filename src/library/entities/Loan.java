package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//Author : Dilanka
//Reviewer : Chiranga
//Mediator : Subhashini

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Change the enum 'lOaN_sTaTe' to 'LoanState'
	
	private int loanId; // Change the variable 'LoAn_Id' to 'loanId'
	private Book book; // Change the variable 'BoOk' to 'book'
	private Member member; // Change the variable 'MeMbEr' to 'member'
	private Date date; // Change the variable 'DaTe' to 'date'
	private LoanState state; // Change the variable 'StAtE' to 'state'

	
	public Loan(int loanId, Book book, Member member, Date dueDate) {
		this.LoAn_Id = loanId;
		this.BoOk = bOoK;
		this.MeMbEr = mEmBeR;
		this.DaTe = DuE_dAtE;
		this.StAtE = lOaN_sTaTe.CURRENT;

	private LoanState state; // Change the variable 'StAtE' to 'state'

	
	public Loan(int loanId, Book book, Member member, Date dueDate) {
		this.loanId = loanId;
		this.book = book;
		this.member = member;
		this.date = dueDate;
		this.state = LoanState.CURRENT;
	}

	
	public void checkOverdue() { // Change the method 'cHeCk_OvEr_DuE' to 'checkOverdue'
		if (state == LoanState.CURRENT &&
			Calendar.getInstance().getDate().after(date)) {
			this.state = LoanState.OVER_DUE;			
		}
	}

	
	public boolean isOverdue() { // Change the method 'Is_OvEr_DuE' to 'isOverdue'
		return state == LoanState.OVER_DUE;
	}

	
	public Integer getId() { // Change the method 'GeT_Id' to 'getId'
		return loanId;
	}


	public Date getDueDate() { // Change the method 'GeT_DuE_DaTe' to 'getDueDate'
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")
		  .append("  Borrower ").append(member.getId()).append(" : ")
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n")
		  .append("  Book ").append(book.getId()).append(" : " )
		  .append(book.getTitle()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public Member getMember() { // Change the method 'GeT_MeMbEr' to 'getMember'
		return member;
	}


	public Book getBook() { // Change the method 'GeT_BoOk' to 'getBook'
		return book;
	}


	public void isDischarge() { // Change the method 'DiScHaRgE' to 'isDischarge'
		state = LoanState.DISCHARGED;		
	}

}
