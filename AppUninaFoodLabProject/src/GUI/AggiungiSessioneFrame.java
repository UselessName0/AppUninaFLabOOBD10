package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Entities.Chef;

public class AggiungiSessioneFrame extends JFrame {

	// ATTRIBUTI
	private JPanel contentPane;
	private Chef c;
    private JComboBox<String> selezionaCorsoComboBox;
    private JTextField luogoField;
    private JTextField conferenzaField;
    private JTextField dataInizioField;
    private JCheckBox  praticaCheckBox;

	// COSTRUTTORI
	public AggiungiSessioneFrame(Chef C) {
		this.c = C;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(CreaMenuBar(this));
        Color sfondoPrincipale = new Color(220, 240, 250);
        setTitle("Aggiungi Sessione");
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setBackground(sfondoPrincipale);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        
        JLabel titoloLabel = new JLabel("Aggiungi una sessione", SwingConstants.CENTER);
        titoloLabel.setForeground(new Color(50, 80, 150));
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        //Bottoni
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnIndietro = new JButton("← Indietro");
        buttonPanel.add(btnIndietro);
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnIndietro.setBackground(new Color(240, 240, 240));
        btnIndietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new DashboardChef(c).setVisible(true);
        		dispose();
        	}
        });
        
        JButton btnAggiungi_1 = new JButton("Aggiungi");
        btnAggiungi_1.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAggiungi_1.setFocusPainted(false);
        btnAggiungi_1.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnAggiungi_1.setBackground(UIManager.getColor("Button.background"));

        JLabel labelCorsoRelativo = new JLabel("Corso relativo:");
        labelCorsoRelativo.setFont(new Font("Arial", Font.BOLD, 18));

        selezionaCorsoComboBox = new JComboBox<>();
        selezionaCorsoComboBox.addItem("");
        selezionaCorsoComboBox.addItem("METTERE I CORSI CHEF");
        
        JLabel labelDataInizio = new JLabel("Data inizio:");
        labelDataInizio.setFont(new Font("Arial", Font.BOLD, 18));
        dataInizioField = new JTextField();
        
        JLabel labelPratica = new JLabel("Sessione pratica:");
        labelPratica.setFont(new Font("Arial", Font.BOLD, 18));
        praticaCheckBox = new JCheckBox();
        praticaCheckBox.setBackground(sfondoPrincipale);

        JLabel labelLuogo = new JLabel("Luogo:");
        labelLuogo.setFont(new Font("Arial", Font.BOLD, 18));
        luogoField = new JTextField();

        JLabel labelConferenza = new JLabel("Conferenza:");
        labelConferenza.setFont(new Font("Arial", Font.BOLD, 18));
        conferenzaField = new JTextField();
        
        //aggiungere coso di ricetta che non so
                        
        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(titoloLabel, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(labelCorsoRelativo, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        						.addComponent(labelDataInizio, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        						.addComponent(labelPratica, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE) 
        						.addComponent(labelLuogo, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        						.addComponent(labelConferenza, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(selezionaCorsoComboBox, 300, 300, 300)
        						.addComponent(dataInizioField, 300, 300, 300)
        						.addComponent(praticaCheckBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(luogoField, 300, 300, 300)
        						.addComponent(conferenzaField, 300, 300, 300))))
        			.addContainerGap())
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(400, Short.MAX_VALUE)
        			.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        			.addGap(43)
        			.addComponent(btnAggiungi_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        			.addGap(20))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(titoloLabel)
        			.addGap(30)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(selezionaCorsoComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelCorsoRelativo))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(dataInizioField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelDataInizio))
        			.addGap(6)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(praticaCheckBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelPratica))
        			.addGap(6)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(luogoField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelLuogo))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(conferenzaField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelConferenza))
        			.addPreferredGap(ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnAggiungi_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        contentPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
	}
	
	// METODI 
    private JMenu menuAttivo = null;
    
    private JMenuBar CreaMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCorsi = new JMenu("Corsi");
        JMenu menuSessioni = new JMenu("Sessioni");
        JMenu menuRicette = new JMenu("Ricette");
        JMenu menuStatsNReport = new JMenu("Stats&Reports");
        JMenu menuAccount = new JMenu("Account");

        JMenuItem itemVediCorsi = new JMenuItem("Corsi Altrui");
        JMenuItem itemAggiungiCorso = new JMenuItem("Aggiungi Corso");
        JMenuItem itemImieiCorsi = new JMenuItem("I Miei Corsi");
        JMenuItem itemAggiungiSessione = new JMenuItem("Aggiungi Sessione");
        JMenuItem itemSessioniDisponibili = new JMenuItem("Calendario Sessioni");
        JMenuItem itemListaRicette = new JMenuItem("Lista Ricette");
        JMenuItem itemCreaRicetta = new JMenuItem("Crea Ricetta");
        JMenuItem itemStatistiche = new JMenuItem("Statistiche");
        JMenuItem itemReport = new JMenuItem("Report");
        JMenuItem itemInfo = new JMenuItem("Il mio profilo");
        JMenuItem itemLogout = new JMenuItem("Logout");

        itemLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WelcomeFrame().setVisible(true);
                dispose();
            }
        });

        itemVediCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CorsiDisponibiliFrame(c).setVisible(true);
                dispose();
            }
        });

        itemAggiungiCorso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AggiungiCorsoFrame(c).setVisible(true);
                dispose();
            }
        });
        
        itemImieiCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IMieiCorsiFrame(c).setVisible(true);
                dispose();
            }
        });

        itemAggiungiSessione.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AggiungiSessioneFrame(c).setVisible(true);
                dispose();
            }
        });

        itemSessioniDisponibili.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CalendarioSessioniFrame(c).setVisible(true);
                dispose();
            }
        });

        itemListaRicette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListaRicetteFrame(c).setVisible(true);
                dispose();
            }
        });
        
        itemCreaRicetta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreaRicettaFrame(c).setVisible(true);
                dispose();
            }
        });
        
        itemStatistiche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatisticheFrame(c).setVisible(true);
                dispose();
            }
        });
        
        itemReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReportFrame(c).setVisible(true);
                dispose();
            }
        });

        itemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ilMioProfiloFrame(c).setVisible(true);
                dispose();
            }
        });

        MenuListener menuListener = new MenuListener() {
            public void menuSelected(MenuEvent e) {
                evidenziaMenu((JMenu) e.getSource());
            }
            public void menuDeselected(MenuEvent e) {
                ripristinaMenu((JMenu) e.getSource());
            }
            public void menuCanceled(MenuEvent e) {
                ripristinaMenu((JMenu) e.getSource());
            }
        };

        menuCorsi.addMenuListener(menuListener);
        menuSessioni.addMenuListener(menuListener);
        menuRicette.addMenuListener(menuListener);
        menuRicette.addMenuListener(menuListener);
        menuStatsNReport.addMenuListener(menuListener);
        menuAccount.addMenuListener(menuListener);

        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemAggiungiCorso);
        menuCorsi.add(itemImieiCorsi);
        menuSessioni.add(itemAggiungiSessione);
        menuSessioni.add(itemSessioniDisponibili);
        menuRicette.add(itemListaRicette);
        menuRicette.add(itemCreaRicetta);
        menuStatsNReport.add(itemStatistiche);
        menuStatsNReport.add(itemReport);
        menuAccount.add(itemInfo);
        menuAccount.add(itemLogout);

        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuStatsNReport);
        menuBar.add(menuAccount);

        setJMenuBar(menuBar);
        return menuBar;
    }
    
    // Metodo che permette di evidenziare il menù
    private void evidenziaMenu(JMenu nuovoMenu) {
        if (menuAttivo != null) {
            ripristinaMenu(menuAttivo);
        }
        nuovoMenu.setOpaque(true);
        nuovoMenu.setBackground(new Color(100, 149, 37));
        nuovoMenu.setForeground(Color.BLACK);
        menuAttivo = nuovoMenu;
    }
    
    // Metodo che permette di ripristinare il menù dopo che è stato evidenziato
    private void ripristinaMenu(JMenu menu) {
        menu.setOpaque(false);
        menu.setBackground(null);
        menu.setForeground(Color.BLACK);
        if (menu == menuAttivo) {
            menuAttivo = null;
        }
    }
}
