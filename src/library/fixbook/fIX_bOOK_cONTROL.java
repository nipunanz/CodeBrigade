package library.fixbook;
import library.entities.Book;
import library.entities.Library;

//Author   :Subhashani
//Reviewer :Chiranga
//Mediator :Nipuna

public class Fix_book_control {  // changed the class name "fIX_bOOK_cONTROL" to "Fix_book_control"
	
	private FixBookUI ui; // Changed the variable name "Ui" to "ui"
	private enum Control_State { INITIALISED, READY, FIXING };  // Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
	private Control_State state;  // Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
								  // Changed the variable name  "StAtE"  to "State"
	
	private Library library;     // Changed the variable name "LiBrArY" name to "library"
	private Book currentbook;   // Changed the variable name "CuRrEnT_BoOk" name to "currentbook"


	public Fix_book_control() {  // changed the class name "fIX_bOOK_cONTROL" to "Fix_book_control"
		this.library = library.getInstance();  // Changed the variable name "LiBrArY" name to "library" 
											// Changed the method name "GeTiNsTaNcE"  to "getInstance"
		State = Control_State.INITIALISED; // Changed the variable name  "StAtE"  to "State"
											// Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
	}
	
	
	public void setUi(FixBookUI ui) {  // changed the method name "SeT_Ui" to "setUi"
		if (!State.equals(Control_State.INITIALISED))  // Changed the variable name  "StAtE"  to "State"  // Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
		{
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		
		}	
		this.ui = ui;  // Changed the variable name "Ui" to "ui"
		ui.setState(FixBookUI.Ui_State.READY);  // Changed the method name "SeT_StAtE"  to "setState"  // Changed the variable name "Ui" to "ui"
												// Changed the  method  name "uI_sTaTe" to "Ui_State"
		State = Control_State.READY;	// Changed the variable name  "StAtE"  to "State"
										// Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"

	   
	
	}


	public void Bookscanned(int bookId) {  // Changed the class name "BoOk_ScAnNeD" to "Bookscanned" // Changed the variable name "BoOkId" to "bookId"
		if (!State.equals(Control_State.READY)) 
		{ // Changed the variable name  "StAtE"  to "State"  // Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentbook = library.getBook(bookId); // Changed the variable name "LiBrArY" name to "library"  // Changed the variable name "BoOkId" to "bookId"
												 // Changed the variable name "CuRrEnT_BoOk" name to "currentbook"
												// Changed the method name "gEt_BoOk" to "getBook"
		
		if (currentbook == null)
		{  // Changed the variable name "CuRrEnT_BoOk" name to "currentbook"
			ui.display("Invalid bookId"); // Changed the method name "Ui.dIsPLAy" to "ui.display"
			return;
		}
		if (!currentbook.is_Damaged()) 
		{  // Changed the variable name "CuRrEnT_BoOk" name to "currentbook"  // Changed the method name "iS_DaMaGeD" to "is_Damaged"
			ui.display("Book has not been damaged"); // Changed the method name "Ui.dIsPLAy" to "ui.display"
			return;
		}
		ui.display(currentbook.toString());  // Changed the variable name "CuRrEnT_BoOk" name to "currentbook"  // Changed the method name "Ui.dIsPLAy" to "ui.display"
		ui.setState(FixBookUI.Ui_State.FIXING);  // Changed the  method  name "uI_sTaTe" to "Ui_State"  // Changed the method name "SeT_StAtE"  to "setState"
		State = Control_State.FIXING;		// Changed the variable name  "StAtE"  to "State"  // Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
	}


	public void FiX_BoOk(boolean mUsT_FiX) {
		if (!State.equals(Control_State.FIXING))  // Changed the variable name  "StAtE"  to "State" // Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mUsT_FiX) 
		{
			library.RePaIr_BoOk(currentbook);  // Changed the variable name "LiBrArY" name to "library"
												// Changed the variable name "CuRrEnT_BoOk" name to "currentbook"
		}
		
		currentbook = null;    // Changed the variable name "CuRrEnT_BoOk" name to "currentbook"
		ui.setState(FixBookUI.Ui_State.READY); // Changed the  method  name "uI_sTaTe" to "Ui_State" // Changed the method name "SeT_StAtE"  to "setState"
		State = Control_State.READY;	// Changed the variable name  "StAtE"  to "State"	// Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
	}

	
	public void SCannING_COMplete() {
		if (!State.equals(Control_State.READY))  // Changed the variable name  "StAtE"  to "State"	// Changed the variable name  "CoNtRoL_StAtE"  to "Control_State"
		{
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		
		}	
		ui.setState(FixBookUI.Ui_State.COMPLETED);		// Changed the  method  name "uI_sTaTe" to "Ui_State" // Changed the method name "SeT_StAtE"  to "setState"

	}

}
