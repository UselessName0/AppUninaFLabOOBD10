package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.IscrizioneSessione;
import Entities.Sessione;
import Entities.Partecipante; 

public class IscrizioneSessioneDAO {
	
	//METODI
	// Metodo per l'inserimento di una nuova iscrizione alla sessione nel DB
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
	
	// Metodo per ottenere le iscrizioni alle sessioni di un partecipante
	public List<IscrizioneSessione> GetIscrizioniSessioniByPartecipante(Partecipante p) {
		List<IscrizioneSessione> ListaIscrizioni = new ArrayList<>();
		String sql = "SELECT * FROM uninafooddlab.iscrizionesessione WHERE idpartecipante = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, p.getID_Partecipante());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					IscrizioneSessione is = new IscrizioneSessione();
					is.setP(p);
					Sessione s = new Sessione(rs.getString("idsessione"));
					is.setS(s);
					is.setAdesione(rs.getBoolean("adesione"));
					ListaIscrizioni.add(is);
				}
				return ListaIscrizioni;
		}catch(SQLException e) {
			System.out.println("Errore nella selezione delle iscrizioni sessioni"+ e.getMessage());
			return null;
		}
	}
	
	// Metodo per ottenere le iscrizioni alle sessioni di una sessione
	public List<IscrizioneSessione> GetIscrizioniSessioniBySessione(Sessione s) {
		List<IscrizioneSessione> ListaIscrizioni = new ArrayList<>();
		String sql = "SELECT * FROM uninafooddlab.iscrizionesessione WHERE idsessione = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, s.getID_Sessione());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					IscrizioneSessione is = new IscrizioneSessione();
					is.setS(s);
					Partecipante p = new Partecipante(rs.getString("idpartecipante"));
					is.setP(p);
					is.setAdesione(rs.getBoolean("adesione"));
					ListaIscrizioni.add(is);
				}
				return ListaIscrizioni;
				}catch(SQLException e) {
					System.out.println("Errore nella selezione delle iscrizioni sessioni"+ e.getMessage());
					return null;
				}
	}
}