package Entities;

public class IscrizioneSessione {
	
	//ATTRIBUTI
	private Partecipante P;
	private Sessione S;
	private boolean Adesione;
	
	//COSTRUTTORI
	public IscrizioneSessione(Partecipante P, Sessione S, boolean Adesione) {
		this.P = P;
		this.S = S;
		this.Adesione = Adesione;
	}
	public IscrizioneSessione(Partecipante P, Sessione S) {
		this.P = P;
		this.S = S;
		this.Adesione = false;
	}
	
	public IscrizioneSessione() {
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
