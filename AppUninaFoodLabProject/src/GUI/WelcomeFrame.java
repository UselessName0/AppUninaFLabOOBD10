package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        setTitle("UninaFoodLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(40, 40, 40, 40));
        setContentPane(contentPane);
        
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Images/UninaFoodLabLogo.png"));
        Image image = logoIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(image));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(logoLabel);
        contentPane.add(Box.createVerticalStrut(20));

        JLabel titleLabel = new JLabel("Benvenuto in UninaFoodLab");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(60, 60, 60));
        contentPane.add(titleLabel);
        contentPane.add(Box.createVerticalStrut(30));

        JButton loginButton = new JButton("Login");
        styleButton(loginButton, new Color(100, 149, 237));
        loginButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        contentPane.add(loginButton);
        contentPane.add(Box.createVerticalStrut(15));

        JButton registerButton = new JButton("Registrati");
        styleButton(registerButton, new Color(60, 179, 113));
        registerButton.addActionListener(e -> {
            new SignUpFrame().setVisible(true);
            dispose();
        });
        contentPane.add(registerButton);
    }

    private void styleButton(JButton button, Color color) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(300, 45));
    }
}
