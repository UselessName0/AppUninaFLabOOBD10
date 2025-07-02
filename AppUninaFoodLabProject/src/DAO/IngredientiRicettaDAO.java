package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.IngredientiRicetta;
import Entities.Ricetta;
import Entities.Ingrediente;

public class IngredientiRicettaDAO {
	
	public boolean insertIngredientiRicetta(IngredientiRicetta IngredientiRicetta_Input) {
		String sql = "INSERT INTO ingredientiricetta(idricetta, idingrediente, quantita, unitadimisura) VALUES (?, ?, ?, ?)";
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

}
