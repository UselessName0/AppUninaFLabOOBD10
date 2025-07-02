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
			System.out.println("Errore durante la verifica della password: "+ e.getMessage());
			return false;
		}
	}
	
	//verificare il dominio password dopo una insert che va contro il vincolo 
	public boolean InsertChef(Chef Chef_Input) {
		String sql = "INSERT INTO uninafoodlab.chef(idchef, nomechef, cognomechef, email, pass)VALUES (?, ?, ?, ?, ?)";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
				pstmt.setString(2, Chef_Input.getNome());
				pstmt.setString(3, Chef_Input.getCognome());
				pstmt.setString(4, Chef_Input.getEmail());
				pstmt.setString(5, Chef_Input.getPassword());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento del nuovo chef: "+ e.getMessage());
				return false;
			}
	}
	
	public String getNomeChefDAO(Chef Chef_Input) {
		String sql = "SELECT Ch.Nomechef FROM Chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("Nomechef");
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	public String getCognomeChefDAO(Chef Chef_Input) {
		String sql = "SELECT Ch.Cognomechef FROM Chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
		        if (rs.next()) 
		            return rs.getString("Cognomechef");
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	public String getEmailChefDAO(Chef Chef_Input) {
		String sql = "SELECT Ch.Email FROM Chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
		        if (rs.next()) 
		            return rs.getString("Email");
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	
	
}