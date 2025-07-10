package Controller;

import Entities.Partecipante;

import java.sql.*;

import DAO.PartecipanteDAO;
import Database.DBManager;
public class ControllerPartecipante {

	private PartecipanteDAO partecipanteDAO = new PartecipanteDAO();
	
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

	        // Se tutto è corretto, recupera il partecipante dal DB
	        Partecipante partecipante = partecipanteDAO.getPartecipanteByEmail(Email_Input);
	        return partecipante;

	    } catch(Exception e){
	        System.out.println("Errore durante il login: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
	//Controllare che la mail non è già stata registrata
	public Partecipante RegisterCheck(String Email_Input, String Password_Input) {
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
					    p.setNome("nome");
					    p.setCognome("cognome");
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
	public boolean InsertPartecipante(Partecipante p) {
		PartecipanteDAO pDAO = new PartecipanteDAO();
		
		if(pDAO.InsertPartecipanteDAO(p)) {
			System.out.println("Inserimento nel DB avvenuto con successo");
			return true;
		}
		else
			return false;
	}
}
