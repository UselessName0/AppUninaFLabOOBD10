package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.IngredienteRicetta;
import Entities.Ricetta;
import Entities.Ingrediente;

public class IngredienteRicettaDAO {
	
	//METODI
	//Metodo per l'inserimento di un nuovo ingrediente in una ricetta nel DB
	public boolean insertIngredientiRicetta(IngredienteRicetta IngredientiRicetta_Input) {
		String sql = "INSERT INTO uninafoodlab.ingredientiricetta(idricetta, idingrediente, quantita, unitadimisura) VALUES (?, ?, ?, ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			Ricetta tempRicetta = IngredientiRicetta_Input.getR();
			Ingrediente tempIngrediente = IngredientiRicetta_Input.getI();		
			pstmt.setString(1, tempRicetta .getIDRicetta());
			pstmt.setString(2, tempIngrediente.getIDIngrediente());
			pstmt.setFloat(3, IngredientiRicetta_Input.getQuantità());
			pstmt.setString(4, IngredientiRicetta_Input.getUnitaDiMisura());
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch(SQLException e) {
			System.out.println("Errore nell'inserimento di un ingrediente per la ricetta");
			return false;
		}
	}
	
	//Metodo per ottenere una lista di ingredienti associati a una ricetta specifica
	public List<IngredienteRicetta> getIngredientiRicettabyRicetta(Ricetta R) {
		List<IngredienteRicetta> ingredientiList = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.ingredientiricetta WHERE idricetta = ?";	
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, R.getIDRicetta());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				IngredienteRicetta ingredienteRicetta = new IngredienteRicetta();
				ingredienteRicetta.setQuantità(rs.getFloat("quantita"));
				ingredienteRicetta.setUnitaDiMisura(rs.getString("unitadimisura"));
				Ricetta ricetta = new Ricetta();
				ricetta.setIDRicetta(rs.getString("r"));
				ingredienteRicetta.setR(ricetta);
				Ingrediente ingrediente = new Ingrediente();
				ingrediente.setIDIngrediente(rs.getString("idingrediente"));
				ingredienteRicetta.setI(ingrediente);
				ingredientiList.add(ingredienteRicetta);
			}
		} catch(SQLException e) {
			System.out.println("Errore nel recupero degli ingredienti per la ricetta con ID: " + e.getMessage());
		}
		return ingredientiList;
	}
	
	//Metodo per ottenere una lista di ricette associate a un ingrediente specifico
	public List<IngredienteRicetta> getIngredientiRicettabyIngrediente(Ingrediente I) {
		List<IngredienteRicetta> ListaIngredienti = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.ingredientiricetta WHERE idingrediente = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, I.getIDIngrediente());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				IngredienteRicetta ingredienteRicetta = new IngredienteRicetta();
				ingredienteRicetta.setQuantità(rs.getFloat("quantita"));
				ingredienteRicetta.setUnitaDiMisura(rs.getString("unitadimisura"));
				ingredienteRicetta.setI(I);
				Ricetta ricetta = new Ricetta();
				ricetta.setIDRicetta(rs.getString("r"));
				ingredienteRicetta.setR(ricetta);
				ListaIngredienti.add(ingredienteRicetta);
			}
		} catch(SQLException e) {
			System.out.println("Errore nel recupero degli ingredienti per la ricetta con ID: " + e.getMessage());
		}
		return ListaIngredienti;
	}
	
	//Metodo per eliminare un ingrediente da una ricetta nel DB usando
	public boolean DeleteIngredientiRicetta(IngredienteRicetta IR) {
		String sql = "DELETE FROM uninafoodlab.ingredientiricetta WHERE idricetta = ? AND idingrediente = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, IR.getR().getIDRicetta());
			pstmt.setString(2, IR.getI().getIDIngrediente());
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch(SQLException e) {
			System.out.println("Errore nella cancellazione dell'ingrediente dalla ricetta: " + e.getMessage());
			return false;
		}
	}
}