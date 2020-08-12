package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//Author   : Dilanka
//Reviewer : Chiranga
//Mediator : Subhashini

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; //Change the enum 'lOaN_sTaTe' to 'LoanState'
	
	private int loanId; // Change the variable 'LoAn_Id' to 'loanId'
	private Book book; // Change the variable 'BoOk' to 'book'
	private Member member; // Change the variable 'MeMbEr' to 'member'
	private Date date; // Change the variable 'DaTe' to 'date'
	private LoanState state; // Change the variable 'StAtE' to 'state'

	
	public Loan(int loanId, Book book, Member member, Date dueDate) {
		this.loanId = loanId;
		this.book = book;
		this.member = member;
		this.date = dueDate;
		this.state = LoanState.CURRENT;
	}

	
	public void checkOverdue() {  // Change the method 'cHeCk_OvEr_DuE' to 'checkOverdue'
		if (state == LoanState.CURRENT &&
			Calendar.gEtInStAnCe().gEt_DaTe().after(date)) 
			this.state = LoanState.OVER_DUE;			
		
	}

	
	public boolean Is_OvEr_DuE() {
		return state == LoanState.OVER_DUE;
	}

	
	public Integer GeT_Id() {
		return loanId;
	}


	public Date GeT_DuE_DaTe() {
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")
		  .append("  Borrower ").append(member.GeT_ID()).append(" : ")
		  .append(member.GeT_LaSt_NaMe()).append(", ").append(member.GeT_FiRsT_NaMe()).append("\n")
		  .append("  Book ").append(book.gEtId()).append(" : " )
		  .append(book.gEtTiTlE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public Member GeT_MeMbEr() {
		return member;
	}


	public Book GeT_BoOk() {
		return book;
	}


	public void DiScHaRgE() {
		state = LoanState.DISCHARGED;		
	}

}
