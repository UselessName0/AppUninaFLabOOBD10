package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(20, 30, 30, 30));
        setContentPane(contentPane);

        // Bottone Indietro
        JButton backButton = new JButton("← Indietro");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setForeground(new Color(100, 149, 237));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            new WelcomeFrame().setVisible(true);
            dispose();
        });
        contentPane.add(backButton);
        contentPane.add(Box.createVerticalStrut(10));

        JLabel title = new JLabel("Login come:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        contentPane.add(title);
        contentPane.add(Box.createVerticalStrut(30));

        // Pulsante Utente
        JButton userButton = new JButton("Utente");
        styleButton(userButton, new Color(100, 149, 237));
        userButton.addActionListener(e -> {
            new LoginUtenteFrame().setVisible(true);
            dispose();
        });
        contentPane.add(userButton);
        contentPane.add(Box.createVerticalStrut(15));

        // Pulsante Chef
        JButton chefButton = new JButton("Chef");
        styleButton(chefButton, new Color(30, 144, 255));
        chefButton.addActionListener(e -> {
            new LoginChefFrame().setVisible(true);
            dispose();
        });
        contentPane.add(chefButton);
    }

    private void styleButton(JButton button, Color color) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(250, 40));
    }
}
