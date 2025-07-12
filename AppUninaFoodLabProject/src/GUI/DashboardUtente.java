package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import Entities.Partecipante;

public class DashboardUtente extends JFrame {
    //ATTRIBUTI
	Partecipante p;
	
	
	//COSTRUTTORI
    public DashboardUtente() {
        setTitle("Dashboard Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        setJMenuBar(CreaMenuBar(this));
        getContentPane().add(creaContentPanel(), BorderLayout.CENTER);
    }
 
    public DashboardUtente(Partecipante P) {
    	this.p = P;
        setTitle("Dashboard Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        setJMenuBar(CreaMenuBar(this));
        getContentPane().add(creaContentPanel(), BorderLayout.CENTER);
    }
    
 //METODI
    //Metodo per creare il contentPanel centrale
    private JPanel creaContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(210, 255, 255));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0; gbc.gridy = 0;
        contentPanel.add(creaSezione("Corsi", new String[] { "Corsi disponibili", "Le mie iscrizioni" }, new Color(189, 226, 249)), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        contentPanel.add(creaSezione("Sessioni", new String[] { "Le mie sessioni", "Calendario sessioni" }, new Color(200, 240, 210)), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        contentPanel.add(creaSezione("Ricette", new String[] { "Le mie ricette" }, new Color(200, 240, 210)), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        contentPanel.add(creaSezione("Chef", new String[] { "Lista Chef" }, new Color(189, 226, 249)), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPanel.add(creaSezione("Account", new String[] { "Il mio profilo", "Logout" }, new Color(255, 250, 205)), gbc);

        return contentPanel;
    }
    
    //Metodo per creare una menù bar
    private JMenu menuAttivo = null;
    
    private JMenuBar CreaMenuBar(JFrame frame) {
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
                new CorsiDisponibiliFrame(p).setVisible(true);
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

        setJMenuBar(menuBar);
        return menuBar;
    }
     
    //Metodo che permette di evidenziare il menù
    private void evidenziaMenu(JMenu nuovoMenu) {
        if (menuAttivo != null) {
            ripristinaMenu(menuAttivo);
        }
        nuovoMenu.setOpaque(true);
        nuovoMenu.setBackground(new Color(100, 149, 37));
        nuovoMenu.setForeground(Color.BLACK);
        menuAttivo = nuovoMenu;
    }
    
    //Metodo che permette di ripristinare il menù dopo che è stato evidenziato
    private void ripristinaMenu(JMenu menu) {
        menu.setOpaque(false);
        menu.setBackground(null);
        menu.setForeground(Color.BLACK);
        if (menu == menuAttivo) {
            menuAttivo = null;
        }
    }
    
    //Metodo che permette di creare una sezione specifica
    private JPanel creaSezione(String titolo, String[] voci, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel lblTitolo = new JLabel(titolo, SwingConstants.CENTER);
        lblTitolo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitolo.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panel.add(lblTitolo, BorderLayout.NORTH);

        JPanel vocePanel = new JPanel();
        vocePanel.setBackground(bgColor);
        vocePanel.setLayout(new BoxLayout(vocePanel, BoxLayout.Y_AXIS));

        for (final String voceText : voci) {
            final JLabel voce = new JLabel("▸ " + voceText);
            voce.setFont(new Font("SansSerif", Font.BOLD, 14));
            voce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            voce.setAlignmentX(Component.LEFT_ALIGNMENT);
            voce.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 10));
            voce.addMouseListener(creaHoverEffect(voce));

            if (voceText.equals("Corsi disponibili")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new CorsiDisponibiliFrame(p).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Le mie iscrizioni")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new LeMieIscrizioniFrame().setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Le mie sessioni")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new LeMieSessioniFrame().setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Calendario sessioni")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new CalendarioSessioniFrame().setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Le mie ricette")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new LeMieRicetteFrame().setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Lista Chef")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new ListaChefFrame().setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Il mio profilo")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new ilMioProfiloFrame().setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Logout")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new WelcomeFrame().setVisible(true);
                        dispose();
                    }
                });
            }

            vocePanel.add(voce);
        }

        panel.add(vocePanel, BorderLayout.CENTER);
        return panel;
    }

    //Metodo che attraverso HTML permette di creare l'effetto hover sul testo, al passaggio del mouse
    private MouseListener creaHoverEffect(final JLabel voce) {
        return new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                voce.setText("<html><u>" + voce.getText() + "</u></html>");
                voce.setForeground(new Color(0, 0, 0));
            }

            public void mouseExited(MouseEvent e) {
                voce.setText(voce.getText().replace("<html><u>", "").replace("</u></html>", ""));
                voce.setForeground(Color.BLACK);
            }
        };
    }
}
