package GUI;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Controller.ControllerPartecipante;
import Entities.Chef;
import Entities.Corso;
import Entities.Partecipante;

public class CorsiDisponibiliFrame extends JFrame {
	ControllerPartecipante CP = new ControllerPartecipante();
    Partecipante p;
	private JMenu menuAttivo = null;

    public CorsiDisponibiliFrame(Partecipante p) {
    	this.p = p;
        setTitle("Corsi Disponibili");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(creaMenuBar(this));

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

        
        final List<Corso> datiCorsi = CP.GetCorsiDovePartecipanteNonIscritto(p);
        
        String[] colonne = {"Nome Corso", "Chef"};
        
        Object[][] righe = new Object[datiCorsi.size()][3];
        for(int i = 0; i< datiCorsi.size(); i++) {
        	Corso c = datiCorsi.get(i);
        	righe[i][0] = c.getNome_Corso();
        	righe[i][1] = c.getChef_Proprietario().getNome() + " " + c.getChef_Proprietario().getCognome();
        	righe[i][2] = "DESCRIZIONE";
        	}
        
        
        
        final JTable tabellaCorsi = new JTable(righe, colonne);
        tabellaCorsi.setFont(new Font("Arial", Font.PLAIN, 16));
        tabellaCorsi.setRowHeight(28);
        tabellaCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaCorsi.setBackground(sfondoTabella);
        tabellaCorsi.setGridColor(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(tabellaCorsi);

        tabellaCorsi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int riga = tabellaCorsi.getSelectedRow();
                    if (riga >= 0) {
                    	Corso c = datiCorsi.get(riga);
                        mostraDettagliCorso(c);
                    }
                }
            }
        });

        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setBackground(new Color(180, 220, 200));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 100), 1));
        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DashboardUtente().setVisible(true);
                dispose();
            }
        });

        JPanel pannelloCentrale = new JPanel();
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));
        pannelloCentrale.setBackground(sfondoPrincipale);

        pannelloCentrale.add(titolo);
        pannelloCentrale.add(Box.createVerticalStrut(20));
        pannelloCentrale.add(scrollPane);
        pannelloCentrale.add(Box.createVerticalStrut(20));
        pannelloCentrale.add(btnIndietro);

        contentPane.add(pannelloCentrale, BorderLayout.CENTER);
    }

    private void mostraDettagliCorso(Corso c) {
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

        //Suddivisione: descrizione, data e frequenza
//        String[] righe = descrizioneCompleta.split("\n");
        String descrizione = "descrizione";
        String dataInizio = c.getData_Inizio().toString();
        String frequenza = c.getFrequenza_Corsi();

        JTextArea txtDescrizione = new JTextArea(descrizione);
        txtDescrizione.setBounds(20, 90, 400, 60);
        txtDescrizione.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setLineWrap(true);
        txtDescrizione.setEditable(false);
        txtDescrizione.setBackground(new Color(245, 250, 255));
        txtDescrizione.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblData = new JLabel(dataInizio);
        lblData.setBounds(20, 160, 400, 25);
        lblData.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblFrequenza = new JLabel(frequenza);
        lblFrequenza.setBounds(20, 190, 400, 25);
        lblFrequenza.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnIscriviti = new JButton("Iscriviti");
        btnIscriviti.setBounds(160, 230, 100, 30);
        btnIscriviti.setBackground(new Color(180, 220, 240));
        btnIscriviti.setBorder(BorderFactory.createLineBorder(new Color(120, 180, 220), 1));
        btnIscriviti.setFocusPainted(false);
        btnIscriviti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(finestraDettagli, "🎉 Iscritto al corso " + c.getNome_Corso() + "!");
                finestraDettagli.dispose();
            }
        });

        finestraDettagli.getContentPane().add(lblNome);
        finestraDettagli.getContentPane().add(lblChef);
        finestraDettagli.getContentPane().add(txtDescrizione);
        finestraDettagli.getContentPane().add(lblData);
        finestraDettagli.getContentPane().add(lblFrequenza);
        finestraDettagli.getContentPane().add(btnIscriviti);

        finestraDettagli.setResizable(false);
        finestraDettagli.setVisible(true);
    }

    private JMenuBar creaMenuBar(JFrame frame) {
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
                new LeMieIscrizioniFrame().setVisible(true);
                dispose();
            }
        });

        itemVediSessioni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieSessioniFrame().setVisible(true);
                dispose();
            }
        });

        itemVediCalendario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CalendarioSessioniFrame().setVisible(true);
                dispose();
            }
        });

        itemListaChef.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListaChefFrame().setVisible(true);
                dispose();
            }
        });

        itemLeMieRicette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieRicetteFrame().setVisible(true);
                dispose();
            }
        });

        itemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ilMioProfiloFrame().setVisible(true);
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
        menuChef.addMenuListener(menuListener);
        menuRicette.addMenuListener(menuListener);
        menuAccount.addMenuListener(menuListener);

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

    //Metodo per evidenziare il menù creando un effetto hover
    private void evidenziaMenu(JMenu nuovoMenu) {
        if (menuAttivo != null) {
            ripristinaMenu(menuAttivo);
        }
        nuovoMenu.setOpaque(true);
        nuovoMenu.setBackground(new Color(100, 149, 37));
        nuovoMenu.setForeground(Color.BLACK);
        menuAttivo = nuovoMenu;
    }

    private void ripristinaMenu(JMenu menu) {
        menu.setOpaque(false);
        menu.setBackground(null);
        menu.setForeground(Color.BLACK);
        if (menu == menuAttivo) {
            menuAttivo = null;
        }
    }
}
