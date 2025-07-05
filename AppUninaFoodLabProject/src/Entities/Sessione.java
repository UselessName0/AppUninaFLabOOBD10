package Entities;

import java.util.*;
import java.time.LocalDate;

import DAO.SessioneDAO;
public class Sessione {
	
	//ATTRIBUTI
	private String ID_Sessione;
	private Corso RelatedCorso;
	private LocalDate Data_Sessione;
	private int Numero_Adesioni;
	private boolean IsPratica;
	private String Luogo;
	private String LinkConferenza;
	private Ricetta Ricetta_Appresa;
	
	//COSTRUTTORI
	public Sessione(String ID_Sessione,Corso RelatedCorso, LocalDate Data_Sessione, boolean IsPratica, String Luogo, String LinkConferenza, Ricetta Ricetta_Appresa) {
		this.ID_Sessione = ID_Sessione;
		this.RelatedCorso = RelatedCorso;
		this.Data_Sessione = Data_Sessione;
		this.Numero_Adesioni = 0;
		this.IsPratica = IsPratica;
		this.Luogo = Luogo;
		this.LinkConferenza = LinkConferenza;
		this.Ricetta_Appresa = Ricetta_Appresa;
	}
	
	public Sessione(String IDSessione) {//Costruttore di una Sessione già presente nel database via IDSessione
		this.ID_Sessione = IDSessione;
		SessioneDAO sessioneDAO = new SessioneDAO();
		String idCorsoTemp = sessioneDAO.getIDCorsoSessioneDAO(IDSessione);
		this.RelatedCorso = new Corso(idCorsoTemp);
		this.Data_Sessione = sessioneDAO.getDataSessioneDAO(IDSessione).toLocalDate();
		this.Numero_Adesioni = sessioneDAO.getAdesioniSessioneDAO(IDSessione);
		this.IsPratica = sessioneDAO.getIsPraticaSessioneDAO(IDSessione);
		this.Luogo = sessioneDAO.getLuogoSessioneDAO(IDSessione);
		this.LinkConferenza = sessioneDAO.getLinkConferenzaSessione(IDSessione);
		String idRicettaTemp = sessioneDAO.getRicettaSessioneDAO(IDSessione);
		this.Ricetta_Appresa = new Ricetta(idRicettaTemp);
	}
	
	//METODI
		//GETTER AND SETTER
	
		public Sessione() {
	}

		public String getID_Sessione() {
			return ID_Sessione;
		}
	
		public void setID_Sessione(String iD_Sessione) {
			ID_Sessione = iD_Sessione;
		}
		public Corso getRelatedCorso() {
			return RelatedCorso;
		}
		public void setRelatedCorso(Corso relatedCorso) {
			RelatedCorso = relatedCorso;
		}
	
		public LocalDate getData_Sessione() {
			return Data_Sessione;
		}
		
		public java.sql.Date getDataSessioneAsSQLDate() {
			return java.sql.Date.valueOf(Data_Sessione);
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
