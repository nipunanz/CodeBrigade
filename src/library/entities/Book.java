

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

	private String title;  //change the variable 'tItle' to 'title'
	private String author; //change the variable 'AuThOr' to 'author'
	private String callno; //change the variable 'CALLNO' to 'callno'
	private int id;        //change the variable 'iD' to 'id'
	
	private enum State { available, ON_LOAN, damaged, RESERVED };
	private State State;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;
		this.title = title;
		this.callno = callNo;
		this.id = id;
		this.State = State.available;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")
		  .append("  Title:  ").append(title).append("\n")
		  .append("  Author: ").append(author).append("\n")
		  .append("  CallNo: ").append(callno).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}

	public Integer getId() {
		return id;
	}

	public String gettitle() {
		return title;
	}


	
	public boolean iS_available() {
		return State == State.available;
	}

	
	public boolean iS_On_Loan() {
		return State == State.ON_LOAN;
	}

	
	public boolean iS_damaged() {
		return State == State.damaged;
	}

	
	public void Borrow() {
		if (State.equals(State.available)) 
			State = State.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		
		
	}


	public void Return(boolean damaged) {
		if (State.equals(State.ON_LOAN)) 
			if (damaged) 
				State = State.damaged;
			
			else 
				State = State.available;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
				
	}

	
	public void Repair() {
		if (State.equals(State.damaged)) 
			State = State.available;
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		
	}


}
