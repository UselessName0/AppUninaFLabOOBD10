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
		String sql = "INSERT INTO uninafoodlab.sessione(idsessione, idcorso, datasessione, \"IsPratica\", adesioni, linkconferenza, luogo, idricetta) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
		String sql = "SELECT IDSessione FROM uninafoodlab.Sessione AS S WHERE (S.IDCorso = ?) AND (S.DataSessione = ?)";
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
		String sql = "SELECT IDCorso FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT IDCorso FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT DataSessione FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT DataSessione FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT ispratica FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT ispratica FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT adesioni FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT adesioni FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT linkconferenza FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT linkconferenza FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT luogo FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT luogo FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT IDRicetta FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT IDRicetta FROM uninafoodlab.Sessione AS S WHERE S.IDSessione= ? ";
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
		String sql = "SELECT co.* , s.* , r.* FROM uninafoodlab.sessione AS s LEFT JOIN uninafoodlab.iscrizionesessione AS ic ON s.idsessione = ic.idsessione JOIN uninafoodlab.corso AS co ON s.idcorso = co.idcorso JOIN uninafoodlab.ricetta AS r ON r.idricetta = s.idricetta WHERE s.datasessione > ? ORDER BY s.datasessione;";
		System.out.println("Recupero sessioni in corso...");
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet rs = pstmt.executeQuery(); 
			
				while(rs.next()) {
					Sessione s = new Sessione();
					
					s.setID_Sessione(rs.getString("idsessione"));
					
					Corso co = new Corso();
					co.setID_Corso(rs.getString("idcorso"));
					co.setNome_Corso(rs.getString("nomecorso"));
					co.setArgomento(rs.getString("argomento"));
					co.setData_Inizio(rs.getDate("datainizio").toLocalDate());
					co.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
					co.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
					co.setDescrizione(rs.getString("descrizione"));
					
					s.setRelatedCorso(co);
					s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
					s.setIsPratica(rs.getBoolean("IsPratica"));
					s.setNumero_Adesioni(rs.getInt("adesioni"));
					s.setLinkConferenza(rs.getString("linkconferenza"));
					s.setLuogo(rs.getString("luogo"));
					
					Ricetta r = new Ricetta();
					r.setIDRicetta(rs.getString("idricetta"));
					r.setTitolo(rs.getString("nominativoricetta"));
					r.setDescrizione(rs.getString("descrizione"));
					
					s.setRicetta_Appresa(r);
					System.out.println("Sessione recuperata: " + s.getID_Sessione() + " - " + s.getData_Sessione());
					ListaSessioni.add(s);
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
		String sql = "SELECT * FROM uninafoodlab.sessione WHERE idcorso = ?;";
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
		String sql = "SELECT * FROM uninafoodlab.sessione WHERE datasessione >= ?;";
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
		String sql = "SELECT * FROM uninafoodlab.sessione WHERE luogo = ?;";
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
		String sql = "SELECT * FROM uninafoodlab.sessione WHERE idricetta = ?;";
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
	
	public List<Sessione> getSessioniDiPartecipante(Partecipante p) {
		List<Sessione> ListaSessioni = new ArrayList<>();
		String sql = "SELECT co.* , s.* , r.* FROM uninafoodlab.sessione AS s JOIN uninafoodlab.iscrizionesessione AS ic ON s.idsessione = ic.idsessione JOIN uninafoodlab.corso AS co ON s.idcorso = co.idcorso JOIN uninafoodlab.ricetta AS r ON r.idricetta = s.idricetta WHERE ic.idpartecipante = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, p.getID_Partecipante());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Sessione s = new Sessione();
				
				s.setID_Sessione(rs.getString("idsessione"));
				
				Corso co = new Corso();
				co.setID_Corso(rs.getString("idcorso"));
				co.setNome_Corso(rs.getString("nomecorso"));
				co.setArgomento(rs.getString("argomento"));
				co.setData_Inizio(rs.getDate("datainizio").toLocalDate());
				co.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				co.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				co.setDescrizione("descrizione");
				
				s.setRelatedCorso(co);
				s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
				s.setIsPratica(rs.getBoolean("isPratica"));
				s.setNumero_Adesioni(rs.getInt("adesioni"));
				s.setLinkConferenza("linkconferenza");
				s.setLuogo("luogo");
				
				Ricetta r = new Ricetta();
				r.setIDRicetta(rs.getString("idricetta"));
				r.setTitolo(rs.getString("nominativoricetta"));
				
				s.setRicetta_Appresa(r);
				
				ListaSessioni.add(s);
			}
			return ListaSessioni;
		}catch(SQLException e) {
			System.out.println("Errore durante il recupero delle sessioni del partecipante: " + e.getMessage());
			return null;
		}
	}
	
	public List<Sessione> getAllSessioniDiChef(Chef c){
		List<Sessione> ListaSessioni = new ArrayList<>();
		String sql = "SELECT s.*, co.*, r.* FROM uninafoodlab.sessione JOIN uninafoodlab.corso AS co ON s.idcorso = co.idcorso JOIN uninafoodlab.ricetta AS r ON r.idricetta = s.idricetta WHERE co.idchef = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, c.getID_Chef());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Sessione s = new Sessione();
				
				s.setID_Sessione(rs.getString("idsessione"));
				
				Corso co = new Corso();
				co.setID_Corso(rs.getString("idcorso"));
				co.setNome_Corso(rs.getString("nomecorso"));
				co.setArgomento(rs.getString("argomento"));
				co.setData_Inizio(rs.getDate("datainizio").toLocalDate());
				co.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				co.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				co.setDescrizione(rs.getString("descrizione"));
				co.setChef_Proprietario(c);
				s.setRelatedCorso(co);
				s.setData_Sessione(rs.getDate("datasessione").toLocalDate());
				s.setIsPratica(rs.getBoolean("ispratica"));
				s.setNumero_Adesioni(rs.getInt("adesioni"));
				s.setLinkConferenza(rs.getString("linkconferenza"));
				s.setLuogo(rs.getString("luogo"));
				Ricetta r = new Ricetta();
				r.setIDRicetta(rs.getString("idricetta"));
				r.setTitolo(rs.getString("nominativoricetta"));
				s.setRicetta_Appresa(r);
				
				ListaSessioni.add(s);
			}
			return ListaSessioni;
	}catch(SQLException e) {
			System.out.println("Errore durante il recupero delle sessioni dello chef: " + e.getMessage());
			return null;
		}
	}

	public int[] getNumeroSessioniByMonth(Chef c) {
		String sql = "SELECT date_trunc('month', sessione.datasessione) AS mese, COUNT(*) AS nsessioni FROM uninafoodlab.sessione JOIN uninafoodlab.corso ON sessione.idcorso = corso.idcorso WHERE corso.idchef = ? AND sessione.datasessione >= CURRENT_DATE - INTERVAL '12 months' GROUP BY mese ORDER BY mese;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, c.getID_Chef());
			
			ResultSet rs = pstmt.executeQuery();
			
			int[] sessionCounts = new int[12];
			while(rs.next()) {
				LocalDate month = rs.getDate("mese").toLocalDate();
				int monthIndex = month.getMonthValue() - 1; // Convert month to 0-based index
				sessionCounts[monthIndex] = rs.getInt("nsessioni");
			}
			return sessionCounts;
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero del numero di sessioni per mese: " + e.getMessage());
			return new int[12]; // Return an array of zeros if there's an error
		}
	}

	public int getNumeroIscrittiSessione(Sessione s) {
		String sql = "SELECT COUNT(*) AS nIscritti FROM uninafoodlab.iscrizionesessione WHERE idsessione = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, s.getID_Sessione());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("nIscritti");
			} else {
				return 0;
			}
			} catch(SQLException e) {
				System.out.println("Errore durante il recupero del numero di iscritti alla sessione: " + e.getMessage());
				return 0;
		}
	}

	public boolean UpdateSessione(Sessione s) {
		String sql = "UPDATE uninafoodlab.sessione SET idcorso = ?, datasessione = ?, ispratica = ?, adesioni = ?, linkconferenza = ?, luogo = ?, idricetta = ? WHERE idsessione = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, s.getRelatedCorso().getID_Corso());
			pstmt.setDate(2, java.sql.Date.valueOf(s.getData_Sessione()));
			pstmt.setBoolean(3, s.isIsPratica());
			pstmt.setInt(4, s.getNumero_Adesioni());
			pstmt.setString(5, s.getLinkConferenza());
			pstmt.setString(6, s.getLuogo());
			pstmt.setString(7, s.getRicetta_Appresa().getIDRicetta());
			pstmt.setString(8, s.getID_Sessione());
			
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
			
		} catch(SQLException e) {
			System.out.println("Errore durante l'aggiornamento della sessione: " + e.getMessage());
			return false;
		}
	}
}
