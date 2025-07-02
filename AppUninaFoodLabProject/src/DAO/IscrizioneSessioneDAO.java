package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.IscrizioneSessione;
import Entities.Sessione;
import Entities.Partecipante; 

public class IscrizioneSessioneDAO {
	
	public boolean insertIscrizioneSessione(IscrizioneSessione IscrizioneSessione_Input) {
		String sql = "INSERT INTO uninafoodlab.iscrizionesessione(idpartecipante, idsessione, adesione) VALUES (?, ?, ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			Partecipante tempPartecipante = IscrizioneSessione_Input.getP();
			Sessione tempSessione = IscrizioneSessione_Input.getS();
			
			pstmt.setString(1, tempPartecipante.getID_Partecipante());
			pstmt.setString(2, tempSessione.getID_Sessione());
			pstmt.setBoolean(3, IscrizioneSessione_Input.isAdesione());
			
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
			
		} catch(SQLException e) {
			System.out.println("Errore durante l'inserimento di un'iscrizione alla sessione");
			return false;
		}
	}
}
