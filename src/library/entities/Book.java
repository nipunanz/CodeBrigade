

package library.entities;
import java.io.Serializable;

//Author   :Subhashani
//Reviewer :Nipuna
//Mediator :Dilanka

@SuppressWarnings("serial")
public class Book implements Serializable {
	


	private String title;  // change the variable 'tItle' to 'title'
	private String author; // change the variable 'AuThOr' to 'author'
	private String callNo; // change the variable 'CALLNO' to 'callno'
	private int id;        // change the variable 'iD' to 'id'
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private State State;
	
	
	public Book (String author, String title, String callNo, int id) {
		this.author = author;
		this.title = title;
		this.callNo = callNo;
		this.id = id;
		this.State = State.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  Book: ").append(id).append("\n")
		  .append("  Title:  ").append(title).append("\n")
		  .append("  Author: ").append(author).append("\n")
		  .append("  CallNo: ").append(callNo).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}

	public Integer getId() {  // changed method name 'gEtiD' to 'getId'
		return id;
	}

	public String getTitle() {  // changed method name 'gEtTitLe' to 'getTitle'
		return title;
	}


	
	public boolean isAvailable() {
		return State == State.AVAILABLE;
	}

	
	public boolean isOnLoan() {
		return State == State.ON_LOAN;
	}

	
	public boolean isDamaged() {
		return State == State.DAMAGED;
	}

	
	public void borrow() {
		if (State.equals(State.AVAILABLE)) {
		

 			State = State.ON_LOAN;
		}
			
		    
		else { 
		
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
		
	}


	public void returnStatus(boolean damaged) {
		if (State.equals(State.ON_LOAN)) {
		
			if (damaged) { 
				State = State.DAMAGED;
			}	
			else {
				State = State.AVAILABLE;
			}	
		
		} 
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void repair() {
		if (State.equals(State.DAMAGED)) {
		
			State = State.AVAILABLE;
		}
		
		else {
		
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
		
	}


}
