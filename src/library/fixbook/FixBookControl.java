package library.fixbook;
import library.entities.Book;
import library.entities.Library;

//Author   :Subhashani
//Reviewer :Chiranga
//Mediator :Nipuna

public class FixBookControl {  // changed the class name "fIX_bOOK_cONTROL" to "FixBookControl"
	
	private FixBookUI ui; // Changed the variable name "Ui" to "ui"
	private enum ControlState { INITIALISED, READY, FIXING };  // Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
	private ControlState state;  // Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
								  // Changed the variable name  "StAtE"  to "state"
	
	private Library library;     // Changed the variable name "LiBrArY" name to "library"
	private Book currentBook;   // Changed the variable name "CuRrEnT_BoOk" name to "currentBook"


	public FixBookControl() {  // changed the class name "fIX_bOOK_cONTROL" to "FixBookControl"
		this.library = library.getInstance();  // Changed the variable name "LiBrArY" name to "library" 
											// Changed the method name "GeTiNsTaNcE"  to "getInstance"
		state = ControlState.INITIALISED; // Changed the variable name  "StAtE"  to "state"
											// Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
	}
	
	
	public void setUi(FixBookUI ui) {  // changed the method name "SeT_Ui" to "setUi"
		if (!state.equals(ControlState.INITIALISED))  // Changed the variable name  "StAtE"  to "state"  // Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
		{
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		
		}	
		this.ui = ui;  // Changed the variable name "Ui" to "ui"
		ui.setState(FixBookUI.UiState.READY);  // Changed the method name "SeT_StAtE"  to "setState"  // Changed the variable name "Ui" to "ui"
												// Changed the  method  name "uI_sTaTe" to "UiState"
		state = ControlState.READY;	// Changed the variable name  "StAtE"  to "state"
										// Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"

	   
	
	}


	public void Bookscanned(int bookId) {  // Changed the class name "BoOk_ScAnNeD" to "Bookscanned" // Changed the variable name "BoOkId" to "bookId"
		if (!state.equals(ControlState.READY)) 
		{ // Changed the variable name  "StAtE"  to "state"  // Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.getBook(bookId); // Changed the variable name "LiBrArY" name to "library"  // Changed the variable name "BoOkId" to "bookId"
												 // Changed the variable name "CuRrEnT_BoOk" name to "currentBook"
												// Changed the method name "gEt_BoOk" to "getBook"
		
		if (currentBook == null)
		{  // Changed the variable name "CuRrEnT_BoOk" name to "currentBook"
			ui.display("Invalid bookId"); // Changed the method name "Ui.dIsPLAy" to "ui.display"
			return;
		}
		if (!currentBook.isDamaged())  // Changed the "is_Damaged" to "isDamaged"
		{  // Changed the variable name "CuRrEnT_BoOk" name to "currentBook"  // Changed the method name "iS_DaMaGeD" to "is_Damaged"
			ui.display("Book has not been damaged"); // Changed the method name "Ui.dIsPLAy" to "ui.display"
			return;
		}
		ui.display(currentBook.toString());  // Changed the variable name "CuRrEnT_BoOk" name to "currentBook"  // Changed the method name "Ui.dIsPLAy" to "ui.display"
		ui.setState(FixBookUI.UiState.FIXING);  // Changed the  method  name "uI_sTaTe" to "UiState"  // Changed the method name "SeT_StAtE"  to "setState"
		state = ControlState.FIXING;		// Changed the variable name  "StAtE"  to "state"  // Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
	}


	public void FiX_BoOk(boolean mustFix) { // Changed the variable name "mUsT_FiX" to "mustFix"
		if (!state.equals(ControlState.FIXING))  // Changed the variable name  "StAtE"  to "state" // Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mustFix)  // Changed the variable name  "mUsT_FiX" to "mustFix"
		{
			library.repairBook(currentBook);  // Changed the variable name "LiBrArY" name to "library" // Changed the varibale name "RePaIr_BoOk" to "repairBook"
												// Changed the variable name "CuRrEnT_BoOk" name to "currentBook"
		}
		
		currentBook = null;    // Changed the variable name "CuRrEnT_BoOk" name to "currentBook"
		ui.setState(FixBookUI.Ui_State.READY); // Changed the  method  name "uI_sTaTe" to "Ui_State" // Changed the method name "SeT_StAtE"  to "setState"
		state = ControlState.READY;	// Changed the variable name  "StAtE"  to "state"	// Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
	}

	
	public void scanningComplete() { // Changed the method name "SCannING_COMplete" to "scanningComplete"
		if (!state.equals(ControlState.READY))  // Changed the variable name  "StAtE"  to "state"	// Changed the variable name  "CoNtRoL_StAtE"  to "ControlState"
		{
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		
		}	
		ui.setState(FixBookUI.UiState.COMPLETED);		// Changed the  method  name "uI_sTaTe" to "UiState" // Changed the method name "SeT_StAtE"  to "setState"

	}

}
