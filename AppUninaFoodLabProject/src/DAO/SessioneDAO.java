package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;
import Entities.Corso;
import Entities.Sessione;
import Entities.Ricetta;

public class SessioneDAO {
	
	public boolean InsertSessione(Sessione Sessione_Input) {
		String sql = "INSERT INTO uninafoodlab.sessione(idsessione, idcorso, datasessione, ispratica, adesioni, linkconferenza, luogo, idricetta) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				Corso tempCorso = Sessione_Input.getRelatedCorso();
				Ricetta tempRicetta = Sessione_Input.getRicetta_Appresa();
				
				pstmt.setString(1, Sessione_Input.getID_Sessione());
				pstmt.setString(2, tempCorso.getID_Corso());
				pstmt.setDate(3, Sessione_Input.getDataSessioneAsSQLDate());
				pstmt.setBoolean(4, Sessione_Input.isIsPratica());
				pstmt.setInt(5, Sessione_Input.getNumero_Adesioni());
				pstmt.setString(6, Sessione_Input.getLinkConferenza());
				pstmt.setString(7, Sessione_Input.getLuogo());
				pstmt.setString(8, tempRicetta.getIDRicetta());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento della nuova sessione: "+ e.getMessage());
				return false;
			}
	}
	public String getIDSessioneDAO(Corso Corso_Input, LocalDate Data_Input) {
		String sql = "SELECT IDSessione FROM Sessione AS S WHERE (S.IDCorso = ?) AND (S.DataSessione = ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getID_Corso());
			pstmt.setDate(2, java.sql.Date.valueOf(Data_Input));
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idsessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'idsessione");
			return null;
		}
	}
	public String getIDCorsoSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT IDCorso FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idsessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del idcorso");
			return null;
		}
	}
	public Date getDataSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT DataSessione FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getDate("dataSessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data della Sessione");
			return null;
		}
	}
	public boolean getIsPraticaSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT ispratica FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getBoolean("ispratica");
	        else 
	            return (Boolean) null;//(TO CHECK)NULL normale non me lo faceva tornare per un booleano, non so se il cast così ritorni null o false
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione di IsPratica");
			return (Boolean) null;
		}
	}
	public int getAdesioniSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT adesioni FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getInt("adesioni");
	        else 
	            return 0;//(TO CHECK) stesso discorso, ma qui non c'è un cast in integer quindi ho optato per 0 che è il caso default
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del numero adesioni");
			return 0;
		}
	}
	public String getLinkConferenzaSessione(Sessione Sessione_Input) {
		String sql = "SELECT linkconferenza FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("linkconferenza");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione di linkconferenza della sessione");
			return null;
		}
	}
	public String getLuogoSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT luogo FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("luogo");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del luogo della sessione");
			return null;
		}
	}
	public String getRicettaSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT IDRicetta FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idricetta");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'IDRicetta");
			return null;
		}
	}
}
