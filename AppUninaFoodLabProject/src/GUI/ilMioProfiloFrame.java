package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Controller.ControllerChef;
import Controller.ControllerPartecipante;
import DAO.CorsoDAO;
import Entities.Chef;
import Entities.MenuFactory;
import Entities.Partecipante;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ilMioProfiloFrame extends JFrame {

	//ATTRIBUTI
    private ControllerPartecipante CP = new ControllerPartecipante();
    private ControllerChef CC = new ControllerChef();
    private Partecipante p;
    private Chef c;
    Color sfondoPrincipale = new Color(220, 240, 250);
    private JPanel contentPane;

    //COSTRUTTORI
    public ilMioProfiloFrame(Partecipante p) {
        setTitle("Il Mio Profilo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setTitle("Profilo Partecipante");
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPane.setBackground(sfondoPrincipale);
        setContentPane(contentPane);
        setJMenuBar(MenuFactory.creaMenuBarPartecipante(this, p));

        //Font 
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font dataFont = new Font("Segoe UI", Font.PLAIN, 16);

        //Label per il titolo 
        JLabel lblTitolo = new JLabel("Profilo Partecipante");
        lblTitolo.setForeground(new Color(50, 80, 150));
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);

        //Label per nome, cognome, email e corsi seguiti 
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCognome = new JLabel("Cognome:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblCorsiSeguiti = new JLabel("Corsi Seguiti:");
        lblNome.setFont(labelFont);
        lblCognome.setFont(labelFont);
        lblEmail.setFont(labelFont);
        lblCorsiSeguiti.setFont(labelFont);
        JLabel dataNome = new JLabel(p.getNome());
        JLabel dataCognome = new JLabel(p.getCognome());
        JLabel dataEmail = new JLabel(p.getEmail());
        JLabel dataCorsiSeguiti = new JLabel(CP.GetNumeroCorsiSeguiti(p) + " Corsi");
        dataNome.setFont(dataFont);
        dataCognome.setFont(dataFont);
        dataEmail.setFont(dataFont);
        dataCorsiSeguiti.setFont(dataFont);

        //Back Button
        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnIndietro.setBackground(new Color(220, 240, 250));
        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DashboardUtente(p).setVisible(true);
                dispose();
            }
        });

        //Layout
        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
        	gl.createParallelGroup(Alignment.CENTER)
        		.addGroup(gl.createSequentialGroup()
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblTitolo)
        				.addGroup(gl.createSequentialGroup()
        					.addGroup(gl.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblNome)
        						.addComponent(lblCognome)
        						.addComponent(lblEmail)
        						.addComponent(lblCorsiSeguiti))
        					.addGap(20)
        					.addGroup(gl.createParallelGroup(Alignment.LEADING)
        						.addComponent(dataNome)
        						.addComponent(dataCognome)
        						.addComponent(dataEmail)
        						.addComponent(dataCorsiSeguiti)))
        				.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(470, Short.MAX_VALUE))
        );
        gl.setVerticalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addComponent(lblTitolo)
        			.addGap(30)
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNome)
        				.addComponent(dataNome))
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCognome)
        				.addComponent(dataCognome))
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblEmail)
        				.addComponent(dataEmail))
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCorsiSeguiti)
        				.addComponent(dataCorsiSeguiti))
        			.addPreferredGap(ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
        			.addComponent(btnIndietro))
        );
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        contentPane.setLayout(gl);
    }
    
    public ilMioProfiloFrame(Chef C) {
    	this.c = C;
    	setTitle("Il Mio Profilo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setTitle("Profilo Chef");
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPane.setBackground(sfondoPrincipale);
        setContentPane(contentPane);
        setJMenuBar(MenuFactory.creaMenuBarChef(this, c));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font dataFont = new Font("Segoe UI", Font.PLAIN, 16);

        JLabel lblTitolo = new JLabel("Profilo Chef");
        lblTitolo.setForeground(new Color(50, 80, 150));
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCognome = new JLabel("Cognome:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblCorsiSeguiti = new JLabel("Corsi Creati:");

        lblNome.setFont(labelFont);
        lblCognome.setFont(labelFont);
        lblEmail.setFont(labelFont);
        lblCorsiSeguiti.setFont(labelFont);

        JLabel dataNome = new JLabel(c.getNome());
        JLabel dataCognome = new JLabel(c.getCognome());
        JLabel dataEmail = new JLabel(c.getEmail());
        JLabel dataCorsiSeguiti = new JLabel(CC.GetNumeroCorsiCreati(c) + " Corsi");

        dataNome.setFont(dataFont);
        dataCognome.setFont(dataFont);
        dataEmail.setFont(dataFont);
        dataCorsiSeguiti.setFont(dataFont);

        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBorder(BorderFactory.createLineBorder(new Color(50, 80, 150), 1));
        btnIndietro.setBackground(new Color(220, 240, 250));

        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DashboardChef(c).setVisible(true);
                dispose();
            }
        });

        //Layout
        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
        	gl.createParallelGroup(Alignment.CENTER)
        		.addGroup(gl.createSequentialGroup()
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblTitolo)
        				.addGroup(gl.createSequentialGroup()
        					.addGroup(gl.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblNome)
        						.addComponent(lblCognome)
        						.addComponent(lblEmail)
        						.addComponent(lblCorsiSeguiti))
        					.addGap(20)
        					.addGroup(gl.createParallelGroup(Alignment.LEADING)
        						.addComponent(dataNome)
        						.addComponent(dataCognome)
        						.addComponent(dataEmail)
        						.addComponent(dataCorsiSeguiti)))
        				.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(470, Short.MAX_VALUE))
        );
        gl.setVerticalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addComponent(lblTitolo)
        			.addGap(30)
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNome)
        				.addComponent(dataNome))
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCognome)
        				.addComponent(dataCognome))
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblEmail)
        				.addComponent(dataEmail))
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCorsiSeguiti)
        				.addComponent(dataCorsiSeguiti))
        			.addPreferredGap(ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
        			.addComponent(btnIndietro))
        );
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        contentPane.setLayout(gl);
    }
}