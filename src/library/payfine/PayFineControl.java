package library.payfine;
import library.entities.Library;
import library.entities.Member;

// Author   : Chiranga
// Reviewer : Dilanka
// Mediator : Subhashini

public class PayFineControl { // changed class name "pAY_fINE_cONTROL" to "PayFineControl"***
	
	private PayFineUi ui; // changed "PayFineUI" to "payFineUi" & changed  "Ui" to "ui"//
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // changed variable "cOnTrOl_sTaTe" to "ControlState"
	private ControlState state; // changed  "cOnTrOl_sTaTe" to "ControlState" & changed  "StAtE" to "state"//
	
	private Library library; // changed  "LiBrArY" to "library"
	private Member member; // changed  "MeMbEr" to "member"


	public payFineControl() {  // changed  "pAY_fINE_cONTROL" to "payFineControl" ***
		this.library = library.getInstance(); // changed  "LiBrArY" to "library" & "GeTiNsTaNcE" to "getInstance"
		state = ControlState.INITIALISED; // changed  "cOnTrOl_sTaTe" to "ControlState" & changed  "StAtE" to "state"
	}
	
	
	public void setUi(PayFineUi ui) { // changed  "SeT_uI" to "setUi" // ui
		if (!state.equals(ControlState.INITIALISED)) { // changed  "cOnTrOl_sTaTe" to "ControlState" &  changed  "StAtE" to "state"
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui; // changed  "Ui" to "ui"
		ui.setState(PayFineUi.UiState.READY); // changed "PayFineUI" to "payFineUi" & "SeT_StAtE" to "setState" & "uI_sTaTe" to "UiState"
		state = ControlState.READY;	// changed  "cOnTrOl_sTaTe" to "ControlState" &  changed  "StAtE" to "State"
	}


	public void cardSwiped(int memberId) { // changed "CaRd_sWiPeD" to "cardSwiped" & "MeMbEr_Id" to "memberId"
		if (!state.equals(ControlState.READY)) { // changed  "cOnTrOl_sTaTe" to "ControlState" &  changed  "StAtE" to "State"
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId); // changed  "LiBrArY" to "library" &  "MeMbEr_Id" to "memberId" & "gEt_MeMbEr" to "getMember"
		
		if (member == null) { // changed  "MeMbEr" to "member"
			ui.display("Invalid Member Id"); // changed  "Ui" to "ui" & "DiSplAY" to "display"
			return;
		}
		ui.display(member.toString()); // changed  "Ui" to "ui" & changed  "MeMbEr" to "member" & "DiSplAY" to "display"
		ui.setState(PayFineUi.UiState.PAYING); // changed "PayFineUI" to "payFineUi" & "SeT_StAtE" to "setState" &
		state = ControlState.PAYING; // changed  "StAtE" to "State"
	}
	
	
	public void cancel() { // changed "CaNcEl" to "cancel"
		ui.setState(PayFineUi.UiState.CANCELLED); // changed "PayFineUI" to "payFineUi" & "SeT_StAtE" to "setState" &
		state = ControlState.CANCELLED; // changed "cOnTrOl_sTaTe" to "ControlState"  
	}


	public double payFine(double amount) { // changed "PaY_FiNe" to "payFine" & "AmOuNt" to "amount"
		if (!state.equals(ControlState.PAYING)) { // changed "cOnTrOl_sTaTe" to "ControlState" &  changed  "StAtE" to "State"
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		   }
	}	
		double change = member.payFine(amount);  // changed  "MeMbEr" to "member" & "PaY_FiNe" to "payFine" & "ChAnGe" to "change" & "AmOuNt" to "amount"
		if (change > 0) { // changed "ChAnGe" to "change"
			ui.display(String.format("Change: $%.2f", change)); // changed  "Ui" to "ui" & "DiSplAY" to "display" & "ChAnGe" to "change"
		}
		ui.display(member.toString()); // changed  "Ui" to "ui" & changed  "MeMbEr" to "member" & "DiSplAY" to "display"
		ui.setState(PayFineUi.UiState.COMPLETED); // changed "PayFineUI" to "payFineUi" & "Ui" to "ui" & "SeT_StAtE" to "setState" & "uI_sTaTe" to "uiState" 
		state = ControlState.COMPLETED; // changed "cOnTrOl_sTaTe" to "ControlState" &  changed  "StAtE" to "State"
		return change; // "ChAnGe" to "change"
	}
	


}
