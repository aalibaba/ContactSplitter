package de.contact.splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactSplitter {
	private List<String> prefixList;
	private List<String> titelList; 
	//Um zu erkennen ob es sich um eine Anrede handelt wird der Klasse eine Liste der möglichen Anreden zugewiesen	(s.u.)

	void setPrefix(List<String> prefix){
		this.prefixList = prefix;
	}
	
	void setAnrede(List<String> anrede){
		this.titelList = anrede;
	}
	
	public ContactSplitter(List<String> prefixList, List<String> anredeList){
		this.prefixList = prefixList;
		this.titelList = anredeList;
	}
	

	
	public static List<String> get_matches(String s, String p) {
	    // returns all matches of p in s for first group in regular expression 
	    List<String> matches = new ArrayList<String>();
	    Matcher m = Pattern.compile(p).matcher(s);
	    System.out.println(m.groupCount());
	    while(m.find()) {
	    	if(m.group(1) != null){
	    		 matches.add(m.group(1));
	    	}	       
	    }
	    return matches;
	}
	
	public Contact split(String eingabe){
		String regAnrede = "(Frau|Herrn|Herr?)";                                    // Passt vermutlich
		
		// Build regex for title
		String regTitle = "(";
		for(String titel : titelList){
			regTitle += titel.trim() + " |";
		}
		regTitle = regTitle.substring(0,regTitle.length()-1); // Cut last | away
		regTitle += ")";
		
		String regVorname = "([A-ZÜÄÖ–]{1}[a-züäö]+)";
		
		// Build regex for prefixes
		// String regPrefix = "( van der | van de | von der | von und zu | von | van | zu | de)";
		String regPrefix = "(";
		for(String prefix : prefixList){
			regPrefix += " " + prefix.trim() + " |";
		}
		regPrefix = regPrefix.substring(0,regPrefix.length()-1); // Cut last | away
		regPrefix += ")";
		System.out.println(regPrefix);
		System.out.println(regTitle);

		String regNachname = "";
		String splitresult[] = null;
		
		String anrede = "";
		String titel = "";
		String vorname = "";
		String nachname = "";
		
		
		// Get anrede and split after it
		List<String> anredeList = get_matches(eingabe,regAnrede);
		if(anredeList.size()>0){
			splitresult = eingabe.split(anredeList.get(anredeList.size()-1));
			eingabe = splitresult[splitresult.length-1].trim();
			
			anrede = anredeList.get(0);
		}

		
		// Get title and split after last
		List<String> titelList = get_matches(eingabe, regTitle);
		if(titelList.size()>0){
			splitresult = eingabe.split(titelList.get(titelList.size()-1));
			eingabe = splitresult[splitresult.length-1].trim();
			
			for(String singelTitel : titelList){
				titel += singelTitel;
			}
		}
		
		// find prefix
		List<String> prefixList = get_matches(eingabe, regPrefix);
		if(prefixList.size() > 0){
			splitresult = eingabe.split(prefixList.get(prefixList.size()-1));
			
			for(String singlePrefix : prefixList){
				nachname += singlePrefix + " ";
			}
			nachname += splitresult[splitresult.length -1];
			
			// if vorname exists
			if(splitresult.length>1){
				vorname = splitresult[0];
			}			
		} else {
			// No Prefix, use last " " to split
			int lastSpace = eingabe.lastIndexOf(" ");
			if(lastSpace == -1){
				vorname = "";
				nachname = eingabe;
			} else {
				vorname = eingabe.substring(0, lastSpace-1);
				nachname = eingabe.substring(lastSpace+1,eingabe.length());
			}
		}

		Contact contact = new Contact();
		contact.setAnrede(anrede);
		contact.setTitel(titel);
		contact.setVorname(vorname);
		contact.setNachname(nachname);
		return contact;
	}
}
