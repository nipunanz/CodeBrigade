package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Author   : Chiranga
// Reviwer  : Subhashini
// Mediator : Nipuna


public class Calendar {
	
	private static Calendar self; // changed variable "sElF" to "self"
	private static java.util.Calendar calendar; // changed variable "cAlEnDaR" to "calendar"
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance(); // changed method "cAlEnDaR" to "calendar"
	}
	
	public static Calendar getInstance() { // changed method "gEtInStAnCe" to "getInstance"
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days); // changed method "cAlEnDaR" to "calendar"		
	}
	
	public synchronized void setDate(Date date) { // changed method "SeT_DaTe" to "setDate" & // changed  "Date DaTe" to "Date Date"
		try {
		calendar.setTime(date);// changed method "cAlEnDaR" to "calendar" & // changed  "DaTe" to "Date"
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  // changed method "cAlEnDaR" to "calendar"
	        calendar.set(java.util.Calendar.MINUTE, 0); // changed method "cAlEnDaR" to "calendar" 
	        calendar.set(java.util.Calendar.SECOND, 0);  // changed method "cAlEnDaR" to "calendar"
	        calendar.set(java.util.Calendar.MILLISECOND, 0); // changed method "cAlEnDaR" to "calendar"
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date getDate() { // changed method "gEt_DaTe" to "getDate"
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0); // changed method "cAlEnDaR" to "calendar"  
	        calendar.set(java.util.Calendar.MINUTE, 0);  // changed method "cAlEnDaR" to "calendar"
	        calendar.set(java.util.Calendar.SECOND, 0);  // changed method "cAlEnDaR" to "calendar"
	        calendar.set(java.util.Calendar.MILLISECOND, 0);// changed method "cAlEnDaR" to "calendar"
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) { // changed method "gEt_DuE_DaTe" to "getDuedate"
		Date now = getDate(); // changed method "gEt_DaTe" to "getDate" & changed "nOw" to "now"
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date duedate = calendar.getTime(); //changed method "dUeDaTe" to "duedate"
		calendar.setTime(now); // changed method "nOw" to "now"
		return duedate; // changed method "dUeDaTe" to "duedate"
	}
	
	public synchronized long getDaysDifference(Date targetDate) { // changed method "GeT_DaYs_DiFfErEnCe" to "getDaysDifferrence"
		
	    long diffMillis = getDate().getTime() - targetDate.getTime(); // changed method "gEt_DaTe" to "getDate" & changed "Diff_Mills" to "diffMillis"
	    long diffDay = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS); // changed method "Diff_Day" to "diffDay"
	    return diffDay;
	}

}
