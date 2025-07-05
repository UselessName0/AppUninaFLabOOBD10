package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.IscrizioneCorso;
import Entities.IscrizioneSessione;
import Entities.Corso;
import Entities.Partecipante;
import Entities.Sessione;

public class IscrizioneCorsoDAO {
	
	public boolean insertIscrizioneCorso(IscrizioneCorso IscrizioneCorso_Input) {
		String sql = "INSERT INTO uninafooddlab.iscrizionecorso(idcorso, idpartecipante, datadiiscrizione) VALUES (?, ?, ?)";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				Corso tempCorso = IscrizioneCorso_Input.getC();
				Partecipante tempPartecipante = IscrizioneCorso_Input.getP();
				
				pstmt.setString(1, tempCorso.getID_Corso());
				pstmt.setString(2, tempPartecipante.getID_Partecipante());
				pstmt.setDate(3, IscrizioneCorso_Input.getDataIscrizioneAsSQLDate());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento dell'iscrizione al corso!");
				return false;
			}
	}
	
				

	}
	
	
}
