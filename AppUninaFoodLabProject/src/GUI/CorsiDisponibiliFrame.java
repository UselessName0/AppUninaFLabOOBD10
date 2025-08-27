package GUI;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.event.*;

import Controller.ControllerChef;
import Controller.ControllerPartecipante;
import Entities.Chef;
import Entities.Corso;
import Entities.Partecipante;
import Entities.MenuFactory; 

public class CorsiDisponibiliFrame extends JFrame {
	
	//ATTRIBUTI
	ControllerPartecipante CP = new ControllerPartecipante();
	ControllerChef CC = new ControllerChef();
    Partecipante p;
    Chef c;

	//COSTRUTTORI
    public CorsiDisponibiliFrame(Partecipante p) {
    	this.p = p;
        setTitle("Corsi Disponibili");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(MenuFactory.creaMenuBarPartecipante(this, p));
        
        Color sfondoPrincipale = new Color(220, 240, 250);  
        Color sfondoTabella = new Color(210, 240, 210);   

        JPanel contentPane = new JPanel(new BorderLayout(20, 20));
        contentPane.setBackground(sfondoPrincipale);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JLabel titolo = new JLabel("Elenco dei Corsi Disponibili");
        titolo.setFont(new Font("Arial", Font.BOLD, 24));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        titolo.setForeground(new Color(50, 80, 150));
        titolo.setText("Corsi Disponibili per " + p.getNome() + " " + p.getCognome());
        
        //lista per i dati dei corsi (presi dal DB)
        List<Corso> datiCorsi; 
        datiCorsi = CP.GetCorsiDovePartecipanteNonIscritto(p);
        String[] colonne = {"Nome Corso", "Chef"};
        Object[][] righe = new Object[datiCorsi.size()][3];
        for(int i = 0; i< datiCorsi.size(); i++) {
        	Corso c = datiCorsi.get(i);
        	righe[i][0] = c.getNome_Corso();
        	righe[i][1] = c.getChef_Proprietario().getNome() + " " + c.getChef_Proprietario().getCognome();
        	righe[i][2] = c.getDescrizione();
        }

        //Creazione della tabella per i vari corsi presi da DB
        final JTable tabellaCorsi = new JTable(righe, colonne);
        tabellaCorsi.setFont(new Font("Arial", Font.PLAIN, 16));
        tabellaCorsi.setRowHeight(28);
        tabellaCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaCorsi.setBackground(sfondoTabella);
        tabellaCorsi.setGridColor(Color.LIGHT_GRAY);
        tabellaCorsi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int riga = tabellaCorsi.getSelectedRow();
                    if (riga >= 0) {
                    	Corso c = datiCorsi.get(riga);
                        mostraDettagliCorsoPartecipante(c);
                    }
                }
            }
        });
        
        //ScrollPane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabellaCorsi);

        //Back Button 
        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setBackground(new Color(220, 240, 250));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(50, 80 , 150), 1));
        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DashboardUtente(p).setVisible(true);
                dispose();
            }
        });

        //Pannello centrale per centrare la tabella
        JPanel pannelloCentrale = new JPanel();
        pannelloCentrale.setBackground(sfondoPrincipale);
        
        //Layout
        GroupLayout layout = new GroupLayout(pannelloCentrale);
        pannelloCentrale.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titolo)
                .addComponent(scrollPane)
                .addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titolo)
                .addGap(20)
                .addComponent(scrollPane)
                .addGap(20)
                .addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        contentPane.add(pannelloCentrale, BorderLayout.CENTER);
    }
    
    //Costruttore per corsi disponibili passando un oggetto di tipo chef
    public CorsiDisponibiliFrame(Chef C) {
    	this.c = C;
        setTitle("Corsi altrui");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(MenuFactory.creaMenuBarChef(this, c));

        Color sfondoPrincipale = new Color(220, 240, 250);  
        Color sfondoTabella = new Color(210, 240, 210);   

        JPanel contentPane = new JPanel(new BorderLayout(20, 20));
        contentPane.setBackground(sfondoPrincipale);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JLabel titolo = new JLabel("Elenco dei corsi altrui");
        titolo.setFont(new Font("Arial", Font.BOLD, 24));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        titolo.setForeground(new Color(50, 80, 150));

        //Lista per i dati corsi presi dal DB
        final List<Corso> datiCorsi = CC.GetCorsiExceptChef(C);
        String[] colonne = {"Nome Corso", "Chef"};
        Object[][] righe = new Object[datiCorsi.size()][3];
        for(int i = 0; i< datiCorsi.size(); i++) {
        	Corso c = datiCorsi.get(i);
        	righe[i][0] = c.getNome_Corso();
        	righe[i][1] = c.getChef_Proprietario().getNome() + " " + c.getChef_Proprietario().getCognome();
        	righe[i][2] = c.getDescrizione();
        }

        final JTable tabellaCorsi = new JTable(righe, colonne);
        tabellaCorsi.setFont(new Font("Arial", Font.PLAIN, 16));
        tabellaCorsi.setRowHeight(28);
        tabellaCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaCorsi.setBackground(sfondoTabella);
        tabellaCorsi.setGridColor(Color.LIGHT_GRAY);
        tabellaCorsi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int riga = tabellaCorsi.getSelectedRow();
                    if (riga >= 0) {
                    	Corso c = datiCorsi.get(riga);
                        mostraDettagliCorsoChef(c);
                    }
                }
            }
        });
        
        //ScrollPane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabellaCorsi);

        //Back Button 
        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setBackground(new Color(220, 240, 250));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(50, 80 , 150), 1));
        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DashboardChef(c).setVisible(true);
                dispose();
            }
        });

        //Pannello centrale per centrare la tabelloa
        JPanel pannelloCentrale = new JPanel();
        pannelloCentrale.setBackground(sfondoPrincipale);
        
        //layout
        GroupLayout layout = new GroupLayout(pannelloCentrale);
        pannelloCentrale.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titolo)
                .addComponent(scrollPane)
                .addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titolo)
                .addGap(20)
                .addComponent(scrollPane)
                .addGap(20)
                .addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        contentPane.add(pannelloCentrale, BorderLayout.CENTER);
    }
    
    //METODI
    //Metodo che prmette di mostrare tutti i dettagli dei vari corsi 
    private void mostraDettagliCorsoPartecipante(Corso c) {
        JFrame finestraDettagli = new JFrame("Dettagli del Corso");
        finestraDettagli.setSize(440, 360);
        finestraDettagli.setLocationRelativeTo(null);
        finestraDettagli.getContentPane().setLayout(null);
        finestraDettagli.getContentPane().setBackground(new Color(230, 240, 250));

        JLabel lblNome = new JLabel("Corso: " + c.getNome_Corso());
        lblNome.setBounds(20, 20, 400, 25);
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblChef = new JLabel("Chef: " + c.getChef_Proprietario().getNome() + " " + c.getChef_Proprietario().getCognome());
        lblChef.setBounds(20, 55, 400, 25);
        lblChef.setFont(new Font("Arial", Font.PLAIN, 14));

        String descrizione = c.getDescrizione();
        LocalDate dataInizio = c.getData_Inizio();
        String dataInizioStringa = (dataInizio!= null) ? dataInizio.toString() : "Data inizio non ancora pubblicata";
        String frequenza = c.getFrequenza_Corsi();

        JTextArea txtDescrizione = new JTextArea(descrizione);
        txtDescrizione.setBounds(20, 90, 400, 60);
        txtDescrizione.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setLineWrap(true);
        txtDescrizione.setEditable(false);
        txtDescrizione.setBackground(new Color(245, 250, 255));
        txtDescrizione.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblData = new JLabel(dataInizioStringa);
        lblData.setBounds(20, 160, 400, 25);
        lblData.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblFrequenza = new JLabel(frequenza);
        lblFrequenza.setBounds(20, 190, 400, 25);
        lblFrequenza.setFont(new Font("Arial", Font.PLAIN, 14));

        finestraDettagli.getContentPane().add(lblNome);
        finestraDettagli.getContentPane().add(lblChef);
        finestraDettagli.getContentPane().add(txtDescrizione);
        finestraDettagli.getContentPane().add(lblData);
        finestraDettagli.getContentPane().add(lblFrequenza);
        
        //Bottone per iscrizione 
        JButton btnIscriviti = new JButton("Iscriviti");
        btnIscriviti.setBounds(160, 230, 100, 30);
        btnIscriviti.setBackground(new Color(180, 220, 240));
        btnIscriviti.setBorder(BorderFactory.createLineBorder(new Color(120, 180, 220), 1));
        btnIscriviti.setFocusPainted(false);
        btnIscriviti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(CP.IscriviPartecipanteACorso(p, c))
            		{
            		JOptionPane.showMessageDialog(finestraDettagli, "🎉 Iscritto al corso " + c.getNome_Corso() + "!");
            		}
            	else
            		{
            		JOptionPane.showMessageDialog(finestraDettagli, "Errore nell'iscrizione al corso " + c.getNome_Corso() + "!");
            		}
                finestraDettagli.dispose();
            }
        });

        finestraDettagli.setResizable(false);
        finestraDettagli.setVisible(true);
        finestraDettagli.getContentPane().add(btnIscriviti);
    }
    
    //Medesimo metodo ma con un oggetto di tipo chef 
    private void mostraDettagliCorsoChef(Corso c) {
        JFrame finestraDettagli = new JFrame("Dettagli del Corso");
        finestraDettagli.setSize(440, 360);
        finestraDettagli.setLocationRelativeTo(null);
        finestraDettagli.getContentPane().setLayout(null);
        finestraDettagli.getContentPane().setBackground(new Color(230, 240, 250));

        JLabel lblNome = new JLabel("Corso: " + c.getNome_Corso());
        lblNome.setBounds(20, 20, 400, 25);
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblChef = new JLabel("Chef: " + c.getChef_Proprietario().getNome() + " " + c.getChef_Proprietario().getCognome());
        lblChef.setBounds(20, 55, 400, 25);
        lblChef.setFont(new Font("Arial", Font.PLAIN, 14));

        String descrizione = c.getDescrizione();
        LocalDate dataInizio = c.getData_Inizio();
        String dataInizioStringa = (dataInizio!= null) ? dataInizio.toString() : "Data inizio non ancora pubblicata";
        String frequenza = c.getFrequenza_Corsi();

        JTextArea txtDescrizione = new JTextArea(descrizione);
        txtDescrizione.setBounds(20, 90, 400, 60);
        txtDescrizione.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setLineWrap(true);
        txtDescrizione.setEditable(false);
        txtDescrizione.setBackground(new Color(245, 250, 255));
        txtDescrizione.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblData = new JLabel(dataInizioStringa);
        lblData.setBounds(20, 160, 400, 25);
        lblData.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblFrequenza = new JLabel(frequenza);
        lblFrequenza.setBounds(20, 190, 400, 25);
        lblFrequenza.setFont(new Font("Arial", Font.PLAIN, 14));

        finestraDettagli.getContentPane().add(lblNome);
        finestraDettagli.getContentPane().add(lblChef);
        finestraDettagli.getContentPane().add(txtDescrizione);
        finestraDettagli.getContentPane().add(lblData);
        finestraDettagli.getContentPane().add(lblFrequenza);
        
        finestraDettagli.setResizable(false);
        finestraDettagli.setVisible(true);
    } 
}