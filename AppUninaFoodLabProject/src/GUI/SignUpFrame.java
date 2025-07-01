package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

public class SignUpFrame extends JFrame {

    public SignUpFrame() {
        setTitle("Registrazione - UninaFoodLab");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
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
        backButton.setAlignmentX(0.0f);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeFrame().setVisible(true);
                dispose();
            }
        });
        
        JLabel lblRegistratiCome = new JLabel("Registrati come:");
        lblRegistratiCome.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistratiCome.setFont(new Font("SansSerif", Font.BOLD, 25));
        lblRegistratiCome.setAlignmentX(0.5f);
        
        JButton userButton = new JButton("Utente");
        userButton.setMaximumSize(new Dimension(250, 40));
        userButton.setForeground(Color.WHITE);
        userButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        userButton.setFocusPainted(false);
        userButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        userButton.setBackground(new Color(100, 149, 237));
        userButton.setAlignmentX(0.5f);
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterUtenteFrame().setVisible(true);
                dispose();
            }
        });
        
        
        JButton btnChef = new JButton("Chef");
        btnChef.setMaximumSize(new Dimension(250, 40));
        btnChef.setForeground(Color.WHITE);
        btnChef.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnChef.setFocusPainted(false);
        btnChef.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnChef.setBackground(new Color(46, 187, 39));
        btnChef.setAlignmentX(0.5f);
        btnChef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterChefFrame().setVisible(true);
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
        				.addComponent(btnChef, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        				.addComponent(userButton, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        				.addComponent(lblRegistratiCome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(142))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        			.addGap(108)
        			.addComponent(lblRegistratiCome, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(userButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnChef, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(133, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
