package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.ControllerPartecipante;
import Entities.Partecipante;

import javax.swing.GroupLayout.Alignment;

public class RegisterUtenteFrame extends JFrame {

    public RegisterUtenteFrame() {
        setTitle("Registrazione Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 550);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(20, 30, 30, 30));
        setContentPane(contentPane);

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
                new SignUpFrame().setVisible(true);
                dispose();
            }
        });

        JLabel title = new JLabel("Registrazione Utente");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JPasswordField confirmField = new JPasswordField();
        confirmField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        confirmField.setBorder(BorderFactory.createTitledBorder("Conferma Password"));

        JCheckBox showPasswordCheckBox = new JCheckBox("Mostra password");
        showPasswordCheckBox.setBackground(Color.WHITE);
        showPasswordCheckBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('•');
                    confirmField.setEchoChar('•');
                }
            }
        });

        JButton registerButton = new JButton("Registrati");
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        registerButton.setBackground(new Color(100, 149, 237));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setPreferredSize(new Dimension(200, 40));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ControllerPartecipante CP = new ControllerPartecipante();
            	
                String email = emailField.getText();
                String pwd = String.valueOf(passwordField.getPassword());
                String confirm = String.valueOf(confirmField.getPassword());

                if (!pwd.equals(confirm)) {
                    JOptionPane.showMessageDialog(RegisterUtenteFrame.this, "Le password non coincidono.");
                } else {
                	Partecipante p = new Partecipante();
                	p = CP.RegisterCheck(email, pwd);
                	if(p==null)
                		JOptionPane.showMessageDialog(RegisterUtenteFrame.this, "Registrazione Utente non andata a buon fine");
                	else {
                		JOptionPane.showMessageDialog(RegisterUtenteFrame.this, "Registrazione Utente completata!");
                		DashboardUtente DU = new DashboardUtente(p);
                		DU.setVisible(true);
                		dispose();
                	}
                }
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(672, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addGap(100)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(registerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showPasswordCheckBox, Alignment.LEADING)
                        .addComponent(confirmField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
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
                    .addGap(20)
                    .addComponent(confirmField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(showPasswordCheckBox)
                    .addGap(20)
                    .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane);
    }
}
