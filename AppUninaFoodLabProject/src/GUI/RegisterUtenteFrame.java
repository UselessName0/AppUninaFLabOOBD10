package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class RegisterUtenteFrame extends JFrame {

    public RegisterUtenteFrame() {
        setTitle("Registrazione Utente - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 450));
        setMinimumSize(new Dimension(450, 350));
        setLocationRelativeTo(null);

        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
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
                new SignUpFrame().setVisible(true);
                dispose();
            }
        });
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(backButton, gbc);

        // Titolo
        JLabel title = new JLabel("Registrazione Utente");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(40, 40, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(title, gbc);

        // Campo Email
        JTextField emailField = createStyledTextFieldWithPlaceholder("Inserisci la tua email");
        gbc.gridy = 2;
        contentPane.add(emailField, gbc);

        // Campo Password
        JPasswordField passwordField = createStyledPasswordFieldWithPlaceholder("Inserisci una password");
        gbc.gridy = 3;
        contentPane.add(passwordField, gbc);

        // Campo Conferma
        JPasswordField confirmField = createStyledPasswordFieldWithPlaceholder("Conferma la password");
        gbc.gridy = 4;
        contentPane.add(confirmField, gbc);

        // Pulsante Registrazione
        JButton registerButton = createStyledButton("Registrati", new Color(60, 179, 113));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String pwd = String.valueOf(passwordField.getPassword());
                String confirm = String.valueOf(confirmField.getPassword());

                if (email.trim().isEmpty() || pwd.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterUtenteFrame.this, "Compila tutti i campi.");
                    return;
                }

                if (!pwd.equals(confirm)) {
                    JOptionPane.showMessageDialog(RegisterUtenteFrame.this, "Le password non coincidono.");
                } else {
                    JOptionPane.showMessageDialog(RegisterUtenteFrame.this, "Registrazione completata con successo!");
                }
            }
        });
        gbc.gridy = 5;
        contentPane.add(registerButton, gbc);

        pack();
        setVisible(true);
    }

    private JTextField createStyledTextFieldWithPlaceholder(final String placeholder) {
        final JTextField field = new JTextField(placeholder);
        field.setForeground(Color.GRAY);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 40));
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(180, 180, 180), 1, true),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.DARK_GRAY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(100, 200, 150), 2, true),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
                field.setBackground(new Color(255, 255, 255));
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(180, 180, 180), 1, true),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
                field.setBackground(Color.WHITE);
            }
        });

        return field;
    }

    private JPasswordField createStyledPasswordFieldWithPlaceholder(final String placeholder) {
        final JPasswordField field = new JPasswordField(placeholder);
        field.setEchoChar((char) 0);
        field.setForeground(Color.GRAY);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 40));
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(180, 180, 180), 1, true),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('•');
                    field.setForeground(Color.DARK_GRAY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(100, 200, 150), 2, true),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
                field.setBackground(new Color(255, 255, 255));
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setEchoChar((char) 0);
                    field.setForeground(Color.GRAY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(180, 180, 180), 1, true),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
                field.setBackground(Color.WHITE);
            }
        });

        return field;
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

    // Sfondo sfumato armonioso
    private class GradientPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(173, 216, 255); // Azzurro vivo
            Color color2 = new Color(255, 215, 180); // Pesca acceso
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }
}
