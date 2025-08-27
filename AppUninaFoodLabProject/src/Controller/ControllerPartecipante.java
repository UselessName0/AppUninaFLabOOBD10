package Controller;
import java.util.*;
import Entities.Partecipante;
import Entities.Sessione;
import Entities.Chef;
import Entities.Corso;
import Entities.IscrizioneCorso;
import Entities.Ricetta;
import Entities.IscrizioneSessione;

import java.sql.*;
import java.time.LocalDate;

import DAO.PartecipanteDAO;
import DAO.RicettaDAO;
import DAO.SessioneDAO;
import DAO.ChefDAO;
import DAO.CorsoDAO;
import DAO.IscrizioneCorsoDAO;
import DAO.IscrizioneSessioneDAO;
import DAO.RicettaDAO;
import Database.DBManager;
public class ControllerPartecipante {

	//ATTRIBUTI
	private PartecipanteDAO partecipanteDAO = new PartecipanteDAO();
	
	//METODI
	//Metodo che permette check del login di un partecipante
	public Partecipante LoginCheck(String Email_Input, String Password_Input) {
	    try {
	        if (!partecipanteDAO.checkEmail(Email_Input)) {
	            System.out.println("Email non registrata.");
	            return null;
	        }
	        if (!partecipanteDAO.checkPassword(Email_Input, Password_Input)) {
	            System.out.println("Password errata.");
	            return null;
	        }
	        Partecipante partecipante = partecipanteDAO.getPartecipanteByEmail(Email_Input);
	        return partecipante;
	    } catch(Exception e){
	        System.out.println("Errore durante il login: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//Metodo che controlla che l'email non è già stata registrata
	public Partecipante RegisterCheck(String Email_Input, String Password_Input, String Nome_Input, String Cognome_Input) {
		try {
			if(partecipanteDAO.checkEmail(Email_Input)) {
				System.out.println("Email già registrata!");
				return null;
			}
			else {
				Partecipante p = new Partecipante();
				String sql = "SELECT MAX(idpartecipante) AS max_id FROM uninafoodlab.partecipante";
				try(Connection conn = DBManager.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
					ResultSet rs = pstmt.executeQuery();
					String nuovoId;
					if (rs.next()) {
						String ultimoid = rs.getString("max_id");
						int numero = Integer.parseInt(ultimoid.substring(2));
					    nuovoId = "PT" + String.valueOf(numero + 1);
					    p.setID_Partecipante(nuovoId);
					    p.setNome(Nome_Input);
					    p.setCognome(Cognome_Input);
						p.setEmail(Email_Input);
						p.setPassword(Password_Input);
						if(InsertPartecipante(p))
							return p;
						else
							return null;
					}
					else 
						return null;
				}
			}
		} catch(Exception e) {
			System.out.println("Errore durante la registrzione: " + e.getMessage());
			return null;
		}
	}
	
	//Metodo che peremtte l'inserimento di un nuovo partecipante 
	public boolean InsertPartecipante(Partecipante p) {
		PartecipanteDAO pDAO = new PartecipanteDAO();
		if(pDAO.InsertPartecipanteDAO(p)) {
			System.out.println("Inserimento nel DB avvenuto con successo");
			return true;
		}
		else
			return false;
	}
	
	//Metodo che restituisce una lista di tutti i corsi presenti nel DB 
	public List<Corso> GetCorsiFromDB(){
		CorsoDAO cDAO = new CorsoDAO();
		return cDAO.getAllCorsi();
	}
	
	//Metodo che restituisce una lista di corsi per chef
	public List<Corso> GetCorsiByChef(Chef C){
		CorsoDAO cDAO = new CorsoDAO();
		if(!C.equals(null)) {
			return cDAO.getAllCorsiByChef(C);
		}
		return cDAO.getAllCorsi();
	}
	
	//Metodo che restituisce una lista di corsi in cui il partecipante non è iscritto
	public List<Corso> GetCorsiDovePartecipanteNonIscritto(Partecipante p){
		CorsoDAO cDAO = new CorsoDAO();
		if(!p.equals(null))
			return cDAO.getCorsiDovePartecipanteNonIscrittoDAO(p);
		else
			return cDAO.getAllCorsi();
	}

	//Metodo che restituisce una lista di tutti i corsi dove il partecipanete è iscritto
	public List<Corso> GetCorsiDovePartecipanteIscritto(Partecipante p){
		CorsoDAO cDAO = new CorsoDAO();
		if(!p.equals(null))
			return cDAO.getCorsiDovePartecipanteIscirtto(p);
		else
			return cDAO.getAllCorsi();
	}
	
	//Metodo che permette di iscrivere un partecipante ad un corso 
	public boolean IscriviPartecipanteACorso(Partecipante p, Corso c) {
		if(p != null && c != null) {
			IscrizioneCorso IC = new IscrizioneCorso(c, p, LocalDate.now());
			IscrizioneCorsoDAO icDAO = new IscrizioneCorsoDAO();
			if(icDAO.insertIscrizioneCorso(IC)) {
				System.out.println("Iscrizione avvenuta con successo");
				return true;
			} else {
				System.out.println("Iscrizione fallita");
				return false;
			}
		} else {
			System.out.println("Partecipante o Corso non valido");
			return false;
		}
	}
	
	//Metodo che restituisce una lista di sessioni dove partecipante è iscritto 
	public List<Sessione> GetSessioneDovePartecipanteIscritto(Partecipante p){
		SessioneDAO sDAO = new SessioneDAO();
		if(!p.equals(null))
			return sDAO.getSessioniDiPartecipante(p);
		else {
			System.out.println("Partecipante nullo impossibile recuperare le sue sessioni.");
			return sDAO.getAllSessioniDAO();
		}
	}
	
	//Metodo che restituisce la lista di ricette del partecipante 
	public List<Ricetta> GetRicettaDiPartecipante(Partecipante p){
		RicettaDAO rDAO = new RicettaDAO();
		if(!p.equals(null))
			return rDAO.GetAllRicettaImparateDaPartecipante(p);
		else {
			System.out.println("Partecipante nullo impossibile recuperare le sue ricette");
			return null;
		}
	}
	
	//Metodo che restituisce tutti gli chef presenti nel DB 
	public List<Chef> GetListaChefFromDB(){
		ChefDAO cDAO = new ChefDAO();
		return cDAO.getAllChef();
	}
	
	//Metodo che restituisce il numero di corsi al quale un partecipante è iscritto
	public int GetNumeroCorsiSeguiti(Partecipante p) {
		if(p != null) {
			CorsoDAO cDAO = new CorsoDAO();
			return cDAO.getNumeroCorsiPartecipante(p);
		} else {
			System.out.println("Partecipante nullo, impossibile recuperare il numero di corsi seguiti.");
			return 0;
		}
	}
	
	//Metodo che restituisce una lista di sessioni 
	public List<Sessione> GetListaSessioni() {
		SessioneDAO sDAO = new SessioneDAO();
		return sDAO.getAllSessioniDAO();
	}
	
	//Metodo che restituisce una lista di sessioni dove il partecipante non è iscritto
	public List<Sessione> GetListaSessioniDovePartecipanteNonIscritto(Partecipante p){
		SessioneDAO sDAO = new SessioneDAO();
		if(!p.equals(null))
			return sDAO.getSessioniDovePartecipanteNonIscritto(p);
		else {
			System.out.println("Partecipante nullo impossibile recuperare le sue sessioni.");
			return sDAO.getAllSessioniDAO();
		}
	}
	
	//Metodo che permette di iscrivere partecipante ad una sessione 
	public boolean IscriviPartecipanteASessione(Partecipante p, Sessione s) {
		if(p != null && s != null) {
			IscrizioneCorsoDAO icDAO = new IscrizioneCorsoDAO();
			if(icDAO.CheckIscrizioneCorso(p, s.getRelatedCorso())) {
				IscrizioneSessione is = new IscrizioneSessione(p, s);
				IscrizioneSessioneDAO isDAO = new IscrizioneSessioneDAO();
				return isDAO.insertIscrizioneSessione(is); 
			}	
		}
		return false;
	}
	
	//Metodo che permette di iscrivere partecipante a sessione con adesione pratica
	public boolean IscriviPartecipanteASessioneConAdesione(Partecipante p, Sessione s, boolean adesione) {
		if(p != null && s != null) {
			IscrizioneCorsoDAO icDAO = new IscrizioneCorsoDAO();
			if(icDAO.CheckIscrizioneCorso(p, s.getRelatedCorso())) {
				IscrizioneSessione is = new IscrizioneSessione(p, s, adesione);
				IscrizioneSessioneDAO isDAO = new IscrizioneSessioneDAO();
				return isDAO.insertIscrizioneSessione(is); 
			}	
		}
		return false;
	}
}