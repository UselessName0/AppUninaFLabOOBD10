package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.Chef;

public class SessioniDisponibiliFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Chef c;

	public SessioniDisponibiliFrame(Chef C) {
		this.c = C;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
