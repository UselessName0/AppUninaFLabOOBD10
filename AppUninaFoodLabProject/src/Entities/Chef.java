package Entities;

import java.time.LocalDate;

import DAO.ChefDAO;

public class Chef extends Utente {
	
	//ATTRIBUTI
	private String ID_Chef;

	
	//COSTRUTTORI
	
	public Chef(String Nome, String Cognome, String Email, String Password, String ID_Chef) {
		super(Nome, Cognome, Email, Password);
		this.ID_Chef = ID_Chef;
	}
	
	public Chef(String IDChef) {//Costruttore per la creazione di un oggetto Chef a partire da un ID_Chef esistente nel database
		super(null, null, null, null);
		this.ID_Chef = IDChef;
		
		ChefDAO chefDAO = new ChefDAO();
		
		this.Nome = chefDAO.getNomeChefDAO(IDChef);
		this.Cognome = chefDAO.getCognomeChefDAO(IDChef);
		this.Email = chefDAO.getEmailChefDAO(IDChef);
		this.Password = chefDAO.getpassChefDAO(IDChef);
		

		}

	public Chef() {
		super(null, null, null, null);
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
