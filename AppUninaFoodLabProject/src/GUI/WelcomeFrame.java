package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        setTitle("UninaFoodLab App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 720));
        setMinimumSize(new Dimension(600, 450));
        setLocationRelativeTo(null);

        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Logo con proporzioni adattabili
        JLabel logoLabel = createLogoLabel("/Images/UninaFoodLabLogo.png", 0.5);
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        contentPane.add(logoLabel, gbc);

        // Titolo
        JLabel titleLabel = new JLabel("Benvenuto in UninaFoodLab");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(new Color(40, 40, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        contentPane.add(titleLabel, gbc);

        // Pulsanti
        gbc.gridy = 2;
        gbc.weighty = 0.2;
        contentPane.add(createButtonPanel(), gbc);

        pack();
        setVisible(true);
    }

    private JLabel createLogoLabel(String path, double scaleWidthRatio) {
        ImageIcon logoIcon = new ImageIcon(getClass().getResource(path));
        Image image = logoIcon.getImage();
        int scaledWidth = (int) (getPreferredSize().width * scaleWidthRatio);
        int scaledHeight = (int) (scaledWidth * image.getHeight(null) / image.getWidth(null));
        Image scaled = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 1, 0, 15));

        final JButton loginButton = createStyledButton("Login", new Color(66, 133, 244));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        final JButton registerButton = createStyledButton("Registrati", new Color(52, 168, 83));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SignUpFrame().setVisible(true);
                dispose();
            }
        });

        panel.add(loginButton);
        panel.add(registerButton);

        return panel;
    }

    private JButton createStyledButton(final String text, final Color bgColor) {
        final JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 50));
        button.setBorder(new LineBorder(bgColor.darker(), 1, true));
        button.setOpaque(true);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    // Sfondo con gradiente più vivace
    private class GradientPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();

            Color color1 = new Color(173, 216, 255); // Azzurro chiaro
            Color color2 = new Color(255, 215, 180); // Pesca chiaro

            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WelcomeFrame();
            }
        });
    }
}
