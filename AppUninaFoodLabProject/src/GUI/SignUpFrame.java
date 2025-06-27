package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SignUpFrame extends JFrame {

    public SignUpFrame() {
        setTitle("Registrazione - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 450));
        setMinimumSize(new Dimension(400, 350));
        setLocationRelativeTo(null);

        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(20, 40, 20, 40));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Bottone Indietro
        JButton backButton = new JButton("← Indietro");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setForeground(new Color(60, 179, 113));
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WelcomeFrame().setVisible(true);
                dispose();
            }
        });
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(backButton, gbc);

        // Titolo
        JLabel title = new JLabel("Registrati come:");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(40, 40, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(title, gbc);

        // Pulsante Utente
        JButton userButton = createStyledButton("Utente", new Color(60, 179, 113));
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterUtenteFrame().setVisible(true);
                dispose();
            }
        });
        gbc.gridy = 2;
        contentPane.add(userButton, gbc);

        // Pulsante Chef
        JButton chefButton = createStyledButton("Chef", new Color(46, 139, 87));
        chefButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterChefFrame().setVisible(true);
                dispose();
            }
        });
        gbc.gridy = 3;
        contentPane.add(chefButton, gbc);

        pack();
        setVisible(true);
    }

    private JButton createStyledButton(final String text, final Color bgColor) {
        final JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 45));
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

    // Sfondo gradiente armonioso e vivace
    private class GradientPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(173, 216, 255); // Azzurro vivo
            Color color2 = new Color(255, 215, 180); // Pesca chiaro acceso
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }
}
