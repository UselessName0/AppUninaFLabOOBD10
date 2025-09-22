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
import Entities.MenuFactory;
import Controller.ControllerChef;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class AggiungiCorsoFrame extends JFrame {

    //ATTRIBUTI
    private ControllerChef CC = new ControllerChef();
    private JPanel contentPane;
    private Chef c;
    private JComboBox<String> categoriaComboBox;
    private JTextField nomeCorsoField;
    private JComboBox<String> frequenzaComboBox;

    //COSTRUTTORI
    public AggiungiCorsoFrame(Chef C) {
        this.c = C;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(MenuFactory.creaMenuBarChef(this, c));
        
        setTitle("Aggiungi Corso");
        Color sfondoPrincipale = new Color(220, 240, 250);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setBackground(sfondoPrincipale);

        //Titolo
        JLabel titoloLabel = new JLabel("Aggiungi un corso", SwingConstants.CENTER);
        titoloLabel.setForeground(new Color(50, 80, 150));
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 24));

        //Label e campi
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
        
        //Add Button (con check)
        JButton btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAggiungi.setFocusPainted(false);
        btnAggiungi.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnAggiungi.setBackground(UIManager.getColor("Button.background"));
        btnAggiungi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Corso corso = new Corso();
                corso.setArgomento((String) categoriaComboBox.getSelectedItem());
                corso.setNome_Corso(nomeCorsoField.getText());
                corso.setChef_Proprietario(C);
                corso.setData_Creazione(LocalDate.now());
                corso.setDescrizione(textArea.getText());
                String Frequenza = (String) frequenzaComboBox.getSelectedItem();
                if(Frequenza.equals("Libera"))
                	Frequenza = null;
                corso.setFrequenza_Corsi(Frequenza);
                if(CC.InsertCorso(corso)) {
					JOptionPane.showMessageDialog(contentPane, "Corso aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
					new DashboardChef(c).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Errore durante l'aggiunta del corso. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
				}
            }
        });

        //Layout
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
        							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
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
        				.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        contentPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    }
}