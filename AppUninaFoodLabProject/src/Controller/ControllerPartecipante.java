package Controller;
import java.util.*;
import Entities.Partecipante;
import Entities.Chef;
import Entities.Corso;
import java.sql.*;
import java.time.LocalDate;

import DAO.PartecipanteDAO;
import DAO.CorsoDAO;
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
	public boolean InsertPartecipante(Partecipante p) {
		PartecipanteDAO pDAO = new PartecipanteDAO();
		
		if(pDAO.InsertPartecipanteDAO(p)) {
			System.out.println("Inserimento nel DB avvenuto con successo");
			return true;
		}
		else
			return false;
	}
	public List<Corso> GetCorsiFromDB(){
		CorsoDAO cDAO = new CorsoDAO();
		return cDAO.getAllCorsi();
	}
	
	public List<Corso> GetCorsiByNome(String Nome){
		CorsoDAO cDAO = new CorsoDAO();
		if(!Nome.isEmpty()) {			
			return cDAO.getAllCorsiByNome(Nome);
		}
		return cDAO.getAllCorsi();
	}
	
	public List<Corso> GetCorsiByArgomento(String Argomento){
		CorsoDAO cDAO = new CorsoDAO();
		if(!Argomento.isEmpty()) {
			return cDAO.getAllCorsiByArgomento(Argomento);
		}
		return cDAO.getAllCorsi();
	}
	
	public List<Corso> GetCorsiByData(LocalDate Date){
		CorsoDAO cDAO = new CorsoDAO();
		if(!Date.equals(null)){
			return cDAO.getAllCorsiByDataInizio(Date);
		}
		return cDAO.getAllCorsi();
	}
	
	public List<Corso> GetCorsiByChef(Chef C){
		CorsoDAO cDAO = new CorsoDAO();
		if(!C.equals(null)) {
			return cDAO.getAllCorsiByChef(C);
		}
		return cDAO.getAllCorsi();
	}
	
	public List<Corso> GetCorsiDaPiuFiltri(List<Corso> Corso1, List<Corso> Corso2){
		List<Corso> Temp = Corso1;
		Temp.retainAll(Corso2);
		
		return Temp;
	}
	
	public List<Corso> GetCorsiDovePartecipanteNonIscritto(Partecipante p){
		CorsoDAO cDAO = new CorsoDAO();
		if(!p.equals(null))
			return cDAO.getCorsiDovePartecipanteNonIscrittoDAO(p);
		else
			return cDAO.getAllCorsi();
		
	}
	
	public List<String> GetIDCorsiDovePartecipanteNonIscritto(Partecipante p){
		CorsoDAO cDAO = new CorsoDAO();
		if(!p.equals(null))
			return cDAO.getIDCorsiDovePartecipanteNonIscrittoDAO(p);
		else
			return cDAO.getAllidCorsi();
	}

	public List<Corso> GetCorsiDovePartecipanteIscritto(Partecipante p){
		CorsoDAO cDAO = new CorsoDAO();
		if(!p.equals(null))
			return cDAO.getCorsiDovePartecipanteIscirtto(p);
		else
			return cDAO.getAllCorsi();
	}
}
