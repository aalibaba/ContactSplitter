package de.contact.splitter;

public class Contact {

	private String anrede;
	private String briefanrede;
	private String titel;
	private String vorname;
	private String nachname;
	
	static String defaultAnrede = "Herr / Frau";
	
	public String getAnrede() {
		return anrede;
	}
	
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	
	public String getBriefanrede() {
		return anrede + " " + titel;
	}
	
	
	public String getTitel() {
		return titel;
	}
	
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
}
