package Entities;

import java.time.LocalDate;

public class Chef extends Utente {
	
	//ATTRIBUTI
	private String ID_Chef;
	
	//COSTRUTTORI
	public Chef(String Nome, String Cognome, String Email, String Password, String ID_Chef) {
		super(Nome, Cognome, Email, Password);
		this.ID_Chef = ID_Chef;
	}
	
	//METODI
		//GETTERS E SETTERS
		public String getID_Chef() {
			return ID_Chef;
		}
		public void setID_Chef(String iD_Chef) {
			ID_Chef = iD_Chef;
		}
	
	public void InstanziaCorso(String IDCorso,String Nome_Corso, String Argomento, LocalDate Data_Inizio, String FrequenzaCorsi) {
		Corso C = new Corso(IDCorso, this, Nome_Corso, Argomento, Data_Inizio, FrequenzaCorsi);
	}
	
}
