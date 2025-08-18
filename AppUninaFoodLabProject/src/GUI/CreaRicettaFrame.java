package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Entities.Chef;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CreaRicettaFrame extends JFrame {

	private JPanel contentPane;
	Chef c;
	private JTextField txtNomeRicetta;
	private JTextArea txtIngredienti;
	
	public CreaRicettaFrame(Chef C) {
		this.c = C;
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Crea Ricetta");
        Color sfondoPrincipale = new Color(220, 240, 250);
        
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

        JLabel lblTitolo = new JLabel("Crea Ricetta");
        lblTitolo.setForeground(new Color(50, 80, 150));
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitolo.setHorizontalAlignment(JLabel.CENTER);

        JLabel lblNome = new JLabel("Nominativo ricetta:");
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));

        txtNomeRicetta = new JTextField();
        txtNomeRicetta.setColumns(20);

        JLabel lblIngredienti = new JLabel("Descrizione ricetta:");
        lblIngredienti.setFont(new Font("Arial", Font.BOLD, 16));

        txtIngredienti = new JTextArea(5, 20);
        txtIngredienti.setLineWrap(true);
        txtIngredienti.setWrapStyleWord(true);
        JScrollPane scrollIngredienti = new JScrollPane(txtIngredienti);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setBackground(sfondoPrincipale);
        
        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAggiungi.setFocusPainted(false);
        btnAggiungi.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnAggiungi.setBackground(UIManager.getColor("Button.background"));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(lblNome)
        				.addComponent(txtNomeRicetta, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblIngredienti)
        				.addComponent(scrollIngredienti, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(246, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addContainerGap(518, Short.MAX_VALUE)
        			.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblTitolo, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(lblTitolo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(lblNome)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtNomeRicetta, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(lblIngredienti)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollIngredienti, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
        );
        contentPane.setLayout(gl_contentPane);
        setJMenuBar(CreaMenuBar(this));
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
