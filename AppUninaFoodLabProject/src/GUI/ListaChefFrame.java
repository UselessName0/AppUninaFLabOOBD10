package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Controller.ControllerPartecipante;
import Entities.Chef;
import Entities.Partecipante;

public class ListaChefFrame extends JFrame {

	//ATTRIBUTI
    private ControllerPartecipante CP = new ControllerPartecipante();
    private Partecipante p;
    private JMenu menuAttivo = null;
    Color sfondoPrincipale = new Color(220, 240, 250);
    Color sfondoTabella = new Color(210, 240, 210);
    
    //COSTRUTTORI
    public ListaChefFrame(Partecipante p) {
    	this.p = p;
        setTitle("Lista Chef");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(creaMenuBar());
        
        //Pannello principale
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(panel);
        panel.setBackground(sfondoPrincipale);
        panel.setLayout(new BorderLayout(0, 20));

        JLabel titolo = new JLabel("Lista Chef", SwingConstants.CENTER);
        titolo.setForeground(new Color(50, 80, 150));
        titolo.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(titolo, BorderLayout.NORTH);
        
        //Lista di tutti gli chef presenti nel DB
        final List<Chef> listaChef = CP.GetListaChefFromDB();
        String[] colonne = { "Nome", "Cognome" };
        Object[][] righe = new Object[listaChef.size()][2];
        for(int i = 0; i < listaChef.size(); i++) {
			Chef chef = listaChef.get(i);
			righe[i][0] = chef.getNome();
			righe[i][1] = chef.getCognome();
		}
        
        //Tabella per tutti i Corsi 
        JTable tabellaCorsi = new JTable(righe, colonne);
        tabellaCorsi.setFont(new Font("Arial", Font.PLAIN, 16));
        tabellaCorsi.setRowHeight(28);
        tabellaCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaCorsi.setBackground(sfondoTabella);
        tabellaCorsi.setGridColor(Color.LIGHT_GRAY);
   
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
    //Metodo per la creazione della menu bar
    private JMenuBar creaMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCorsi = new JMenu("Corsi");
        JMenu menuSessioni = new JMenu("Sessioni");
        JMenu menuChef = new JMenu("Chef");
        JMenu menuRicette = new JMenu("Ricette");
        JMenu menuAccount = new JMenu("Account");
        JMenuItem itemVediCorsi = new JMenuItem("Corsi Disponibili");
        JMenuItem itemMieIscrizioni = new JMenuItem("Le Mie Iscrizioni");
        JMenuItem itemVediSessioni = new JMenuItem("Le Mie Sessioni");
        JMenuItem itemVediCalendario = new JMenuItem("Calendario Sessioni");
        JMenuItem itemListaChef = new JMenuItem("Lista Chef");
        JMenuItem itemLeMieRicette = new JMenuItem("Le Mie Ricette");
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
                new CorsiDisponibiliFrame(p).setVisible(true);
                dispose();
            }
        });

        itemMieIscrizioni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieIscrizioniFrame(p).setVisible(true);
                dispose();
            }
        });

        itemVediSessioni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieSessioniFrame(p).setVisible(true);
                dispose();
            }
        });

        itemVediCalendario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CalendarioSessioniFrame(p).setVisible(true);
                dispose();
            }
        });

        itemListaChef.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListaChefFrame(p).setVisible(true);
                dispose();
            }
        });

        itemLeMieRicette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieRicetteFrame(p).setVisible(true);
                dispose();
            }
        });

        itemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ilMioProfiloFrame(p).setVisible(true);
                dispose();
            }
        });

        MenuListener evidenziaListener = new MenuListener() {
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

        menuCorsi.addMenuListener(evidenziaListener);
        menuSessioni.addMenuListener(evidenziaListener);
        menuChef.addMenuListener(evidenziaListener);
        menuRicette.addMenuListener(evidenziaListener);
        menuAccount.addMenuListener(evidenziaListener);
        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemMieIscrizioni);
        menuSessioni.add(itemVediSessioni);
        menuSessioni.add(itemVediCalendario);
        menuChef.add(itemListaChef);
        menuRicette.add(itemLeMieRicette);
        menuAccount.add(itemInfo);
        menuAccount.add(itemLogout);
        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuChef);
        menuBar.add(menuAccount);
        
        return menuBar;
    }

    //Metodo per evidenzare menù
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