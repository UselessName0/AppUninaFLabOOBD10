package Entities;

public class Partecipante extends Utente {
	//ATTRIBUTI
	private String ID_Partecipante;
	
	//COSTRUTTORI
	public Partecipante(String Nome, String Cognome, String Email, String Password, String ID_Partecipante) {
		super(Nome, Cognome, Email, Password);
		this.ID_Partecipante = ID_Partecipante;
	}

	
	//METODI
		//GETTERS E SETTERS
		public String getID_Partecipante() {
			return ID_Partecipante;
		}
	
		public void setID_Partecipante(String iD_Partecipante) {
			ID_Partecipante = iD_Partecipante;
		}
		
	
	
	
}
