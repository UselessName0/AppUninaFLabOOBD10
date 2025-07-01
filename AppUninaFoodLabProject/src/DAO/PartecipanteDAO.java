package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;
import Entities.Partecipante;

public class PartecipanteDAO {
	//La funzione ritorna false se l'email non è presente all'interno del DB, true altrimenti
	public boolean checkEmail(String emailInput) {
	    String sql = "SELECT email FROM uninafoodlab.partecipante WHERE email = ?";
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
		String sql = "SELECT pass FROM uninafoodlab.partecipante WHERE email = ?";
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
	public boolean InsertPartecipante(Partecipante Partecipante_Input) {
		String sql = "INSERT INTO uninafoodlab.partecipante(idpartecipante, nomepartecipante, email, pass)VALUES (?, ?, ?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
				pstmt.setString(2, Partecipante_Input.getNome());
				pstmt.setString(3, Partecipante_Input.getEmail());
				pstmt.setString(4, Partecipante_Input.getPassword());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento della nuova entità: "+ e.getMessage());
				return false;
			}
	}
	
	public String getNomePartecipanteDAO(Partecipante Partecipante_Input) {
		String sql = "SELECT P.Nomepartecipante FROM Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
		        if (rs.next()) 
		            return rs.getString("Nomepartecipante");
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	public String getCognomePartecipanteDAO(Partecipante Partecipante_Input) {
		String sql = "SELECT P.Cognomepartecipante FROM Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
		        if (rs.next()) 
		            return rs.getString("Cognomepartecipante");
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	public String getEmailPartecipanteDAO(Partecipante Partecipante_Input) {
		String sql = "SELECT P.Email FROM Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
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
