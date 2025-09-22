package Entities;

import javax.swing.*;
import javax.swing.event.*;

import GUI.AggiungiCorsoFrame;
import GUI.AggiungiSessioneFrame;
import GUI.CalendarioSessioniFrame;
import GUI.CorsiDisponibiliFrame;
import GUI.CreaRicettaFrame;
import GUI.IMieiCorsiFrame;
import GUI.LeMieIscrizioniFrame;
import GUI.LeMieRicetteFrame;
import GUI.LeMieSessioniFrame;
import GUI.ListaChefFrame;
import GUI.ListaRicetteFrame;
import GUI.StatisticheFrame;
import GUI.WelcomeFrame;
import GUI.ilMioProfiloFrame;

import java.awt.*;
import java.awt.event.*;

public class MenuFactory {

    private static JMenu menuAttivo = null;
    public static JMenuBar creaMenuBarChef(final JFrame frame, final Chef c) {
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
        JMenuItem itemCreaRicetta = new JMenuItem("Crea Ricetta");
        JMenuItem itemStatistiche = new JMenuItem("Statistiche");
        JMenuItem itemInfo = new JMenuItem("Il mio profilo");
        JMenuItem itemLogout = new JMenuItem("Logout");

        //Listener
        itemLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WelcomeFrame().setVisible(true);
                frame.dispose();
            }
        });

        itemVediCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CorsiDisponibiliFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemAggiungiCorso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AggiungiCorsoFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemImieiCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IMieiCorsiFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemAggiungiSessione.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AggiungiSessioneFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemSessioniDisponibili.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CalendarioSessioniFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemListaRicette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListaRicetteFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemCreaRicetta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreaRicettaFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemStatistiche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatisticheFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        itemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ilMioProfiloFrame(c).setVisible(true);
                frame.dispose();
            }
        });

        //Evidenziazione menù
        aggiungiMenuListener(menuCorsi, menuSessioni, menuRicette, menuStatsNReport, menuAccount);

        //Struttura
        menuCorsi.add(itemVediCorsi);
        menuCorsi.add(itemAggiungiCorso);
        menuCorsi.add(itemImieiCorsi);
        menuSessioni.add(itemAggiungiSessione);
        menuSessioni.add(itemSessioniDisponibili);
        menuRicette.add(itemListaRicette);
        menuRicette.add(itemCreaRicetta);
        menuStatsNReport.add(itemStatistiche);
        menuAccount.add(itemInfo);
        menuAccount.add(itemLogout);
        menuBar.add(menuCorsi);
        menuBar.add(menuSessioni);
        menuBar.add(menuRicette);
        menuBar.add(menuStatsNReport);
        menuBar.add(menuAccount);
        frame.setJMenuBar(menuBar);

        return menuBar;
    }

    public static JMenuBar creaMenuBarPartecipante(final JFrame frame, final Partecipante p) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCorsi = new JMenu("Corsi");
        JMenu menuSessioni = new JMenu("Sessioni");
        JMenu menuChef = new JMenu("Chef");
        JMenu menuRicette = new JMenu("Ricette");
        JMenu menuAccount = new JMenu("Account");
        JMenuItem itemVediCorsi = new JMenuItem("Corsi disponibili");
        JMenuItem itemMieIscrizioni = new JMenuItem("Le Mie Iscrizioni");
        JMenuItem itemVediSessioni = new JMenuItem("Le Mie Sessioni");
        JMenuItem itemVediCalendario = new JMenuItem("Calendario Sessioni");
        JMenuItem itemListaChef = new JMenuItem("Lista Chef");
        JMenuItem itemLeMieRicette = new JMenuItem("Le Mie Ricette");
        JMenuItem itemInfo = new JMenuItem("Il mio profilo");
        JMenuItem itemLogout = new JMenuItem("Logout");

        //Listener
        itemLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WelcomeFrame().setVisible(true);
                frame.dispose();
            }
        });

        itemVediCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CorsiDisponibiliFrame(p).setVisible(true);
                frame.dispose();
            }
        });

        itemMieIscrizioni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieIscrizioniFrame(p).setVisible(true);
                frame.dispose();
            }
        });

        itemVediSessioni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieSessioniFrame(p).setVisible(true);
                frame.dispose();
            }
        });

        itemVediCalendario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CalendarioSessioniFrame(p).setVisible(true);
                frame.dispose();
            }
        });

        itemListaChef.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListaChefFrame(p).setVisible(true);
                frame.dispose();
            }
        });

        itemLeMieRicette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LeMieRicetteFrame(p).setVisible(true);
                frame.dispose();
            }
        });

        itemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ilMioProfiloFrame(p).setVisible(true);
                frame.dispose();
            }
        });

        // Evidenziazione menu
        aggiungiMenuListener(menuCorsi, menuSessioni, menuChef, menuRicette, menuAccount);

        //Struttura
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
        frame.setJMenuBar(menuBar);

        return menuBar;
    }

    //utility per evidenziare i menù
    private static void aggiungiMenuListener(JMenu... menus) {
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
        for (JMenu m : menus) {
            m.addMenuListener(menuListener);
        }
    }

    private static void evidenziaMenu(JMenu nuovoMenu) {
        if (menuAttivo != null) {
            ripristinaMenu(menuAttivo);
        }
        nuovoMenu.setOpaque(true);
        nuovoMenu.setBackground(new Color(100, 149, 37));
        nuovoMenu.setForeground(Color.BLACK);
        menuAttivo = nuovoMenu;
    }

    private static void ripristinaMenu(JMenu menu) {
        menu.setOpaque(false);
        menu.setBackground(null);
        menu.setForeground(Color.BLACK);
        if (menu == menuAttivo) {
            menuAttivo = null;
        }
    }
}