package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Ingrediente;
import Entities.Partecipante;
import Entities.Ricetta;

public class IngredienteDAO {
	
	//METODI 
	//Metodo per inserire un nuovo ingrediente nel DB
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
	
	//Metodo per selezionare l'IDIngrediente in base al nome dell'ingrediente
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
	
	//Metodo per selezionare il nome dell'ingrediente con un oggetto ingrediente
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
	
	//Metodo per selezionare il nome dell'ingrediente con un IDIngrediente
	public String GetNomeIngredienteDAO(String IDIngrediente_Input) {
		String sql = "SELECT nomeingrediente FROM uninafoodlab.ingrediente WHERE idingrediente = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareCall(sql)) {
				pstmt.setString(1, IDIngrediente_Input);
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
	
	//Metodo per eliminare un ingrediente dal database
	public boolean DeleteIngredienteDAO(String IDIngrediente_Input) {
		String sql ="DELETE FROM uninafoodlab.corso WHERE corso.IDcorso = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDIngrediente_Input);
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
			} catch(SQLException e) {
				System.out.println("Errore durante l'eliminazione del partecipante : "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo che restituisce la lista di tutti gli ingredienti 
	public List<Ingrediente> getAllIngredienteDAO() {
		List<Ingrediente> ListaIngredienti = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.ingredienti;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Ingrediente i = new Ingrediente();
					i.setIDIngrediente(rs.getString("idingrediente"));
					i.setNome(rs.getString("nome"));
				}
				return ListaIngredienti;	
			}catch(SQLException e) {
				System.out.println("Errore durante il recupero degli ingredienti: " + e.getMessage());
				return null;
			}
	}
}