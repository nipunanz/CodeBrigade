package library.fixbook;
import java.util.Scanner;

//Author   :Subhashani
//Reviewer :Chiranga
//Mediator :Nipuna


public class FixBookUI {  

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED }; // Changed the enum name "uI_sTaTe" to "Ui_State"

	private FixBookcontrol control;  // Changed the variable name "CoNtRoL"  to "control"  // changed the variable type to "Fix_Book_control"
	private Scanner input;  // changed the variable name "InPuT" to "input"
	private UiState state;  // Changed the enum name "uI_sTaTe" to "Ui_State"  // Changed the variable name "StAtE" to "state"

	
	public FixBookUI(FixBookControl control) {  // changed the variable type to "Fix_Book_control"
		this.control = control;
		input = new Scanner(System.in);  // changed the variable name "InPuT" to "input"
		state = UiState.INITIALISED;  // Changed the enum name "uI_sTaTe" to "Ui_State"  // Changed the variable name "StAtE" to "state"
		control.setUi(this);  // Changed the variable name "CoNtRoL"  to "control"  // Changed the method name "SeT_Ui"  to "setUi"
	}


	public void setState(UiState state) { // Cahnged the method name "SeT_StAtE"  to "setState"
		this.state = state;  // Changed the variable name "StAtE" to "state"
	}

	
	public void run() {  // Changed the method name "RuN" to "Run"
 		output("Fix Book Use Case UI\n");  // Changed the method name "OuTpUt"  to "output"
		
		while (true) {
			
			switch (state) {  // Changed the variable name "StAtE" to "state"
			
			case READY:
				String bookEntryString = input("Scan Book (<enter> completes): ");  // Changed the variable name  "BoOk_EnTrY_StRiNg"  to "bookEntryString"
				if (bookEntryString.length() == 0){  // Changed the variable name  "BoOk_EnTrY_StRiNg"  to "bookEntryString"
				
					control.scanningComplete();  // Changed the variable name "CoNtRoL"  to "control"  // Changed the method name "SCannING_COMplete" to "Scanning_Complete"
				}
				
				else {
					try {
						int bookId = Integer.valueOf(bookEntryString).intValue();  // Changed the variable name  "BoOk_EnTrY_StRiNg"  to "bookEntryString"  // changed the variable name "BoOk_Id" to "bookId"
						control.bookScanned(bookId);  // Changed the variable name "CoNtRoL"  to "control"  // changed the variable name "BoOk_Id" to "bookId"
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");  // Changed the method name "OuTpUt" to "output"
					}
				}
				break;	
				
			case FIXING:
				String ans = input("Fix Book? (Y/N) : ");  // Changed the variable name "AnS" to "ans" // changed the method name "iNpUt" to "input"
				boolean fix = false;  // Changed the variable name "FiX" to "fix"
				if (ans.toUpperCase().equals("Y"))  // Changed the variable name "AnS" to "ans"
					fix = true; // Changed the variable name "FiX" to "fix"
				
				control.fixBook(fix);  // Changed the variable name "CoNtRoL"  to "control"  // Changed the method name "FiX_BoOk" to fixBook  // Changed the variable name "FiX" to "fix"
				break;
								
			case COMPLETED:
				output("Fixing process complete"); // Changed the method name "OuTpUt" to "output"
				return;
			
			default:
				output("Unhandled state");  // Changed the method name "OuTpUt" to "output"
				throw new RuntimeException("FixBookUI : unhandled state :" + state);  // Changed the variable name "StAtE"	 to "state"		
			
			}		
		}
		
	}

	
	private String input(String prompt) { // Changed the method name "iNpUt" to "input"
		System.out.print(prompt);
		return input.nextLine();  // changed the variable name "InPuT" to "input"
	}	
		
		
	private void output(Object object) { // Changed the method name "OuTpUt" to "output"
		System.out.println(object);
	}
	

	public void display(Object object) { // changed the method name "dIsPlAy" to "display"
		output(object); // Changed the method name "OuTpUt" to "output"
	}
	
	
}
