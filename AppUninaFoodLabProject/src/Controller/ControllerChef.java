package Controller;

import Entities.Chef;
import Entities.Corso;
import Entities.Partecipante;
import Entities.Ricetta;
import Entities.Sessione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import DAO.ChefDAO;
import DAO.CorsoDAO;
import DAO.PartecipanteDAO;
import DAO.RicettaDAO;
import DAO.SessioneDAO;
import Database.DBManager;

public class ControllerChef {
	
	//ATTRIBUTI
	private ChefDAO chefDAO = new ChefDAO();
	
	//METODI
	//Metodo per il check del login da parte di uno chef
	public Chef LoginCheck(String email_Input, String password_Input) {
		try {
			if (!chefDAO.checkEmail(email_Input)) {
				System.out.println("Email non registrata.");
				return null;
			}
			
			if (!chefDAO.checkPassword(email_Input, password_Input)) {
				System.out.println("Password errata.");
				return null;
			}
			Chef chef = chefDAO.getChefByEmail(email_Input);
			return chef;
		} catch (Exception e) {
			System.out.println("Errore durante il login: " + e.getMessage());
			return null;
		}
	}
	
	//Metodo per il check della registrazione di uno chef
	public Chef RegisterCheck(String Email_Input, String Password_Input, String Nome_Input, String Cognome_Input) {
		try {
			if(chefDAO.checkEmail(Email_Input)) {
				return null;
			}
			else {
				Chef c = new Chef();
				String sql = "SELECT MAX(idchef) AS max_id FROM uninafoodlab.chef";
				try(Connection conn = DBManager.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
					ResultSet rs = pstmt.executeQuery();
					String nuovoId;
					if (rs.next()) {
						if((!Email_Input.isEmpty()) || (!Password_Input.isEmpty())) {
							String ultimoid = rs.getString("max_id");
							int numero = Integer.parseInt(ultimoid.substring(2));
						    nuovoId = "CH" + String.valueOf(numero + 1);
						    c.setID_Chef(nuovoId);
						    c.setNome(Nome_Input);
						    c.setCognome(Cognome_Input);
							c.setEmail(Email_Input);
							c.setPassword(Password_Input);
							if(InsertChef(c))
								return c;
							else
								return null;
						}
						else {
							System.out.println("Uno dei campi è vuoto");
							return null;
						}
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
	
	//Metodo che permette l'inserimento di un nuovo chef (dopo avvenuta registrazione) nel DB
	public boolean InsertChef(Chef c) {
		ChefDAO cDAO = new ChefDAO();
		if(cDAO.InsertChefDAO(c)) {
			return true;
		}
		else
			return false;
	}
	
	//Metodo che permette di avere una lista di tutti i corsi partendo da uno chef 
	public List<Corso> GetCorsiByChef(Chef C){
		CorsoDAO cDAO = new CorsoDAO();
		if(!C.equals(null)) 
			return cDAO.getAllCorsiByChef(C);
		return cDAO.getAllCorsi();
	}
	
	//Metodo che permette di avere una lista di tutte le sessioni partendo da uno chef 	
	public List<Sessione> GetSessioniByChef(Chef c){
		SessioneDAO sDAO = new SessioneDAO();
		if(!c.equals(null) ) 
			return sDAO.getAllSessioniDiChef(c);
		return sDAO.getAllSessioniDAO();
	}
	
	//Metodo per permette di avere tutti i corsi creati da uno chef
	public int GetNumeroCorsiCreati(Chef c) {
		ChefDAO cDAO = new ChefDAO();
		if(!c.equals(null)) 
			return cDAO.getNumeroCorsiByChef(c);
		return 0;
	}
	
	//Metoto che permette di avere una lista di tutti i corsi di uno chef e non 
	public List<Corso> GetCorsiExceptChef(Chef c){
		CorsoDAO cDAO = new CorsoDAO();
		if(!c.equals(null)) {
			return cDAO.getAllCorsiExceptChef(c);
		}
		return cDAO.getAllCorsi();
	}
	
	//Metodo che permette di inserire un nuovo corso 
	public boolean InsertCorso(Corso c) {
		CorsoDAO cDAO = new CorsoDAO();
		if(!c.equals(null) && !(c.getChef_Proprietario() ==null) && !c.getNome_Corso().isEmpty() && !c.getArgomento().isEmpty() && !c.getDescrizione().isEmpty()) {
			c.setData_Creazione(java.time.LocalDate.now());
			String sql = "SELECT MAX(idcorso) AS max_id FROM uninafoodlab.corso";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				String nuovoId;
				if (rs.next()) {
					String ultimoid = rs.getString("max_id");					
					int numero = Integer.parseInt(ultimoid.substring(2));
					nuovoId = "CO" + String.valueOf(numero + 1);				
					c.setID_Corso(nuovoId);
				}else{
				System.out.println("Errore durante la generazione dell'ID del corso.");
				return false;
				}
			}catch(Exception e) {
				System.out.println("Errore durante la generazione dell'ID del corso: " + e.getMessage());
				return false;
			}
			return cDAO.InsertCorso(c);
		}
		System.out.println("Errore dati corso non validi.");
		return false;
	}
	
	//Metodo che permette di inserire una sessione 
	public boolean InserisciSessione(Chef Ch, Corso Co, LocalDate DataSe, boolean IsPratica, String Luogo, String LinkConferenza, Ricetta R) {
		SessioneDAO sDAO = new SessioneDAO();
		CorsoDAO cDAO = new CorsoDAO();
		if(!Ch.equals(null) && !Co.equals(null) && !DataSe.equals(null)) {
			if(IsPratica && !Luogo.isEmpty() || !IsPratica && !LinkConferenza.isEmpty() && R != null) {
				Sessione s = new Sessione();
				s.setRelatedCorso(Co);
				s.setData_Sessione(DataSe);
				s.setIsPratica(IsPratica);
				s.setLuogo(Luogo);
				s.setLinkConferenza(LinkConferenza);
				s.setNumero_Adesioni(0);
				s.setRicetta_Appresa(R);
				String sql = "SELECT MAX(idsessione) AS max_id FROM uninafoodlab.sessione";
				try(Connection conn = DBManager.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
					ResultSet rs = pstmt.executeQuery();
					String nuovoId;
					if (rs.next()) {
						String ultimoid = rs.getString("max_id");
						int numero = Integer.parseInt(ultimoid.substring(2));
						nuovoId = "IS" + String.valueOf(numero + 1);
						s.setID_Sessione(nuovoId);
					}else{
						System.out.println("Errore durante la generazione dell'ID della sessione.");
						return false;
					}
				}catch(Exception e) {
					System.out.println("Errore durante la generazione dell'ID della sessione: " + e.getMessage());
					return false;
				}
				if(cDAO.CheckIfNoSessioniByCorso(Co)) {
					Co.setData_Inizio(DataSe);
					cDAO.UpdateCorso(Co);
				}
				return sDAO.InsertSessione(s);
			}
			else {
				System.out.println("Errore dati sessione non validi.");
				return false;
			}
		}
		System.out.println("Errore dati sessione non validi.");
		return false;
	}
	
	//Metodo che permette di avere tutta li lista di ricette di uno chef
	public List<Ricetta> GetRicetteByChef(Chef c) {
		RicettaDAO rDAO = new RicettaDAO();
		if(c != null) {
			return rDAO.getRicetteByChef(c);
		}
		return null;
	}
	
	//Metodo che restituisce un array di tutte le sessioni per singolo mese 
	public int[] GetNumeroSessioniByMonth(Chef c) {
		SessioneDAO sDAO = new SessioneDAO();
		if(c != null) {
			return sDAO.getNumeroSessioniByMonth(c);
		}
		
		return null;
	}
	
	//Metodo che permette di avere la lista di tutte le ricette
	public List<Ricetta> GetAllRicette(){
		RicettaDAO rDAO = new RicettaDAO();
		return rDAO.GetAllRicettaDAO();
	}
	
	//Metodo che premtte di inserire: titolo e descrizione di una ricetta 
	public boolean InsertRicetta(String Titolo, String Descrizione) {
		RicettaDAO rDAO = new RicettaDAO();
		if(!Titolo.isEmpty() && !Descrizione.isEmpty()) {
			Ricetta r = new Ricetta();
			r.setTitolo(Titolo);
			r.setDescrizione(Descrizione);
			String sql = "SELECT MAX(idricetta) AS max_id FROM uninafoodlab.ricetta";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				String nuovoId;
				if (rs.next()) {
					String ultimoid = rs.getString("max_id");
					int numero = Integer.parseInt(ultimoid.substring(2));
					nuovoId = "RC" + String.valueOf(numero + 1);
					r.setIDRicetta(nuovoId);
				}else{
					System.out.println("Errore durante la generazione dell'ID della ricetta.");
					return false;
				}
				rDAO.InsertRicettaDAO(r);
			return true;
			}catch(Exception e) {
				System.out.println("Errore durante la generazione dell'ID della ricetta: " + e.getMessage());
				return false;
			}
		} else {
			System.out.println("Dati ricetta non validi.");
			return false;
		}
	}
	
	//Metodo che restituisce un array di tutte le sessioni con specifica modalità per un singolo chef 
	public int[] GetModalitaDiSessionePerChef(Chef c) {
		int [] modalita = new int[2];
		SessioneDAO sDAO = new SessioneDAO();
		if(c != null) {
			modalita[0] = sDAO.getNumeroSessioniPraticheByChef(c);
			modalita[1] = sDAO.getNumeroSessioniOnlineByChef(c);
			return modalita;
		}
		return null;
	}
}