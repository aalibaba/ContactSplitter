package de.contact.splitter;

import java.util.ArrayList;
import java.util.List;

public class ContactSplitter {
	private List<String> prefixList;
	private List<String> anredeList; 
	//Um zu erkennen ob es sich um eine Anrede handelt wird der Klasse eine Liste der möglichen Anreden zugewiesen	(s.u.)

	void setPrefix(List<String> prefix){
		this.prefixList = prefix;
	}
	
	void setAnrede(List<String> anrede){
		this.anredeList = anrede;
	}
	
	public ContactSplitter(List<String> prefixList, List<String> anredeList){
		this.prefixList = prefixList;
		this.anredeList = anredeList;
	}
	
	void addToPrefixList(String prefix){
		if(prefixList == null){
			prefixList = new ArrayList<String>();
		}
		prefixList.add(prefix);
	}
	
	void addToAnredeList(String anrede){
		if(anredeList == null){
			anredeList = new ArrayList<String>();
		}
		anredeList.add(anrede);
	}
	
	
	
	void split(String eingabe){
		Contact contact = new Contact();
		//Befindet sich nur 1 Wort in dem String wird davon ausgegangen dass es sich um den Nachnamen handelt
		
		// 	// Nachdem die Anrede identifiziert wurde kann über diese das Geschlecht und eine weitere Briefanrede gesetzt werden (s.u.).
		
		//Des Weiteren erhält die Klasse eine weitere Liste von markierten Wörtern die einen Vor- und Nachnamen Päfixe / Suffixe sein können
		// (von, de, van de, usw.). Diese müssen dem Nachnamen mit zugeordnet werden.
		// Wörter die danach folgen sind eindeutig dem Nachnamen zugehörig.
		
		// Ansonsten gilt die Regel dass das letzte zusammenhängende Wort der Nachname ist. Besteht der Nachname 
		// aus 2 (nicht markierten) Wörtern müssen diese durch einen Bindestrich verbunden sein
		// z.B. Schmidt-Müller.
	}
}
