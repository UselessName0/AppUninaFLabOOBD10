package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login - UninaFoodLab");
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
                new WelcomeFrame().setVisible(true);
                dispose();
            }
        });

        // Titolo
        JLabel lblLoginCome = new JLabel("Login:");
        lblLoginCome.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoginCome.setFont(new Font("SansSerif", Font.BOLD, 25));

        // Pulsante Utente
        JButton userButton = new JButton("Utente");
        styleButton(userButton, new Color(100, 149, 237));
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginUtenteFrame().setVisible(true);
                dispose();
            }
        });

        // Pulsante Chef
        JButton chefButton = new JButton("Chef");
        styleButton(chefButton, new Color(46, 187, 39));
        chefButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginChefFrame().setVisible(true);
                dispose();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(424, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap(159, Short.MAX_VALUE)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(chefButton, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        				.addComponent(userButton, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        			.addGap(142))
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addGap(150)
        			.addComponent(lblLoginCome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(151))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        			.addGap(108)
        			.addComponent(lblLoginCome, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(userButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(chefButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(133, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane);
    }

    private void styleButton(JButton button, Color color) {
        button.setMaximumSize(new Dimension(250, 40));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBackground(color);
    }
}