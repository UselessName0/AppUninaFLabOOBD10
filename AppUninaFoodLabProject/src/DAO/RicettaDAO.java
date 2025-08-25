package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;
import Entities.Partecipante;
import Entities.Ricetta;

public class RicettaDAO {
	
	//METODI
	//Metodo per l'inserimento di una nuova ricetta nel DB
	public boolean InsertRicettaDAO(Ricetta Ricetta_Input) {
		String sql = "INSERT INTO uninafoodlab.ricetta(idricetta, nominativoricetta, descrizione)VALUES (?, ?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, Ricetta_Input.getIDRicetta());
				pstmt.setString(2, Ricetta_Input.getTitolo());
				pstmt.setString(3, Ricetta_Input.getDescrizione());				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento della nuova ricetta: "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo per selezionare l'IDicetta dal DB usando un titolo 
	public String GetIDRicettaDAO(String Titolo){
		String sql = "SELECT IDRicetta FROM uninafoodlab.ricetta WHERE nominativoricetta = ?;";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, Titolo);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("idricetta");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	//Metodo per selezionare il titolo di una ricetta dal DB usando un oggetto Ricetta 
	public String GetTitoloRicettaDAO(Ricetta Ricetta_Input){
		String sql = "SELECT IDRicetta FROM uninafoodlab.ricetta WHERE nominativoricetta = ?;";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, Ricetta_Input.getIDRicetta());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("titolo");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	//Metodo per selezionare il titolo di una ricetta dal DB usando l'IDRicetta 
	public String GetTitoloRicettaDAO(String IDRicetta_Input){
		String sql = "SELECT IDRicetta FROM uninafoodlab.ricetta WHERE nominativoricetta = ?;";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDRicetta_Input);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getString("titolo");
				else
					return null;
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	//Metodo per eliminare una ricetta dal DB usando l'IDRicetta
	public boolean DeleteRicettaDAO(String IDRicetta_Input) {
		String sql ="DELETE FROM uninafoodlab.ricetta WHERE ricetta.idricetta = ?;";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDRicetta_Input);
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
			} catch(SQLException e) {
				System.out.println("Errore durante l'eliminazione della ricetta : "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo per selezionare tutte le ricette dal DB e restituirle come una lista di oggetti Ricetta
	public List<Ricetta> GetAllRicettaDAO() {
		List<Ricetta> ListaRicette = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.ricetta;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
					while(rs.next()) {
						Ricetta R = new Ricetta(); 
						R.setIDRicetta(rs.getString("idricetta"));
						R.setTitolo(rs.getString("nominativoricetta"));
						R.setDescrizione(rs.getString("descrizione"));
						ListaRicette.add(R);
					}
					return ListaRicette;	
				}catch(SQLException e) {
					System.out.println("Errore durante il recupero delle ricette: " + e.getMessage());
					return null;
				}
	}
	
	public List<Ricetta> GetAllRicettaImparateDaPartecipante(Partecipante p) { //PRENDO SOLO LE RICETTE DELLE SESSIONI AVVENUTE PRIMA DI OGGI
		List<Ricetta> ListaRicette = new ArrayList<>();
		String sql = "SELECT r.* FROM uninafoodlab.ricetta AS r JOIN uninafoodlab.sessione AS s ON r.idricetta = s.idricetta JOIN uninafoodlab.iscrizionesessione AS ises ON ises.idsessione = s.idsessione WHERE ises.idpartecipante = ? AND s.datasessione < ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setString(1, p.getID_Partecipante());
				pstmt.setDate(2, Date.valueOf(LocalDate.now()));
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) {
					Ricetta R = new Ricetta();
					R.setIDRicetta(rs.getString("idricetta"));
					R.setTitolo(rs.getString("nominativoricetta"));
					R.setDescrizione(rs.getString("descrizione"));
					ListaRicette.add(R);
					}
				return ListaRicette;
			}catch(SQLException e) {
				System.out.println("Errore durante il recupero delle ricette del partecipante : " +e.getMessage());
				return null;
			}
	}

	//Metodo che restituisce la lista di ricette di uno chef
	public List<Ricetta> getRicetteByChef(Chef c) {
		List<Ricetta> ListaRicette = new ArrayList<>();
		String sql = "SELECT r.* FROM uninafoodlab.ricetta AS r JOIN uninafoodlab.sessione AS s ON r.idricetta = s.idricetta JOIN uninafoodlab.corso AS co ON co.idcorso = s.idcorso WHERE co.idchef = ?";
		try(Connection conn = DBManager.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setString(1, c.getID_Chef());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Ricetta R = new Ricetta();
					R.setIDRicetta(rs.getString("idricetta"));
					R.setTitolo(rs.getString("nominativoricetta"));
					R.setDescrizione(rs.getString("descrizione"));
					ListaRicette.add(R);
				}
				return ListaRicette;
			}catch(SQLException e) {
				System.out.println("Errore durante il recupero delle ricette del chef : " + e.getMessage());
				return null;
			}
	}

	//Metodo che prende il numero di ricette di uno chef 
	public int getNumeroRicetteByChef(Chef c) {
		String sql = "SELECT COUNT(*) AS nricette FROM uninafoodlab.ricetta AS r JOIN uninafoodlab.sessione AS s ON r.idricetta = s.idricetta JOIN uninafoodlab.corso AS co ON co.idcorso = s.idcorso WHERE co.idchef = ?";
		try(Connection conn = DBManager.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setString(1, c.getID_Chef());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt("nricette");
				} else {
					return 0;
				}
			} catch(SQLException e) {
				System.out.println("Errore durante il recupero del numero di ricette del chef : " + e.getMessage());
				return 0;
			}
	}

	//Metodo che prende la descrizione di una ricetta tramite il suo stesso ID
	public String GetDescrizioneRicettaDAO(String iDRicetta) {
		String sql = "SELECT descrizione FROM uninafoodlab.ricetta WHERE idricetta = ?;";
		try (Connection conn = DBManager.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, iDRicetta);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("descrizione");
			}
		} catch (SQLException e) {
			System.out.println("Errore durante il recupero della descrizione della ricetta: " + e.getMessage());
		}
		return null;
	}

	//Metodo che prende la ricetta tramite il suo stesso ID
	public Ricetta getRicettaByID(String idRicetta) {
		String sql = "SELECT * FROM uninafoodlab.ricetta WHERE idricetta = ?;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, idRicetta);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Ricetta ricetta = new Ricetta();
				ricetta.setIDRicetta(rs.getString("idricetta"));
				ricetta.setTitolo(rs.getString("nominativoricetta"));
				ricetta.setDescrizione(rs.getString("descrizione"));
				return ricetta;
			}
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero della ricetta: " + e.getMessage());
		}
		return null;
	}
}