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
	
	//metodo per l'inserimento di una nuova iscrizione al corso nel DB usando un oggetto IscrizioneCorso (True se l'inserimento va a buon fine, False altrimenti)
	public boolean insertIscrizioneCorso(IscrizioneCorso IscrizioneCorso_Input) {
		String sql = "INSERT INTO uninafoodlab.iscrizionecorso(idcorso, idpartecipante, datadiiscrizione) VALUES (?, ?, ?)";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				Corso tempCorso = IscrizioneCorso_Input.getC();
				Partecipante tempPartecipante = IscrizioneCorso_Input.getP();
				System.out.println(IscrizioneCorso_Input.getDataIscrizioneAsSQLDate());
				
				pstmt.setString(1, tempCorso.getID_Corso());
				pstmt.setString(2, tempPartecipante.getID_Partecipante());
				pstmt.setDate(3, IscrizioneCorso_Input.getDataIscrizioneAsSQLDate());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento dell'iscrizione al corso : " + e.getMessage());
				return false;
			}
	}
	
	//Metodo per ottenere le iscrizioni ai corsi di un partecipante
	public List<IscrizioneCorso> GetIscrizioniCorsoByPartecipante(Partecipante p) {
		List<IscrizioneCorso> ListaIscrizioniCorso = new ArrayList<>();
		String sql = "SELECT * FROM uninafoolab.iscrizionecorso WHERE idpartecipante = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
				pstmt.setString(1, p.getID_Partecipante());
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					IscrizioneCorso iscrizioneCorso = new IscrizioneCorso();
					iscrizioneCorso.setP(p);
					Corso C = new Corso(rs.getString("idcorso"));
					iscrizioneCorso.setC(C);
					iscrizioneCorso.setData_di_Iscrizione(rs.getDate("datadiiscrizione").toLocalDate());
					ListaIscrizioniCorso.add(iscrizioneCorso);
				}
				
				return ListaIscrizioniCorso;
				
			} catch(SQLException e) {
				System.out.println("Errore nella selezione delle iscrizioni ai corsi: " + e.getMessage());
				return null;
			}
	}
	
	//Metodo per ottenere le iscrizioni ai corsi di un corso
	public List<IscrizioneCorso>GetIscrizioniCorsoByCorso(Corso c) {
		List<IscrizioneCorso> ListaIscrizioniCorso = new ArrayList<>();
		String sql = "SELECT * FROM uninafoolab.iscrizionecorso WHERE idcorso = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
				pstmt.setString(1, c.getID_Corso());
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					IscrizioneCorso iscrizioneCorso = new IscrizioneCorso();
					iscrizioneCorso.setC(c);
					Partecipante p = new Partecipante(rs.getString("idpartecipante"));
					iscrizioneCorso.setP(p);
					iscrizioneCorso.setData_di_Iscrizione(rs.getDate("datadiiscrizione").toLocalDate());
					ListaIscrizioniCorso.add(iscrizioneCorso);
				}
				return ListaIscrizioniCorso;
				
			} catch(SQLException e) {
				System.out.println("Errore nella selezione delle iscrizioni ai corsi: " + e.getMessage());
				return null;
			}
	}
	
	// Metodo per eliminare un'iscrizione al corso dal DB usando un oggetto IscrizioneCorso (True se l'eliminazione va a buon fine, False altrimenti)
	public boolean DeleteIscrizioneCorso(IscrizioneCorso iscrizioneCorso) {
		String sql = "DELETE FROM uninafooddlab.iscrizionecorso WHERE idcorso = ? AND idpartecipante = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, iscrizioneCorso.getC().getID_Corso());
			pstmt.setString(2, iscrizioneCorso.getP().getID_Partecipante());
			
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
			
		} catch(SQLException e) {
			System.out.println("Errore durante l'eliminazione dell'iscrizione al corso: " + e.getMessage());
			return false;
		}
	}
	
	//Metodo per controllare l'iscrizione al corso di un partecipante, restituisce true se il partecipante è iscritto al corso
	public boolean CheckIscrizioneCorso(Partecipante p, Corso c) {
		String sql = "SELECT * FROM uninafoodlab.iscrizionecorso AS ic WHERE ic.idcorso = ? AND ic.idpartecipante = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, c.getID_Corso());
			pstmt.setString(2, p.getID_Partecipante());
			
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next();
		} catch(SQLException e) {
			System.out.println("Errore durante il controllo dell'iscrizione al corso" + e.getMessage());
			return false;
		}
	}
	
}
