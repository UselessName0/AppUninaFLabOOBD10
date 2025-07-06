package Entities;

import java.time.LocalDate;

public class IscrizioneCorso {
	
	//ATTRIBUTI
	private Corso C;
	private Partecipante P;
	private LocalDate Data_di_Iscrizione;
	
	//COSTRUTTORI
	public IscrizioneCorso(Corso C,Partecipante P, LocalDate Data_di_Iscrizione) {
		this.C = C;
		this.P = P;
		this.Data_di_Iscrizione = Data_di_Iscrizione;
	}
	
	public IscrizioneCorso(Corso C,Partecipante P) {
		this.C = C;
		this.P = P;
		Data_di_Iscrizione = LocalDate.now();
	}
	
	public IscrizioneCorso() {

	}

		//METODI
		//GETTER E SETTERS
		public Corso getC() {
			return C;
		}
	
		public void setC(Corso c) {
			C = c;
		}
	
		public Partecipante getP() {
			return P;
		}
	
		public void setP(Partecipante p) {
			P = p;
		}
	
		public LocalDate getData_di_Iscrizione() {
			return Data_di_Iscrizione;
		}
		
	    public java.sql.Date getDataIscrizioneAsSQLDate() {
	        return java.sql.Date.valueOf(Data_di_Iscrizione);
	    }
	
		public void setData_di_Iscrizione(LocalDate data_di_Iscrizione) {
			Data_di_Iscrizione = data_di_Iscrizione;
		}
		
}
