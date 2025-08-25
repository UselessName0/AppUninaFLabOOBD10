package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Controller.ControllerChef;
import Entities.Chef;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.LayoutStyle.ComponentPlacement;

public class StatisticheFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    Chef c;
    ControllerChef CC = new ControllerChef();
    
    public StatisticheFrame(Chef C) {
        
        this.c = C;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(CreaMenuBar(this));
        Color sfondoPrincipale = new Color(220, 240, 250);
        setTitle("Statistiche");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(sfondoPrincipale);
        setContentPane(contentPane);
        
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
        
        //setting dei dati per il grafico a torta
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Corso 1", 40);
        dataset.setValue("Corso 2", 30);
        dataset.setValue("Corso 3", 30);

        //creazione del grafico
        JFreeChart chart = ChartFactory.createPieChart(
            "Statistiche Corso", 
            dataset, 
            true,  
            true,  
            false  
        );

        //pannello con grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(700, 320));
        
        org.jfree.data.category.DefaultCategoryDataset barDataset = new org.jfree.data.category.DefaultCategoryDataset();
        barDataset.addValue(40, "Partecipanti", "Corso 1");
        barDataset.addValue(30, "Partecipanti", "Corso 2");
        barDataset.addValue(30, "Partecipanti", "Corso 3");

        JFreeChart barChart = ChartFactory.createBarChart(
            "Partecipanti per Corso",   
            "Numero di sessioni tenute",                   
            "Numero Partecipanti",    
            barDataset,                
            org.jfree.chart.plot.PlotOrientation.VERTICAL,
            true,
            true,   
            false  
        );
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setPreferredSize(new java.awt.Dimension(700, 200));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.CENTER)
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addGap(34)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(barChartPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(32, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addContainerGap(314, Short.MAX_VALUE)
        			.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        			.addGap(302))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(barChartPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        			.addGap(29)
        			.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(65))
        );

        contentPane.setLayout(gl_contentPane);

        CC.GetNumeroSessioniByMonth(C);
    }
    
    // METODI MENU
    private JMenu menuAttivo = null;
    private JMenuBar CreaMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCorsi = new JMenu("Corsi");
        JMenu menuSessioni = new JMenu("Sessioni");
        JMenu menuRicette = new JMenu("Ricette");
        JMenu menuStatsNReport = new JMenu("Stats&Reports");
        JMenu menuAccount = new JMenu("Account");

        JMenuItem itemVediCorsi = new JMenuItem("Corsi Altrui");
        JMenuItem itemAggiungiCorso = new JMenuItem("Aggiungi Corso");
        JMenuItem itemImieiCorsi = new JMenuItem("I Miei Corsi");
        JMenuItem itemAggiungiSessione = new JMenuItem("Aggiungi Sessione");
        JMenuItem itemSessioniDisponibili = new JMenuItem("Calendario Sessioni");
        JMenuItem itemListaRicette = new JMenuItem("Lista Ricette");
        JMenuItem itemStatistiche = new JMenuItem("Statistiche");
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
                new CorsiDisponibiliFrame(c).setVisible(true);
                dispose();
            }
        });
        
        itemImieiCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IMieiCorsiFrame(c).setVisible(true);
                dispose();
            }
        });

        itemAggiungiCorso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AggiungiCorsoFrame(c).setVisible(true);
                dispose();
            }
        });

        itemAggiungiSessione.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AggiungiSessioneFrame(c).setVisible(true);
                dispose();
            }
        });

        itemSessioniDisponibili.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CalendarioSessioniFrame(c).setVisible(true);
                dispose();
            }
        });

        itemListaRicette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListaRicetteFrame(c).setVisible(true);
                dispose();
            }
        });
        
        itemStatistiche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatisticheFrame(c).setVisible(true);
                dispose();
            }
        });

        itemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ilMioProfiloFrame(c).setVisible(true);
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
        menuRicette.addMenuListener(menuListener);
        menuStatsNReport.addMenuListener(menuListener);
        menuAccount.addMenuListener(menuListener);

        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemAggiungiCorso);
        menuCorsi.add(itemImieiCorsi);
        menuSessioni.add(itemAggiungiSessione);
        menuSessioni.add(itemSessioniDisponibili);
        menuRicette.add(itemListaRicette);
        menuStatsNReport.add(itemStatistiche);
        menuAccount.add(itemInfo);
        menuAccount.add(itemLogout);

        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuStatsNReport);
        menuBar.add(menuAccount);

        return menuBar;
    }
    
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
