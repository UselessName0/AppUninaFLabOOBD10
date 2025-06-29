package Entities;

public class IscrizioneSessione {
	
	//ATTRIBUTI
	private Partecipante P;
	private Sessione S;
	private boolean Adesione;
	
	//COSTRUTTORI
	private IscrizioneSessione(Partecipante P, Sessione S, boolean Adesione) {
		this.P = P;
		this.S = S;
		this.Adesione = Adesione;
	}
	private IscrizioneSessione(Partecipante P, Sessione S) {
		this.P = P;
		this.S = S;
	}
	
	//METODI
		//GETTERS AND SETTERS
		public Partecipante getP() {
			return P;
		}
		public void setP(Partecipante p) {
			P = p;
		}
		public Sessione getS() {
			return S;
		}
		public void setS(Sessione s) {
			S = s;
		}
		public boolean isAdesione() {
			return Adesione;
		}
		public void setAdesione(boolean adesione) {
			Adesione = adesione;
		}
		
}
