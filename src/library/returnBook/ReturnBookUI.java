package library.returnBook;
import java.util.Scanner;


public class ReturnBookUI {

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED }; // Change enum name 'uI_sTaTe' to 'UiState'

	private ReturnBookControl control; // Change class name 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'
	private Scanner input; // Change varibale name 'iNpUt' to 'input'
	private UiState state; // Change varibale name 'StATe' to 'state'

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control; // Change varibale name 'CoNtRoL' to 'control' 
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.setUi(this);
	}


	public void run() {	// Change method name 'RuN' to 'run' 	
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookInputString = input("Scan Book (<enter> completes): "); // Change varibale name 'BoOk_InPuT_StRiNg' to 'bookInputString' 
				if (bookInputString.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookInputString).intValue(); // Change varibale name 'Book_Id' to 'bookId' 
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");// Change varibale name 'AnS' to 'ans' 
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {				
					isDamaged = true;
				}
				control.dischageLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String input(String prompt) { // Change method name 'iNpUt' to 'input' 
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) { // Change method name 'oUtPuT' to 'output' 
		System.out.println(object);
	}
	
			
	public void display(Object object) { // Change method name 'DiSpLaY' to 'display' 
		output(object);
	}
	
	public void setState(UiState state) { // Change method name 'sEt_sTaTe' to 'setState' 
		this.state = state;
	}

	
}
