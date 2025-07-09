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
		} catch(Exception e){
			System.out.println("Errore durante il login" +e.getMessage());
		}
		return null;
	}
	
}
