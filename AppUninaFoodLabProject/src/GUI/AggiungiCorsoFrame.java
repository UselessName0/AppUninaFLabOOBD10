package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Entities.Chef;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AggiungiCorsoFrame extends JFrame {

    private JPanel contentPane;
    private Chef c;
    Color sfondoPrincipale = new Color(220, 240, 250);

    // Componenti per l'inserimento dei dati
    private JComboBox<String> categoriaComboBox;
    private JTextField dataInizioField;
    private JTextField frequenzaField;
    private JTextField numeroSessioniField;

    public AggiungiCorsoFrame(Chef C) {
        
    	this.c = C;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Impostazioni base del frame
        setTitle("Aggiungi Corso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);

        // Pannello principale
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Pannello centrale per i campi di input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        // Categoria
        JLabel label_2 = new JLabel("Categoria:");
        label_2.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(label_2);
        categoriaComboBox = new JComboBox<>();
        categoriaComboBox.addItem("Primi");
        categoriaComboBox.addItem("Secondi");
        categoriaComboBox.addItem("Dolci");
        categoriaComboBox.addItem("Vegetariano");
        inputPanel.add(categoriaComboBox);

        // Data di inizio
        JLabel label_1 = new JLabel("Data di inizio (gg/mm/aaaa):");
        label_1.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(label_1);
        dataInizioField = new JTextField();
        inputPanel.add(dataInizioField);

        // Frequenza
        JLabel lblFrequenza = new JLabel("Frequenza:");
        lblFrequenza.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(lblFrequenza);
        frequenzaField = new JTextField();
        inputPanel.add(frequenzaField);

        // Numero di sessioni
        JLabel label = new JLabel("Numero di sessioni:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(label);
        numeroSessioniField = new JTextField();
        inputPanel.add(numeroSessioniField);

        // Pannello inferiore per i pulsanti
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
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
        			.addGap(74))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(20)
        			.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
        			.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        
        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAggiungi.setFocusPainted(false);
        btnAggiungi.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnAggiungi.setBackground(new Color(240, 240, 240));
        buttonPanel.add(btnAggiungi);
        contentPane.setLayout(gl_contentPane);
    }
    
private JMenu menuAttivo = null;
    
    private JMenuBar CreaMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCorsi = new JMenu("Corsi");
        JMenu menuSessioni = new JMenu("Sessioni");
        JMenu menuRicette = new JMenu("Ricette");
        JMenu menuStatsNReport = new JMenu("Stats&Reports");
        JMenu menuAccount = new JMenu("Account");

        JMenuItem itemVediCorsi = new JMenuItem("Corsi Disponibili");
        JMenuItem itemAggiungiCorso = new JMenuItem("Aggiungi Corso");
        JMenuItem itemAggiungiSessione = new JMenuItem("Aggiungi Sessione");
        JMenuItem itemSessioniDisponibili = new JMenuItem("Sessioni Disponibili");
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
                new SessioniDisponibiliFrame(c).setVisible(true);
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
