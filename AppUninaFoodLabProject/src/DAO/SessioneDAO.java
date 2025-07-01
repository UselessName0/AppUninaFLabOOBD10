package DAO;

import java.sql.*;
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
}
