package Entities;

import java.util.*;
import java.time.LocalDate;

public class Corso {
	//ATTRIBUTI
	private Chef Chef_Proprietario;
	private String Nome_Corso;
	private String Argomento;
	private LocalDate Data_Inizio;
	private LocalDate Data_Creazione;
	private String FrequenzaCorsi;
	
	//COSTRUTTORI
	private Corso(Chef Chef_Proprietario, String Nome_Corso, String Argomento, LocalDate Data_Inizio, Date Data_Creazione, String FrequenzaCorsi) {
		this.Chef_Proprietario = Chef_Proprietario;
		this.Nome_Corso = Nome_Corso;
		this.Argomento = Argomento;
		this.Data_Inizio = Data_Inizio;
		this.Data_Creazione = LocalDate.now();
		this.FrequenzaCorsi = FrequenzaCorsi;
	}
	//METODI
		//GETTER AND SETTERS
		public Chef getChef_Proprietario() {
			return Chef_Proprietario;
		}
	
		public void setChef_Proprietario(Chef chef_Proprietario) {
			Chef_Proprietario = chef_Proprietario;
		}
	
		public String getNome_Corso() {
			return Nome_Corso;
		}
	
		public void setNome_Corso(String nome_Corso) {
			Nome_Corso = nome_Corso;
		}
	
		public String getArgomento() {
			return Argomento;
		}
	
		public void setArgomento(String argomento) {
			Argomento = argomento;
		}
	
		public LocalDate getData_Inizio() {
			return Data_Inizio;
		}
	
		public void setData_Inizio(LocalDate data_Inizio) {
			Data_Inizio = data_Inizio;
		}
	
		public LocalDate getData_Creazione() {
			return Data_Creazione;
		}
	
		public void setData_Creazione(LocalDate data_Creazione) {
			Data_Creazione = data_Creazione;
		}
	
		public String getFrequenzaCorsi() {
			return FrequenzaCorsi;
		}
	
		public void setFrequenzaCorsi(String frequenzaCorsi) {
			FrequenzaCorsi = frequenzaCorsi;
		}
		
	
}
