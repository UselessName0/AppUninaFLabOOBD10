package Controller;

import Entities.Partecipante;
import DAO.PartecipanteDAO;
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
				
			}
			
		} catch(Exception e) {
			System.out.println("Errore durante la registrzione: " + e.getMessage());
			return null;
		}
	}
	
}
