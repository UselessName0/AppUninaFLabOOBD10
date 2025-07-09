package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import Entities.Partecipante;
import DAO.PartecipanteDAO;
import Controller.ControllerPartecipante;
import javax.swing.LayoutStyle.ComponentPlacement;


public class DashboardUtente extends JFrame {
	private JTextField textField;

    public DashboardUtente(Partecipante P) {
        setTitle("Dashboard Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // MENU BAR
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCorsi = new JMenu("Corsi");
        JMenuItem itemVediCorsi = new JMenuItem("Corsi Disponibili");
        JMenuItem itemMieIscrizioni = new JMenuItem("Le Mie Iscrizioni");
        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemMieIscrizioni);

        JMenu menuSessioni = new JMenu("Sessioni");
        JMenuItem itemVediSessioni = new JMenuItem("Le Mie Sessioni");
        JMenuItem itemVediCalendario = new JMenuItem("Calendario Sessioni");
        menuSessioni.add(itemVediSessioni);
        menuSessioni.add(itemVediCalendario);

        JMenu menuChef = new JMenu("Chef");
        JMenuItem itemInfoChef = new JMenuItem("Info Chef");
        menuChef.add(itemInfoChef);

        JMenu menuRicette = new JMenu("Ricette");
        JMenuItem itemLeMieRicette = new JMenuItem("Le Mie Ricette");
        menuRicette.add(itemLeMieRicette);

        JMenu menuAccount = new JMenu("Account");
        JMenuItem itemLogout = new JMenuItem("Logout");
        JMenuItem itemInfo = new JMenuItem("Info Personali");
        menuAccount.add(itemLogout);
        menuAccount.add(itemInfo);

        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuChef);
        menuBar.add(menuAccount);

        setJMenuBar(menuBar);
        
        textField = new JTextField();
        textField.setColumns(10);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(228)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(421, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(68)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(492, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
        textField.setText("Benvenuto " + P.getNome());
    }
}
