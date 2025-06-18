package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginUtenteFrame extends JFrame {

    public LoginUtenteFrame() {
        setTitle("Login Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40));
        setContentPane(panel);

        // Indietro
        JButton backButton = new JButton("← Indietro");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setForeground(new Color(100, 149, 237));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        panel.add(backButton);
        panel.add(Box.createVerticalStrut(10));

        JLabel title = new JLabel("Login Utente");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        panel.add(emailField);
        panel.add(Box.createVerticalStrut(10));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(20));

        JButton loginButton = new JButton("Accedi");
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());
            JOptionPane.showMessageDialog(this, "Login Utente:\nEmail: " + email + "\nPassword: " + password);
            // Verifica credenziali qui...
        });
        panel.add(loginButton);
    }
}
