package library.payfine;
import java.util.Scanner;

// Author   : Chiranga
// Reviwer  : Dilanka
// Mediator : Subhashini

public class PayFineUI {


	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // changed the variable name "uI_sTaTe" to "UiState"

	private PayFineControl control; // changed the variable name "pAY_fINE_cONTROL" to "payFineControl" & "CoNtRoL" to "control"
	private Scanner input;
	private UiState state; // changed the variable name "uI_sTaTe" to "UiState" & "StAtE" to "state"

	
	public PayFineUI(PayFineControl control) { // chnage "CoNtRoL" to "control"
		this.control = control; // "CoNtRoL" to "control"
		input = new Scanner(System.in);
		state = UiState.INITIALISED; // changed variable name "uI_sTaTe" to "UiState" & "StAtE" to "state"
		control.setUi(this); // changed method "SeT_uI" to "setUi"
	}
	
	
	public void setState(UiState state) {// changed variable name "uI_sTaTe" to "UiState" & "SeT_StAtE" to "setState"
		this.state = state; // changed "StAtE" to "state"
	}


	public void run() { // changed "RuN" to "run"
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) { // changed "StAtE" to "state"
			
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): "); // chanaged "Mem_Str" to "memStr "
				if (memStr.length() == 0) { // chanaged "Mem_Str" to "memStr "
					control.cancel(); // changed "CoNtRoL" to "control" & "CaNcEl" to "cancel"
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue(); // chanaged "Mem_Str" to "memStr" & "Member_ID" to "memberId"
					control.cardSwiped(memberId); // changed "CoNtRoL" to "control"  & "Member_ID" to "memberId" & "CaRd_sWiPeD" to "cardSwiped"
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0; // changed "AmouNT" to "amount"  
				String amtStr = input("Enter amount (<Enter> cancels) : "); // changed "Amt_Str" to "amtStr"  
				if (amtStr.length() == 0) { // changed "Amt_Str" to "amtStr"
					control.cancel(); // changed "CoNtRoL" to "control" & "CaNcEl" to "cancel"
					break;
				}
				try {
					amount = Double.valueOf(amtStr).doubleValue(); // changed "AmouNT" to "amount" & "Amt_Str" to "amtStr"
				}
				catch (NumberFormatException e) {
					output("Invalid number format");
				}
				if (amount <= 0) { // changed "AmouNT" to "amount"  
					output("Amount must be positive");
					break;
				}
				control.payFine(amount); // changed "CoNtRoL" to "control" & "AmouNT" to "amount"  & "PaY_FiNe" to "payFine"
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state); // chanaged variable "StAtE" to "state"		
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void display(Object object) { // changed the method name "DiSplAY" to "display"
		output(object);
	}


}
