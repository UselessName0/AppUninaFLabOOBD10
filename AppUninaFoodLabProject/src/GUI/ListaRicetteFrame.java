package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Entities.Chef;
import Entities.Ricetta;
import Controller.ControllerChef;

public class ListaRicetteFrame extends JFrame {

	//ATTRIBUTI
    private Chef c;
    private JTable table;
    private JMenu menuAttivo; 
    private Color sfondoPrincipale = new Color(220, 240, 250);
    private Color sfondoTabella = new Color(210, 240, 210);
    private ControllerChef CC = new ControllerChef();
    
    //COSTRUTTORI
    public ListaRicetteFrame(Chef C) {
        this.c = C;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Lista Ricette");
        getContentPane().setBackground(sfondoPrincipale);
        getContentPane().setLayout(new BorderLayout(10, 10));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        creaMenuBar();

        //Label Titolo
        JLabel lblTitolo = new JLabel("Lista Ricette", JLabel.CENTER);
        lblTitolo.setForeground(new Color(50, 80, 150));
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 24));
        getContentPane().add(lblTitolo, BorderLayout.NORTH);

        //Creazione della Tabella
        List<Ricetta> ricette = CC.GetAllRicette();
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Nome", "Descrizione" }
        ));
        table.setBackground(sfondoTabella);
        table.setFillsViewportHeight(true);
        
        for (Ricetta r : ricette) {
			((DefaultTableModel) table.getModel()).addRow(new Object[] { r.getTitolo(), r.getDescrizione() });
		}

        //ScrollPane per lo scorrimento di tutta la tabella 
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));

        //Pannello centrale (usato per la tabella) 
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(sfondoPrincipale);
        centerPanel.add(scrollPane);
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        //Back Button
        JButton btnIndietro = new JButton("← Indietro");
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

        //Pannello sottostante usato per centrare il Back Button 
        JPanel southPanel = new JPanel();
        southPanel.setBackground(sfondoPrincipale);
        southPanel.add(btnIndietro);
        getContentPane().add(southPanel, BorderLayout.SOUTH);
    }

    //METODI
    //Metodo per la creazione della menu bar 
    private void creaMenuBar() {
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
        JMenuItem itemInfo = new JMenuItem("Il mio profilo");
        JMenuItem itemLogout = new JMenuItem("Logout");

        //Listener 
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

        itemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ilMioProfiloFrame(c).setVisible(true);
                dispose();
            }
        });

        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemAggiungiCorso);
        menuCorsi.add(itemImieiCorsi);
        menuSessioni.add(itemAggiungiSessione);
        menuSessioni.add(itemSessioniDisponibili);
        menuRicette.add(itemListaRicette);
        menuRicette.add(itemCreaRicetta);
        menuStatsNReport.add(itemStatistiche);
        menuAccount.add(itemInfo);
        menuAccount.add(itemLogout);
        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuStatsNReport);
        menuBar.add(menuAccount);
        setJMenuBar(menuBar);
    }

    //Metodo per evidenzare il menù
    private void evidenziaMenu(JMenu nuovoMenu) {
        if (menuAttivo != null) {
            ripristinaMenu(menuAttivo);
        }
        nuovoMenu.setOpaque(true);
        nuovoMenu.setBackground(new Color(100, 149, 37));
        nuovoMenu.setForeground(Color.BLACK);
        menuAttivo = nuovoMenu;
    }

    //Metodo per ripristinare il menù
    private void ripristinaMenu(JMenu menu) {
        menu.setOpaque(false);
        menu.setBackground(null);
        menu.setForeground(Color.BLACK);
        if (menu == menuAttivo) {
            menuAttivo = null;
        }
    }
}