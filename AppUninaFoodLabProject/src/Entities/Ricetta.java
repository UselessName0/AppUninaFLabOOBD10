package Entities;

public class Ricetta {
	
	//ATTRIBUTI
	private String Titolo;
	private String Descrizione;
	
	//COSTRUTTORI
	public Ricetta(String Titolo, String Descrizione) {
		this.Titolo = Titolo;
		this.Descrizione = Descrizione;
	}
	
	//METODI
		//GETTERS AND SETTERS
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
