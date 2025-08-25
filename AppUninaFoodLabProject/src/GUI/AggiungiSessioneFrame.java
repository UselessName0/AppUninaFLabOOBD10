package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.toedter.calendar.JCalendar;

import Controller.ControllerChef;
import DAO.CorsoDAO;
import DAO.RicettaDAO;
import Entities.Chef;
import Entities.Corso;
import Entities.Ricetta;

public class AggiungiSessioneFrame extends JFrame {

	// ATTRIBUTI
	private JPanel contentPane;
	private Chef c;
    private JComboBox<String> selezionaCorsoComboBox;
    private JTextField discriminatoField;
    private JCalendar dataInizioCalendar;
    private JCheckBox praticaCheckBox;
    private JComboBox<String> selezionaRicettaComboBox; 
    ControllerChef CC = new ControllerChef();
    
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
        
        // Bottoni
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnIndietro = new JButton("← Indietro");
        buttonPanel.add(btnIndietro);
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
				CorsoDAO coDAO = new CorsoDAO();
				String corsoSelezionato = (String) selezionaCorsoComboBox.getSelectedItem();
				String[] parts = corsoSelezionato.split(" \\(");
				String nomeCorso = parts[0];
				String idCorso = parts[1].replace(")", "");
				
				RicettaDAO rDAO = new RicettaDAO();
				String ricettaSelezionata = (String) selezionaRicettaComboBox.getSelectedItem();
				String[] partsRicetta = ricettaSelezionata.split(" \\(");
				String nomeRicetta = partsRicetta[0];
				String idRicetta = partsRicetta[1].replace(")", "");
				
				Date dataSelezionata = dataInizioCalendar.getDate();
				if(dataSelezionata == null) {
					JOptionPane.showMessageDialog(null, "Seleziona una data.", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				LocalDate data = dataSelezionata.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if(data.isBefore(LocalDate.now())) {
					JOptionPane.showMessageDialog(null, "La data non può essere precedente a oggi.", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean sessionePratica = praticaCheckBox.isSelected();
				String discriminato = discriminatoField.getText();
				if(sessionePratica) {
					if(CC.InserisciSessione(c, coDAO.getCorsoByID(idCorso), data, sessionePratica, discriminato, null, rDAO.getRicettaByID(idRicetta)))
					{
						JOptionPane.showMessageDialog(null, "Sessione aggiunta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
						new DashboardChef(c).setVisible(true);
						dispose();
					}
				} else {
					if(CC.InserisciSessione(c, coDAO.getCorsoByID(idCorso), data, sessionePratica, null, discriminato, rDAO.getRicettaByID(idRicetta))) {
						JOptionPane.showMessageDialog(null, "Sessione aggiunta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
						new DashboardChef(c).setVisible(true);
						dispose();
				}
					else {
						JOptionPane.showMessageDialog(null, "Errore durante l'aggiunta della sessione.", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

        JLabel labelCorsoRelativo = new JLabel("Corso relativo:");
        labelCorsoRelativo.setFont(new Font("Arial", Font.BOLD, 18));

        List<Corso> corsi = CC.GetCorsiByChef(c);
        selezionaCorsoComboBox = new JComboBox<>();
        for (Corso corso : corsi) {
			selezionaCorsoComboBox.addItem(corso.getNome_Corso() + " (" + corso.getID_Corso() + ")");
		}
        
        JLabel labelDataInizio = new JLabel("Data:");
        labelDataInizio.setFont(new Font("Arial", Font.BOLD, 18));
        dataInizioCalendar = new JCalendar();
        dataInizioCalendar.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel labelPratica = new JLabel("Sessione pratica:");
        labelPratica.setFont(new Font("Arial", Font.BOLD, 18));
        praticaCheckBox = new JCheckBox();
        praticaCheckBox.setBackground(sfondoPrincipale);
      
        JLabel labelDiscriminato = new JLabel("Link Conferenza");
        labelDiscriminato.setFont(new Font("Arial", Font.BOLD, 18));
        discriminatoField = new JTextField();

        praticaCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (praticaCheckBox.isSelected()) {
					labelDiscriminato.setText("Luogo:");
				} else {
					labelDiscriminato.setText("Link Conferenza:");
				}
			}
		});

        JLabel labelRicetta = new JLabel("Ricetta:");
        labelRicetta.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Nuovo testo cliccabile accanto alla JComboBox
        JLabel testoCliccabile = new JLabel("Crea ricetta");
        testoCliccabile.setFont(new Font("Arial", Font.PLAIN, 14));
        testoCliccabile.setForeground(Color.BLUE);
        testoCliccabile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        testoCliccabile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new CreaRicettaFrame(c).setVisible(true);
                dispose();
            }
        });

        selezionaRicettaComboBox = new JComboBox<String>();
        List<Ricetta> ricette = CC.GetAllRicette();
        for (Ricetta ricetta : ricette) {
			selezionaRicettaComboBox.addItem(ricetta.getTitolo() + " (" + ricetta.getIDRicetta() + ")");
		}
        
        
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
        						.addComponent(labelDiscriminato, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        						.addComponent(labelRicetta, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(selezionaCorsoComboBox, 300, 300, 300)
        						.addComponent(dataInizioCalendar, 300, 300, 300)
        						.addComponent(praticaCheckBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(discriminatoField, 300, 300, 300)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(selezionaRicettaComboBox, 200, 200, 200)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(testoCliccabile, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))))
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
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(dataInizioCalendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelDataInizio))
        			.addGap(6)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(praticaCheckBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelPratica))
        			.addGap(6)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(discriminatoField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelDiscriminato))
        			.addGap(6)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(selezionaRicettaComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelRicetta)
        				.addComponent(testoCliccabile))
        			.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
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
        JMenuItem itemStatistiche = new JMenuItem("Statistiche");
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
        menuStatsNReport.addMenuListener(menuListener);
        menuAccount.addMenuListener(menuListener);

        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemAggiungiCorso);
        menuCorsi.add(itemImieiCorsi);
        menuSessioni.add(itemAggiungiSessione);
        menuSessioni.add(itemSessioniDisponibili);
        menuRicette.add(itemListaRicette);
        menuStatsNReport.add(itemStatistiche);
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
