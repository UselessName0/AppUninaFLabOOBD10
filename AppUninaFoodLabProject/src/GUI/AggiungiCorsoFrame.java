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
import javax.swing.border.LineBorder;


public class AggiungiCorsoFrame extends JFrame {

    // ATTRIBUTI
    private ControllerChef CC = new ControllerChef();
    private JPanel contentPane;
    private Chef c;
    private JComboBox<String> categoriaComboBox;
    private JTextField nomeCorsoField;
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
        Color sfondoPrincipale = new Color(220, 240, 250);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setBackground(sfondoPrincipale);

        // Titolo
        JLabel titoloLabel = new JLabel("Aggiungi un corso", SwingConstants.CENTER);
        titoloLabel.setForeground(new Color(50, 80, 150));
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Label e campi
        JLabel labelNomeCorso = new JLabel("Nome corso:");
        labelNomeCorso.setFont(new Font("Arial", Font.BOLD, 18));
        nomeCorsoField = new JTextField();

        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setFont(new Font("Arial", Font.BOLD, 18));

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

        JLabel labelFrequenza = new JLabel("Frequenza:");
        labelFrequenza.setFont(new Font("Arial", Font.BOLD, 18));

        frequenzaComboBox = new JComboBox<>();
        frequenzaComboBox.addItem("");
        frequenzaComboBox.addItem("1 volta a settimana");
        frequenzaComboBox.addItem("2 volte a settimana");
        frequenzaComboBox.addItem("3 volte a settimana");
        frequenzaComboBox.addItem("Libera");

        JLabel labelDescrizione = new JLabel("Descrizione:");
        labelDescrizione.setFont(new Font("Arial", Font.BOLD, 18));
        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBorder(new LineBorder(Color.BLACK, 1));
        
        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnIndietro.setBackground(UIManager.getColor("Button.background"));
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
        btnAggiungi_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Corso corso = new Corso();
                corso.setArgomento((String) categoriaComboBox.getSelectedItem());
                corso.setNome_Corso(nomeCorsoField.getText());
                corso.setChef_Proprietario(C);
                corso.setData_Creazione(LocalDate.now());
                corso.setDescrizione(textArea.getText());
                corso.setFrequenza_Corsi((String) frequenzaComboBox.getSelectedItem());

                if(CC.InsertCorso(corso)) {
					JOptionPane.showMessageDialog(contentPane, "Corso aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
					new DashboardChef(c).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Errore durante l'aggiunta del corso. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
				}
            }
        });

        // Layout aggiornato
        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(titoloLabel, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(labelNomeCorso, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        								.addComponent(labelCategoria, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        								.addComponent(labelFrequenza, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        								.addComponent(labelDescrizione))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(nomeCorsoField, 300, 300, 300)
        								.addComponent(categoriaComboBox, 300, 300, 300)
        								.addComponent(frequenzaComboBox, 300, 300, 300)
        								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        							.addGap(43)
        							.addComponent(btnAggiungi_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
        					.addContainerGap())))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(titoloLabel)
        			.addGap(30)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nomeCorsoField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelNomeCorso))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelCategoria))
        			.addGap(6)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(frequenzaComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelFrequenza))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelDescrizione))
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
    
    //METODI 
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
        
        itemImieiCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IMieiCorsiFrame(c).setVisible(true);
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
        menuCorsi.add(itemImieiCorsi);
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
