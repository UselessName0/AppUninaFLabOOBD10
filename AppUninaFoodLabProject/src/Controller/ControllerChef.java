package Controller;

import Entities.Chef;
import Entities.Corso;
import Entities.Partecipante;
import Entities.Sessione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import DAO.ChefDAO;
import DAO.CorsoDAO;
import DAO.PartecipanteDAO;
import DAO.SessioneDAO;
import Database.DBManager;

public class ControllerChef {
	
	private ChefDAO chefDAO = new ChefDAO();
	
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
	
	
	public boolean InsertChef(Chef c) {
		ChefDAO cDAO = new ChefDAO();
		
		if(cDAO.InsertChefDAO(c)) {
			return true;
		}
		else
			return false;
	}
	
	public List<Corso> GetCorsiByChef(Chef C){
		CorsoDAO cDAO = new CorsoDAO();
		if(!C.equals(null)) {
			return cDAO.getAllCorsiByChef(C);
		}
		return cDAO.getAllCorsi();
	}
	
	public List<Sessione> GetSessioniByChef(Chef c){
		SessioneDAO sDAO = new SessioneDAO();
		if(!c.equals(null) ) {
			return sDAO.getAllSessioniDiChef(c);
		}
		return sDAO.getAllSessioniDAO();
	}
	
	public boolean InserisciCorso(Corso c) {
		CorsoDAO cDAO = new CorsoDAO();
		if(cDAO.InsertCorso(c)) {
			return true;
		}
		else
			return false;
	}
	
	public int GetNumeroCorsiCreati(Chef c) {
		ChefDAO cDAO = new ChefDAO();
		if(!c.equals(null)) {
			return cDAO.getNumeroCorsiByChef(c);
		}
		return 0;
	}
}
