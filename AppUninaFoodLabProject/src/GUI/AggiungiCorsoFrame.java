package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import Entities.Chef;
import Entities.Corso;
import Controller.ControllerChef;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AggiungiCorsoFrame extends JFrame {

    // ATTRIBUTI
    private ControllerChef CC = new ControllerChef();
    private JPanel contentPane;
    private Chef c;
    private JComboBox<String> categoriaComboBox;
    private JTextField dataInizioField;
    private JComboBox<String> frequenzaComboBox;

    // COSTRUTTORE
    public AggiungiCorsoFrame(Chef C) {
        this.c = C;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(CreaMenuBar(this));
        setTitle("Aggiungi Corso");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        // Label e campi
        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setFont(new Font("Arial", Font.BOLD, 14));

        categoriaComboBox = new JComboBox<>();
        categoriaComboBox.addItem("");
        categoriaComboBox.addItem("Cucina Giapponese");
        categoriaComboBox.addItem("Cucina Italiana");
        categoriaComboBox.addItem("Cucina Guatemalteca");
        categoriaComboBox.addItem("Cucina Africana");
        categoriaComboBox.addItem("Cucina Inglese");
        categoriaComboBox.addItem("Cucina Indiana");
        categoriaComboBox.addItem("Pizza Napoletana");
        categoriaComboBox.addItem("Paella");
        categoriaComboBox.addItem("Pasticceria");
        categoriaComboBox.addItem("Pala Romana");

        JLabel labelDataInizio = new JLabel("Data di inizio (gg/mm/aaaa):");
        labelDataInizio.setFont(new Font("Arial", Font.BOLD, 14));

        dataInizioField = new JTextField();

        JLabel labelFrequenza = new JLabel("Frequenza:");
        labelFrequenza.setFont(new Font("Arial", Font.BOLD, 14));

        frequenzaComboBox = new JComboBox<>();
        frequenzaComboBox.addItem("1 volta a settimana");
        frequenzaComboBox.addItem("2 volte a settimana");
        frequenzaComboBox.addItem("3 volte a settimana");
        frequenzaComboBox.addItem("Da impostare");

        JLabel labelDescrizione = new JLabel("Descrizione:");
        labelDescrizione.setFont(new Font("Arial", Font.BOLD, 14));

        // Bottoni
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

        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAggiungi.setFocusPainted(false);
        btnAggiungi.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnAggiungi.setBackground(new Color(240, 240, 240));
        btnAggiungi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Corso corso = new Corso();
                corso.setArgomento((String) categoriaComboBox.getSelectedItem());
                corso.setChef_Proprietario(C);
                corso.setData_Creazione(LocalDate.now());
                // corso.setDescrizione();
                // corso.setFrequenza_Corsi();
                // corso.setData_Inizio();

                CC.InserisciCorso(corso);
            }
        });
        
        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        // Layout
        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.CENTER)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.LEADING)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(labelCategoria, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        							.addGap(36))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(labelFrequenza, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED)))
        					.addGroup(layout.createSequentialGroup()
        						.addComponent(labelDescrizione)
        						.addPreferredGap(ComponentPlacement.RELATED)))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(labelDataInizio)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(142)
        					.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
        				.addComponent(categoriaComboBox, 300, 300, 300)
        				.addComponent(dataInizioField, 300, 300, 300)
        				.addComponent(frequenzaComboBox, 300, 300, 300)
        				.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
        			.addGap(10))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(labelCategoria)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(dataInizioField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addGap(6)
        					.addComponent(labelDataInizio)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(labelFrequenza))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(frequenzaComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(labelDescrizione)
        					.addGap(365))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))))
        );
        contentPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    }
    
    //METODI 
    private JMenu menuAttivo = null;
    
    private JMenuBar CreaMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCorsi = new JMenu("Corsi");
        JMenu menuSessioni = new JMenu("Sessioni");
        JMenu menuRicette = new JMenu("Ricette");
        JMenu menuStatsNReport = new JMenu("Stats&Reports");
        JMenu menuAccount = new JMenu("Account");

        JMenuItem itemVediCorsi = new JMenuItem("Corsi altrui");
        JMenuItem itemAggiungiCorso = new JMenuItem("Aggiungi Corso");
        JMenuItem itemAggiungiSessione = new JMenuItem("Aggiungi Sessione");
        JMenuItem itemSessioniDisponibili = new JMenuItem("Calendario Sessioni");
        JMenuItem itemListaRicette = new JMenuItem("Lista Ricette");
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
        menuSessioni.add(itemAggiungiSessione);
        menuSessioni.add(itemSessioniDisponibili);
        menuRicette.add(itemListaRicette);
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
    
    //Metodo che permette di evidenziare il menù
    private void evidenziaMenu(JMenu nuovoMenu) {
        if (menuAttivo != null) {
            ripristinaMenu(menuAttivo);
        }
        nuovoMenu.setOpaque(true);
        nuovoMenu.setBackground(new Color(100, 149, 37));
        nuovoMenu.setForeground(Color.BLACK);
        menuAttivo = nuovoMenu;
    }
    
    //Metodo che permette di ripristinare il menù dopo che è stato evidenziato
    private void ripristinaMenu(JMenu menu) {
        menu.setOpaque(false);
        menu.setBackground(null);
        menu.setForeground(Color.BLACK);
        if (menu == menuAttivo) {
            menuAttivo = null;
        }
    }
}
