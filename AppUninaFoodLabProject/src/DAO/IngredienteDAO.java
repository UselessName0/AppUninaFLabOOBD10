package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Ingrediente;
import Entities.Ricetta;

public class IngredienteDAO {
	public boolean InsertIngrediente(Ingrediente Ingrediente_Input) {
		String sql = "INSERT INTO uninafoodlab.ricetta(idricetta, nominativoricetta)VALUES (?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Ingrediente_Input.getIDIngrediente());
				pstmt.setString(2, Ingrediente_Input.getNome());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento del nuovo ingrediente: "+ e.getMessage());
				return false;	
			}
	}
	
	public String GetIDIngredienteDAO(String NomeIngrediente){
		String sql = "SELECT IDIngrediente FROM uninafoodlab.ingrediente WHERE nomeingrediente = ?;";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, NomeIngrediente);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("idingrediente");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public String GetNomeIngredienteDAO(Ingrediente Ingrediente_Input) {
		String sql = "SELECT nomeingrediente FROM uninafoodlab.ingrediente WHERE idingrediente = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareCall(sql)) {
				
				pstmt.setString(1, Ingrediente_Input.getIDIngrediente());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("nomeingrediente");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}	
	}
}
