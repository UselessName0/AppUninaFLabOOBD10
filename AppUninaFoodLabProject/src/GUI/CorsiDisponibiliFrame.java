package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.GroupLayout.Alignment;

public class CorsiDisponibiliFrame extends JFrame {

    private JMenu menuAttivo = null;

    public CorsiDisponibiliFrame() {
        setTitle("Corsi Disponibili");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(creaMenuBar(this));

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        
        //Pulsante Indietro
        JButton btnIndietro = new JButton("← Indietro");
        btnIndietro.setHorizontalAlignment(SwingConstants.LEFT);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIndietro.setContentAreaFilled(false);
        btnIndietro.setBorderPainted(false);
        btnIndietro.setForeground(new Color(0, 0, 0));

        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DashboardUtente().setVisible(true);
                dispose();
            }
        });
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addContainerGap(648, Short.MAX_VALUE)
        			.addComponent(btnIndietro))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addContainerGap(476, Short.MAX_VALUE)
        			.addComponent(btnIndietro))
        );
        contentPane.setLayout(gl_contentPane);
    }
    
    //Metodo per creare il menù
    private JMenuBar creaMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
     
        JMenu menuCorsi = new JMenu("Corsi");
        JMenu menuSessioni = new JMenu("Sessioni");
        JMenu menuChef = new JMenu("Chef");
        JMenu menuRicette = new JMenu("Ricette");
        JMenu menuAccount = new JMenu("Account");

        JMenuItem itemVediCorsi = new JMenuItem("Corsi Disponibili");
        JMenuItem itemMieIscrizioni = new JMenuItem("Le Mie Iscrizioni");
        JMenuItem itemVediSessioni = new JMenuItem("Le Mie Sessioni");
        JMenuItem itemVediCalendario = new JMenuItem("Calendario Sessioni");
        JMenuItem itemListaChef = new JMenuItem("Lista Chef");
        JMenuItem itemLeMieRicette = new JMenuItem("Le Mie Ricette");
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
                new CorsiDisponibiliFrame().setVisible(true);
                dispose();
            }
        });
        
        itemMieIscrizioni.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new LeMieIscrizioniFrame().setVisible(true);
        		dispose();
        	}
        });
        
        itemVediSessioni.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new LeMieSessioniFrame().setVisible(true);
        		dispose();
        	}
        });
        
        itemVediCalendario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new CalendarioSessioniFrame().setVisible(true);
        		dispose();
        	}
        });
        
        itemListaChef.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ListaChefFrame().setVisible(true);
        		dispose();
        	}
        });
        
        itemLeMieRicette.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new LeMieRicetteFrame().setVisible(true);
        		dispose();
        	}
        });
        
        itemInfo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ilMioProfiloFrame().setVisible(true);
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
        menuChef.addMenuListener(menuListener);
        menuRicette.addMenuListener(menuListener);
        menuAccount.addMenuListener(menuListener);

        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemMieIscrizioni);
        menuSessioni.add(itemVediSessioni);
        menuSessioni.add(itemVediCalendario);
        menuChef.add(itemListaChef);
        menuRicette.add(itemLeMieRicette);
        menuAccount.add(itemInfo);
        menuAccount.add(itemLogout);

        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuChef);
        menuBar.add(menuAccount);

        return menuBar;
    }

    //Metodo per evidenziare il menù creando un effetto hover
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
