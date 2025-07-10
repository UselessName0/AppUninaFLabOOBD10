package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.ControllerChef;
import Controller.ControllerPartecipante;
import Entities.Chef;
import Entities.Partecipante;

import javax.swing.GroupLayout.Alignment;

public class RegisterChefFrame extends JFrame {

    public RegisterChefFrame() {
        setTitle("Registrazione Chef - UninaFoodLab");
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

        JLabel title = new JLabel("Registrazione Chef");
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

        JCheckBox showPasswordCheck = new JCheckBox("Mostra password");
        showPasswordCheck.setBackground(Color.WHITE);
        showPasswordCheck.setFont(new Font("SansSerif", Font.PLAIN, 14));
        showPasswordCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheck.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u2022'); // pallino standard
                    confirmField.setEchoChar('\u2022');
                }
            }
        });

        JButton registerButton = new JButton("Registrati");
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        registerButton.setBackground(new Color(46, 187, 39));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setPreferredSize(new Dimension(200, 40));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ControllerChef CC = new ControllerChef();
            	
                String email = emailField.getText();
                String pwd = String.valueOf(passwordField.getPassword());
                String confirm = String.valueOf(confirmField.getPassword());

                if (!pwd.equals(confirm)) {
                    JOptionPane.showMessageDialog(RegisterChefFrame.this, "Le password non coincidono.");
                } else {
                	Chef c = new Chef();
                	c = CC.RegisterCheck(email, pwd);
                	if(c==null)
                		JOptionPane.showMessageDialog(RegisterChefFrame.this, "Registrazione Chef non andata a buon fine");
                	else {
                		JOptionPane.showMessageDialog(RegisterChefFrame.this, "Registrazione Chef completata!");
                		DashboardChef DC = new DashboardChef(c);
                		DC.setVisible(true);
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
                    .addContainerGap(466, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addGap(100)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(registerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showPasswordCheck, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
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
                    .addComponent(showPasswordCheck)
                    .addGap(20)
                    .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
