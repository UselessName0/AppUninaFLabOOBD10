package Entities;

import java.time.LocalDate;

import DAO.CorsoDAO;

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
    
    public Corso(String IDCorso) { //Costruttore di un corso già presente nel database via IDCorso
    	CorsoDAO CorsoDAO = new CorsoDAO();
    	this.ID_Corso = IDCorso;
    	Chef tempChef = new Chef(CorsoDAO.getIDChefProprietarioDAO(IDCorso));
    	this.Chef_Proprietario = tempChef;
    	this.Nome_Corso = CorsoDAO.getNomeCorsoDAO(IDCorso);
    	this.Argomento = CorsoDAO.getArgomentoCorsoDAO(IDCorso);
    	if(CorsoDAO.getDataInizioCorsoDAO(IDCorso) != null)
    		this.Data_Inizio = CorsoDAO.getDataInizioCorsoDAO(IDCorso).toLocalDate();
    	else 
    		this.Data_Inizio = null;
    	this.Data_Creazione = CorsoDAO.getDataCreazioneCorsoDAO(IDCorso).toLocalDate();
    	if(CorsoDAO.getFrequenzaCorsoDAO(IDCorso) == null)
    		this.Frequenza_Corsi = "Libero";
    	else
    		this.Frequenza_Corsi = CorsoDAO.getFrequenzaCorsoDAO(IDCorso);
    }
    
    
    //COSTRUTTORE SENZA PASSAGGIO DI DATA (NULL) INIZIALIZZA A LocalDate.now
    public Corso(String IDCorso,Chef Chef_Proprietario, String Nome_Corso, String Argomento, LocalDate Data_Inizio, String Frequenza_Corsi) {
        this(IDCorso,Chef_Proprietario, Nome_Corso, Argomento, Data_Inizio, LocalDate.now(), Frequenza_Corsi);
    }
    
    public Corso() {
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
	    
	    public java.sql.Date getDataInizioAsSQLDate() {
	        return java.sql.Date.valueOf(Data_Inizio);
	    }
	
	    public void setData_Inizio(LocalDate Data_Inizio) {
	        this.Data_Inizio = Data_Inizio;
	    }
	
	    public LocalDate getData_Creazione() {
	        return Data_Creazione;
	    }
	    
	    public java.sql.Date getDataCreazioneAsSQLDate() {
	        return java.sql.Date.valueOf(Data_Creazione);
	    }
	
	    public void setData_Creazione(LocalDate Data_Creazione) {
	        this.Data_Creazione = Data_Creazione;
	    }
	
	    public String getFrequenza_Corsi() {
	        return Frequenza_Corsi;
	    }
	
	    public void setFrequenza_Corsi(String Frequenza_Corsi) {
	        this.Frequenza_Corsi = Frequenza_Corsi;
	    }

		public String getID_Corso() {
			return ID_Corso;
		}

		public void setID_Corso(String iD_Corso) {
			ID_Corso = iD_Corso;
		}

		public String getDescrizione() {
			// TODO Auto-generated method stub
			return null;
		}   
}
