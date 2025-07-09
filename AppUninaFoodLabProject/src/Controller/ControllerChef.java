package Controller;

import Entities.Chef;

import DAO.ChefDAO;

public class ControllerChef {
	
	private ChefDAO chefDAO = new ChefDAO();
	
	public Chef LoginCheck(String email_Input, String password_nput) {
		try {
			if (!chefDAO.checkEmail(email_Input)) {
				System.out.println("Email non registrata.");
				return null;
			}
			
			if (!chefDAO.checkPassword(email_Input, password_Input)) {
				System.out.println("Password errata.");
				return null;
			}

			return new Chef(chefDAO.getIDChefDAO(email_Input, password_Input));

		} catch (Exception e) {
			System.out.println("Errore durante il login: " + e.getMessage());
			return null;
		}
	}
}	
