package Entities;

import java.util.*;
import java.time.LocalDate;

public class Sessione {
	//ATTRIBUTI
	private Corso RelatedCorso;
	private LocalDate Data_Sessione;
	private int Numero_Adesioni;
	private boolean IsPratica;
	private String Luogo;
	private String LinkConferenza;
	private Ricetta Ricetta_Appresa;
	
	//COSTRUTTORI
	public Sessione(Corso RelatedCorso, LocalDate Data_Sessione, boolean IsPratica, String Luogo, String LinkConferenza, Ricetta Ricetta_Appresa) {
		this.RelatedCorso = RelatedCorso;
		this.Data_Sessione = Data_Sessione;
		this.Numero_Adesioni = 0;
		this.IsPratica = IsPratica;
		this.Luogo = Luogo;
		this.LinkConferenza = LinkConferenza;
		this.Ricetta_Appresa = Ricetta_Appresa;
	}
	//METODI
		//GETTER AND SETTER
		public Corso getRelatedCorso() {
			return RelatedCorso;
		}
	
		public void setRelatedCorso(Corso relatedCorso) {
			RelatedCorso = relatedCorso;
		}
	
		public LocalDate getData_Sessione() {
			return Data_Sessione;
		}
	
		public void setData_Sessione(LocalDate data_Sessione) {
			Data_Sessione = data_Sessione;
		}
	
		public int getNumero_Adesioni() {
			return Numero_Adesioni;
		}
	
		public void setNumero_Adesioni(int numero_Adesioni) {
			Numero_Adesioni = numero_Adesioni;
		}
	
		public boolean isIsPratica() {
			return IsPratica;
		}
	
		public void setIsPratica(boolean isPratica) {
			IsPratica = isPratica;
		}
	
		public String getLuogo() {
			return Luogo;
		}
	
		public void setLuogo(String luogo) {
			Luogo = luogo;
		}
	
		public String getLinkConferenza() {
			return LinkConferenza;
		}
	
		public void setLinkConferenza(String linkConferenza) {
			LinkConferenza = linkConferenza;
		}
	
		public Ricetta getRicetta_Appresa() {
			return Ricetta_Appresa;
		}
	
		public void setRicetta_Appresa(Ricetta ricetta_Appresa) {
			Ricetta_Appresa = ricetta_Appresa;
		}
		
	
}
