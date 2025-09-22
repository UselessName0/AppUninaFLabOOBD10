package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import DAO.SessioneDAO;
import Entities.Chef;
import Entities.MenuFactory;
import Entities.Sessione;

public class DashboardChef extends JFrame {
	
	//ATTRBUTI
	Chef c = new Chef();
	
	//COSTRUTTORI
	public DashboardChef(Chef C) {
		this.c = C;
		setTitle("Dashboard Chef - UninaFoodLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        setJMenuBar(MenuFactory.creaMenuBarChef(this, c));
        getContentPane().add(creaContentPanel(), BorderLayout.CENTER);
	}
	
	//METODI
	//Metodo per la creazione del pannello centralee
    private JPanel creaContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(210, 255, 255));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        //Layout GridBagCostraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0; gbc.gridy = 0;
        contentPanel.add(creaSezione("Corsi", new String[] { "Aggiungi corso", "Corsi altrui", "I miei corsi" }, new Color(189, 226, 249)), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        contentPanel.add(creaSezione("Sessioni", new String[] { "Aggiungi sessione", "Calendario sessioni" }, new Color(200, 240, 210)), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        contentPanel.add(creaSezione("Ricette", new String[] { "Lista ricette", "Crea ricetta" }, new Color(200, 240, 210)), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        contentPanel.add(creaSezione("Stats & Reports", new String[] { "Statistiche" }, new Color(189, 226, 249)), gbc);

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

            if (voceText.equals("Corsi altrui")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new CorsiDisponibiliFrame(c).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Aggiungi corso")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new AggiungiCorsoFrame(c).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("I miei corsi")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new IMieiCorsiFrame(c).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Aggiungi sessione")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new AggiungiSessioneFrame(c).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Calendario sessioni")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        SessioneDAO sDAO = new SessioneDAO();
                        List<Sessione> listaSessioni = sDAO.getAllSessioniDiChef(c);
                        if(listaSessioni == null || listaSessioni.isEmpty()) {
                        	JOptionPane.showMessageDialog(null, "Nessuna sessione disponibile.");
                			return;
                		}else{
                			new CalendarioSessioniFrame(c).setVisible(true);
                			dispose();
                			return;
                		}
                    }
                });
            }
            
            if (voceText.equals("Lista ricette")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new ListaRicetteFrame(c).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Crea ricetta")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new CreaRicettaFrame(c).setVisible(true);
                        dispose();
                    }
                });
            }
            
            if (voceText.equals("Statistiche")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new StatisticheFrame(c).setVisible(true);
                        dispose();
                    }
                });
            }
                       
            if (voceText.equals("Il mio profilo")) {
                voce.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new ilMioProfiloFrame(c).setVisible(true);
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