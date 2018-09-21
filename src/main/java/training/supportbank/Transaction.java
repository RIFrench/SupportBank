package training.supportbank;

public class Transaction {
	
	public String date;
	public String from;
	public String to;
	public String narrative;
	public float amount;
	
	public Transaction(String date_in, String narrative_in, float amount_in) {
		date = date_in;
		narrative = narrative_in;
		amount = amount_in;
	}
	
	public void populate(String date_in, String narrative_in, float amount_in) {
		date = date_in;
		narrative = narrative_in;
		amount = amount_in;
	}
}
