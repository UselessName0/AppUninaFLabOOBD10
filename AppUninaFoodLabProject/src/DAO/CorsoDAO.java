package DAO;
	
import Entities.Corso;
import Entities.Partecipante;
import Entities.Chef;
import Database.DBManager;
	
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
	
public class CorsoDAO {
		
	public boolean InsertCorso(Corso Corso_Input) {
		String sql = "INSERT INTO uninafoodlab.corso(idcorso, idchef, nomecorso, argomento, datainizio, datacreazione, frequenzacorsi)VALUES (?, ?, ?, ?, ?, ?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				Chef tempChef = Corso_Input.getChef_Proprietario();
				
				pstmt.setString(1, Corso_Input.getID_Corso());
				pstmt.setString(2, tempChef.getID_Chef());
				pstmt.setString(3, Corso_Input.getNome_Corso());
				pstmt.setString(4, Corso_Input.getArgomento());
				pstmt.setDate(5, Corso_Input.getDataInizioAsSQLDate()); 
				pstmt.setDate(6, Corso_Input.getDataCreazioneAsSQLDate());
				pstmt.setString(7, Corso_Input.getFrequenza_Corsi());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento del nuovo corso: "+ e.getMessage());
				return false;
			}
	}
	
	public String getNomeCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT Nomecorso FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getNome_Corso());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("Nomecorso");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del nome corso");
			return null;
		}
	}
	
	public String getNomeCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT Nomecorso FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDCorso_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("Nomecorso");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del nome corso");
			return null;
		}
	}
	
	public String getArgomentoCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT argomento FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getNome_Corso());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("argomento");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'argomento del corso");
			return null;
		}
	}
	
	public String getArgomentoCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT argomento FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDCorso_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("argomento");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'argomento del corso");
			return null;
		}
	}
	
	public Date getDataInizioCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT DataInizio FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getNome_Corso());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getDate("DataInizio");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data di inizio corso");
			return null;
		}
	}
	
	public Date getDataInizioCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT DataInizio FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDCorso_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getDate("DataInizio");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data di inizio corso");
			return null;
		}
	}
	
	public Date getDataCreazioneCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT datacreazione FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getNome_Corso());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getDate("datacreazione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data di creazione del corso");
			return null;
		}
	}
	
	public Date getDataCreazioneCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT datacreazione FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDCorso_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getDate("datacreazione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data di creazione del corso");
			return null;
		}
	}
	
	public String getFrequenzaCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT frequenzacorsi FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getNome_Corso());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("frequenzacorsi");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della frequenza dei corsi");
			return null;
		}
	}
	public String getFrequenzaCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT frequenzacorsi FROM Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDCorso_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("frequenzacorsi");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della frequenza dei corsi");
			return null;
		}
	}
	
	public String getIDCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT idcorso FROM Corso AS Co WHERE (Co.idchef = ?) AND (Co.nomecorso = ?) AND (Co.datacreazione = ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			Chef tempChef = Corso_Input.getChef_Proprietario();
			
			pstmt.setString(1, tempChef.getID_Chef());
			pstmt.setString(2, Corso_Input.getNome_Corso());
			pstmt.setDate(3,   Corso_Input.getDataCreazioneAsSQLDate());
			
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("Nomecorso");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'id corso");
			return null;
		}
	}
	
	public String getIDChefProprietarioDAO(Corso Corso_Input) {
		String sql = "SELECT idchef FROM Corso AS Co WHERE Co.idcorso = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getID_Corso());
			
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idchef");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'id chef proprietario del corso");
			return null;
		}
	}
	public String getIDChefProprietarioDAO(String IDCorso_Input) {
		String sql = "SELECT idchef FROM Corso AS Co WHERE Co.idcorso = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDCorso_Input);
			
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idchef");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'id chef proprietario del corso");
			return null;
		}
	}
	

}
	
