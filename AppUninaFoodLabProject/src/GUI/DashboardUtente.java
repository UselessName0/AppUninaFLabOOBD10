package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Entities.MenuFactory;
import Entities.Partecipante;

public class DashboardUtente extends JFrame {
    
	//ATTRIBUTI
	Partecipante p;

	//COSTRUTTORI
    public DashboardUtente(Partecipante P) {
    	this.p = P;
        setTitle("Dashboard Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        setJMenuBar(MenuFactory.creaMenuBarPartecipante(this, p));
        getContentPane().add(creaContentPanel(), BorderLayout.CENTER);
    }
    
 //METODI
    //Metodo per creare il contentPanel centrale
    private JPanel creaContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(210, 255, 255));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        //Layout GridBagCostraint
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
                        new LeMieIscrizioniFrame(p).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Le mie sessioni")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new LeMieSessioniFrame(p).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Calendario sessioni")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new CalendarioSessioniFrame(p).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Le mie ricette")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new LeMieRicetteFrame(p).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Lista Chef")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new ListaChefFrame(p).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Il mio profilo")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new ilMioProfiloFrame(p).setVisible(true);
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

    //Metodo che attraverso HTML permette di creare l'effetto hover sul testo, al passaggio del mouse (mouse listener)
    private MouseListener creaHoverEffect(final JLabel voce) {
        return new MouseAdapter() {
        	
        	//mouse entered
            public void mouseEntered(MouseEvent e) {
                voce.setText("<html><u>" + voce.getText() + "</u></html>");
                voce.setForeground(new Color(0, 0, 0));
            }
            
            //moude exited
            public void mouseExited(MouseEvent e) {
                voce.setText(voce.getText().replace("<html><u>", "").replace("</u></html>", ""));
                voce.setForeground(Color.BLACK);
            }
        };
    }
}