	package DAO;
	
	import Entities.Corso;
	import Entities.Chef;
	import Database.DBManager;
	
	import java.sql.*;
	import java.time.LocalDate;
	import java.util.ArrayList;
	import java.util.List;
	
	public class CorsoDAO {
	
	    public void salva(Corso corso) {
	        String sql = "query SQL";
	        try (Connection conn = DBManager.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	
	            stmt.setString(1, corso.getNome_Corso());
	            stmt.setString(2, corso.getArgomento());
	            stmt.setDate(3, Date.valueOf(corso.getData_Inizio()));
	            stmt.setDate(4, Date.valueOf(corso.getData_Creazione()));
	            stmt.setString(5, corso.getFrequenzaCorsi());
	            stmt.setString(6, corso.getChef_Proprietario().getID_Chef());
	
	            stmt.executeUpdate();
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
