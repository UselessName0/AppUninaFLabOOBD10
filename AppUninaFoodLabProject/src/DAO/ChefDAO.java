package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;

public class ChefDAO {

	//La funzione ritorna false se l'email non è presente all'interno del DB, true altrimenti
	public boolean checkEmail(String emailInput) {
	    String sql = "SELECT email FROM uninafoodlab.Chef WHERE email = ?";
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, emailInput);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) 
	            return true; 
	        else 
	            return false;  
	        
	    } catch (SQLException e) {
	        System.out.println("Errore durante la verifica dell'email: " + e.getMessage());
	        return false;
	    } 
	}
	
	public static Chef getChefById(String idChef) {
        String sql = "SELECT idchef, nomechef, cognomechef, email FROM uninafoodlab.chef WHERE idchef = ?";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idChef);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Chef(
                    rs.getString("idchef"),
                    rs.getString("nomechef"),
                    rs.getString("cognomechef"),
                    rs.getString("email")
                );
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Errore nel recupero chef con id " + idChef + ": " + e.getMessage());
            return null;
        }
    }
	
	//La funzione ritorna true se la password inserita corrisponde a quella presente nel DB, false altrimenti
	public boolean checkPassword(String emailInput, String pwdInput) {
		String sql = "SELECT pass FROM uninafoodlab.Chef WHERE email = ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1,  emailInput);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("pass").equals(pwdInput);
			}else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Errore durante la verifica della password: "+ e.getMessage());
			return false;
		}
	}
	
	//Metodo per l'inserimento di un nuovo Chef nel DB usando un oggetto Chef(True se l'inserimento va a buon fine, False altrimenti)
	public boolean InsertChefDAO(Chef Chef_Input) {
		String sql = "INSERT INTO uninafoodlab.chef(idchef, nomechef, cognomechef, email, pass)VALUES (?, ?, ?, ?, ?)";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
				pstmt.setString(2, Chef_Input.getNome());
				pstmt.setString(3, Chef_Input.getCognome());
				pstmt.setString(4, Chef_Input.getEmail());
				pstmt.setString(5, Chef_Input.getPassword());
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'inserimento del nuovo chef: "+ e.getMessage());
				return false;
			}
	}
	
	public Chef getChefByEmail(String Email_Input) {
		String sql = "SELECT * FROM uninafoodlab.chef WHERE email = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Email_Input);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
				
					Chef C = new Chef();
					C.setID_Chef(rs.getString("idchef"));
					C.setNome(rs.getString("nomechef"));
					C.setCognome(rs.getString("cognomechef"));
					C.setEmail(rs.getString("email"));
					C.setPassword(rs.getString("pass"));
					return C;
				} else 
					return null;
			} catch(SQLException e) {
				e.getMessage();
				return null;
			}
	}
	//Metodo per selezionare un idchef usando Email e Password
	public String getIDChefDAO(String Email_Input, String Password_Input) {
		String sql = "SELECT ch.idchef FROM uninafoodlab.chef WHERE (email = ?) AND (pass = ?)";
				try(Connection conn = DBManager.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
					
					pstmt.setString(1, Email_Input);
					pstmt.setString(2, Password_Input);
					
					ResultSet rs = pstmt.executeQuery();
					if(rs.next())
						return rs.getString("idchef");
					else
						return null;
				} catch(SQLException e) {
					e.getMessage();
					return null;
				}
	}
	
	//Metodo per selezionare uno Chef dal DB usando un oggetto Chef per cercarlo
	public String getNomeChefDAO(Chef Chef_Input) {
		String sql = "SELECT Ch.nomechef FROM uninafoodlab.Chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            return rs.getString("nomechef");
		        }
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare il nome Chef dal DB usando l'IDChef per cercarlo
	public String getNomeChefDAO(String IDChef_Input) {
		String sql = "SELECT Ch.Nomechef FROM uninafoodlab.chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDChef_Input);
				ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
//		        	System.out.println("ciao" + rs.getString("nomechef"));
		            return rs.getString("Nomechef");
		        }
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare il cognome Chef dal DB usando un oggetto Chef per cercarlo
	public String getCognomeChefDAO(Chef Chef_Input) {
		String sql = "SELECT Ch.Cognomechef FROM uninafoodlab.chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
				ResultSet rs = pstmt.executeQuery();
				
				
		        if (rs.next()) 
		            return rs.getString("Cognomechef");
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare il cognome Chef dal DB usando l'IDChef per cercarlo
	public String getCognomeChefDAO(String IDChef_Input) {
		String sql = "SELECT Ch.Cognomechef FROM uninafoodlab.chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDChef_Input);
				ResultSet rs = pstmt.executeQuery();
				
				
		        if (rs.next()) 
		            return rs.getString("Cognomechef");
		        else 
		            return null;
		         
			} catch(SQLException e) {
				e.printStackTrace();
		        return null;
			}
	}
	
	//Metodo per selezionare l'email Chef dal DB usando un oggetto Chef per cercarlo
	public String getEmailChefDAO(Chef Chef_Input) {
		String sql = "SELECT Ch.Email FROM uninafoodlab.chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, Chef_Input.getID_Chef());
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
	
	//Metodo per selezionare l'email Chef dal DB usando l'IDChef per cercarlo
	public String getEmailChefDAO(String IDChef_Input) {
		String sql = "SELECT Ch.Email FROM uninafoodlab.chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDChef_Input);
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
	
	//Non credo abbia senso fare un metodo per selezionare la password Chef usando un oggetto Chef, faccio solo quella via IDChef per costruire gli oggetti  
	
	//Metodo per selezionare la password Chef dal DB usando l'IDChef per cercarlo
	public String getpassChefDAO(String IDChef_Input) {
		String sql = "SELECT Ch.pass FROM uninafoodlab.chef AS Ch WHERE Ch.IDChef = ?";
			try(Connection conn = DBManager.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDChef_Input);
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
	
	//Metodo per eliminare i dati di uno Chef nel DB usando un oggetto Chef
	public boolean DeleteChefDAO(String IDChef_Input) {
		String sql ="DELETE FROM uninafoodlab.chef WHERE Chef.IDChef = ?";
			try(Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, IDChef_Input);
				int rowsAffected = pstmt.executeUpdate();
				
				return rowsAffected > 0;
				
			} catch(SQLException e) {
				System.out.println("Errore durante l'eliminazione dello Chef : "+ e.getMessage());
				return false;
			}
	}
	
	//Metodo per prendere tutti gli Chef presenti nel DB
	public List<Chef> getAllChef() {
		List<Chef> ListaChef = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.Chef";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				Chef Ch = new Chef();
				Ch.setID_Chef(rs.getString("idchef"));
				Ch.setNome(rs.getString("nomechef"));
				Ch.setCognome(rs.getString("cognomechef"));
				Ch.setEmail(rs.getString("email"));
				Ch.setPassword(rs.getString("pass"));
				ListaChef.add(Ch);
			}
			return ListaChef;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Chefs: " + e.getMessage());
			return null;
		}
	}
	
	//Metodo per prendere tutti gli Chef presenti nel DB che hanno un nome simile a quello passato come parametro
	public List<Chef> getAllChefByNome(String nome) {
		List<Chef> ListaChef = new ArrayList<>();
		String sql = "SELECT * FROM uninafoodlab.Chef WHERE nomechef LIKE ?";
		try(Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Chef Ch = new Chef();
				Ch.setID_Chef(rs.getString("idchef"));
				Ch.setNome(rs.getString("nomechef"));
				Ch.setCognome(rs.getString("cognomechef"));
				Ch.setEmail(rs.getString("email"));
				Ch.setPassword(rs.getString("pass"));
				ListaChef.add(Ch);
			}
			return ListaChef;
			
		} catch(SQLException e) {
			System.out.println("Errore durante il recupero dei Chef: " + e.getMessage());
			return null;
		}
	}
			
	public int getNumeroCorsiByChef(Chef c) {
		String sql = "SELECT COUNT(*) AS ncorsi FROM uninafoodlab.Corso WHERE idchef = ?";
		try (Connection conn = DBManager.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, c.getID_Chef());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("ncorsi");
			} else {
				return 0; // Nessun corso trovato
			}
			
		} catch (SQLException e) {
			System.out.println("Errore durante il conteggio dei corsi: " + e.getMessage());
			return 0;
		}
	}
}