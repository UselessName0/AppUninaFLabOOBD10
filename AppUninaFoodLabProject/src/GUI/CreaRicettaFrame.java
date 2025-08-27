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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Controller.ControllerChef;
import DAO.RicettaDAO;
import Entities.Chef;
import Entities.MenuFactory;
import Entities.Ricetta;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CreaRicettaFrame extends JFrame {

	//ATTRIBUTI
	private JPanel contentPane;
	Chef c;
	private ControllerChef CC = new ControllerChef();
	private JTextField txtNomeRicetta;
	private JTextArea txtIngredienti;
	
	//COSTRUTTORI 
	public CreaRicettaFrame(Chef C) {
		this.c = C;
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(MenuFactory.creaMenuBarChef(this, c));
        setTitle("Crea Ricetta");
        Color sfondoPrincipale = new Color(220, 240, 250);
        
        //Back Button 
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

        //Label per il titolo 
        JLabel lblTitolo = new JLabel("Crea Ricetta");
        lblTitolo.setForeground(new Color(50, 80, 150));
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitolo.setHorizontalAlignment(JLabel.CENTER);

        //Label per il Nome della ricetta
        JLabel lblNome = new JLabel("Nominativo ricetta:");
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));
        txtNomeRicetta = new JTextField();
        txtNomeRicetta.setColumns(20);

        //Label per la descrizione della ricetta
        JLabel lblIngredienti = new JLabel("Descrizione ricetta:");
        lblIngredienti.setFont(new Font("Arial", Font.BOLD, 16));
        txtIngredienti = new JTextArea(5, 20);
        txtIngredienti.setLineWrap(true);
        txtIngredienti.setWrapStyleWord(true);
        
        //scroll pane per tutti gli ingredienti 
        JScrollPane scrollIngredienti = new JScrollPane(txtIngredienti);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setBackground(sfondoPrincipale);
        
        //Add Button 
        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAggiungi.setFocusPainted(false);
        btnAggiungi.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnAggiungi.setBackground(UIManager.getColor("Button.background"));
        btnAggiungi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        	String TitoloRicetta = txtNomeRicetta.getText();
        	String DescrizioneRicetta = txtIngredienti.getText();
        	if(CC.InsertRicetta(TitoloRicetta, DescrizioneRicetta)) {
				JOptionPane.showMessageDialog(null, "Ricetta inserita con successo!");
				dispose();
				new DashboardChef(c).setVisible(true);
				} else {
				JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della ricetta. Riprova.");
				}
            }	
        });

        //layout
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
	}
}
