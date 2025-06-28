package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class DashboardUtente extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public DashboardUtente() {
        setTitle("Dashboard Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

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

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel corsiPanel = createPlaceholderPanel("Corsi Disponibili");
        JPanel iscrizioniPanel = createPlaceholderPanel("Le Tue Iscrizioni");
        JPanel ricettePanel = createPlaceholderPanel("Le Tue Ricette");

        mainPanel.add(corsiPanel, "CORSI");
        mainPanel.add(iscrizioniPanel, "ISCRIZIONI");
        mainPanel.add(ricettePanel, "RICETTE");

        itemVediCorsi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CORSI");
            }
        });

        itemMieIscrizioni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "ISCRIZIONI");
            }
        });

        itemLeMieRicette.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RICETTE");
            }
        });

        itemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DashboardUtente.this, "Logout effettuato.");
                dispose();
                new WelcomeFrame().setVisible(true);
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        contentPane.setLayout(layout);
        setContentPane(contentPane);

        cardLayout.show(mainPanel, "CORSI");
    }

    private JPanel createPlaceholderPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));

        GroupLayout layout = new GroupLayout(panel);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 700, Short.MAX_VALUE)
        );
        panel.setLayout(layout);

        return panel;
    }
}
