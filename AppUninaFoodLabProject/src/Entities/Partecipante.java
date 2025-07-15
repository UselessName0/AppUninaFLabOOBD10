package Entities;

import DAO.PartecipanteDAO;


public class Partecipante extends Utente {
	
	//ATTRIBUTI
	private String ID_Partecipante;
	
	//COSTRUTTORI
	public Partecipante(String Nome, String Cognome, String Email, String Password, String ID_Partecipante) {
		super(Nome, Cognome, Email, Password);
		this.ID_Partecipante = ID_Partecipante;
	}
	
	public Partecipante(String IDPartecipante) {//COSTRUTTORE PER LA CREAZIONE DI UN PARTECIPANTE PRESENTE NEL DATABASE VIA IDPARTECIPANTE
		super(null, null, null, null);
		PartecipanteDAO partecipanteDAO = new PartecipanteDAO();
		
		this.ID_Partecipante = IDPartecipante;
		this.Nome = partecipanteDAO.getNomePartecipanteDAO(IDPartecipante);
		this.Cognome = partecipanteDAO.getCognomePartecipanteDAO(IDPartecipante);
		this.Email = partecipanteDAO.getEmailPartecipanteDAO(IDPartecipante);
		this.Password = partecipanteDAO.getPassPartecipanteDAO(IDPartecipante);
	}
	
	public Partecipante() {
		super(null, null, null, null);
	}
	
	//METODI
		//GETTERS E SETTERS
		public String getID_Partecipante() {
			return ID_Partecipante;
		}
	
		public void setID_Partecipante(String iD_Partecipante) {
			ID_Partecipante = iD_Partecipante;
		}
		
		public String getCognome() {
			return Cognome;
		}
		
	
	
	
}
