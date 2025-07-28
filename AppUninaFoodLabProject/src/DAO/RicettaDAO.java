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
	//Metodo per l'inserimento di una nuova ricetta nel DB usando un oggetto Ricetta (True se l'inserimento va a buon fine, False altrimenti)
	public boolean InsertRicetta(Ricetta Ricetta_Input) {
		String sql = "INSERT INTO uninafoodlab.ricetta(idricetta, nominativoricetta)VALUES (?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Ricetta_Input.getIDRicetta());
				pstmt.setString(2, Ricetta_Input.getTitolo());
				
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
	
	//Metodo per eliminare una ricetta dal DB usando l'IDRicetta (True se l'eliminazione va a buon fine, False altrimenti)
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
					
					ListaRicette.add(R);
			
					}
				return ListaRicette;
			}catch(SQLException e) {
				System.out.println("Errore durante il recupero delle ricette del partecipante : " +e.getMessage());
				return null;
			}
	}
}