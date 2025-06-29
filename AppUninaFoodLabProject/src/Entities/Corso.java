package Entities;

import java.time.LocalDate;

public class Corso {

    // ATTRIBUTI
	private String ID_Corso;
    private Chef Chef_Proprietario;
    private String Nome_Corso;
    private String Argomento;
    private LocalDate Data_Inizio;
    private LocalDate Data_Creazione;
    private String Frequenza_Corsi;

    //COSTRUTTORI
    public Corso(String IDCorso,Chef Chef_Proprietario, String Nome_Corso, String Argomento, LocalDate Data_Inizio, LocalDate Data_Creazione, String Frequenza_Corsi) {
    	this.ID_Corso = IDCorso;
        this.Chef_Proprietario = Chef_Proprietario;
        this.Nome_Corso = Nome_Corso;
        this.Argomento = Argomento;
        this.Data_Inizio = Data_Inizio;
        this.Data_Creazione = (Data_Creazione != null) ? Data_Creazione : LocalDate.now();
        this.Frequenza_Corsi = Frequenza_Corsi;
    }

    //COSTRUTTORE SENZA PASSAGGIO DI DATA (NULL) INIZIALIZZA A LocalDate.now
    public Corso(String IDCorso,Chef Chef_Proprietario, String Nome_Corso, String Argomento, LocalDate Data_Inizio, String Frequenza_Corsi) {
        this(IDCorso,Chef_Proprietario, Nome_Corso, Argomento, Data_Inizio, LocalDate.now(), Frequenza_Corsi);
    }
    
    //METODI
	    // GETTERS E SETTERS
	    public Chef getChef_Proprietario() {
	        return Chef_Proprietario;
	    }
	
	    public void setChef_Proprietario(Chef Chef_Proprietario) {
	        this.Chef_Proprietario = Chef_Proprietario;
	    }
	
	    public String getNome_Corso() {
	        return Nome_Corso;
	    }
	
	    public void setNome_Corso(String Nome_Corso) {
	        this.Nome_Corso = Nome_Corso;
	    }
	
	    public String getArgomento() {
	        return Argomento;
	    }
	
	    public void setArgomento(String Argomento) {
	        this.Argomento = Argomento;
	    }
	
	    public LocalDate getData_Inizio() {
	        return Data_Inizio;
	    }
	
	    public void setData_Inizio(LocalDate Data_Inizio) {
	        this.Data_Inizio = Data_Inizio;
	    }
	
	    public LocalDate getData_Creazione() {
	        return Data_Creazione;
	    }
	
	    public void setData_Creazione(LocalDate Data_Creazione) {
	        this.Data_Creazione = Data_Creazione;
	    }
	
	    public String getFrequenzaCorsi() {
	        return Frequenza_Corsi;
	    }
	
	    public void setFrequenza_Corsi(String Frequenza_Corsi) {
	        this.Frequenza_Corsi = Frequenza_Corsi;
	    }
}
