package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegisterChefFrame extends JFrame {

    public RegisterChefFrame() {
        setTitle("Registrazione Chef - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40));
        setContentPane(panel);

        JButton backButton = new JButton("← Indietro");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setForeground(new Color(46, 139, 87));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            new SignUpFrame().setVisible(true);
            dispose();
        });
        panel.add(backButton);
        panel.add(Box.createVerticalStrut(10));

        JLabel title = new JLabel("Registrazione Chef");
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
        panel.add(Box.createVerticalStrut(10));

        JPasswordField confirmField = new JPasswordField();
        confirmField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        confirmField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        confirmField.setBorder(BorderFactory.createTitledBorder("Conferma Password"));
        panel.add(confirmField);
        panel.add(Box.createVerticalStrut(20));

        JButton registerButton = new JButton("Registrati");
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        registerButton.setBackground(new Color(46, 139, 87));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> {
            String email = emailField.getText();
            String pwd = String.valueOf(passwordField.getPassword());
            String confirm = String.valueOf(confirmField.getPassword());

            if (!pwd.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Le password non coincidono.");
            } else {
                // Salva chef...
                JOptionPane.showMessageDialog(this, "Registrazione Chef completata!");
            }
        });
        panel.add(registerButton);
    }
}
