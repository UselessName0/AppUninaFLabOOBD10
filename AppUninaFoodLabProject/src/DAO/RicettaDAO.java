package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;
import Entities.Partecipante;
import Entities.Ricetta;

public class RicettaDAO {
	public boolean InsertRicetta(Ricetta Ricetta_Input) {
		String sql = "INSERT INTO uninafoodlab.ricetta(idricetta, nominativoricetta)VALUES (?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Ricetta_Input.getIDRicetta());
				pstmt.setString(2, Ricetta_Input.getTitolo());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento della nuova ricetta: "+ e.getMessage());
				return false;
			}
	}
	
	public String GetIDRicettaDAO(String Titolo){
		String sql = "SELECT IDRicetta FROM uninafoodlab.ricetta WHERE nominativoricetta = ?,";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Titolo);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("idricetta");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public String GetTitoloRicettaDAO(Ricetta Ricetta_Input){
		String sql = "SELECT IDRicetta FROM uninafoodlab.ricetta WHERE nominativoricetta = ?,";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Ricetta_Input.getIDRicetta());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("titolo");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public String GetTitoloRicettaDAO(String IDRicetta_Input){
		String sql = "SELECT IDRicetta FROM uninafoodlab.ricetta WHERE nominativoricetta = ?,";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDRicetta_Input);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("titolo");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public boolean DeleteRicettaDAO(String IDRicetta_Input) {
		String sql ="DELETE FROM uninafoodlab.ricetta WHERE ricetta.idricetta = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDRicetta_Input);
				int rowsAffected = pstmt.executeUpdate();
				
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'eliminazione della ricetta : "+ e.getMessage());
				return false;
			}
	}
	
	public List<Ricetta> GetAllRicettaDAO() {
		List<Ricetta> ListaRicette = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.ricetta;";
		try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
					while(rs.next()) {
						Ricetta R = new Ricetta(); 
						R.setIDRicetta(rs.getString("idricetta"));
						R.setTitolo(rs.getString("nominativoricetta"));
					}
					return ListaRicette;	
				}catch(SQLException e) {
					System.out.println("Errore durante il recupero delle ricette: " + e.getMessage());
					return null;
				}
	}
}