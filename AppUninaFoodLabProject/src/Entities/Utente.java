package Entities;

abstract class Utente {
	
	//ATTRIBUTI
	protected String Nome;
	protected String Cognome;
	protected String Email;
	protected String Password;
		
	//COSTRUTTORI
	protected Utente(String Nome, String Cognome, String Email, String Password) {
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.Email = Email;
		this.Password = Password;
	}
	
	//METODI
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getCognome() {
		return Cognome;
	}
	
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
}