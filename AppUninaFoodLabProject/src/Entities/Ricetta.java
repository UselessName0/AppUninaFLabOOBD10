package Entities;

import DAO.RicettaDAO;
public class Ricetta {
	
	//ATTRIBUTI
	private String IDRicetta;
	private String Titolo;
	private String Descrizione;
	
	//COSTRUTTORI
	public Ricetta(String IDRicetta, String Titolo, String Descrizione) {
		this.IDRicetta = IDRicetta;
		this.Titolo = Titolo;
		this.Descrizione = Descrizione;
	}
	
	public Ricetta(String IDRicetta) { //Costruttore di una ricetta già presente nel database via IDRicetta
		RicettaDAO ricettaDAO = new RicettaDAO();
		this.IDRicetta = IDRicetta;
		this.Titolo = ricettaDAO.GetTitoloRicettaDAO(IDRicetta);
	}
	
	public Ricetta() {
		
	}
	//METODI
		//GETTERS AND SETTERS	
		public String getIDRicetta() {
			return IDRicetta;
		}
		public void setIDRicetta(String iDRicetta) {
			IDRicetta = iDRicetta;
		}
		public String getTitolo() {
			return Titolo;
		}
		public void setTitolo(String titolo) {
			Titolo = titolo;
		}
		public String getDescrizione() {
			return Descrizione;
		}
		public void setDescrizione(String descrizione) {
			Descrizione = descrizione;
		}
			
}
