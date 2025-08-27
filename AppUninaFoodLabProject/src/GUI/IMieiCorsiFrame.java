package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Entities.Chef;
import Entities.Corso;
import Entities.MenuFactory;
import Controller.ControllerChef;

import javax.swing.UIManager;

public class IMieiCorsiFrame extends JFrame {

	//ATTRIBUTI
	private JPanel contentPane;
	Chef c;
	ControllerChef CC = new ControllerChef();
	Color sfondoTabella = new Color(210, 240, 210);
	Color sfondoPrincipale = new Color(220, 240, 250);

	//COSTRUTTORI
	public IMieiCorsiFrame(Chef C) {
		this.c = C;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setJMenuBar(MenuFactory.creaMenuBarChef(this, c));
		
		setTitle("I miei corsi");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setBackground(sfondoPrincipale);
		
		//Label titolo
		JLabel lblIMieiCorsi = new JLabel("I miei corsi", SwingConstants.CENTER);
		lblIMieiCorsi.setForeground(new Color(50, 80, 150));
		lblIMieiCorsi.setFont(new Font("Arial", Font.BOLD, 24));
		
		//Lista di corsi presi dal DB passando uno chef preciso 
		final List<Corso> datiCorsi = CC.GetCorsiByChef(C);
		String[] colonne = { "Nome Corso" };
		Object[][] righe = new Object[datiCorsi.size()][1];
		for(int i = 0; i < datiCorsi.size(); i++) {
			Corso co = datiCorsi.get(i);
			righe[i][0] = co.getNome_Corso();
		}
		
		//Creazione tabella
		JTable tabellaCorsi = new JTable(righe, colonne);
		tabellaCorsi.setFont(new Font("Arial", Font.PLAIN, 16));
		tabellaCorsi.setRowHeight(28);
		tabellaCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabellaCorsi.setBackground(sfondoTabella);
		tabellaCorsi.setGridColor(sfondoTabella);
		tabellaCorsi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int riga = tabellaCorsi.getSelectedRow();
                    if (riga >= 0) {
                    	Corso c = datiCorsi.get(riga);
                        mostraDettagliCorsoChef(c);
                    }
                }
            }
        });
   
		//ScrollPane per la tabella
		JScrollPane scrollPane = new JScrollPane(tabellaCorsi);
		scrollPane.setPreferredSize(new Dimension(700, 360));

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

		//Layout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(224, Short.MAX_VALUE)
					.addComponent(lblIMieiCorsi, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(222))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(313)
					.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(313, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(lblIMieiCorsi, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		contentPane.setLayout(gl_contentPane);
	}

	// METODI
	//Metodo per mostrare i dettagli dei corsi per lo chef
	private void mostraDettagliCorsoChef(Corso c) {
        JFrame finestraDettagli = new JFrame("Dettagli del Corso");
        finestraDettagli.setSize(440, 360);
        finestraDettagli.setLocationRelativeTo(null);
        finestraDettagli.getContentPane().setLayout(null);
        finestraDettagli.getContentPane().setBackground(new Color(230, 240, 250));

        //Label per il nome 
        JLabel lblNome = new JLabel("Corso: " + c.getNome_Corso());
        lblNome.setBounds(20, 20, 400, 25);
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));
        
        String descrizione = c.getDescrizione();
        LocalDate dataInizio = c.getData_Inizio();
        String dataInizioStringa = (dataInizio!= null) ? dataInizio.toString() : "Data inizio non ancora pubblicata";
        String frequenza = c.getFrequenza_Corsi();

        //Text Area per la descrizione del corso 
        JTextArea txtDescrizione = new JTextArea(descrizione);
        txtDescrizione.setBounds(20, 90, 400, 60);
        txtDescrizione.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setLineWrap(true);
        txtDescrizione.setEditable(false);
        txtDescrizione.setBackground(new Color(245, 250, 255));
        txtDescrizione.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        //Label per la data
        JLabel lblData = new JLabel(dataInizioStringa);
        lblData.setBounds(20, 160, 400, 25);
        lblData.setFont(new Font("Arial", Font.PLAIN, 14));

        //Label per la frequenza
        JLabel lblFrequenza = new JLabel(frequenza);
        lblFrequenza.setBounds(20, 190, 400, 25);
        lblFrequenza.setFont(new Font("Arial", Font.PLAIN, 14));
        
        finestraDettagli.getContentPane().add(lblNome);
        finestraDettagli.getContentPane().add(txtDescrizione);
        finestraDettagli.getContentPane().add(lblData);
        finestraDettagli.getContentPane().add(lblFrequenza);
        finestraDettagli.setResizable(false);
        finestraDettagli.setVisible(true);
	}
}