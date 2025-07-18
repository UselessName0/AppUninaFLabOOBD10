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
	
	//Metodo per inserire un nuovo corso nel database(True se l'inserimento va a buon fine, False altrimenti)
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
	
	//Metodo per recuperare il nome del corso con un oggetto Corso
	public String getNomeCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT Nomecorso FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare il nome del corso con un IDCorso
	public String getNomeCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT Nomecorso FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare l'argomento del corso con un oggetto Corso
	public String getArgomentoCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT argomento FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare l'argomento del corso con un IDCorso
	public String getArgomentoCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT argomento FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare la data di inizio del corso con un oggetto Corso
	public Date getDataInizioCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT DataInizio FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare la data di inizio del corso con un IDCorso
	public Date getDataInizioCorsoDAO(String IDCorso_Input) {
		Date d;
		String sql = "SELECT DataInizio FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, IDCorso_Input);
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) { 
	        	d = rs.getDate("DataInizio");
	        	if(d == null)
	        		return null;
	        	else
	        		return d; //Decidere se tornare in date normale o in localDate
	        }
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della data di inizio corso");
			return null;
		}
	}
	
	//Metodo per recuperare la data di creazione del corso con un oggetto Corso
	public Date getDataCreazioneCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT datacreazione FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare la data di creazione del corso con un IDCorso
	public Date getDataCreazioneCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT datacreazione FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare la frequenza del corso con un oggetto Corso
	public String getFrequenzaCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT frequenzacorsi FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	//Metodo per recuperare la frequenza del corso con un IDCorso
	public String getFrequenzaCorsoDAO(String IDCorso_Input) {
		String sql = "SELECT frequenzacorsi FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ? ";
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
	
	
	//Metodo per recuperare l'ID del corso con un oggetto Corso
	public String getIDCorsoDAO(Corso Corso_Input) {
		String sql = "SELECT idcorso FROM uninafoodlab.Corso AS Co WHERE (Co.idchef = ?) AND (Co.nomecorso = ?) AND (Co.datacreazione = ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			Chef tempChef = Corso_Input.getChef_Proprietario();
			
			pstmt.setString(1, tempChef.getID_Chef());
			pstmt.setString(2, Corso_Input.getNome_Corso());
			pstmt.setDate(3,   Corso_Input.getDataCreazioneAsSQLDate());
			
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) 
	            return rs.getString("idcorso");
	        else 
	            return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione dell'id corso");
			return null;
		}
	}
	
	//Metodo per recuperare lo chef proprietario con un oggetto Corso
	public String getIDChefProprietarioDAO(Corso Corso_Input) {
		String sql = "SELECT idchef FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ?";
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
	
	//Metodo per recuperare lo chef proprietario con un IDCorso
	public String getIDChefProprietarioDAO(String IDCorso_Input) {
		String sql = "SELECT idchef FROM uninafoodlab.Corso AS Co WHERE Co.idcorso = ?";
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
	
	public String getDescrizioneDAO(Corso Corso_Input) {
		String sql = "SELECT descrizione from uninafoodlab.corso AS co WHERE co.idcorso = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Corso_Input.getID_Corso());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getString("descrizione");
			else
				return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della descrizione del corso");
			return null;
		}
	}
	
	public String getDescrizioneDAO(String idcorso_input) {
		String sql = "SELECT descrizione from uninafoodlab.corso AS co WHERE co.idcorso = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, idcorso_input);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getString("descrizione");
			else
				return null;
			
		} catch(SQLException e) {
			System.out.println("Errore durante la selezione della descrizione del corso");
			return null;
		}
	}
	
	//Metodo per eliminare un corso dal database
	public boolean DeleteCorsoDAO(String IDCorso_Input) {
		String sql ="DELETE FROM uninafoodlab.corso WHERE corso.IDcorso = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDCorso_Input);
				int rowsAffected = pstmt.executeUpdate();
				
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'eliminazione del corso : "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo per aggiornare un corso nel database
	public List<Corso> getAllCorsi() {
		List<Corso> ListaCorsi = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.corso";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				Corso C = new Corso();
				C.setID_Corso(rs.getString("idcorso"));
				Chef Ch = new Chef(rs.getString("idchef"));
				C.setChef_Proprietario(Ch);
				C.setNome_Corso(rs.getString("nomecorso"));
				C.setArgomento(rs.getString("argomento"));
				C.setData_Inizio(rs.getDate("datainizio").toLocalDate());
				C.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				C.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				C.setDescrizione(rs.getString("descrizione"));
				
				ListaCorsi.add(C);
			}
			return ListaCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi: " + e.getMessage());
			return null;
		}
	}
	
	public List<String> getAllidCorsi() {
		List<String> ListaidCorsi = new ArrayList<>();
		String sql = "SELECT idcorso FROM uninafoodlab.corso";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				ListaidCorsi.add(rs.getString("idcorso"));
			}
			return ListaidCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi: " + e.getMessage());
			return null;
		}
	}
	
	//Metodo per recuperare tutti i corsi di un determinato chef
	public List<Corso> getAllCorsiByChef(Chef chef) {
		List<Corso> ListaCorsi = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.corso WHERE idchef = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, chef.getID_Chef());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Corso C = new Corso();
				C.setID_Corso(rs.getString("idcorso"));
				C.setChef_Proprietario(chef);
				C.setNome_Corso(rs.getString("nomecorso"));
				C.setArgomento(rs.getString("argomento"));
				C.setData_Inizio(rs.getDate("datainizio").toLocalDate());
				C.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				C.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				
				ListaCorsi.add(C);
			}
			return ListaCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi per Chef: " + e.getMessage());
			return null;
		}
	}
	
	//Metodo per recuperare tutti i corsi in base al nom
	public List<Corso> getAllCorsiByNome(String nome) {
		List<Corso> ListaCorsi = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.corso WHERE nomecorso LIKE ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Corso C = new Corso();
				C.setID_Corso(rs.getString("idcorso"));
				Chef Ch = new Chef(rs.getString("idchef"));
				C.setChef_Proprietario(Ch);
				C.setNome_Corso(rs.getString("nomecorso"));
				C.setArgomento(rs.getString("argomento"));
				C.setData_Inizio(rs.getDate("datainizio").toLocalDate());
				C.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				C.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				
				ListaCorsi.add(C);
			}
			return ListaCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi per Nome: " + e.getMessage());
			return null;
		}
	}
	
	//Metodo per recuperare tutti i corsi in base all'argomento
	public List<Corso> getAllCorsiByArgomento(String argomento) {
		List<Corso> ListaCorsi = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.Corso WHERE argomento LIKE ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, "%" + argomento + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Corso C = new Corso();
				C.setID_Corso(rs.getString("idcorso"));
				Chef Ch = new Chef(rs.getString("idchef"));
				C.setChef_Proprietario(Ch);
				C.setNome_Corso(rs.getString("nomecorso"));
				C.setArgomento(rs.getString("argomento"));
				C.setData_Inizio(rs.getDate("datainizio").toLocalDate());
				C.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				C.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				
				ListaCorsi.add(C);
			}
			return ListaCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi per Argomento: " + e.getMessage());
			return null;
		}
	}
	
	//Metodo per recuperare tutti i corsi in base alla data di inizio
	public List<Corso> getAllCorsiByDataInizio(LocalDate dataInizio) {
		List<Corso> ListaCorsi = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.Corso WHERE datainizio >= ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setDate(1, Date.valueOf(dataInizio));
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Corso C = new Corso();
				C.setID_Corso(rs.getString("idcorso"));
				Chef Ch = new Chef(rs.getString("idchef"));
				C.setChef_Proprietario(Ch);
				C.setNome_Corso(rs.getString("nomecorso"));
				C.setArgomento(rs.getString("argomento"));
				C.setData_Inizio(rs.getDate("datainizio").toLocalDate());
				C.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				C.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				
				ListaCorsi.add(C);
			}
			return ListaCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi per Data Inizio: " + e.getMessage());
			return null;
		}
	}
	
	public List<Corso> getCorsiDovePartecipanteNonIscrittoDAO(Partecipante p){
		List<Corso> ListaCorsi = new ArrayList<>();
		String sql = "SELECT co.* , ch.* FROM uninafoodlab.corso AS co JOIN uninafoodlab.chef AS ch ON co.idchef = ch.idchef WHERE co.idcorso NOT IN (SELECT idcorso FROM uninafoodlab.iscrizionecorso WHERE idpartecipante = ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, p.getID_Partecipante());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Chef ch = new Chef();
				ch.setID_Chef(rs.getString("idchef"));
				ch.setNome(rs.getString("nomechef"));
				ch.setCognome(rs.getString("cognomechef"));
				ch.setEmail(rs.getString("email"));
				ch.setPassword(rs.getString("pass"));
				Corso co = new Corso();
				co.setID_Corso(rs.getString("idcorso"));
				co.setChef_Proprietario(ch);
				co.setNome_Corso(rs.getString("nomecorso"));
				co.setArgomento(rs.getString("argomento"));
				Date dataInizio = rs.getDate("datainizio");
				if(dataInizio != null)
					co.setData_Inizio(dataInizio.toLocalDate());
				else
					co.setData_Inizio(null);
				co.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				co.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				co.setDescrizione(rs.getString("descrizione"));
				ListaCorsi.add(co);
			}
			return ListaCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi a cui il partecipante non è iscritto " + e.getMessage());
			return null;
		}
	}
	
	public List<Corso> getCorsiDovePartecipanteIscirtto(Partecipante p){
		List<Corso> ListaCorsi = new ArrayList<>();
		String sql = "SELECT co.* , ch.* FROM uninafoodlab.corso AS co JOIN uninafoodlab.chef AS ch ON co.idchef = ch.idchef WHERE co.idcorso IN (SELECT idcorso FROM uninafoodlab.iscrizionecorso WHERE idpartecipante = ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, p.getID_Partecipante());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Chef ch = new Chef();
				ch.setID_Chef(rs.getString("idchef"));
				ch.setNome(rs.getString("nomechef"));
				ch.setCognome(rs.getString("cognomechef"));
				ch.setEmail(rs.getString("email"));
				ch.setPassword(rs.getString("pass"));
				Corso co = new Corso();
				co.setID_Corso(rs.getString("idcorso"));
				co.setChef_Proprietario(ch);
				co.setNome_Corso(rs.getString("nomecorso"));
				co.setArgomento(rs.getString("argomento"));
				Date dataInizio = rs.getDate("datainizio");
				if(dataInizio != null)
					co.setData_Inizio(dataInizio.toLocalDate());
				else
					co.setData_Inizio(null);
				co.setData_Creazione(rs.getDate("datacreazione").toLocalDate());
				co.setFrequenza_Corsi(rs.getString("frequenzacorsi"));
				co.setDescrizione(rs.getString("descrizione"));
				ListaCorsi.add(co);
			}
			return ListaCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi a cui il partecipante è iscritto " + e.getMessage());
			return null;
		}
	}

	public List<String> getIDCorsiDovePartecipanteNonIscrittoDAO(Partecipante p) {
		List<String> ListaidCorsi = new ArrayList<>();
		String sql = "SELECT DISTINCT idcorso FROM uninafoodlab.corso WHERE idcorso NOT in ( SELECT idcorso FROM uninafoodlab.iscrizionecorso WHERE idpartecipante = ?)";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, p.getID_Partecipante());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ListaidCorsi.add(rs.getString("idcorso"));
			}
			return ListaidCorsi;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Corsi a cui il partecipante non è iscritto " + e.getMessage());
			return null;
		}
	}
}
	
