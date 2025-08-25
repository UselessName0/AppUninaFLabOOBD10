package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;
import Entities.Partecipante;

public class PartecipanteDAO {
	
	//METODI
	//Metodo che ritorna true se l'email è presente all'interno del DB
	public boolean checkEmail(String emailInput) {
	    String sql = "SELECT 1 FROM uninafoodlab.partecipante WHERE email = ?";
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, emailInput);
	        ResultSet rs = pstmt.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
	        System.out.println("Errore durante la verifica dell'email: " + e.getMessage());
	        return false;
	    } 
	}
	
	//La funzione ritorna true se la password inserita corrisponde a quella presente nel DB
	public boolean checkPassword(String emailInput, String pwdInput) {
		String sql = "SELECT pass FROM uninafoodlab.partecipante WHERE email = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,  emailInput);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if(rs.getString(1).equals(pwdInput))
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.out.println("Errore durante la verifica della password: "+ e.getMessage());
			return false;
		}
	}
	
	//Metodo per l'inserimento di un nuovo partecipante nel DB
	public boolean InsertPartecipanteDAO(Partecipante Partecipante_Input) {
		String sql = "INSERT INTO uninafoodlab.partecipante(idpartecipante, nomepartecipante, email, pass)VALUES (?, ?, ?, ?);";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
				pstmt.setString(2, Partecipante_Input.getNome());
				pstmt.setString(3, Partecipante_Input.getEmail());
				pstmt.setString(4, Partecipante_Input.getPassword());
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento della nuova entità: "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo per selezionare il nome di un partecipante dal DB 
	public String getNomePartecipanteDAO(Partecipante Partecipante_Input) {
		String sql = "SELECT P.Nomepartecipante FROM uninafoodlab.Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("Nomepartecipante");
		        else 
		            return null;
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare il nome di un partecipante dal DB tramite il suo stesso ID
	public String getNomePartecipanteDAO(String IDPartecipante_Input) {
		String sql = "SELECT P.Nomepartecipante FROM uninafoodlab.partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDPartecipante_Input);
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("Nomepartecipante");
		        else 
		            return null;
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare il cognome di un partecipante dal DB usando un oggetto Partecipante
	public String getCognomePartecipanteDAO(Partecipante Partecipante_Input) {
		String sql = "SELECT P.Cognomepartecipante FROM uninafoodlab.partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("Cognomepartecipante");
		        else 
		            return null;
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	//Metodo per selezionare il cognome di un partecipante dal DB usando l'IDPartecipante
	public String getCognomePartecipanteDAO(String IDPartecipante_Input) {
		String sql = "SELECT P.Cognomepartecipante FROM uninafoodlab.Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDPartecipante_Input);
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("Cognomepartecipante");
		        else 
		            return null;
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare l'email di un partecipante dal DB usando un oggetto Partecipante
	public String getEmailPartecipanteDAO(Partecipante Partecipante_Input) {
		String sql = "SELECT P.Email FROM uninafoodlab.Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, Partecipante_Input.getID_Partecipante());
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("Email");
		        else 
		            return null;
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare l'email di un partecipante dal DB usando l'IDPartecipante
	public String getEmailPartecipanteDAO(String IDPartecipante_Input) {
		String sql = "SELECT P.Email FROM uninafoodlab.Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDPartecipante_Input);
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("Email");
		        else 
		            return null;
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	// Metodo per ottenere un oggetto Partecipante dal DB usando l'email
	public Partecipante getPartecipanteByEmail(String email) {
		String sql = "SELECT * FROM uninafoodlab.partecipante WHERE email = ?";
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, email);
	        ResultSet rs = pstmt.executeQuery();        
	        if (rs.next()) {
	            Partecipante partecipante = new Partecipante();
	            partecipante.setID_Partecipante(rs.getString("idpartecipante"));
	            partecipante.setNome(rs.getString("nomepartecipante"));
	            partecipante.setEmail(rs.getString("email"));
	            partecipante.setPassword(rs.getString("pass"));
	            return partecipante;
	        } else 
	            return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//Metodo per selezionare la password di un partecipante dal DB usando un oggetto Partecipante
	public String getPassPartecipanteDAO(String IDPartecipante_Input) {
		String sql = "SELECT P.pass FROM uninafoodlab.Partecipante AS P WHERE P.IDPartecipante = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDPartecipante_Input);
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) 
		            return rs.getString("pass");
		        else 
		            return null;
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per eliminare un partecipante dal DB usando l'IDPartecipante
	public boolean DeletePartecipanteDAO(String IDPartecipante_Input) {
		String sql ="DELETE FROM uninafoodlab.corso WHERE corso.IDcorso = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, IDPartecipante_Input);
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
			} catch(SQLException e) {
				System.out.println("Errore durante l'eliminazione del partecipante : "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo per selezionare tutti i partecipanti dal DB e restituirli come una lista di oggetti
	public List<Partecipante> getAllPartecipanti() {
		List<Partecipante> ListaPartecipanti = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.partecipante;";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Partecipante p = new Partecipante();
					p.setID_Partecipante(rs.getString("idpartecipante"));
					p.setNome(rs.getString("nomepartecipante"));
					p.setCognome("cognomepartecipante");
					p.setEmail("email");
					p.setPassword("pass");
					ListaPartecipanti.add(p);
				}
				return ListaPartecipanti;	
			}catch(SQLException e) {
				System.out.println("Errore durante il recupero dei partecipanti: " + e.getMessage());
				return null;
			}
	}
	
	//Metodo per selezionare i partecipanti dal DB in base al nome e restituirli come una lista di oggetti
	public List<Partecipante> getPartecipantiByNome(String nome) {
		List<Partecipante> ListaPartecipanti = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.partecipante WHERE nomepartecipante LIKE ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Partecipante p = new Partecipante();
				p.setID_Partecipante(rs.getString("idpartecipante"));
				p.setNome(rs.getString("nomepartecipante"));
				p.setCognome(rs.getString("cognomepartecipante"));
				p.setEmail(rs.getString("email"));
				p.setPassword(rs.getString("pass"));
				ListaPartecipanti.add(p);
			}
			return ListaPartecipanti;	
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei partecipanti: " + e.getMessage());
			return null;
		}
	}
}