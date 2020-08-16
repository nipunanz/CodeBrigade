package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl { // Change class name 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'

	private ReturnBookUI ui; // Change variable name 'Ui' to 'ui'
	private enum ControlState { INITIALISED, READY, INSPECTING }; // Change enum name 'cOnTrOl_sTaTe' to 'ControlState'
	private ControlState state; // Change variable name 'sTaTe' to 'state'
	
	private Library library; // Change variable name 'lIbRaRy' to 'library'
	private Loan currentLoan; // Change variable name 'CurrENT_loan' to 'currentLoan'
	

	public ReturnBookControl() { // Change method name 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'
		this.library = Library.getInstance();
		state = ControlState.INITIALISED;
	}
	
	
	public void setUi(ReturnBookUI ui) { // Change method name 'sEt_uI' to 'setUi'
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}
		this.ui = ui;
		ui.setState(ReturnBookUI.uiState.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) { // Change method name 'bOoK_sCaNnEd' to 'bookScanned'
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}
		Book currentBook = library.getBook(bookId); // Change variable name 'cUrReNt_bOoK' to 'currentBook'
		
		if (currentBook == null) {
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.isOnLoan()) {
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overdueFine = 0.0; // Change variable name 'Over_Due_Fine' to 'overdueFine'
		if (currentLoan.isOverdue()) {
		    overdueFine = library.calculateOverdueFine(currentLoan);
		}
		ui.display("Inspecting");
		ui.display(currentBook.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.isOverdue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", overdueFine));
		}
		ui.setState(ReturnBookUI.UiState.INSPECTING);
		state = ControlState.INSPECTING;		
	}


	public void scanningComplete() { // Change method name 'sCaNnInG_cOmPlEtE' to 'scanningComplete'
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UiState.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) { // Change method name 'dIsChArGe_lOaN' to 'dischargeLoan'
		if (!state.equals(ControlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UiState.READY);
		state = ControlState.READY;				
	}


}
