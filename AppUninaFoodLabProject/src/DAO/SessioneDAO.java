package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;
import Entities.Corso;
import Entities.Partecipante;
import Entities.Sessione;
import Entities.Ricetta;

public class SessioneDAO {
	
	//Metodo per l'inserimento di una nuova sessione nel DB usando un oggetto Sessione (True se l'inserimento va a buon fine, False altrimenti)	
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
	
	//Metodo per selezionare l'IDSessione dal DB usando un oggetto Corso e una data
	public String getIDSessioneDAO(Corso Corso_Input, LocalDate Data_Input) {
		String sql = "SELECT IDSessione FROM Sessione AS S WHERE (S.IDCorso = ?) AND (S.DataSessione = ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getID_Corso());
			pstmt.setDate(2, java.sql.Date.valueOf(Data_Input));//fare metodo per convertire LocalDate in java.sql.Date
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idsessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'idsessione");
			return null;
		}
	}
	
	//Metodo per selezionare l'IDSessione dal DB usando un oggetto Sessione
	public String getIDCorsoSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT IDCorso FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idsessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del idcorso");
			return null;
		}
	}
	
	//Metodo per selezionare l'IDCorso a cui appartiene la sessione dal DB usando l'IDSessione
	public String getIDCorsoSessioneDAO(String IDSessione_Input) {
		String sql = "SELECT IDCorso FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDSessione_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idsessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del idcorso");
			return null;
		}
	}
	
	//Metodo per selezionare la data della sessione dal DB usando un oggetto Sessione
	public Date getDataSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT DataSessione FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getDate("dataSessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data della Sessione");
			return null;
		}
	}
	
	//Metodo per selezionare la data della sessione dal DB usando l'IDSessione
	public Date getDataSessioneDAO(String IDSessione_Input) {
		String sql = "SELECT DataSessione FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDSessione_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getDate("dataSessione");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data della Sessione");
			return null;
		}
	}
	
	//Metodo per selezionare se la sessione è pratica o teorica dal DB usando un oggetto Sessione
	public boolean getIsPraticaSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT ispratica FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getBoolean("ispratica");
	        else 
	            return (Boolean) null;//(TO CHECK)NULL normale non me lo faceva tornare per un booleano, non so se il cast così ritorni null o false
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione di IsPratica");
			return (Boolean) null;
		}
	}
	
	//Metodo per selezionare se la sessione è pratica o teorica dal DB usando l'IDSessione
	public boolean getIsPraticaSessioneDAO(String IDSessione_Input) {
		String sql = "SELECT ispratica FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDSessione_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getBoolean("ispratica");
	        else 
	            return (Boolean) null;//(TO CHECK)NULL normale non me lo faceva tornare per un booleano, non so se il cast così ritorni null o false
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione di IsPratica");
			return (Boolean) null;
		}
	}
	
	//Metodo per selezionare il numero di adesioni alla sessione dal DB usando un oggetto Sessione
	public int getAdesioniSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT adesioni FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getInt("adesioni");
	        else 
	            return 0;//(TO CHECK) stesso discorso, ma qui non c'è un cast in integer quindi ho optato per 0 che è il caso default
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del numero adesioni");
			return 0;
		}
	}
	
	//Metodo per selezionare il numero di adesioni alla sessione dal DB usando l'IDSessione
	public int getAdesioniSessioneDAO(String IDSessione_Input) {
		String sql = "SELECT adesioni FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDSessione_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getInt("adesioni");
	        else 
	            return 0;//(TO CHECK) stesso discorso, ma qui non c'è un cast in integer quindi ho optato per 0 che è il caso default
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del numero adesioni");
			return 0;
		}
	}
	
	//Metodo per selezionare il link della conferenza della sessione dal DB usando un oggetto Sessione
	public String getLinkConferenzaSessione(Sessione Sessione_Input) {
		String sql = "SELECT linkconferenza FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("linkconferenza");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione di linkconferenza della sessione");
			return null;
		}
	}
	
	//Metodo per selezionare il link della conferenza della sessione dal DB usando l'IDSessione
	public String getLinkConferenzaSessione(String IDSessione_Input) {
		String sql = "SELECT linkconferenza FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDSessione_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("linkconferenza");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione di linkconferenza della sessione");
			return null;
		}
	}
	
	//Metodo per selezionare il luogo della sessione dal DB usando un oggetto Sessione
	public String getLuogoSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT luogo FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("luogo");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del luogo della sessione");
			return null;
		}
	}
	
	//Metodo per selezionare il luogo della sessione dal DB usando l'IDSessione
	public String getLuogoSessioneDAO(String IDSessione_Input) {
		String sql = "SELECT luogo FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDSessione_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("luogo");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione del luogo della sessione");
			return null;
		}
	}
	
	//Metodo per selezionare l'IDRicetta della sessione dal DB usando un oggetto Sessione
	public String getRicettaSessioneDAO(Sessione Sessione_Input) {
		String sql = "SELECT IDRicetta FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Sessione_Input.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idricetta");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'IDRicetta");
			return null;
		}
	}
	
	//Metodo per selezionare l'IDRicetta della sessione dal DB usando l'IDSessione
	public String getRicettaSessioneDAO(String IDSessione_Input) {
		String sql = "SELECT IDRicetta FROM Sessione AS S WHERE S.IDSessione= ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDSessione_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idricetta");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'IDRicetta");
			return null;
		}
	}
	
	//Metodo per eliminare una sessione dal DB usando un oggetto Sessione (True se l'eliminazione va a buon fine, False altrimenti)
	public boolean DeleteSessioneDAO(String IDSessione_Input) {
		String sql ="DELETE FROM uninafoodlab.sessione WHERE sessione.IDSessione = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDSessione_Input);
				int rowsAffected = pstmt.executeUpdate();
				
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'eliminazione della sessione : "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo per recuperare tutte le sessioni dal DB
	public List<Sessione> getAllSessioniDAO() {
		List<Sessione> ListaSessioni = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.sessioni;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Sessione s = new Sessione();
					
					s.setID_Sessione(rs.getString("idsessione"));
					
					Corso c = new Corso(rs.getString("idcorso"));	
					s.setRelatedCorso(c);
					s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
					s.setIsPratica(rs.getBoolean("ispratica"));
					s.setNumero_Adesioni(rs.getInt("adesioni"));
					s.setLinkConferenza(rs.getString("linkconferenza"));
					s.setLuogo(rs.getString("luogo"));
					Ricetta r = new Ricetta(rs.getString("idricetta"));
					s.setRicetta_Appresa(r);
	
				}
				return ListaSessioni;	
			}catch(SQLException e) {
				System.out.println("Errore durante il recupero delle sessioni: " + e.getMessage());
				return null;
			}
	}
	
	//Metodo per recuperare tutte le sessioni di un corso specifico dal DB
	public List<Sessione> getAllSessioniCorsoDAO(Corso Corso_Input) {
		List<Sessione> ListaSessioni = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.sessioni WHERE idcorso = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getID_Corso());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Sessione s = new Sessione();
				
				s.setID_Sessione(rs.getString("idsessione"));
				s.setRelatedCorso(Corso_Input);
				s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
				s.setIsPratica(rs.getBoolean("ispratica"));
				s.setNumero_Adesioni(rs.getInt("adesioni"));
				s.setLinkConferenza(rs.getString("linkconferenza"));
				s.setLuogo(rs.getString("luogo"));
				Ricetta r = new Ricetta(rs.getString("idricetta"));
				s.setRicetta_Appresa(r);
				
				ListaSessioni.add(s);
			}
			return ListaSessioni;	
		}catch(SQLException e) {
			System.out.println("Errore durante il recupero delle sessioni: " + e.getMessage());
			return null;
		}
	}
	
	public List<Sessione> getAllSessioniCorsoDAObyDate(LocalDate Date_Input) {
		List<Sessione> ListaSessioni = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.sessioni WHERE datasessione >= ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setDate(1, java.sql.Date.valueOf(Date_Input));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Sessione s = new Sessione();
				
				s.setID_Sessione(rs.getString("idsessione"));
				
				Corso c = new Corso(rs.getString("idcorso"));
				s.setRelatedCorso(c);
				s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
				s.setIsPratica(rs.getBoolean("ispratica"));
				s.setNumero_Adesioni(rs.getInt("adesioni"));
				s.setLinkConferenza(rs.getString("linkconferenza"));
				s.setLuogo(rs.getString("luogo"));
				Ricetta r = new Ricetta(rs.getString("idricetta"));
				s.setRicetta_Appresa(r);
				
				ListaSessioni.add(s);
			}
			return ListaSessioni;	
		}catch(SQLException e) {
			System.out.println("Errore durante il recupero delle sessioni: " + e.getMessage());
			return null;
		}
	}
	
	public List<Sessione> getAllSessioniCorsoDAObyLuogo(String Luogo_Input) {
		List<Sessione> ListaSessioni = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.sessioni WHERE luogo = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Luogo_Input);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Sessione s = new Sessione();
				
				s.setID_Sessione(rs.getString("idsessione"));
				
				Corso c = new Corso(rs.getString("idcorso"));
				s.setRelatedCorso(c);
				s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
				s.setIsPratica(rs.getBoolean("ispratica"));
				s.setNumero_Adesioni(rs.getInt("adesioni"));
				s.setLinkConferenza(rs.getString("linkconferenza"));
				s.setLuogo(rs.getString("luogo"));
				Ricetta r = new Ricetta(rs.getString("idricetta"));
				s.setRicetta_Appresa(r);
				
				ListaSessioni.add(s);
			}
			return ListaSessioni;	
		}catch(SQLException e) {
			System.out.println("Errore durante il recupero delle sessioni: " + e.getMessage());
			return null;
		}
	}
	
	public List<Sessione> getAllSessioniCorsoDAObyRicetta(Ricetta Ricetta_Input) {
		List<Sessione> ListaSessioni = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.sessioni WHERE idricetta = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Ricetta_Input.getIDRicetta());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Sessione s = new Sessione();
				
				s.setID_Sessione(rs.getString("idsessione"));
				
				Corso c = new Corso(rs.getString("idcorso"));
				s.setRelatedCorso(c);
				s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
				s.setIsPratica(rs.getBoolean("ispratica"));
				s.setNumero_Adesioni(rs.getInt("adesioni"));
				s.setLinkConferenza(rs.getString("linkconferenza"));
				s.setLuogo(rs.getString("luogo"));
				Ricetta r = new Ricetta(rs.getString("idricetta"));
				s.setRicetta_Appresa(r);
				
				ListaSessioni.add(s);
			}
			return ListaSessioni;	
		}catch(SQLException e) {
			System.out.println("Errore durante il recupero delle sessioni: " + e.getMessage());
			return null;
		}
	}
}
