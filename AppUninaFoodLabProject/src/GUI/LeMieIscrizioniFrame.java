package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Controller.ControllerPartecipante;
import Entities.Corso;
import Entities.MenuFactory;
import Entities.Partecipante;

public class LeMieIscrizioniFrame extends JFrame {

	//ATTRIBUTI
    private ControllerPartecipante CP = new ControllerPartecipante();
    private Partecipante p;
    Color sfondoPrincipale = new Color(220, 240, 250);
    Color sfondoTabella = new Color(210, 240, 210);

    //COSTRUTTORI
    public LeMieIscrizioniFrame(Partecipante p) {
        this.p = p;
        setTitle("Le Mie Iscrizioni");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(MenuFactory.creaMenuBarPartecipante(this, p));

        //Pannello principale
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(panel);
        panel.setBackground(sfondoPrincipale);
        panel.setLayout(new BorderLayout(0, 20));

        //Label per inserimento titolo
        JLabel titolo = new JLabel("Le mie iscrizioni", SwingConstants.CENTER);
        titolo.setForeground(new Color(50, 80, 150));
        titolo.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(titolo, BorderLayout.NORTH);
        
        //Lista per prendere tutti i corsi dove partecipante non è iscritto 
        final List<Corso> datiCorsi = CP.GetCorsiDovePartecipanteIscritto(p);
        String[] colonne = { "Nome Corso", "Data Inizio" };
        Object[][] righe = new Object[datiCorsi.size()][2];
        for(int i = 0; i < datiCorsi.size(); i++) {
        	Corso c = datiCorsi.get(i);
        	righe[i][0] = c.getNome_Corso();
        	righe[i][1] = c.getData_Inizio();
        }
        
        //Creazione della tabella
        JTable tabellaCorsi = new JTable(righe, colonne);
        tabellaCorsi.setFont(new Font("Arial", Font.PLAIN, 16));
        tabellaCorsi.setRowHeight(28);
        tabellaCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaCorsi.setBackground(sfondoTabella);
        tabellaCorsi.setGridColor(Color.LIGHT_GRAY);

        //Creazione dello scrollPane
        JScrollPane scrollPane = new JScrollPane(tabellaCorsi);
        scrollPane.setPreferredSize(new Dimension(700, 360));

        //Pannello centrale per centrare la tabella
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centro.setBackground(sfondoPrincipale);
        centro.add(scrollPane);
        panel.add(centro, BorderLayout.CENTER);

        //Pannello per bottone indietro
        JPanel pannellobtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pannellobtn.setBackground(sfondoPrincipale);

        //Back Button 
        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnIndietro.setBackground(new Color(220, 240, 250));
        pannellobtn.add(btnIndietro);
        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DashboardUtente(p).setVisible(true);
                dispose();
            }
        });
        panel.add(pannellobtn, BorderLayout.SOUTH);
    }
}