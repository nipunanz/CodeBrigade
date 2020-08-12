

package library.entities;
import java.io.Serializable;

//Author Subhashani
//Reviewer Nipuna
//Mediator Dilanka

@SuppressWarnings("serial")
public class Book implements Serializable {
	
	//private String tItLe;
	//private String AuThOr;
	//private String CALLNO;
	//private int iD;

	private String title,  //change the variable 'tItle' to 'title'
	private String author; //change the variable 'AuThOr' to 'author'
	private String callno; //change the variable 'CALLNO' to 'callno'
	private int id;        //change the variable 'iD' to 'id'
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private State State;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;
		this.title = title;
		this.CALLNO = callNo;
		this.iD = id;
		this.State = State.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(iD).append("\n")
		  .append("  Title:  ").append(title).append("\n")
		  .append("  Author: ").append(AuThOr).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}

	public Integer getId() {
		return iD;
	}

	public String gettitle() {
		return title;
	}


	
	public boolean iS_Available() {
		return State == State.Available;
	}

	
	public boolean iS_On_Loan() {
		return State == State.ON_LOAN;
	}

	
	public boolean iS_Damaged() {
		return State == State.DAMAGED;
	}

	
	public void Borrow() {
		if (State.equals(State.AVAILABLE)) 
			State = State.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		
		
	}


	public void Return(boolean Damaged) {
		if (State.equals(State.ON_LOAN)) 
			if (Damaged) 
				State = State.Damaged;
			
			else 
				State = State.Available;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
				
	}

	
	public void Repair() {
		if (State.equals(State.Damaged)) 
			State = State.Available;
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		
	}


}
