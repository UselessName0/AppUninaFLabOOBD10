package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;

public class ChefDAO {

	//La funzione ritorna false se l'email non è presente all'interno del DB, true altrimenti
	public boolean checkEmail(String emailInput) {
	    String sql = "SELECT email FROM uninafoodlab.Chef WHERE email = ?";
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, emailInput);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) 
	            return true; 
	        else 
	            return false;  
	        
	    } catch (SQLException e) {
	        System.out.println("Errore durante la verifica dell'email: " + e.getMessage());
	        return false;
	    } 
	}
	
	public boolean checkPassword(String emailInput, String pwdInput) {
		String sql = "SELECT pass FROM uninafoodlab.Chef WHERE email = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1,  emailInput);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if(rs.getString(1) == pwdInput)
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.out.println("Errore durante la verifica della password");
			return false;
		}
	}
	
	//verificare il dominio password dopo una insert che va contro il vincolo 
	
}