package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

import Entities.Partecipante;
import DAO.PartecipanteDAO;
import Controller.ControllerPartecipante;

public class LoginUtenteFrame extends JFrame {

    public LoginUtenteFrame() {
        setTitle("Login Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(20, 30, 30, 30));
        setContentPane(contentPane);

        // Bottone Indietro
        JButton backButton = new JButton("← Indietro");
        backButton.setForeground(new Color(0, 0, 0));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        JLabel title = new JLabel("Login Utente");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginButton = new JButton("Accedi");
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControllerPartecipante CP = new ControllerPartecipante();

                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                Partecipante partecipante = CP.LoginCheck(email, password);

                if (partecipante == null) {
                    JOptionPane.showMessageDialog(LoginUtenteFrame.this,
                        "Email non registrata.",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
                } else if (partecipante.getNome() == null) {
                    JOptionPane.showMessageDialog(LoginUtenteFrame.this,
                        "Password errata. Riprova.",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(LoginUtenteFrame.this,
                        "Benvenuto " + partecipante.getNome());

                    DashboardUtente dashboard = new DashboardUtente(partecipante);
                    dashboard.setVisible(true);
                    dispose();
                }
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(466, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addGap(100)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addComponent(emailField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(84))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addGap(40)
                    .addComponent(title, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(20)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(108, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
