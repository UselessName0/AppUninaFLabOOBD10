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
        JMenuItem itemListaChef = new JMenuItem("Lista Chef");
        menuChef.add(itemListaChef);

        JMenu menuRicette = new JMenu("Ricette");
        JMenuItem itemLeMieRicette = new JMenuItem("Le Mie Ricette");
        menuRicette.add(itemLeMieRicette);

        JMenu menuAccount = new JMenu("Account");
        JMenuItem itemLogout = new JMenuItem("Logout");
        itemLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		WelcomeFrame WF = new WelcomeFrame();
        		WF.setVisible(true);
        		dispose();
        	}
        });
        JMenuItem itemInfo = new JMenuItem("Il mio profilo ");
        menuAccount.add(itemLogout);
        menuAccount.add(itemInfo);

        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuChef);
        menuBar.add(menuAccount);

        setJMenuBar(menuBar);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 1403, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 707, Short.MAX_VALUE)
        );
    }
}
