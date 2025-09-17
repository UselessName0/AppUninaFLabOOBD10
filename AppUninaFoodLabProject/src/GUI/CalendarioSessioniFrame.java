package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerPartecipante;
import Controller.ControllerChef;
import Entities.Chef;
import Entities.Corso;
import Entities.MenuFactory;
import Entities.Partecipante;
import Entities.Sessione;

public class CalendarioSessioniFrame extends JFrame {
	
	//ATTRIBUTI
    private ControllerPartecipante CP = new ControllerPartecipante();
    private ControllerChef CC = new ControllerChef();
    private Partecipante p;
    private Chef c;
    Color sfondoPrincipale = new Color(220, 240, 250);
    Color sfondoTabella = new Color(210, 240, 210);
    
    //COSTRUTTORI
    public CalendarioSessioniFrame(Chef C) {
    	this.c = C;
        setTitle("Calendario Sessioni");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(MenuFactory.creaMenuBarChef(this, c));
        
        //Pannello principale
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(panel);
        panel.setBackground(sfondoPrincipale);
        panel.setLayout(new BorderLayout(0, 20));

        JLabel titolo = new JLabel("Calendario Sessioni", SwingConstants.CENTER);
        titolo.setForeground(new Color(50, 80, 150));
        titolo.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(titolo, BorderLayout.NORTH);
    	
        //Creazione lista delle sessioni per chef 
        List<Sessione> listaSessioni = CC.GetSessioniByChef(c);
        if(listaSessioni == null || listaSessioni.isEmpty()) {
        	JOptionPane.showMessageDialog(null, "Nessuna sessione disponibile.");
        	dispose();
			return;
		}
        String[] colonne = { "Nome Corso", "Data Sessione" , "Ricetta"};
        Object[][] righe = new Object[listaSessioni.size()][3];
        for(int i = 0; i<listaSessioni.size(); i++) {
			Sessione s = listaSessioni.get(i);
			righe[i][0] = s.getRelatedCorso().getNome_Corso();
			righe[i][1] = s.getData_Sessione().toString();
			righe[i][2] = s.getRicetta_Appresa().getTitolo();
		}
        
        //Creazione tabella
        DefaultTableModel modelloNonEditabile = new DefaultTableModel(righe, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tutte le celle non editabili
            }
        };

        JTable tabellaCorsi = new JTable(modelloNonEditabile);
        tabellaCorsi.setFont(new Font("Arial", Font.PLAIN, 16));
        tabellaCorsi.setRowHeight(28);
        tabellaCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaCorsi.setBackground(sfondoTabella);
        tabellaCorsi.setGridColor(Color.LIGHT_GRAY);

   
        //Creazione ScrollPane per la tabella
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
                new DashboardChef(c).setVisible(true);
                dispose();
            }
        });

        panel.add(pannellobtn, BorderLayout.SOUTH);
    }
    
    //Costruttore ma per un oggetto di tipo partecipante 
    public CalendarioSessioniFrame(Partecipante p) {
    	this.p = p;
        setTitle("Calendario Sessioni");
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

        JLabel titolo = new JLabel("Calendario Sessioni", SwingConstants.CENTER);
        titolo.setForeground(new Color(50, 80, 150));
        titolo.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(titolo, BorderLayout.NORTH);
    	
        //Lista per ospitare tutte le sessioni dove il partecipante (nel DB) non è iscritto
        List<Sessione> listaSessioni = CP.GetListaSessioniDovePartecipanteNonIscritto(p);
        String[] colonne = { "Nome Corso", "Data Sessione", "Ricetta" };
        Object[][] righe = new Object[listaSessioni.size()][3];
        for(int i = 0; i<listaSessioni.size(); i++) {
			Sessione s = listaSessioni.get(i);
			righe[i][0] = s.getRelatedCorso().getNome_Corso();
			righe[i][1] = s.getData_Sessione().toString();
			righe[i][2] = s.getRicetta_Appresa().getTitolo();		
		}
        
        //Creazione tabella
        DefaultTableModel modelloNonEditabile = new DefaultTableModel(righe, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabellaCorsi = new JTable(modelloNonEditabile);
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
                        Sessione s = listaSessioni.get(riga);
                        mostraDettagliSessionePartecipante(s, p);
                    }
                }
            }
        });

   
        //ScrollPane per la tabella
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
    
    
    //METODI
    //Metodo per mostrare tutti i dettaglli delle sessioni per un partecipante
    private void mostraDettagliSessionePartecipante(Sessione s, Partecipante p) {
    	JFrame finestraDettagli = new JFrame("Dettagli della Sessione");
        finestraDettagli.setSize(440, 360);
        finestraDettagli.setLocationRelativeTo(null);
        finestraDettagli.getContentPane().setLayout(null);
        finestraDettagli.getContentPane().setBackground(new Color(230, 240, 250));

        JLabel lblNome = new JLabel("Corso: " + s.getRelatedCorso().getNome_Corso());
        lblNome.setBounds(20, 20, 400, 25);
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));
        
        Corso co = s.getRelatedCorso();
        Chef c = co.getChef_Proprietario();
        JLabel lblChef = new JLabel("Chef: " + c.getNome() + " " + c.getCognome());
        lblChef.setBounds(20, 50, 400, 25);
        lblChef.setFont(new Font("Arial", Font.PLAIN, 14));

        LocalDate dataSessione = s.getData_Sessione();
        String dataSessioneString = "Data Sessione: " + dataSessione.toString();

        JLabel lblData = new JLabel(dataSessioneString);
        lblData.setBounds(20, 80, 400, 25);
        lblData.setFont(new Font("Arial", Font.PLAIN, 14));
        
   
        JLabel lblTipologia = new JLabel("");
        lblTipologia.setBounds(20, 110, 400, 25);
        lblTipologia.setFont(new Font("Arial", Font.PLAIN, 14));
        		
        JLabel lblDiscriminato = new JLabel("");
        lblDiscriminato.setBounds(20, 140, 400, 25);
        lblDiscriminato.setFont(new Font("Arial", Font.PLAIN, 14));
        
        if(s.isIsPratica()) {
			lblTipologia.setText("Tipologia: Pratica");
			lblDiscriminato.setText("Luogo: " + s.getLuogo());	
        }else {
        	lblTipologia.setText("Tipologia: Teorica");
        	lblDiscriminato.setText("Link: " + s.getLinkConferenza());
		}
        
        finestraDettagli.getContentPane().add(lblNome);
        finestraDettagli.getContentPane().add(lblChef);
        finestraDettagli.getContentPane().add(lblData);
        finestraDettagli.getContentPane().add(lblTipologia);
        finestraDettagli.getContentPane().add(lblDiscriminato);
        

        //Bottone per iscrizione 
        JButton btnIscriviti = new JButton("Iscriviti");
        btnIscriviti.setBounds(160, 260, 100, 30);
        btnIscriviti.setBackground(new Color(180, 220, 240));
        btnIscriviti.setBorder(BorderFactory.createLineBorder(new Color(120, 180, 220), 1));
        btnIscriviti.setFocusPainted(false);
        btnIscriviti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(CP.IscriviPartecipanteASessione(p, s))
            		{
            		JOptionPane.showMessageDialog(finestraDettagli, "🎉 Iscritto alla sessione!");
            		}
            	else
            		{
            		JOptionPane.showMessageDialog(finestraDettagli, "Errore nell'iscrizione alla sessione!");
            		}
                finestraDettagli.dispose();
            }
        });

        finestraDettagli.setResizable(false);
        finestraDettagli.setVisible(true);
        finestraDettagli.getContentPane().add(btnIscriviti);
		
	}
}